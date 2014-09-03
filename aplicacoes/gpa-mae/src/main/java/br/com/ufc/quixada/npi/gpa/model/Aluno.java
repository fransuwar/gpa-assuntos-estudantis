package br.com.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;






@Entity
public class Aluno {
	
	public Aluno() {}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	@Column(nullable = false)
	private String matricula;
	
	@ManyToMany
	private List<SelecaoBolsa> editais;
	
	@ManyToMany(mappedBy="alunosSelecao")
	private List<SelecaoBolsa> editaisAluno;
	
	@OneToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;
	
	@OneToOne
	private QuestionarioIniciacaoAcademica iniciacaoAcademica;
	
	
	private String nome;
			
	private String anoIngresso;
	
	private String ira;
	
	private String curso;
	
	private String banco;
	
	private String agencia;
	
	private String conta;
    
	@ManyToOne
	private Pessoa pessoa;
	
	public Pessoa getUsuario() {
		return pessoa;
	}

	public void setUsuario(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
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

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getAnoIngresso() {
		return anoIngresso;
	}

	public void setAnoIngresso(String anoIngresso) {
		this.anoIngresso = anoIngresso;
	}

	public String getIra() {
		return ira;
	}

	public void setIra(String ira) {
		this.ira = ira;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", matricula=" + matricula + ", editais="
				+ editais + ", editaisAluno=" + editaisAluno
				+ ", auxilioMoradia=" + auxilioMoradia
				+ ", iniciacaoAcademica=" + iniciacaoAcademica + ", nome="
				+ nome + ", anoIngresso=" + anoIngresso + ", ira=" + ira
				+ ", curso=" + curso + ", banco=" + banco + ", agencia="
				+ agencia + ", conta=" + conta + ", pessoa=" + pessoa + "]";
	}
	
	

}