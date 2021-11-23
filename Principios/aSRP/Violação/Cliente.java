package br.ufscar.dc.dsw.domain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class Cliente{

	private Long id;
    	private static String cpf;
	private String nome;
	private String telefone;
	
	public void insert() throws Exception {
		String sql = "INSERT INTO CLIENTE (cpf, nome, telefone) VALUES (?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement = conn.prepareStatement(sql);
			statement.setString(1, cpf);
			statement.setString(2, nome);
			statement.setString(3, telefone);
			statement.executeUpdate();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
		    	throw new RuntimeException(e);
		}
    	}
	
	public void delete() {
		String sql = "DELETE FROM CLIENTE where cpf = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, cpf);
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    	}
	
	public Cliente(Long id, String cpf, String nome, String telefone) {
	    	this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
    	}
	
    	public Cliente() {}
    
    	public Cliente(Long id) {
    		this.id = id;
    	}
    
	public Long getId() {
        	return id;
    	}

    	public void setId(Long id) {
        	this.id = id;
   	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
