package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.ufscar.dc.dsw.domain.Consultas;

public class ConsultasDAO extends GenericDAO {
	
    public void insert(Consultas consulta) throws Exception {
    	//confere se o cliente já possui consulta marcada
    	String sql = "SELECT * from CONSULTAS WHERE ano = ? and mes = ? and dia = ? and hora = ? and id_C = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, consulta.getAno());
            statement.setInt(2, consulta.getMes());
            statement.setInt(3, consulta.getDia());
            statement.setInt(4, consulta.getHora());
            statement.setLong(5, consulta.getCliente());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new Exception("Esse cliente já possui consulta agendada para esse horário");
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    	
    	//insere
        sql = "INSERT INTO Consultas (id_C, id_P, ano, mes, dia, hora) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, consulta.getCliente());
            statement.setLong(2, consulta.getProfissional());
            statement.setInt(3, consulta.getAno());
            statement.setInt(4, consulta.getMes());
            statement.setInt(5, consulta.getDia());
            statement.setInt(6, consulta.getHora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void delete(Consultas consulta) {
        String sql = "DELETE FROM Consultas where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /*R7 - Lista de todas as Consultas de um Cliente*/
    public ArrayList<Consultas> getbyCliente(Long id) throws Exception {
        String sql = "SELECT c.*, cl.id as id_C, p.id as id_P, cl.nome as nome_C, p.nome as nome_P, p.especialidade as especialidade, p.area as area FROM CONSULTAS c, PROFISSIONAIS p , CLIENTES cl where c.id_C = ? AND p.id = c.id_P AND c.id_C = cl.id order by ano desc, mes desc, dia desc, hora desc";
        
        ArrayList<Consultas> listaConsultas = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Long id_ = resultSet.getLong("id");
                Long cliente = resultSet.getLong("id_C");
                Long profissional = resultSet.getLong("id_P");
                String nome_C = resultSet.getString("nome_C");
                String nome_P = resultSet.getString("nome_P");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                int ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                int dia = resultSet.getInt("dia");
                int hora = resultSet.getInt("hora");
                
                Consultas consulta = new Consultas(id_, cliente, profissional, ano, mes, dia, hora);
                consulta.setNome_C(nome_C);
                consulta.setNome_P(nome_P);
                consulta.setArea(area);
                consulta.setEspecialidade(especialidade);
                listaConsultas.add(consulta);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
    
    /*R7 - Lista de todas as Consultas de um Profissional*/
    public ArrayList<Consultas> getbyProfissional(Long id) throws Exception {
       
        String sql = "SELECT c.*, cl.id as id_C, p.id as id_P, cl.nome as nome_C, p.nome as nome_P, p.especialidade as especialidade, p.area as area FROM CONSULTAS c, PROFISSIONAIS p , CLIENTES cl where c.id_P = ? AND p.id = c.id_P AND c.id_C = cl.id order by ano desc, mes desc, dia desc, hora desc";
        
        ArrayList<Consultas> listaConsultas = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Long id_ = resultSet.getLong("id");
                Long cliente = resultSet.getLong("id_C");
                Long profissional = resultSet.getLong("id_P");
                String nome_C = resultSet.getString("nome_C");
                String nome_P = resultSet.getString("nome_P");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                int ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                int dia = resultSet.getInt("dia");
                int hora = resultSet.getInt("hora");
                
                Consultas consulta = new Consultas(id_, cliente, profissional, ano, mes, dia, hora);
                consulta.setNome_C(nome_C);
                consulta.setNome_P(nome_P);
                consulta.setArea(area);
                consulta.setEspecialidade(especialidade);
                listaConsultas.add(consulta);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
}
