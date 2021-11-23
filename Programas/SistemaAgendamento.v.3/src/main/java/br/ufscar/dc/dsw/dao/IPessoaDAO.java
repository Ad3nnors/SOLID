package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Pessoa;

@SuppressWarnings("unchecked")
public interface IPessoaDAO extends CrudRepository<Pessoa, Long> {
	
	Pessoa findById(long id);

	List<Pessoa> findAll();
	
	Pessoa save(Pessoa pessoa);

	void deleteById(Long id);
	
    @Query("SELECT u FROM Pessoa u WHERE u.email = :email")
    public Pessoa getUserByEmail(@Param("email") String email);
}