package br.senai.sp.info.pweb.biblioteca.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.biblioteca.dao.Dao;
import br.senai.sp.info.pweb.biblioteca.models.Usuario;

@Repository 
public class UsuarioDao implements Dao<Usuario>{
	
	private FabricaDeConexoes fabricaDeConexoes;
	
	public UsuarioDao() {
		fabricaDeConexoes = new FabricaDeConexoes();
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
		String sql = "INSERT INTO usuario SET nome = ?" +
					", sobrenome = ?" + 
					", dt_nascimento = ?, email = ?, senha = ?";
		try {/*insere os dados no banco, deois de ter os ter recebidos pela sessao*/
			
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getSobrenome());
			stmt.setDate(3, new Date(obj.getDataNascimento().getTime()));
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getSenha());
			stmt.execute();
			fabricaDeConexoes.fechar();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario autenticar(Usuario usuario) {
		String sql = "SELECT id, nome, sobrenome, dt_nascimento " +
							"FROM usuario WHERE email = ? AND senha = ?";
		
		try {/*depois de ter obtido os dados ele envia para o banco*/
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			ResultSet resultados = stmt.executeQuery();
			Usuario usuarioAutenticado = null;
			if(resultados.next()) {
				usuarioAutenticado = new Usuario();
				usuarioAutenticado.setId(resultados.getLong("id"));
				usuarioAutenticado.setDataNascimento(resultados.getDate("dt_nascimento"));
				usuarioAutenticado.setNome(resultados.getString("nome"));
				usuarioAutenticado.setSobrenome(resultados.getString("sobrenome"));
				
				usuarioAutenticado.setEmail(usuario.getEmail());
				usuarioAutenticado.setSenha(usuario.getSenha());
			}
			resultados.close();
			fabricaDeConexoes.fechar();
			return usuarioAutenticado;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
