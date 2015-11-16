package br.ufc.quixada.npi.gpa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.npi.gpa.enums.Resultado;

@Entity
public class Inscricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	private boolean avaliacaoDocumentos;
	private Resultado resultado;
	private String observacoes;
	@OneToOne
	private QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica;
	@OneToOne
	private QuestionarioAuxilioMoradia questionarioAuxilioMoradia;
	@OneToOne
	private Selecao selecaoBolsa;
	@OneToOne
	private VisitaDomiciliar visitaDomiciliar;
	
	public Inscricao() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAvaliacaoDocumentos() {
		return avaliacaoDocumentos;
	}

	public void setAvaliacaoDocumentos(boolean avaliacaoDocumentos) {
		this.avaliacaoDocumentos = avaliacaoDocumentos;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public QuestionarioIniciacaoAcademica getQuestionarioIniciacaoAcademica() {
		return questionarioIniciacaoAcademica;
	}

	public void setQuestionarioIniciacaoAcademica(QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica) {
		this.questionarioIniciacaoAcademica = questionarioIniciacaoAcademica;
	}

	public Selecao getSelecaoBolsa() {
		return selecaoBolsa;
	}

	public void setSelecaoBolsa(Selecao selecaoBolsa) {
		this.selecaoBolsa = selecaoBolsa;
	}

	public QuestionarioAuxilioMoradia getQuestionarioAuxilioMoradia() {
		return questionarioAuxilioMoradia;
	}

	public void setQuestionarioAuxilioMoradia(QuestionarioAuxilioMoradia questionarioAuxilioMoradia) {
		this.questionarioAuxilioMoradia = questionarioAuxilioMoradia;
	}

	public VisitaDomiciliar getVisitaDomiciliar() {
		return visitaDomiciliar;
	}

	public void setVisitaDomiciliar(VisitaDomiciliar visitaDomiciliar) {
		this.visitaDomiciliar = visitaDomiciliar;
	}
}