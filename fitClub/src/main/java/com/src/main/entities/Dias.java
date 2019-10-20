package com.src.main.entities;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dias {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

	private DayOfWeek dia;
    
    @ManyToOne
    @JoinColumn(name="aula_id", nullable=false)
    private Aula aula;
    
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DayOfWeek getDia() {
		return dia;
	}

	public void setDia(DayOfWeek dia) {
		this.dia = dia;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

}
