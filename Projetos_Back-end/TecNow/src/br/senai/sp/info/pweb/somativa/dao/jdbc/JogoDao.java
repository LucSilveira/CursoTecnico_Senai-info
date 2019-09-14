package br.senai.sp.info.pweb.somativa.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.somativa.dao.Dao;
import br.senai.sp.info.pweb.somativa.models.Categoria;
import br.senai.sp.info.pweb.somativa.models.Jogo;
import br.senai.sp.info.pweb.somativa.models.Usuario;

@Repository
public class JogoDao implements Dao<Jogo>{
	
	private FabricaDeConexoes fabricaDeConexoes;
	
	public JogoDao() {
		fabricaDeConexoes = new FabricaDeConexoes();

	}
	
	@Override
	public Jogo buscar(Long id) {
		String sql = "SELECT id_jogo, nome FROM jogo WHERE id_jogo = ?";
		
		try {
			this.fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet resultados = stmt.executeQuery();
			Jogo c = null;
			if(resultados.next()) {
				c = new Jogo();
				c.setId(resultados.getLong("id_jogo"));
				c.setNome(resultados.getString("nome"));
			}
			
			resultados.close();
			return c;
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally{
			this.fabricaDeConexoes.fechar();
		}
	}
	
	@Override
	public List<Jogo> buscarTodos(){
		return null;
	}
	
	public List<Jogo> buscarTodos(Usuario dono){
		String sql = "SELECT id_jogo, nome, categoria, dt_cadastro FROM jogo WHERE user_id = ?";
		List<Jogo> listaJogos = new ArrayList<>(100);
		
		try {
			this.fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setLong(1, dono.getId());
			
			ResultSet resultados = stmt.executeQuery();
			while(resultados.next()) {
				Jogo c = new Jogo();
				c.setId(resultados.getLong("id_jogo"));
				c.setNome(resultados.getString("nome"));
				c.setCategorias(Categoria.valueOf(resultados.getString("categoria")));
				c.setDataCadastro(resultados.getDate("dt_cadastro"));
				
				listaJogos.add(c);
			}
			
			resultados.close();
			return listaJogos;
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally{
			this.fabricaDeConexoes.fechar();
		}
	}

	@Override
	public void alterar(Jogo obj) {
		String sql = "UPDATE jogo SET nome = ?, categoria = ? WHERE id_jogo = ?";
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setLong(3,  obj.getId());
			stmt.setString(2 , obj.getCategorias().toString());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}		
	}

	@Override
	public void deletar(Jogo obj) {
		String sql = "DELETE FROM jogo WHERE id_jogo = ?";
		
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			
			//Aplica o id
			stmt.setLong(1, obj.getId());
			stmt.execute();
		}catch(Exception e) {
			throw new RuntimeException(e);				
			
		}finally {
			fabricaDeConexoes.fechar();
		}
		
	}
	
	@Override
	public void persistir(Jogo obj) {
		String sql = "INSERT INTO jogo SET nome = ?, categoria = ?, dt_cadastro = ?, user_id = ?";
		
		try {
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCategorias().toString());
			stmt.setDate(3, new Date(obj.getDataCadastro().getTime()));
			stmt.setLong(4, obj.getUsuario().getId());
			stmt.execute();
			fabricaDeConexoes.fechar();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	}
		
}