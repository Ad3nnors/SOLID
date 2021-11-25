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
    		// implementação
	}
    
	public ArrayList<Clientes> getAll() {
		// implementação
	}

	public void delete(Clientes cliente) {
        	// implementação
	}

	public void update(Clientes cliente) {
		// implementação
	}

	public Clientes get(Long id) {
        	// implementação
	}
    
	public Clientes getbyID(Long id) {
        	// implementação
	}
    
	public Clientes getbyLogin(String login) {
		// implementação
	}
}
