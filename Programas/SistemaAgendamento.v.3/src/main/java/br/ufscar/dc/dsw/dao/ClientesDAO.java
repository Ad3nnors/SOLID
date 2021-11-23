package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;


import br.ufscar.dc.dsw.domain.Clientes;

public class ClientesDAO extends GenericDAO {

    public void insert(Clientes cliente) throws Exception {
    	//confere se o cliente já foi cadastrado
    	String sql = "SELECT * from CLIENTES WHERE cpf = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getCpf());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new Exception("Esse cliente já possui login cadastrado");
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    	
        //insere novo cliente
        sql = "INSERT INTO CLIENTES (cpf, email, senha, nome, telefone, sexo, ano, mes, dia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setInt(9, cliente.getAno());
            statement.setInt(8, cliente.getMes());
            statement.setInt(7, cliente.getDia());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public ArrayList<Clientes> getAll() {

    	ArrayList<Clientes> listaClientes = new ArrayList<>();

    	String sql = "SELECT * from CLIENTES order by nome";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                int ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                int dia = resultSet.getInt("dia");
                Clientes cliente = new Clientes(id, cpf, email, senha, nome, telefone, sexo, ano, mes, dia);

                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }



    public void delete(Clientes cliente) {
        String sql = "DELETE FROM CLIENTES where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Clientes cliente) {
        String sql = "UPDATE CLIENTES SET cpf = ?, email = ?, senha = ?, nome = ?, telefone = ?, sexo = ?, ano = ?, mes = ?, dia =? ";
        sql += "WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, cliente.getId());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getSenha());
            statement.setString(5, cliente.getNome());
            statement.setString(6, cliente.getTelefone());
            statement.setString(7, cliente.getSexo());
            statement.setInt(8, cliente.getAno());
            statement.setInt(9, cliente.getMes());
            statement.setInt(10, cliente.getDia());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Clientes get(Long id) {
        Clientes cliente = null;

        String sql = "SELECT * from CLIENTES where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String cpf_c = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                int ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                int dia = resultSet.getInt("dia");
              		
                cliente = new Clientes(id, cpf_c, email, senha, nome, telefone, sexo, ano, mes, dia);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
    
    public Clientes getbyID(Long id) {
        Clientes cliente = null;
        String sql = "SELECT * from Usuario WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String cpf_c = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                int ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                int dia = resultSet.getInt("dia");
              		
                cliente = new Clientes(id, cpf_c, email, senha, nome, telefone, sexo, ano, mes, dia);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
    
    public Clientes getbyLogin(String login) {
        Clientes cliente = null;
        String sql = "SELECT * from CLIENTES WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("id");
            	String cpf_c = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");int ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                int dia = resultSet.getInt("dia");
              		
                cliente = new Clientes(id, cpf_c, email, senha, nome, telefone, sexo, ano, mes, dia);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}