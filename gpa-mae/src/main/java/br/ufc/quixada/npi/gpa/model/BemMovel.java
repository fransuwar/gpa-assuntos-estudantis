package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;

@Entity

public class BemMovel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String veiculo;

	
	@Enumerated(EnumType.STRING)
	private GrauParentescoVeiculos parentesco;
	
	private String outro;
	
	@Enumerated(EnumType.STRING)
	private FinalidadeVeiculo finalidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private QuestionarioAuxilioMoradia auxilioMoradia;

	public QuestionarioAuxilioMoradia getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(QuestionarioAuxilioMoradia auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}
	
	public GrauParentescoVeiculos getParentesco() {
		return parentesco;
	}

	public void setParentesco(GrauParentescoVeiculos parentesco) {
		this.parentesco = parentesco;
	}
	
	public FinalidadeVeiculo getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(FinalidadeVeiculo finalidade) {
		this.finalidade = finalidade;
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
				+ ", id=" + id + ", Parentesco=" + parentesco + "]";
	}

	public String getOutro() {
		return outro;
	}

	public void setOutro(String outro) {
		this.outro = outro;
	}	
}
