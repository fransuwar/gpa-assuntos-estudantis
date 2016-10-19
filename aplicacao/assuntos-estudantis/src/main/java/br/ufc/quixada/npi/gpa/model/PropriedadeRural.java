package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.ufc.quixada.npi.gpa.enums.GrauParentesco;

@Entity
public class PropriedadeRural {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private GrauParentesco parentescoProprietario;
	
	private Double area;
	
	private String cidade;
	
	private String estado;
	
	private String outroParentesco;
	
	@ManyToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;

	public GrauParentesco getParentescoProprietario() {
		return parentescoProprietario;
	}

	public void setParentescoProprietario(GrauParentesco parentescoProprietario) {
		this.parentescoProprietario = parentescoProprietario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getArea() {
		return area;
	}
	
	public void setArea(Double area) {
		this.area = area;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getOutroParentesco() {
		return outroParentesco;
	}

	public void setOutroParentesco(String outroParentesco) {
		this.outroParentesco = outroParentesco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public QuestionarioAuxilioMoradia getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(QuestionarioAuxilioMoradia auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}
	
	
}
