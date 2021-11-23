package br.ufscar.dc.dsw.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Consultas")
public class Consultas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{NotBlank.consulta.data_hora}")
	@Size(max = 18)
	@Column(nullable = false, length = 18)
	private String dataHora;

	@NotNull(message = "{NotNull.consulta.cliente}")
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Clientes cliente;

	@NotNull(message = "{NotNull.consulta.profissional}")
	@ManyToOne
	@JoinColumn(name = "profissional_id")
	private Profissionais profissional;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	
	public Clientes getCliente() {
		return cliente;
	}
	
	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	
	public Profissionais getProfissional() {
		return profissional;
	}
	
	public void setProfissional(Profissionais profissional) {
		this.profissional = profissional;
	}

}
