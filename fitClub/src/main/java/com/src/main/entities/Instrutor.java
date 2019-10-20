package com.src.main.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Instrutor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotBlank(message = "O Nome é obrigatório.")
	private String name;

	@Column(unique = true)
	@NotBlank(message = "O CPF é obrigatório.")
	private String cpf;

	@NotBlank(message = "O RG é obrigatório.")
	private String rg;

	@OneToMany(mappedBy="instrutor")
	private Set<Atividade> atividades; 

	public Set<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
	}

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

	public String getCpf() {
		return cpf;
	}

	//	@Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$")
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

}