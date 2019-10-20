package com.src.main.entities;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity // This tells Hibernate to make a table out of this class
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotBlank(message = "O login é obrigatório.")
    private String login;
    
    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
	
    @OneToOne(mappedBy="usuario")
    private Aluno aluno;
    
    @OneToOne(mappedBy="usuario")
    private Funcionario funcionario;
	
    private Blob digital;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Blob getDigital() {
		return digital;
	}

	public void setDigital(Blob digital) {
		this.digital = digital;
	}
    

}