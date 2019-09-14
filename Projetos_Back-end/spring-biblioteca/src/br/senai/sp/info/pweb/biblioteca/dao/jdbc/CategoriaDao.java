package br.senai.sp.info.pweb.biblioteca.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.biblioteca.dao.Dao;
import br.senai.sp.info.pweb.biblioteca.models.Categoria;
import br.senai.sp.info.pweb.biblioteca.models.Usuario;

@Repository 
public class CategoriaDao implements Dao<Categoria>{
	
	private FabricaDeConexoes fabricaDeConexoes;
	
	public CategoriaDao() {
		fabricaDeConexoes = new FabricaDeConexoes();
	}
	
	@Override
	public Categoria buscar(Long id) {

		String sql = "SELECT id, nome FROM categoria WHERE id = ?";
		
		try {
			
			this.fabricaDeConexoes.abrir();
			
			//Criando o stmt
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			//Passa o id do usuario para filtrar as categorias
			stmt.setLong(1, id);
			
			//executa a busca 
			ResultSet resultados = stmt.executeQuery();
			Categoria c = null;
			if(resultados.next()){
				c = new Categoria();
				c.setId(resultados.getLong("id"));
				c.setNome(resultados.getString("nome"));
				
			}
				//Fecha os resultados
				resultados.close();
				
				return c;
				
		}catch(Exception e) {
			
			throw new RuntimeException(e);
			//manda quem chamar o catch tratar o erro
			
		}finally {
			//colocamos o fechar conexão pois ela deve ser encerrada tanto se der certo ou se ser merda
			this.fabricaDeConexoes.fechar();
			
		}

	}
	
	@Override
	public List<Categoria> buscarTodos(){
		return null;
	}
	
	/**
	 * busca as categorias de um usuario expecifico
	 * @param dono - o usuario que sera utilizado como filtro de busca
	 * @return
	 */
	public List<Categoria> buscarTodos(Usuario dono){
		
		String sql = "SELECT id, nome FROM categoria WHERE usuario_id = ?";
		List<Categoria> listaCategorias = new ArrayList<>(100);
		
		try {
			
			this.fabricaDeConexoes.abrir();
			
			//Criando o stmt
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			//Passa o id do usuario para filtrar as categorias
			stmt.setLong(1, dono.getId());
			
			//executa a busca 
			ResultSet resultados = stmt.executeQuery();
			while(resultados.next()){
				Categoria c = new Categoria();
				c.setId(resultados.getLong("id"));
				c.setNome(resultados.getString("nome"));
				
				//add a categoria na lista para retorna-la
				listaCategorias.add(c);
				
			}
				//Fecha os resultados
				resultados.close();
				
				return listaCategorias;
		}catch(Exception e) {
			
			throw new RuntimeException(e);
			//manda quem chamar o catch tratar o erro
			
		}finally {
			//colocamos o fechar conexão pois ela deve ser encerrada tanto se der certo ou se ser merda
			this.fabricaDeConexoes.fechar();
			
		}

	}
	
	@Override
	public void alterar(Categoria obj) {
		String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
		
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setLong(2,  obj.getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}		
	}
	
	@Override
	public void deletar(Categoria obj) {
		String sql = "DELETE FROM categoria WHERE id = ?";
		
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
	
	/*salva a categoria e manda para o banco atraves da fabrica*/
	@Override
	public void persistir(Categoria obj) {
		String sql = "INSERT INTO categoria SET nome = ?, usuario_id = ?";
		try {
			
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setLong(2, obj.getUsuario().getId());
			stmt.execute();
			fabricaDeConexoes.fechar();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	}
}
