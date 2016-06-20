package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.ufc.quixada.npi.gpa.enums.GrauParentescoImovelRural;

@Entity

public class PropriedadeRural {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Double area;
	
	private String cidadeEstado;

	@Enumerated(EnumType.STRING)
	private GrauParentescoImovelRural grauParentescoImovelRural;
	
	private String outroGrauParentescoImovelRural;

	@ManyToOne(fetch = FetchType.LAZY)
	private QuestionarioAuxilioMoradia auxilioMoradia;

	public QuestionarioAuxilioMoradia getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(QuestionarioAuxilioMoradia auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Propriedade Rural [auxilioMoradia=" + auxilioMoradia
				+ ", id=" + id + ", Parentesco=" + grauParentescoImovelRural + "]";
	}

	public Double getArea() {
		return area;
	}
	
	public GrauParentescoImovelRural getGrauParentescoImovelRural() {
		return grauParentescoImovelRural;
	}

	public void setGrauParentescoImovelRural(GrauParentescoImovelRural grauParentescoImovelRural) {
		this.grauParentescoImovelRural = grauParentescoImovelRural;
	}
	
	public void setArea(Double area) {
		this.area = area;
	}

	public String getCidadePropriedadeRural() {
		return cidadeEstado;
	}

	public void setCidadePropriedadeRural(String cidadePropriedadeRural) {
		this.cidadeEstado = cidadePropriedadeRural;
	}

	public String getOutroGrauParentescoImovelRural() {
		return outroGrauParentescoImovelRural;
	}

	public void setOutroGrauParentescoImovelRural(String outroGrauParentescoImovelRural) {
		this.outroGrauParentescoImovelRural = outroGrauParentescoImovelRural;
	}
}
