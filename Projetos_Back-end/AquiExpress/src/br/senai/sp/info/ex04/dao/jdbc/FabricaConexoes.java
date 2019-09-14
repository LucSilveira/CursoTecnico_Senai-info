package br.senai.sp.info.ex04.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexoes {
	private Connection conexao;

	public void abrir() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		final String USUARIO = "root";
		final String SENHA = "root132";
		
		String sql = "jdbc:mysql://localhost:3306/Mercado?serverTime";
	
		conexao = DriverManager.getConnection(sql, USUARIO, SENHA);
	
}

public void fechar() {
	
	if(conexao != null) {
		
		try {
			
			conexao.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			
		} // fim try...catch
		
	}  // fim if
	
} //fim fechar()

public Connection getConexao() {
	
	return conexao;
	
} // fim getConnection()

}
