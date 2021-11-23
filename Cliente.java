package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Cliente{

	private Long id;
	private static String cpf;
	private String nome;
	private String telefone;

	public Cliente(Long id, String cpf, String nome, String telefone) {
	    	this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
    	}
	
	public Cliente() {}
    
    	public Cliente(Long id) {
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

}
}
