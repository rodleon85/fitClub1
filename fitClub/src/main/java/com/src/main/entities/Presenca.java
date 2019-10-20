package com.src.main.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Presenca {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name="aula_id", nullable=false)
    private Aula aula;
    
    private Date diaPresenca;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Date getDiaPresenca() {
		return diaPresenca;
	}

	public void setDiaPresenca(Date diaPresenca) {
		this.diaPresenca = diaPresenca;
	}
    

	   
}