package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Clientes;

public interface IClientesDAO extends CrudRepository<Clientes, Long> {
	Clientes findById(long id);

	Clientes findBycpf(String cpf);

	List<Clientes> findAll();

	Clientes save(Clientes cliente);

	void deleteById(Long id);
}
