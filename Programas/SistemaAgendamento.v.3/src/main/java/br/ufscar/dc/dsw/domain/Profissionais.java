package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissionais")
public class Profissionais extends Pessoa<Long> {

	@Size(max = 128)
	@Column(length = 128)
	private String area;

	@Size(max = 128)
	@Column(length = 128)
	private String especialidade;

	@OneToMany(mappedBy = "profissional")
	private List<Consultas> consulta;
	
	public List<Consultas> getConsultas() {
		return consulta;
	}

	public void setConsultas(List<Consultas> consulta) {
		this.consulta = consulta;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

}
