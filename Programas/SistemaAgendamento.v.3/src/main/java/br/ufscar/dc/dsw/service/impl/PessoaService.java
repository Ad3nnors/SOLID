package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IPessoaDAO;
import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.service.spec.IPessoaService;

@Service
@Transactional(readOnly = false)
public class PessoaService implements IPessoaService {

	@Autowired
	IPessoaDAO dao;

	public void salvar(Pessoa pessoa) {
		dao.save(pessoa);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Pessoa buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Pessoa> buscarTodos() {
		return dao.findAll();
	}
}