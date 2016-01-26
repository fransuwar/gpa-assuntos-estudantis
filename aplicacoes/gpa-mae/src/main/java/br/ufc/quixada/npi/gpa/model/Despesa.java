package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Despesa {

	public Despesa() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Campo obrigatório")
	private Double moradia;
	@NotNull(message = "Campo obrigatório")
	private Double energia;
	@NotNull(message = "Campo obrigatório")
	private Double telefone;
	@NotNull(message = "Campo obrigatório")
	private Double educacao;
	@NotNull(message = "Campo obrigatório")
	private Double saude;
	@NotNull(message = "Campo obrigatório")
	private Double alimentacao;
	@NotNull(message = "Campo obrigatório")
	private Double outro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMoradia() {
		return moradia;
	}

	public void setMoradia(Double moradia) {
		this.moradia = moradia;
	}

	public Double getEnergia() {
		return energia;
	}

	public void setEnergia(Double energia) {
		this.energia = energia;
	}

	public Double getTelefone() {
		return telefone;
	}

	public void setTelefone(Double telefone) {
		this.telefone = telefone;
	}

	public Double getEducacao() {
		return educacao;
	}

	public void setEducacao(Double educacao) {
		this.educacao = educacao;
	}

	public Double getSaude() {
		return saude;
	}

	public void setSaude(Double saude) {
		this.saude = saude;
	}

	public Double getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(Double alimentacao) {
		this.alimentacao = alimentacao;
	}

	public Double getOutro() {
		return outro;
	}

	public void setOutro(Double outro) {
		this.outro = outro;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Despesa [id=" + id + "]";
	}

}
