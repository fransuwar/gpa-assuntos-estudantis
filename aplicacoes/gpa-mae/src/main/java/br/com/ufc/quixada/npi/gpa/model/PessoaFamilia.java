package br.com.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity

public class PessoaFamilia {

	public QuestionarioAuxilioMoradia getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(QuestionarioAuxilioMoradia auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}

	public QuestionarioIniciacaoAcademica getIniciacaoAcademica() {
		return iniciacaoAcademica;
	}

	public void setIniciacaoAcademica(
			QuestionarioIniciacaoAcademica iniciacaoAcademica) {
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

	public String getAtividadeProfissao() {
		return atividadeProfissao;
	}

	public void setAtividadeProfissao(String atividadeProfissao) {
		this.atividadeProfissao = atividadeProfissao;
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public GrauParentesco getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(GrauParentesco grauParentesco) {
		this.grauParentesco = grauParentesco;
	}
	

	@ManyToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;

	@ManyToOne
	private QuestionarioIniciacaoAcademica iniciacaoAcademica;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	private int idade;
	private String escolaridade;
	private String atividadeProfissao;
	private double rendaMensal;
	
	public enum GrauParentesco{
		Filho_a("Filho(a)"), Neto("Neto(a)"), Sobrinho("Sobrinho(a)"), Irmao("Irmão"),
		Conjuge_Companheiro("Cônjuge ou Companheiro(a)"), Outros("Outros");

		GrauParentesco(String nome){}
	}
	
	@Enumerated(EnumType.STRING)
	private GrauParentesco grauParentesco;

	@Override
	public String toString() {
		return "PessoaFamilia [auxilioMoradia=" + auxilioMoradia
				+ ", iniciacaoAcademica=" + iniciacaoAcademica + ", id=" + id
				+ ", nome=" + nome + ", idade=" + idade
				+ ", atividadeProfissao=" + atividadeProfissao
				+ ", rendaMensal=" + rendaMensal + ", grauParentesco="
				+ grauParentesco + "]";
	}
}
