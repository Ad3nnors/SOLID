package br.ufscar.dc.dsw.domain;

public class Profissionais {

	private Long id;
    private String email;
    private String senha;
    private String cpf;
    private String nome;
    private String area;
    private String especialidade;
    
    public Profissionais(Long id, String email,	String senha, String cpf, String nome, String area, String especialidade) {
    	this.id = id;
		this.email           = email;
    	this.senha           = senha;
    	this.cpf             = cpf;
    	this.nome            = nome;
    	this.area            = area;
    	this.especialidade   = especialidade;

	}
    
    public Profissionais(Long id) {
    	this.id = id;
    }
	
    public Profissionais() {
    }
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getArea() {
		return area;
	}
    
    public String getEmail() {
		return email;
	}
    
    public String getCpf() {
		return cpf;
	}
    
    public String getEspecialidade() {
		return especialidade;
	}
    
    public String getNome() {
		return nome;
	}
    
    public String getSenha() {
		return senha;
	}
    
    public void setArea(String area) {
		this.area = area;
	}
    
    public void setCpf(String cpf) {
    	this.cpf = cpf;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
    
    public void setNome(String nome) {
		this.nome = nome;
	}

    public void setSenha(String senha) {
		this.senha = senha;
	}
}
