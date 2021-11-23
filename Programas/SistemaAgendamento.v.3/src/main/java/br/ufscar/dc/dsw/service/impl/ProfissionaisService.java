package br.ufscar.dc.dsw.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.IProfissionaisDAO;
import br.ufscar.dc.dsw.domain.Profissionais;
import br.ufscar.dc.dsw.service.spec.IProfissionaisService;

@Service
@Transactional(readOnly = false)
public class ProfissionaisService implements IProfissionaisService {

    @Autowired
    IProfissionaisDAO dao;

    public void salvar(Profissionais profissional) {
        dao.save(profissional);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Profissionais buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Profissionais> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public boolean profissionalTemConsultas(Long id) {
        return !dao.findById(id.longValue()).getConsultas().isEmpty();
    }

    @Transactional(readOnly = true)
	public List<Profissionais> buscarPorArea(String area) {
		if (area == null || area == "") {
		return dao.findAll();
		}
		return dao.findByArea(area);
	}
}
