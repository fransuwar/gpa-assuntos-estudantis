package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

@Entity

public class PropriedadeRural {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Double area;
	
	private String cidade;

	@Enumerated(EnumType.STRING)
	private GrauParentesco parentesco;
	
	private String outro;	

	@ManyToOne(fetch = FetchType.LAZY)
	private QuestionarioAuxilioMoradia auxilioMoradia;
	
	public GrauParentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(GrauParentesco parentesco) {
		this.parentesco = parentesco;
	}

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

	public String getOutro() {
		return outro;
	}

	public void setOutro(String outro) {
		this.outro = outro;
	}
}
