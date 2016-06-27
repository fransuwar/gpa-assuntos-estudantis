package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Receita {
	public Receita() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Campo obrigatório")
	private Double trabalhoFormal;
	@NotNull(message = "Campo obrigatório")
	private Double trabalhoInformal;
	@NotNull(message = "Campo obrigatório")
	private Double aposentadoria;
	@NotNull(message = "Campo obrigatório")
	private Double beneficioSocial;
	@NotNull(message = "Campo obrigatório")
	private Double auxilioParentes;
	@NotNull(message = "Campo obrigatório")
	private Double outro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTrabalhoFormal() {
		return trabalhoFormal;
	}

	public void setTrabalhoFormal(Double trabalhoFormal) {
		this.trabalhoFormal = trabalhoFormal;
	}

	public Double getTrabalhoInformal() {
		return trabalhoInformal;
	}

	public void setTrabalhoInformal(Double trabalhoInformal) {
		this.trabalhoInformal = trabalhoInformal;
	}

	public Double getAposentadoria() {
		return aposentadoria;
	}

	public void setAposentadoria(Double aposentadoria) {
		this.aposentadoria = aposentadoria;
	}

	public Double getBeneficioSocial() {
		return beneficioSocial;
	}

	public void setBeneficioSocial(Double beneficioSocial) {
		this.beneficioSocial = beneficioSocial;
	}

	public Double getOutro() {
		return outro;
	}

	public void setOutro(Double outro) {
		this.outro = outro;
	}

	public Double getAuxilioParentes() {
		return auxilioParentes;
	}

	public void setAuxilioParentes(Double auxilioParentes) {
		this.auxilioParentes = auxilioParentes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receita other = (Receita) obj;

		return (id == other.id);
	}

	@Override
	public String toString() {
		return "Receita [id=" + id + "]";
	}

}
