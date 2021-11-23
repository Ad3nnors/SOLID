package br.ufscar.dc.dsw.domain;

public class Consultas {

	private Long id;
	private Long cliente;
	private Long profissional;
	private String nome_C;
	private String nome_P;
	private String area;
	private String especialidade;
	private int ano;
    private int mes;
    private int dia;
    private int hora;
	
	public Consultas(Long id, Long cliente, Long profissional, int ano, int mes, int dia, int hora){
		this.id = id;
		this.cliente = cliente;
		this.profissional = profissional;
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
		this.hora = hora;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCliente() {
		return cliente;
	}
    
    public void setCliente(Long cliente) {
		this.cliente = cliente;
	}
    
    public Long getProfissional() {
		return profissional;
	}
    
    public void setProfissional(Long profissional) {
		this.profissional = profissional;
	}
    
    public String getNome_C() {
		return this.nome_C;
	}
    
    public void setNome_C(String cliente) {
		this.nome_C = cliente;
	}
    
    public String getNome_P() {
		return this.nome_P;
	}
    
    public void setNome_P(String profissional) {
		this.nome_P = profissional;
	}
    
    public String getArea() {
		return this.area;
	}
    
    public void setArea(String area) {
		this.area = area;
	}
    
    public String getEspecialidade() {
		return this.especialidade;
	}
    
    public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
    public String getData() {
        return String.format("%02d/%02d/%04d - %02d:00", 
        this.dia, this.mes, this.ano, this.hora);
    }
    
    public void setData(int ano, int mes, int dia, int hora){
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
    }
	
    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }
}
