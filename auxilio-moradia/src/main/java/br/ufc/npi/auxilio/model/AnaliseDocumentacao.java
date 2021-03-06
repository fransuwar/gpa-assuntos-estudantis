package br.ufc.npi.auxilio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import br.ufc.npi.auxilio.enums.Resultado;

@Entity
public class AnaliseDocumentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	private String cidade;
	
	private String beneficio;
	
	private String cidadeOrigem;
	
	private Double rendaPai;
	
	private Double rendaMae;
	
	private Double rendaOutros;
	
	private Double rendaPerCapita;
	
	private Integer grupoFamiliar;
	
	private Double energia;
	
	@Column(columnDefinition="varchar(1000)")
	private String observacoes;
	
	@OneToOne
	private Inscricao inscricao;

	@ManyToOne
	private Servidor responsavel;
	
	@ManyToMany(mappedBy = "analiseDocumentacao", cascade = CascadeType.REMOVE)
	private List<Documentacao> documentacao;
	
	
	public Double getEnergia() {
		return energia;
	}

	public void setEnergia(Double energia) {
		this.energia = energia;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	
	public AnaliseDocumentacao() {
		this.documentacao = new ArrayList<Documentacao>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public Servidor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public List<Documentacao> getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(List<Documentacao> documentacao) {
		this.documentacao = documentacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Double getRendaPai() {
		return rendaPai;
	}

	public void setRendaPai(Double rendaPai) {
		this.rendaPai = rendaPai;
	}

	public Double getRendaMae() {
		return rendaMae;
	}

	public void setRendaMae(Double rendaMae) {
		this.rendaMae = rendaMae;
	}

	public Double getRendaOutros() {
		return rendaOutros;
	}

	public void setRendaOutros(Double rendaOutros) {
		this.rendaOutros = rendaOutros;
	}

	public Double getRendaPerCapita() {
		return rendaPerCapita;
	}

	public void setRendaPerCapita(Double rendaPerCapita) {
		this.rendaPerCapita = rendaPerCapita;
	}

	public String getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public Integer getGrupoFamiliar() {
		return grupoFamiliar;
	}

	public void setGrupoFamiliar(Integer grupoFamiliar) {
		this.grupoFamiliar = grupoFamiliar;
	}
}