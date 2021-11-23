package br.ufscar.dc.dsw.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.IClientesDAO;
import br.ufscar.dc.dsw.domain.Clientes;
import br.ufscar.dc.dsw.service.spec.IClientesService;

@Service
@Transactional(readOnly = false)
public class ClientesService implements IClientesService {

	@Autowired
	IClientesDAO dao;

	public void salvar(Clientes cliente) {
		dao.save(cliente);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Clientes buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Clientes> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public boolean clienteTemConsultas(Long id) {
		return !dao.findById(id.longValue()).getConsultas().isEmpty();
	}
}
