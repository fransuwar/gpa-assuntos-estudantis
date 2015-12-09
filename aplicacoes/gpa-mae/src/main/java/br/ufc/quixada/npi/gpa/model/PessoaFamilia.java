package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.ufc.quixada.npi.gpa.enums.GrauParentesco;

@Entity
@NamedQueries({
	@NamedQuery(name = "PessoaFamilia.findPessoaFamiliaByIdQuestBIA",  query = "select pf from PessoaFamilia pf where pf.iniciacaoAcademica.id = :idIniciacaoAcademica"),
	@NamedQuery(name = "PessoaFamilia.findPessoaFamiliaByIdQuestAMOR", query = "select pf from PessoaFamilia pf where pf.auxilioMoradia.id = :idAuxilioMoradia")
})
public class PessoaFamilia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;

	private Integer idade;

	private String escolaridade;

	private String profissao;

	private Double rendaMensal;

	@Enumerated(EnumType.STRING)
	private GrauParentesco parentesco;

	@ManyToOne(fetch = FetchType.LAZY)
	private QuestionarioAuxilioMoradia auxilioMoradia;

	@ManyToOne(fetch = FetchType.LAZY)
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
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

	@Override
	public String toString() {
		return "PessoaFamilia [auxilioMoradia=" + auxilioMoradia + ", iniciacaoAcademica=" + iniciacaoAcademica
				+ ", id=" + id + ", nome=" + nome + ", idade=" + idade + ", Profissao=" + profissao + ", rendaMensal="
				+ rendaMensal + ", Parentesco=" + parentesco + "]";
	}
}
