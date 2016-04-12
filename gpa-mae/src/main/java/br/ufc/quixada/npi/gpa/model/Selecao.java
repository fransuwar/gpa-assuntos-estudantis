package br.ufc.quixada.npi.gpa.model;

import java.util.ArrayList;
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

import br.ufc.quixada.npi.gpa.enums.TipoSelecao;

@NamedQueries({
		@NamedQuery(name = "Selecao.findSelecoesComMembros",	query = "SELECT distinct sb FROM Selecao sb LEFT JOIN FETCH sb.membrosComissao")})


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

	@Range(min = 1, max = 999, message = "O número de vagas deve ser maior ou igual a 1")
	private Integer quantidadeVagas;

	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;

	@Future(message = "Data de término deve ser maior que a data atual")
	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTermino;

	@Enumerated(EnumType.STRING)
	private TipoSelecao tipoSelecao;

	@OneToMany(mappedBy = "selecao", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private List<Documento> documentos;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Servidor> membrosComissao;

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

	public List<Servidor> getMembrosComissao() {
		return membrosComissao;
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


	public TipoSelecao getTipoSelecao() {
		return tipoSelecao;
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

	public void setMembrosComissao(List<Servidor> membrosComissao) {
		this.membrosComissao = membrosComissao;
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


	public void setTipoSelecao(TipoSelecao tipoSelecao) {
		this.tipoSelecao = tipoSelecao;
	}

	public List<Inscricao> getInscritos() {
		return inscritos;
	}
	

	public void setInscritos(List<Inscricao> inscritos) {
		this.inscritos = inscritos;
	}
	public void addCoordenador (Servidor coordenador){
		if(this.membrosComissao == null){
			membrosComissao = new ArrayList<Servidor>();
		}
		this.membrosComissao.add(coordenador);
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

	@Override
	public String toString() {
		return "Selecao [id=" + id + ", ano=" + ano + ", sequencial=" + sequencial + ", quantidadeVagas="
				+ quantidadeVagas + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino				
				+ ", tipoSelecao=" + tipoSelecao + ", documentos=" + documentos + ", membrosComissao=" + membrosComissao
				+ ", responsavel=" + responsavel + ", inscritos=" + inscritos + "]";
	}

	public List<Aluno> getAlunosSelecao() {
		// TODO Auto-generated method stub
		return null;
	}

}
