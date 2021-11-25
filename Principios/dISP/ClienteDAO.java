package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import br.ufscar.dc.dsw.domain.Cliente;

public class ClienteDAO extends GenericDAO {

	public void insert(Cliente cliente) throws Exception {
    		// implementação
	}
    
	public ArrayList<Cliente> getAll() {
		// implementação
	}

	public void delete(Cliente cliente) {
        	// implementação
	}

	public void update(Cliente cliente) {
		// implementação
	}

	public Cliente get(Long id) {
        	// implementação
	}
    
	public Cliente getbyID(Long id) {
        	// implementação
	}
    
	public Cliente getbyLogin(String login) {
		// implementação
	}
}
