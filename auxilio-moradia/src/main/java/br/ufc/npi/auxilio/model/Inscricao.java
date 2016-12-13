package br.ufc.npi.auxilio.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.npi.auxilio.enums.Resultado;

@Entity
public class Inscricao implements Comparable<Inscricao>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	private boolean classificado;
	
	@OneToOne
	private Selecao selecao;
	
	@ManyToOne
	private Aluno aluno;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private QuestionarioAuxilioMoradia questionarioAuxilioMoradia;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private VisitaDomiciliar visitaDomiciliar;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Entrevista entrevista;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private AnaliseDocumentacao analiseDocumentacao;

	private boolean realizarVisita;
	
	private boolean consolidacao;

	public boolean isConsolidacao() {
		return consolidacao;
	}

	public void setConsolidacao(boolean consolidacao) {
		this.consolidacao = consolidacao;
	}

	public boolean isRealizarVisita() {
		return realizarVisita;
	}

	public void setRealizarVisita(boolean realizarVisita) {
		this.realizarVisita = realizarVisita;
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

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
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
		if(visitaDomiciliar == null){
			visitaDomiciliar = new VisitaDomiciliar();
		}
 		
		return visitaDomiciliar;
	}

	public void setVisitaDomiciliar(VisitaDomiciliar visitaDomiciliar) {
		this.visitaDomiciliar = visitaDomiciliar;
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
	
	public boolean isClassificado() {
		return classificado;
	}

	public void setClassificado(boolean classificado) {
		this.classificado = classificado;
	}
	

	public AnaliseDocumentacao getAnaliseDocumentacao() {
		return analiseDocumentacao;
	}

	public void setAnaliseDocumentacao(AnaliseDocumentacao documentacao) {
		this.analiseDocumentacao = documentacao;
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

	@Override
	public int compareTo(Inscricao o) {
		return getAluno().getPessoa().getNome().compareTo(o.getAluno().getPessoa().getNome());
	}

}
