package com.src.main.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity // This tells Hibernate to make a table out of this class
public class Aluno {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotBlank(message = "O Nome é obrigatório.")
    private String name;
    
    @Column(unique = true)
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;
    
    private String telefone;
    
    @NotBlank(message = "O RG é obrigatório.")
    private String rg;
    
    @NotBlank(message = "O Endereço é obrigatório.")
    private String endereco;
    
    @NotBlank(message = "O E-mail é obrigatório.")
    private String email;
    
    private String plano;
    
    private Date dataMatricula;
    
	private Date dataProximoPagamento;
    
    

	//Getters e Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}
	
//	@Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$")
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
    public Date getDataMatricula() {
    	return dataMatricula;
    }
    
    public void setDataMatricula(Date dataMatricula) {
    	this.dataMatricula = dataMatricula;
    }

	/**
	 * @return the dataProximoPagamento
	 */
	public Date getDataProximoPagamento() {
		return dataProximoPagamento;
	}

	/**
	 * @param dataProximoPagamento the dataProximoPagamento to set
	 */
	public void setDataProximoPagamento(Date dataProximoPagamento) {
		this.dataProximoPagamento = dataProximoPagamento;
	}
	
	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}


}