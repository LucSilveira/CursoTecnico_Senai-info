package br.senai.sp.info.pweb.somativa.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.somativa.dao.Dao;
import br.senai.sp.info.pweb.somativa.models.Sexo;
import br.senai.sp.info.pweb.somativa.models.Usuario;

@Repository
public class UsuarioDao implements Dao<Usuario>{

	private FabricaDeConexoes fabricaDeConexoes;
	
	public UsuarioDao() {
		fabricaDeConexoes  = new FabricaDeConexoes();
	}
	
	@Override
	public Usuario buscar(Long id) {
		return null;
	}
	@Override
	public List<Usuario> buscarTodos(){
		return null;
	}
	@Override
	public void alterar(Usuario obj) {
		
	}
	@Override
	public void deletar(Usuario obj) {
		
	}
	
	@Override
	public void persistir(Usuario obj) {
		String sql = "INSERT INTO usuario SET nome = ?, email = ?, senha = ?, dt_nascimento = ?, sexo = ?";
		try {
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getEmail());
			stmt.setString(3, obj.getSenha());
			stmt.setDate(4, new Date(obj.getDataNascimento().getTime()));
			stmt.setString(5, obj.getSexo().toString());
			stmt.execute();
			fabricaDeConexoes.fechar();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean emailChecar(String email) {
			String sql = "SELECT * from usuario WHERE email = ?";

			try {
				fabricaDeConexoes.abrir();
				PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
				stmt.setString(1, email);
				ResultSet resultados = stmt.executeQuery();
				boolean emailExistente = resultados.next();
				resultados.close();
				return emailExistente;
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				fabricaDeConexoes.fechar();
			}
		}
	
	public boolean senhaChecar(String senha) {
		String sql = "SELECT * from usuario WHERE senha = ?";

		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, senha);
			ResultSet resultados = stmt.executeQuery();
			boolean senhaExistente = resultados.next();
			resultados.close();
			return senhaExistente;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fabricaDeConexoes.fechar();
		}
	}
	
	public Usuario autenticar(Usuario usuario) {
		String sql = "SELECT id_usuario, nome, dt_nascimento, sexo "+
							"FROM usuario WHERE email = ? AND senha = ?";
		
		try {
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			ResultSet resultados = stmt.executeQuery();
			Usuario usuarioAutenticado = null;
			if(resultados.next()) {
				usuarioAutenticado = new Usuario();
				usuarioAutenticado.setId(resultados.getLong("id_usuario"));
				usuarioAutenticado.setDataNascimento(resultados.getDate("dt_nascimento"));
				usuarioAutenticado.setNome(resultados.getString("nome"));
				usuarioAutenticado.setEmail(usuario.getEmail());
				usuarioAutenticado.setSenha(usuario.getSenha());
				usuarioAutenticado.setSexo(Sexo.valueOf(resultados.getString("sexo")));
				
			}
			resultados.close();
			fabricaDeConexoes.fechar();
			return usuarioAutenticado;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}