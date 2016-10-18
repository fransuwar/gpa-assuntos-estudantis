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
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	private QuestionarioAuxilioMoradia auxilioMoradia;

	public QuestionarioAuxilioMoradia getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(QuestionarioAuxilioMoradia auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}
	
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

	@Override
	public String toString() {
		return new GsonBuilder().setLongSerializationPolicy( LongSerializationPolicy.STRING )
				.setExclusionStrategies(new ExclusionStrategy() {
			
			@Override
			public boolean shouldSkipField(FieldAttributes clazz) {
				return false;
			}
			
			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return (clazz == QuestionarioAuxilioMoradia.class || clazz == QuestionarioAuxilioMoradia.class);
			}
		}).create().toJson(this);
	}

	public String getOutro() {
		return outroParentesco;
	}

	public void setOutro(String outro) {
		this.outroParentesco = outro;
	}	
}
