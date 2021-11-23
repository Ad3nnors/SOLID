package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Clientes")
public class Clientes extends Pessoa<Long> {

	@Size(min = 11, max = 12, message = "{Size.telefone}")
	@Column(length = 12)
	private String telefone;

	@Size(max = 2)
	@Column(length = 2)
	private String sexo;

	@Size(max = 10)
	@Column(length = 10)
	private String nascimento;
	
	@OneToMany(mappedBy = "cliente")
	private List<Consultas> consulta;
	
	public List<Consultas> getConsultas() {
		return consulta;
	}

	public void setConsultas(List<Consultas> consulta) {
		this.consulta = consulta;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

}
