package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.ufc.quixada.npi.gpa.enums.GrauParentesco;

@Entity
public class PessoaFamilia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private int idade;
	private String escolaridade;
	private String profissao;
	private double rendaMensal;
	@Enumerated(EnumType.STRING)
	private GrauParentesco parentesco;
	@ManyToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;
	@ManyToOne
	private QuestionarioIniciacaoAcademica iniciacaoAcademica;

	public QuestionarioAuxilioMoradia getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(QuestionarioAuxilioMoradia auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}

	public QuestionarioIniciacaoAcademica getIniciacaoAcademica() {
		return iniciacaoAcademica;
	}

	public void setIniciacaoAcademica(QuestionarioIniciacaoAcademica iniciacaoAcademica) {
		this.iniciacaoAcademica = iniciacaoAcademica;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String atividadeProfissao) {
		this.profissao = atividadeProfissao;
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public GrauParentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(GrauParentesco grauParentesco) {
		this.parentesco = grauParentesco;
	}

	@Override
	public String toString() {
		return "PessoaFamilia [auxilioMoradia=" + auxilioMoradia + ", iniciacaoAcademica=" + iniciacaoAcademica
				+ ", id=" + id + ", nome=" + nome + ", idade=" + idade + ", Profissao=" + profissao
				+ ", rendaMensal=" + rendaMensal + ", Parentesco=" + parentesco + "]";
	}
}
