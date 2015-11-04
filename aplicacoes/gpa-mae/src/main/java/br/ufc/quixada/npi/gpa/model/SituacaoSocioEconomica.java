package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SituacaoSocioEconomica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	

	public SituacaoSocioEconomica() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
