package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Clientes;
import br.ufscar.dc.dsw.domain.Consultas;
import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Profissionais;

import org.springframework.stereotype.Service;

@Service
public interface IConsultasService {
	Consultas buscarPorId(Long id);

	List<Consultas> buscarTodos();

	void salvar(Consultas consulta);

	void excluir(Long id);
	
	List<Consultas> buscarTodosPorProfissional(Profissionais u);

	List<Consultas> buscarTodosPorCliente(Clientes cli);


	Consultas BuscarPorProfissionalEDataHora(Profissionais profissional, String dataHora);

	Consultas BuscarPorClienteEDataHora(Clientes cliente, String dataHora);

}
