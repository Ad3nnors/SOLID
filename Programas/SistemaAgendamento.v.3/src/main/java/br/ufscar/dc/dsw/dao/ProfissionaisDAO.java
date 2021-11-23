package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Clientes;
import br.ufscar.dc.dsw.domain.Profissionais;
import br.ufscar.dc.dsw.domain.Consultas;

public class ProfissionaisDAO extends GenericDAO {

    public void insert(Profissionais profissional) {

        String sql = "insert into PROFISSIONAIS(id, cpf, email, senha, nome, area, especialidade)  VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, profissional.getId());
            statement.setString(2, profissional.getArea());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getCpf());
            statement.setString(5, profissional.getEspecialidade());
            statement.setString(6, profissional.getNome());
            statement.setString(7, profissional.getSenha());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Profissionais> getAll() {

        ArrayList<Profissionais> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from Profissionais order by nome ASC";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
            	String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                Profissionais profissional = new Profissionais(id, email, senha, cpf, nome, area, especialidade);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Profissionais profissional) {
        String sql = "DELETE FROM PROFISSIONAIS where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissionais profissional) {
        String sql = "UPDATE PROFISSIONAIS SET profissional = ?, autor = ?, senha = ?, nome = ?, area = ?, especialidade = ?";
        sql += ", WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement;
            
            statement = conn.prepareStatement(sql);
            statement.setLong(1, profissional.getId());
            statement.setString(2, profissional.getArea());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getCpf());
            statement.setString(5, profissional.getEspecialidade());
            statement.setString(6, profissional.getNome());
            statement.setString(7, profissional.getSenha());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissionais get(Long id) {
    	Profissionais profissional = null;

    	String sql = "SELECT * from PROFISSIONAIS where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {              
            	String cpf = resultSet.getString("cpf");
            	String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
 
                profissional = new Profissionais(id, email, senha, cpf, nome, area, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
    public Profissionais getbyID(Long id) {
    	Profissionais profissional = null;

    	String sql = "SELECT * from PROFISSIONAIS where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {              
            	String cpf = resultSet.getString("cpf");
            	String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
 
                profissional = new Profissionais(id, email, senha, cpf, nome, area, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
    
    public Profissionais getbyLogin(String emailx) {
        Profissionais profissional = null;
        String sql = "SELECT * from PROFISSIONAIS WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, emailx);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                profissional = new Profissionais(id, email, senha, cpf, nome, area, especialidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
    public ArrayList<Profissionais> getbyArea(String aarea) {

        ArrayList<Profissionais> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from PROFISSIONAIS WHERE area = ? order by nome ASC";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, aarea);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
            	String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                Profissionais profissional = new Profissionais(id, email, senha, cpf, nome, area, especialidade);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }
}