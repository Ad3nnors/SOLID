package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Profissionais;

public interface IProfissionaisDAO extends CrudRepository<Profissionais, Long> {
	Profissionais findById(long id);

	Profissionais findBycpf(String CPF);

	List<Profissionais> findAll();

	Profissionais save(Profissionais profissional);

	void deleteById(Long id);
/*
	@Query("SELECT u FROM Profissionais u WHERE u.area = :area")
	public List<Profissionais> getProfissionalByArea(@Param("area") String area);
	*/
	List<Profissionais> findByArea(String area);
	
}
