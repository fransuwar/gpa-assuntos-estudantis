package br.ufc.quixada.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Aluno {
	
	public Aluno() {}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	@Column(nullable = false)
	private String matricula;
	
	@ManyToMany
	private List<Edital> editais;
	
	@ManyToMany(mappedBy="alunosSelecao")
	private List<Edital> editaisAluno;
	
	@OneToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;
	
	@OneToOne
	private QuestionarioIniciacaoAcademica iniciacaoAcademica;
	
	private String anoIngresso;
	
	private String ira;
	
	private String curso;
	
	private String banco;
	
	private String agencia;
	
	private String conta;
    
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
				+ ", iniciacaoAcademica=" + iniciacaoAcademica
				+ ", anoIngresso=" + anoIngresso + ", ira=" + ira + ", curso="
				+ curso + ", banco=" + banco + ", agencia=" + agencia
				+ ", conta=" + conta + ", usuario=" + usuario + "]";
	}
	
	

}