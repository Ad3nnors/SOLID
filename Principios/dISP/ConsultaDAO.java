package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends GenericDAO {
	
	public void insert(Consulta consulta) throws Exception {
    		// implementação
	}
    
	public void delete(Consulta consulta) {
        	// implementação
	}

	public ArrayList<Consulta> getbyCliente(Long id) throws Exception {
        	// implementação
	}
    
	public ArrayList<Consulta> getbyProfissional(Long id) throws Exception {
		// implementação
	}
}
