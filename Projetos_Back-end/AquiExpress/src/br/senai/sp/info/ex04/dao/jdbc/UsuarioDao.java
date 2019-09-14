package br.senai.sp.info.ex04.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;


import br.senai.sp.info.ex04.dao.Dao;
import br.senai.sp.info.ex04.models.Usuario;

@Repository
public class UsuarioDao implements Dao<Usuario>{
	
	private FabricaConexoes fabricaConexoes;
	
	public UsuarioDao() {
		fabricaConexoes  = new FabricaConexoes();
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
	
	public void persistir(Usuario obj) {
		String sql = "INSERT INTO usuario SET nome = ?"+
							", dt_nascimento = ?," + "email = ?, senha = ?";
		
		try {
			fabricaConexoes.abrir();
			
			PreparedStatement stmt = fabricaConexoes.getConexao().prepareStatement(sql);
			
			stmt.setString(1, obj.getNomeCompleto());
			stmt.setString(2, obj.getEmail());
			stmt.setDate(3, new Date(obj.getDataNascimento().getTime()));
			stmt.setString(4,  obj.getSenha());
			stmt.execute();
			fabricaConexoes.fechar();
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	public Usuario autenticar(Usuario usuario) {
		String sql = "SELECT id, nome, dt_nascimento  FROM usuario WHERE email = ? and senha = ?";
		
		try {
			
			fabricaConexoes.abrir();
			
			PreparedStatement stmt = fabricaConexoes.getConexao().prepareStatement(sql);
			
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			ResultSet resultados = stmt.executeQuery();
			Usuario usuarioAutenticado = null;
			
			//se tiver proximo ->
			if(resultados.next()) {
				
				usuarioAutenticado = new Usuario();
				usuarioAutenticado.setId(resultados.getLong("id"));
				usuarioAutenticado.setDataNascimento(resultados.getDate("dt_nascimento"));
				usuarioAutenticado.setNomeCompleto(resultados.getString("nome"));
				usuarioAutenticado.setEmail(usuario.getEmail());
				usuarioAutenticado.setSenha(usuario.getSenha());
			}
			
			resultados.close();
			fabricaConexoes.fechar();
			
			return usuarioAutenticado;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return usuario;
	}
	
}
