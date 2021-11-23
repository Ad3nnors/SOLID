package br.ufscar.dc.dsw.domain;
import java.util.Date;



public class Clientes{

	private Long id;
    private static String cpf;
    private String email;
    private String senha;
	private String nome;
	private String telefone;
	private String sexo;
    private int mes;
    private int dia;
    private int ano;

    public Clientes(Long id, String cpf, String email, String senha, String nome, String telefone, String sexo, int ano, int mes, int dia) {
    	this.id = id;
		this.cpf = cpf;
        this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.telefone = telefone;
		this.sexo = sexo;
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
    }
	
    public Clientes() {}
    
    public Clientes(Long id) {
    	this.id = id;
    }
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
        return String.format("%02d/%02d/%04d", 
        this.dia, this.mes, this.ano);
    }
    public void setNascimento(int ano, int mes, int dia){
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
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

}