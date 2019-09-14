package br.senai.sp.info.ex04.dao.jdbc;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.info.ex04.dao.Dao;
import br.senai.sp.info.ex04.models.Produto;
import br.senai.sp.info.ex04.models.Usuario;

public class ProdutoDao implements Dao<Produto>{
	
	private FabricaConexoes conexao;
	
	public ProdutoDao() {
		
		conexao = new FabricaConexoes();
		
	}
	
	@Override
	public Produto buscar(Long id) {
		
		String sql = "SELECT id, nome, preco, dt_cadastro FROM produto WHERE id = ?";
		
		try {
			//abrir tudo na conexao
			this.conexao.abrir();
			
			//cria o stmt
			PreparedStatement statement = conexao.getConexao().prepareStatement(sql);
			
			//Passa o id do user para filtrar os produtos
			statement.setLong(1, id);
			
			//Executa a busca
			ResultSet rs = statement.executeQuery();
			
			Produto produto = null;
			
			if(rs.next()) {
				 
				produto = new Produto();
				
				produto.setId(rs.getLong("id"));
				produto.setNome("nome");
				produto.setPreco(rs.getFloat("preco"));
				produto.setDataCadastro(rs.getDate("dt_cadastro"));
				;
			}
			
			rs.close();
			
			return produto;
		
		}catch(Exception e) {
			//Lanca o erro para cima, fazendo a classe que chama e este metodo tratar o erro
			throw new RuntimeException(e);
		}
		
		//Colocamos o fechar conexao no finnaly pois ela deve ser encerrada tanto
		// se der erro 'catch' ou se der certo !
		finally {
			this.conexao.fechar();
		}
	}

	
	public List<Produto> buscarTodos(){
		return null;
	}
 	// Buscar todos que filtra a categoria por usuário
	
	/**
	 * Busca as categorias de um usuário especifico
	 * 
	 * @param usuario
	 *            - O usuário que será utilizado no filtro de busca
	 * @return
	 */
		public List<Produto> buscarTodos(Usuario usuario){
			String sql = "SELECT id, nome, preco, dt_cadastro FROM produto WHERE usuario_id = ?";
			
			List<Produto> ListaProdutos = new ArrayList<>(100);
			
			try {
				this.conexao.abrir();
				
				//Cria o stmt
				PreparedStatement statement = conexao.getConexao().prepareStatement(sql);
				
				//Passa o id do usuario para filtrar os produtos
				statement.setLong(1, usuario.getId());
				
				//Executa a busca
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					Produto produto = new Produto();
					
					produto.setId(rs.getLong("id"));
					produto.setNome(rs.getString("nome"));
					produto.setPreco(rs.getFloat("preco"));
					produto.setDataCadastro(rs.getDate("dt_cadastro"));
					
					//Adiciona o produto na lista
					ListaProdutos.add(produto);
					
				}
				
				rs.close();
				return ListaProdutos;
			
			}catch (Exception e) {
				//Lanca o erro pra cima pra que a clasee chamou o metodo resolva
				throw new RuntimeException(e);
			}
			
			/*
			 * Colocamos o fechar conexão no finnaly pois ela deve ser encerrada tanto
			 * se der erro 'catch' ou se der tudo certo :-)!
			 */
			finally {
				this.conexao.fechar();
			}
		}

	@Override
	public void alterar(Produto obj) {
		
	}
	
	@Override
	public void deletar(Produto obj) {
		String sql = "DELETE FROM produto WHERE id = ?";
		
		try {
			conexao.abrir();
			PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
			
			//Aplica o id
			stmt.setLong(1, obj.getId());
			stmt.execute();
			
		}catch (Exception e) {
			throw new RuntimeException(e);
			
		}finally {
			conexao.fechar();
		}
	}
	
	@Override
	public void persistir(Produto obj) {
		String sql = "INSERE INTO produto SET nome = ?, usuario_id = ?, preco = ?, dt_cadastro = ?";
		obj.setDataCadastro(new java.util.Date());
		
		try {
			conexao.abrir();
			
			PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setLong(2, obj.getUsuario().getId());
			stmt.setFloat(3, obj.getPreco());			
			stmt.execute();
		
		}catch (Exception e) {
			throw new RuntimeException(e);
			
		}finally {
			conexao.fechar();
		}
	}
}