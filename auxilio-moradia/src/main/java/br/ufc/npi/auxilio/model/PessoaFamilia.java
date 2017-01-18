package br.ufc.npi.auxilio.model;

import javax.persistence.*;

import br.ufc.npi.auxilio.enums.Escolaridade;
import br.ufc.npi.auxilio.enums.GrauParentesco;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PessoaFamilia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private GrauParentesco parentesco;

	private String outroParentesco;

	@Enumerated(EnumType.STRING)
	private Escolaridade escolaridade;

	private Integer idade;

	private String profissao;

	private Double rendaMensal;

	@JsonIgnore
	@ManyToOne
	private QuestionarioAuxilioMoradia questionario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String atividadeProfissao) {
		this.profissao = atividadeProfissao;
	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public GrauParentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(GrauParentesco grauParentesco) {
		this.parentesco = grauParentesco;
	}

	public String getOutroParentesco() {
		return outroParentesco;
	}

	public void setOutroParentesco(String outroParentesco) {
		this.outroParentesco = outroParentesco;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public QuestionarioAuxilioMoradia getQuestionario() {
		return questionario;
	}

	public void setQuestionario(QuestionarioAuxilioMoradia questionario) {
		this.questionario = questionario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PessoaFamilia that = (PessoaFamilia) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
