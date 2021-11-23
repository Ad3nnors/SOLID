package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;


abstract public class GenericDAO {
    
    public GenericDAO() {
        try {           
        	/* Setup Banco de dados MySQL */
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
    	/* Conexão banco de dados MySQL */
    	String url = "jdbc:mysql://localhost:3306/SistemaAgendamento";
        return DriverManager.getConnection(url, "root", "root");
    }
}