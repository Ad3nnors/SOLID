package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IConsultaDAO extends CrudRepository<Consulta, Long> {
	Consulta findById(long id);

	List<Consulta> findAll();

	Consulta save(Consulta consulta);

	void deleteById(Long id);
	
	List<Consulta> findAllByProfissional(Profissional u);

	List<Consulta> findAllByCliente(Cliente cli);
	
	Consulta findByProfissionalAndDataHora(Profissional profissional, String dataHora);

	Consulta findByClienteAndDataHora(Cliente cliente, String dataHora);
}
