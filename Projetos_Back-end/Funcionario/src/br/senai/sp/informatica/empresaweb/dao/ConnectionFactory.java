package br.senai.sp.informatica.empresaweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			// registra o driver jdbc
			Class.forName("com.mysql.jdbc.Driver");
			// retorna um objeto do tipo java.sql.Connection
			return DriverManager.getConnection("jdbc:mysql://172.16.7.23/empresam", "manha", "manha");
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}