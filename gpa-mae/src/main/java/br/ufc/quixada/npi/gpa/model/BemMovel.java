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

import br.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;

@Entity

public class BemMovel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String veiculo;

	
	@Enumerated(EnumType.STRING)
	private GrauParentescoVeiculos grauParentescoVeiculos;
	
	private String outroGrauParentescoVeiculos;
	
	@Enumerated(EnumType.STRING)
	private FinalidadeVeiculo finalidadeVeiculo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private QuestionarioAuxilioMoradia auxilioMoradia;

	public QuestionarioAuxilioMoradia getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(QuestionarioAuxilioMoradia auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}
	
	public GrauParentescoVeiculos getGrauParentescoVeiculos() {
		return grauParentescoVeiculos;
	}

	public void setGrauParentescoVeiculos(GrauParentescoVeiculos grauParentescoVeiculos) {
		this.grauParentescoVeiculos = grauParentescoVeiculos;
	}
	
	public FinalidadeVeiculo getFinalidadeVeiculo() {
		return finalidadeVeiculo;
	}

	public void setFinalidadeVeiculo(FinalidadeVeiculo finalidadeVeiculo) {
		this.finalidadeVeiculo = finalidadeVeiculo;
	}
	
	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
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
				+ ", id=" + id + ", Parentesco=" + grauParentescoVeiculos + "]";
	}

	public String getOutroGrauParentescoVeiculos() {
		return outroGrauParentescoVeiculos;
	}

	public void setOutroGrauParentescoVeiculos(String outroGrauParentescoVeiculos) {
		this.outroGrauParentescoVeiculos = outroGrauParentescoVeiculos;
	}	
}
