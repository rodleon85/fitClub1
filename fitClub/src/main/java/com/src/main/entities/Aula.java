package com.src.main.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotBlank(message = "O Nome é obrigatório.")
    private String name;
    
    @DateTimeFormat(pattern = "hh:MM")
    private Date horaInicio;
    
    @DateTimeFormat(pattern = "hh:MM")
	private Date horaFim;
    
    @ManyToOne
    @JoinColumn(name="instrutor_id", nullable=false)
    private Instrutor instrutor;
    
    @NotBlank(message = "A Sala é obrigatória.")
    private String sala;
    
    @OneToMany(mappedBy="aula")
    private Set<Dias> dias;

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

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

//	public Set<Dias> getDias() {
//		return dias;
//	}
//
//	public void setDias(Set<Dias> dias) {
//		this.dias = dias;
//	}
    
}