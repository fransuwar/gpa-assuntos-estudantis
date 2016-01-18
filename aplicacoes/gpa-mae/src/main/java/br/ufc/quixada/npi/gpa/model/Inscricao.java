package br.ufc.quixada.npi.gpa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.npi.gpa.enums.Resultado;

@NamedQueries({
		@NamedQuery(name = "Inscricao.findIncricoesByIdAluno", query = "select i from Inscricao i where i.aluno.id = :idAluno"), 
		@NamedQuery(name ="Inscricao.findByIdAlunoEmSelecao", query = "SELECT i from Selecao s, Aluno a, Inscricao i where s.id =:idSelecao and i.selecao.id = s.id and i.aluno.id =:idAluno") })

@Entity
public class Inscricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;

	private boolean avaliacaoDocumentos;

	@Enumerated(EnumType.STRING)

	private Resultado resultado;

	private String observacoes;

	@Column(nullable = false)
	private boolean deferimento;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private QuestionarioAuxilioMoradia questionarioAuxilioMoradia;

	@OneToOne
	private Selecao selecao;

	@OneToOne
	private VisitaDomiciliar visitaDomiciliar;

	@OneToOne
	private Entrevista entrevista;

	@ManyToOne
	private Aluno aluno;

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

	public Selecao getSelecao() {
		return selecao;
	}

	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
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

	public boolean isDeferimento() {
		return deferimento;
	}

	public void setDeferimento(boolean deferimento) {
		this.deferimento = deferimento;
	}

	public Entrevista getEntrevista() {
		return entrevista;
	}

	public void setEntrevista(Entrevista entrevista) {
		this.entrevista = entrevista;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inscricao other = (Inscricao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inscricao [id=" + id + "]";
	}

}
