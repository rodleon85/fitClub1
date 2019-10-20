package com.src.main.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Cadastrar Anamnese do Paciente.")
    private String anamnese;
    
    @NotBlank(message = "Cadastrar Dobras do Paciente.")
    private String dobras;
    
    @NotBlank(message = "Cadastrar Exame Ergométrico do Paciente.")
    private String ergometrigo;
    
    @NotBlank(message = "Informar Data da Avaliação.")
    private Date dataAvaliacao;
    
    @ManyToOne
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;

	public long getId() {
		return id;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(String anamnese) {
		this.anamnese = anamnese;
	}

	public String getDobras() {
		return dobras;
	}

	public void setDobras(String dobras) {
		this.dobras = dobras;
	}

	public String getErgometrigo() {
		return ergometrigo;
	}

	public void setErgometrigo(String ergometrigo) {
		this.ergometrigo = ergometrigo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	   
}