package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Clientes;
import br.ufscar.dc.dsw.domain.Consultas;
import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Profissionais;

public interface IConsultasDAO extends CrudRepository<Consultas, Long> {
	Consultas findById(long id);

	List<Consultas> findAll();

	Consultas save(Consultas consulta);

	void deleteById(Long id);
	
	List<Consultas> findAllByProfissional(Profissionais u);

	List<Consultas> findAllByCliente(Clientes cli);
	
	
	Consultas findByProfissionalAndDataHora(Profissionais profissional, String dataHora);

	Consultas findByClienteAndDataHora(Clientes cliente, String dataHora);
	
}
