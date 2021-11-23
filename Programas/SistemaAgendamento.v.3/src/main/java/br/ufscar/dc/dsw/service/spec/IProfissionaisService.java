package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.domain.Profissionais;
import org.springframework.stereotype.Service;

@Service
public interface IProfissionaisService {
	Profissionais buscarPorId(Long id);

	List<Profissionais> buscarTodos();

	void salvar(Profissionais profissional);

	void excluir(Long id);

	boolean profissionalTemConsultas(Long id);

	List<Profissionais> buscarPorArea(String area);
}
