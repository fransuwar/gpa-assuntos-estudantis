package br.quixada.ufc.npi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bolsa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String requisitos;
	
	public enum tipoBolsa{
		BolsaIniciacaoCientifica, BolsaAuxilioMoradia;
	}
	
	private tipoBolsa tipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public tipoBolsa getTipo() {
		return tipo;
	}

	public void setTipo(tipoBolsa tipo) {
		this.tipo = tipo;
	}
	
	
	
}
