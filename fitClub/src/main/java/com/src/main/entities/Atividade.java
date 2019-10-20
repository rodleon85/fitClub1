package com.src.main.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Atividade {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotBlank(message = "O Nome da atividade é obrigatório.")
	private String atividade;

	@ManyToOne
    @JoinColumn(name="instrutor_id", nullable=false)
    private Instrutor instrutor;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

}
