package br.ufc.quixada.npi.gpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Selecao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "Campo obrigatório")
	private Integer ano;

	private Integer sequencial;

	private Integer quantidadeVagas;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTermino;
	
	@ManyToOne
	private Servidor responsavel;

	@OneToMany
	private List<Documento> documentos;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "comissao")
	private List<Servidor> comissao;

	@OneToMany(mappedBy = "selecao")
	private List<Inscricao> inscricoes;
	
	@ManyToMany
	private List<TipoDocumento> tiposDeDocumento;

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
		if(documentos == null){
			documentos = new ArrayList<>();
		}
		return documentos;
	}

	public Integer getId() {
		return id;
	}

	public List<Servidor> getComissao() {
		return comissao;
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

	public void setQuantidadeVagas(Integer quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}
	public void addMembroComissao (Servidor servidor){
		if(this.comissao == null){
			comissao = new ArrayList<Servidor>();
		}
		if (servidor != null && !this.comissao.contains(servidor)) {
			this.comissao.add(servidor);
		}
	}
	
	public List<TipoDocumento> getTiposDeDocumento() {
		return tiposDeDocumento;
	}
	
	public void addTipoDocumento(TipoDocumento tipoDocumento) {
		if (this.tiposDeDocumento == null) {
			this.tiposDeDocumento = new ArrayList<TipoDocumento>();
		}
		if (tipoDocumento != null && !this.tiposDeDocumento.contains(tipoDocumento)) {
			this.tiposDeDocumento.add(tipoDocumento);
		}
	}
	
	public void removeAllTipoDocumento() {
		this.tiposDeDocumento = new ArrayList<TipoDocumento>();
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
	

	public List<Inscricao> getAlunosSelecionadosVisita(){
		List<Inscricao> listaInscritos = new ArrayList<Inscricao>();
		for(Inscricao inscricao:this.inscricoes){
			if(inscricao.isRealizarVisita()){
				listaInscritos.add(inscricao);
			}
		}
		return listaInscritos;
	}
	
	public List<Inscricao> getAlunosNaoSelecionadosVisita(){
		List<Inscricao> listaInscritos = new ArrayList<Inscricao>();
		for(Inscricao inscricao:this.inscricoes){
			if(!inscricao.isRealizarVisita()){
				listaInscritos.add(inscricao);
			}
		}
		return listaInscritos;
	}
	
	public Map<String, Integer> getCidadesVisita(){
		Map<String, Integer> mapaCidades = new TreeMap<String, Integer>();
		for(Inscricao inscricao:inscricoes){
			if(inscricao.isRealizarVisita()){
				String cidade = inscricao.getQuestionarioAuxilioMoradia().getCidadeOrigem();
				if(mapaCidades.containsKey(cidade)){
					int numAlunos = mapaCidades.get(cidade);
					mapaCidades.put(cidade, numAlunos+1);
				}else{
					mapaCidades.put(cidade, 1);
				}
			}
		}
		return mapaCidades;
	}

}
