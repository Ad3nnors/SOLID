package Solução.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import Solução.domain.Cliente;

public class ClienteDAO{

	public void insert(Cliente cli) throws Exception {
		String sql = "INSERT INTO CLIENTE (cpf, nome, telefone) VALUES (?, ?, ?)";
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement = conn.prepareStatement(sql);
			statement.setString(1, cli.getCpf());
			statement.setString(2, cli.getNome());
			statement.setString(3, cli.getTelefone());
			statement.executeUpdate();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
		    	throw new RuntimeException(e);
		}
    	}
	
	public void delete(Cliente cli) {
		String sql = "DELETE FROM CLIENTE where cpf = ?";
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, cli.getCpf());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    	}
}
