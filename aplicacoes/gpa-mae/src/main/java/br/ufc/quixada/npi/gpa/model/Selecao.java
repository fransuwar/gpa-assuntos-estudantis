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

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.TipoBolsa;

@NamedQueries({
		@NamedQuery(name = "Selecao.findSelecaoBolsaComDocumentos", query = "SELECT sb FROM Selecao sb LEFT JOIN FETCH sb.documentos WHERE sb.id = :selecaoBolsaId "),
		@NamedQuery(name = "Selecao.findSelecaoBolsaComMembros", query = "SELECT distinct sb FROM Selecao sb LEFT JOIN FETCH sb.membrosBanca"),
		@NamedQuery(name = "Selecao.findSelecaoBolsaIdComMembros", query = "SELECT sb FROM Selecao sb LEFT JOIN FETCH sb.membrosBanca WHERE sb.id = :selecaoBolsaId"), })

@Entity
public class Selecao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "Campo obrigatório")
	private Integer ano;
	
	@NotNull(message = "Campo obrigatório")
	@Range(min = 1, message = "O valor do edital deve ser maior que 0")
	private Integer sequencial;
	
	@NotNull(message = "Campo obrigatório")
	@Range(min = 1, max = 999, message = "O número de vagas deve ser maior ou igual a 1")
	private Integer quantidadeVagas;
	
	@Future(message = "Data de início deve ser maior que a data atual")
	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	
	@Future(message = "Data de término deve ser maior que a data atual")
	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTermino;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private TipoBolsa tipoBolsa;
	
	@OneToMany(mappedBy = "selecaoBolsa", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private List<Documento> documentos;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Servidor> membrosBanca;
	
	@ManyToOne
	private Servidor responsavel;
	
	@OneToMany(mappedBy = "selecao")
	private List<Inscricao> inscritos;

	public Integer getAno() {
		return ano;
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
			} else {
				return Status.NOVA;
			}

		}
		return status;
	}

	public TipoBolsa getTipoBolsa() {
		return tipoBolsa;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
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
		Selecao other = (Selecao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Inscricao> getInscritos() {
		return inscritos;
	}

	public void setInscritos(List<Inscricao> inscritos) {
		this.inscritos = inscritos;
	}

	@Override
	public String toString() {
		return "Selecao [id=" + id + ", ano=" + ano + ", sequencial=" + sequencial + ", quantidadeVagas="
				+ quantidadeVagas + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", status=" + status
				+ ", tipoBolsa=" + tipoBolsa + ", documentos=" + documentos + ", membrosBanca=" + membrosBanca
				+ ", responsavel=" + responsavel + ", inscritos=" + inscritos + "]";
	}
	
	
	
}
