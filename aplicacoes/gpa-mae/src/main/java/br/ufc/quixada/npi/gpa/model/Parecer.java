package br.ufc.quixada.npi.gpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Parecer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Boolean selecionado;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Selecao selecao;
	
	@OneToOne
	private Aluno alunoApto;
	
	@NotNull(message = "Campo obrigatório.")
	private String peso;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Selecao getSelecao() {
		return selecao;
	}

	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
	}

	public Aluno getAlunoApto() {
		return alunoApto;
	}

	public void setAlunoApto(Aluno alunoApto) {
		this.alunoApto = alunoApto;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Parecer [id=" + id + "]";
	}
	
}
