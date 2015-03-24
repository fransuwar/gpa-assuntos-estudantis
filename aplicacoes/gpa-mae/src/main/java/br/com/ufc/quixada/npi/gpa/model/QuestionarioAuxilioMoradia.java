package br.com.ufc.quixada.npi.gpa.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.ufc.quixada.npi.gpa.enums.Estado;
import br.com.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.com.ufc.quixada.npi.gpa.enums.GrauParentescoImovelRural;
import br.com.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;
import br.com.ufc.quixada.npi.gpa.enums.MoraCom;
import br.com.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.com.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.com.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;

@Entity
public class QuestionarioAuxilioMoradia {

	public QuestionarioAuxilioMoradia(){}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Integer id;
	
	@ManyToOne
	private SelecaoBolsa selecaoBolsa;

	@Column(nullable = false)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name= "auxiliomoradia_id")
	private List<PessoaFamilia> pessoas;
	
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = MoraCom.class)
	private List<MoraCom> moraCom;
	
	@Column(nullable = false)
	private String enderecoSedeCurso;
	
	@Column(nullable = false)
	private String nomeMae;
	
	@Column(nullable = false)
	private String nomePai;
	
	@Column(nullable = false)
	private String rua;
	
	@Column(nullable = false)
	private int numeroCasa;
	
	@Column(nullable = false)
	private String bairro;
	
	private String complemento;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String pontoReferencia;
	
	@Column(nullable = false)
	private String telefone;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Enumerated(EnumType.STRING)
	private SituacaoImovel situacaoImovel;
	
	private String valorMensalFinanciamento;
	
	private String propriedadeRural;

	@Enumerated(EnumType.STRING)
	private GrauParentescoImovelRural grauParentescoImovelRural;
	
	private double areaPropriedade;
	
	private String cidadeEstado;
	
	private String veiculos;

	@Enumerated(EnumType.STRING)
	private GrauParentescoVeiculos grauParentescoVeiculos;

	private String tipo;
	
	private String marca;
	
	private String modelo;
	
	private String ano;

	@Enumerated(EnumType.STRING)
	private FinalidadeVeiculo finalidadeVeiculo;

	@Enumerated(EnumType.STRING)
	private TipoEnsinoFundamental ensinoFundamental;
	
	private int percentualParticularFundamental;

	@Enumerated(EnumType.STRING)
	private TipoEnsinoMedio ensinoMedio;
	
	private int percentualParticularMedio;

	@Column(nullable = false)
	private boolean cursinho;
	
	private String nomeCursinho;

	@Column(nullable = false)
	private double rendaMediaFamilia;
	
	@Column(nullable = false)
	private double rendaMediaPessoa;
	
	@Column(nullable = false)
	private boolean bolsista;
	
	private String tipoBolsa;
	
	@Column(nullable = false)
	private boolean possuiGraduacao;
	
	private String descricaoGraduacao;
	
	@Column(nullable = false)
	private String justificativa;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public List<MoraCom> getMoraCom() {
		return moraCom;
	}
	public void setMoraCom(List<MoraCom> moraCom) {
		this.moraCom = moraCom;
	}
	public String getEnderecoSedeCurso() {
		return enderecoSedeCurso;
	}
	public void setEnderecoSedeCurso(String enderecoSedeCurso) {
		this.enderecoSedeCurso = enderecoSedeCurso;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumeroCasa() {
		return numeroCasa;
	}
	public void setNumeroCasa(int numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public SituacaoImovel getSituacaoImovel() {
		return situacaoImovel;
	}
	public void setSituacaoImovel(SituacaoImovel situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}
	public String getValorMensalFinanciamento() {
		return valorMensalFinanciamento;
	}
	public void setValorMensalFinanciamento(String valorMensalFinanciamento) {
		this.valorMensalFinanciamento = valorMensalFinanciamento;
	}
	public String getPropriedadeRural() {
		return propriedadeRural;
	}
	public void setPropriedadeRural(String propriedadeRural) {
		this.propriedadeRural = propriedadeRural;
	}
	public GrauParentescoImovelRural getGrauParentescoImovelRural() {
		return grauParentescoImovelRural;
	}
	public void setGrauParentescoImovelRural(
			GrauParentescoImovelRural grauParentescoImovelRural) {
		this.grauParentescoImovelRural = grauParentescoImovelRural;
	}
	public double getAreaPropriedade() {
		return areaPropriedade;
	}
	public void setAreaPropriedade(double areaPropriedade) {
		this.areaPropriedade = areaPropriedade;
	}
	public String getCidadeEstado() {
		return cidadeEstado;
	}
	public void setCidadeEstado(String cidadeEstado) {
		this.cidadeEstado = cidadeEstado;
	}
	public String getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(String veiculos) {
		this.veiculos = veiculos;
	}
	public GrauParentescoVeiculos getGrauParentescoVeiculos() {
		return grauParentescoVeiculos;
	}
	public void setGrauParentescoVeiculos(
			GrauParentescoVeiculos grauParentescoVeiculos) {
		this.grauParentescoVeiculos = grauParentescoVeiculos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public FinalidadeVeiculo getFinalidadeVeiculo() {
		return finalidadeVeiculo;
	}
	public void setFinalidadeVeiculo(FinalidadeVeiculo finalidadeVeiculo) {
		this.finalidadeVeiculo = finalidadeVeiculo;
	}
	public TipoEnsinoFundamental getEnsinoFundamental() {
		return ensinoFundamental;
	}
	public void setEnsinoFundamental(TipoEnsinoFundamental ensinoFundamental) {
		this.ensinoFundamental = ensinoFundamental;
	}
	public int getPercentualParticularFundamental() {
		return percentualParticularFundamental;
	}
	public void setPercentualParticularFundamental(
			int percentualParticularFundamental) {
		this.percentualParticularFundamental = percentualParticularFundamental;
	}
	public TipoEnsinoMedio getEnsinoMedio() {
		return ensinoMedio;
	}
	public void setEnsinoMedio(TipoEnsinoMedio ensinoMedio) {
		this.ensinoMedio = ensinoMedio;
	}
	public int getPercentualParticularMedio() {
		return percentualParticularMedio;
	}
	public void setPercentualParticularMedio(int percentualParticularMedio) {
		this.percentualParticularMedio = percentualParticularMedio;
	}
	public boolean isCursinho() {
		return cursinho;
	}
	public void setCursinho(boolean cursinho) {
		this.cursinho = cursinho;
	}
	public String getNomeCursinho() {
		return nomeCursinho;
	}
	public void setNomeCursinho(String nomeCursinho) {
		this.nomeCursinho = nomeCursinho;
	}
	public List<PessoaFamilia> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaFamilia> pessoas) {
		this.pessoas = pessoas;
	}
	public double getRendaMediaFamilia() {
		return rendaMediaFamilia;
	}
	public void setRendaMediaFamilia(double rendaMediaFamilia) {
		this.rendaMediaFamilia = rendaMediaFamilia;
	}
	public double getRendaMediaPessoa() {
		return rendaMediaPessoa;
	}
	public void setRendaMediaPessoa(double rendaMediaPessoa) {
		this.rendaMediaPessoa = rendaMediaPessoa;
	}
	public boolean isBolsista() {
		return bolsista;
	}
	public void setBolsista(boolean bolsista) {
		this.bolsista = bolsista;
	}
	public String getTipoBolsa() {
		return tipoBolsa;
	}
	public void setTipoBolsa(String tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}
	public boolean isPossuiGraduacao() {
		return possuiGraduacao;
	}
	public void setPossuiGraduacao(boolean possuiGraduacao) {
		this.possuiGraduacao = possuiGraduacao;
	}
	public String getDescricaoGraduacao() {
		return descricaoGraduacao;
	}
	public void setDescricaoGraduacao(String descricaoGraduacao) {
		this.descricaoGraduacao = descricaoGraduacao;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	@Override
	public String toString() {
		return "QuestionarioAuxilioMoradia [id=" + id + ", selecaoBolsa="
				+ selecaoBolsa + ", pessoas=" + pessoas + ", moraCom="
				+ moraCom + ", enderecoSedeCurso=" + enderecoSedeCurso
				+ ", nomeMae=" + nomeMae + ", nomePai=" + nomePai + ", rua="
				+ rua + ", numeroCasa=" + numeroCasa + ", bairro=" + bairro
				+ ", complemento=" + complemento + ", cidade=" + cidade
				+ ", cep=" + cep + ", pontoReferencia=" + pontoReferencia
				+ ", telefone=" + telefone + ", estado=" + estado
				+ ", situacaoImovel=" + situacaoImovel
				+ ", valorMensalFinanciamento=" + valorMensalFinanciamento
				+ ", propriedadeRural=" + propriedadeRural
				+ ", grauParentescoImovelRural=" + grauParentescoImovelRural
				+ ", areaPropriedade=" + areaPropriedade + ", cidadeEstado="
				+ cidadeEstado + ", veiculos=" + veiculos
				+ ", grauParentescoVeiculos=" + grauParentescoVeiculos
				+ ", tipo=" + tipo + ", marca=" + marca + ", modelo=" + modelo
				+ ", ano=" + ano + ", finalidadeVeiculo=" + finalidadeVeiculo
				+ ", ensinoFundamental=" + ensinoFundamental
				+ ", percentualParticularFundamental="
				+ percentualParticularFundamental + ", ensinoMedio="
				+ ensinoMedio + ", percentualParticularMedio="
				+ percentualParticularMedio + ", cursinho=" + cursinho
				+ ", nomeCursinho=" + nomeCursinho + ", rendaMediaFamilia="
				+ rendaMediaFamilia + ", rendaMediaPessoa=" + rendaMediaPessoa
				+ ", bolsista=" + bolsista + ", tipoBolsa=" + tipoBolsa
				+ ", possuiGraduacao=" + possuiGraduacao
				+ ", descricaoGraduacao=" + descricaoGraduacao
				+ ", justificativa=" + justificativa + "]";
	}
}
