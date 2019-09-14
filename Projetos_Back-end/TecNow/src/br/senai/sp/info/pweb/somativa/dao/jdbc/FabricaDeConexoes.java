package br.senai.sp.info.pweb.somativa.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexoes {
	
	private Connection conexao;
	
	public void abrir() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//acessa o mysql
		final String USUARIO = "root";
		final String SENHA = "root132";
		
		String sql = "jdbc:mysql://localhost:3306/tecnow_17?serverTimezone=UTC";
		conexao = DriverManager.getConnection(sql, USUARIO, SENHA);
	}//VAI JNO BANCO PARA PEGAR AS INFORMACOES
	
	public void fechar() {
		if(conexao != null) {
			try {
				conexao.close();
			}catch(Exception e) {
			}
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}
}