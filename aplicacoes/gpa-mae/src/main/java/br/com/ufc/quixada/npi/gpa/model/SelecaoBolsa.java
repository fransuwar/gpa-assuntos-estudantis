package br.com.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SelecaoBolsa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;


	@OneToMany(mappedBy="selecaoBolsa")
	private List<QuestionarioIniciacaoAcademica> questionariosIniciacaoAcademica;
	
	@OneToMany(mappedBy="selecaoBolsa")
	private List<QuestionarioAuxilioMoradia> questionariosAuxilioMoradia;
	
	@Min(value = 1, message = "Número de bolsas deve ser maior que 0")
	private int quantidadeVagas;
	
	@Future
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
		
	@ManyToOne
	private  Pessoa autor;
		
	@OneToMany(mappedBy = "selecaoBolsa", cascade = CascadeType.REMOVE)
	private List<Documento> documentos;
	
	@NotNull
	private Integer sequencial;

	
	@Enumerated(EnumType.STRING)
	private Status status;
	public enum Status {
		NOVA, SUBMETIDO, INSCRICAO_ABERTA, PROCESSO_SELETIVO, FINALIZADA, CANCELADA
	}
	@Future
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTermino;
	
	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	@NotNull
	private String duracao;

	private String local;
	
	@NotNull
	private Integer ano;
	
	@Lob
	@Size(min = 2, message = "Mínimo 2 caracteres")
	private String comentarios;

	private String edital;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "servidor_selecaobolsa")
	private List<Servidor> membrosBanca;

	@ManyToOne
	private Servidor responsavel;

	@ManyToMany
	private List<Aluno> alunosSelecao;

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Pessoa getAutor() {
		return autor;
	}

	public void setAutor(Pessoa autor) {
		this.autor = autor;
	}

	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public void setQuantidadeVagas(int QuantidadeVagas) {
		this.quantidadeVagas = QuantidadeVagas;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date datadeInicio) {
		this.dataInicio = datadeInicio;
	}

	public String getEdital() {
		return edital;
	}

	public void setEdital(String Edital) {
		this.edital = Edital;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date datadeTermino) {
		this.dataTermino = datadeTermino;
	}

	public List<Servidor> getMembrosBanca() {
		return membrosBanca;
	}

	public void setMembrosBanca(List<Servidor> membrosBanca) {
		this.membrosBanca = membrosBanca;
	}

	public Servidor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
	}

	public List<Aluno> getAlunosSelecao() {
		return alunosSelecao;
	}

	public void setAlunosSelecao(List<Aluno> alunosSelecao) {
		this.alunosSelecao = alunosSelecao;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum TipoBolsa {
		INICIACAO_ACADEMICA("Iniciação Acadêmica"), AUXILIO_MORADIA(
				"Auxilio Moradia");
		private String tipo;

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		TipoBolsa(String tipo) {
			this.tipo = tipo;
		}
	}

	@Enumerated(EnumType.STRING)
	private TipoBolsa tipoBolsa;

	public TipoBolsa getTipoBolsa() {
		return tipoBolsa;
	}

	public void setTipoBolsa(TipoBolsa tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	

}
