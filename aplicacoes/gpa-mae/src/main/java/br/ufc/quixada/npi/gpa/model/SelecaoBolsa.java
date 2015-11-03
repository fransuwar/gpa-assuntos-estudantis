package br.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.TipoBolsa;


@NamedQueries({ @NamedQuery(
				name = "SelecaoBolsa.findSelecaoBolsaComDocumentos", 
				query = "SELECT sb FROM SelecaoBolsa sb LEFT JOIN FETCH sb.documentos WHERE sb.id = :selecaoBolsaId "),
				@NamedQuery(
				name = "SelecaoBolsa.findSelecaoBolsaComMembros", 
				query = "SELECT distinct sb FROM SelecaoBolsa sb LEFT JOIN FETCH sb.membrosBanca"),
				@NamedQuery(
				name = "SelecaoBolsa.findSelecaoBolsaIdComMembros", 
				query = "SELECT sb FROM SelecaoBolsa sb LEFT JOIN FETCH sb.membrosBanca WHERE sb.id = :selecaoBolsaId"),
				@NamedQuery(
				name = "SelecaoBolsa.findSelecaoBolsaIdComAlunos", 
				query = "SELECT DISTINCT sb FROM SelecaoBolsa sb LEFT JOIN FETCH sb.alunosSelecao WHERE sb.id = :selecaoBolsaId"),
				@NamedQuery(
				name = "SelecaoBolsa.findSelecaoComAlunos",
				query = "SELECT DISTINCT sb from SelecaoBolsa as sb LEFT JOIN FETCH sb.alunosSelecao")
			})


@Entity
public class SelecaoBolsa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany(mappedBy = "selecaoBolsa")
	private List<QuestionarioIniciacaoAcademica> questionariosIniciacaoAcademica;

	@OneToMany(mappedBy = "selecaoBolsa")
	private List<QuestionarioAuxilioMoradia> questionariosAuxilioMoradia;
	
	@OneToMany(mappedBy = "selecaoBolsa")
	private List<VisitaDomiciliar> relatoriosVisitaDomiciliar;

	@NotNull(message = "Campo obrigatório")
	@Range(min=1, max=999, message="O número de vagas deve ser maior ou igual a 1")
	private Integer quantidadeVagas;

	@Future(message = "Data de início deve ser maior que a data atual")
	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;

	@ManyToOne
	private Pessoa autor;
	
	@OneToMany(mappedBy = "selecaoBolsa", cascade = {CascadeType.REMOVE, CascadeType.PERSIST} )
	private List<Documento> documentos;

	@NotNull(message="Campo obrigatório")
	@Range(min=1, message="O valor do edital deve ser maior que 0")
	private Integer sequencial;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Future(message = "Data de término deve ser maior que a data atual")
	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTermino;

	

	private String local;

	@NotNull(message = "Campo obrigatório")
	private Integer ano;

	@Size(min = 2, message = "Mínimo 2 caracteres")
	private String comentarios;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Servidor> membrosBanca;

	@ManyToOne
	private Servidor responsavel;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Aluno> alunosSelecao;

	@NotNull(message="Selecione o tipo de bolsa")
	@Enumerated(EnumType.STRING)
	private TipoBolsa tipoBolsa;

	public List<Aluno> getAlunosSelecao() {
		return alunosSelecao;
	}

	public Integer getAno() {
		return ano;
	}

	public Pessoa getAutor() {
		return autor;
	}

	public String getComentarios() {
		return comentarios;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}


	public Integer getId() {
		return id;
	}

	public String getLocal() {
		return local;
	}

	public List<Servidor> getMembrosBanca() {
		return membrosBanca;
	}

	public Integer getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public Servidor getResponsavel() {
		return responsavel;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public Status getStatus() {
		if (status == null) {
			if (dataTermino.getTime() <= System.currentTimeMillis()) {
				return Status.PROC_SELETIVO;
			} else if (dataInicio.getTime() <= System.currentTimeMillis()) {
				return Status.INSC_ABERTA;
			}else {
				return Status.NOVA;
			} 
			
		}
		return status;
	}
	public TipoBolsa getTipoBolsa() {
		return tipoBolsa;
	}

	
	public void setAlunosSelecao(List<Aluno> alunosSeleAlunos) {
		this.alunosSelecao = alunosSeleAlunos;
	}

	public void addAlunosSelecao(Aluno... a) {
		for (int i = 0; i < a.length; i++) {
			this.alunosSelecao.add(a[i]);
		}
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setAutor(Pessoa autor) {
		this.autor = autor;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public void setDataInicio(Date datadeInicio) {
		this.dataInicio = datadeInicio;
	}

	public void setDataTermino(Date datadeTermino) {
		this.dataTermino = datadeTermino;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public void setLocal(String local) {
		this.local = local;
	}

	public void setMembrosBanca(List<Servidor> membrosBanca) {
		this.membrosBanca = membrosBanca;
	}

	public void setQuantidadeVagas(Integer quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTipoBolsa(TipoBolsa tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}
	
	

	public List<QuestionarioIniciacaoAcademica> getQuestionariosIniciacaoAcademica() {
		return questionariosIniciacaoAcademica;
	}

	public void setQuestionariosIniciacaoAcademica(
			List<QuestionarioIniciacaoAcademica> questionariosIniciacaoAcademica) {
		this.questionariosIniciacaoAcademica = questionariosIniciacaoAcademica;
	}

	public List<QuestionarioAuxilioMoradia> getQuestionariosAuxilioMoradia() {
		return questionariosAuxilioMoradia;
	}

	public void setQuestionariosAuxilioMoradia(
			List<QuestionarioAuxilioMoradia> questionariosAuxilioMoradia) {
		this.questionariosAuxilioMoradia = questionariosAuxilioMoradia;
	}

	public List<VisitaDomiciliar> getRelatoriosVisitaDomiciliar() {
		return relatoriosVisitaDomiciliar;
	}

	public void setRelatoriosVisitaDomiciliar(
			List<VisitaDomiciliar> relatoriosVisitaDomiciliar) {
		this.relatoriosVisitaDomiciliar = relatoriosVisitaDomiciliar;
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
		SelecaoBolsa other = (SelecaoBolsa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
