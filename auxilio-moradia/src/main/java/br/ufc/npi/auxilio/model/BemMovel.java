package br.ufc.npi.auxilio.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.ufc.npi.auxilio.enums.FinalidadeVeiculo;
import br.ufc.npi.auxilio.enums.GrauParentesco;

@Entity

public class BemMovel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String veiculo;
	
	@Enumerated(EnumType.STRING)
	private GrauParentesco parentescoProprietario;
	
	private String outroParentesco;
	
	@Enumerated(EnumType.STRING)
	private FinalidadeVeiculo finalidade;
	
	public GrauParentesco getParentescoProprietario() {
		return parentescoProprietario;
	}

	public void setParentescoProprietario(GrauParentesco parentescoProprietario) {
		this.parentescoProprietario = parentescoProprietario;
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

	public String getOutro() {
		return outroParentesco;
	}

	public void setOutro(String outro) {
		this.outroParentesco = outro;
	}	
}
