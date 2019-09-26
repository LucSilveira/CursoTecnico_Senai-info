package br.senai.sp.informatica.empresaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.senai.sp.informatica.empresaweb.model.Funcionario;

public class FuncionarioDao {

	// atributos
	Connection connection;

	// construtor
	public FuncionarioDao() {

		// abre uma conexao com o banco de dados
		this.connection = new ConnectionFactory().getConnection();
	}

	// metodo salva
	public void salva(Funcionario funcionario) {

		String sql = "INSERT INTO funcionario" + "(nome, email, cpf, senha)" + "Values(?, ?, ?, ?)";

		try {
			// cria um Prepared Statement com o comando sql
			// java.sql.preparedStatement
			PreparedStatement stmt = connection.prepareStatement(sql);

			// cria os parametros
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getEmail());
			stmt.setString(3, funcionario.getCpf());
			stmt.setString(4, funcionario.getSenha());

			// executa a instrução sql
			stmt.execute();

			// libera o recurso de prepared statement
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Funcionario> getLista() {

		try {
			// cria um PreparedStatement
			List<Funcionario> funcionarios = new ArrayList<>();

			// cria um preparedsStatement
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM funcionario");

			// guarda os resultados da consulta em um
			// resultSet
			ResultSet rs = stmt.executeQuery();

			// enquanto haver um proximo registro no resultSet
			while (rs.next()) {

				// cria um novo contato com os dados do resultSet
				Funcionario funcionario = new Funcionario();

				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setSenha(rs.getString("senha"));

				// adiciona o contato ao arrayList funcionarios
				funcionarios.add(funcionario);
			}

			// libera o recurso do result
			rs.close();

			// libera o preparedStatement
			stmt.close();

			// retorna a lista de contatos
			return funcionarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
