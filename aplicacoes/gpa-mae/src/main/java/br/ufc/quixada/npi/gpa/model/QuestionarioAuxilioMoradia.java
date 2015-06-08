package br.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoImovelRural;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;
import br.ufc.quixada.npi.gpa.enums.MoraCom;
import br.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;

@Entity
@NamedQueries({ @NamedQuery(name = "AuxMor.findAuxMorById", query = "SELECT DISTINCT am FROM QuestionarioAuxilioMoradia am WHERE am.aluno.id = :idAluno") })
public class QuestionarioAuxilioMoradia {

	public QuestionarioAuxilioMoradia() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	private SelecaoBolsa selecaoBolsa;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Aluno aluno;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@NotEmpty(message = "Campo obrigatório")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "auxiliomoradia_id")
	private List<PessoaFamilia> pessoas;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = MoraCom.class, fetch = FetchType.EAGER)
	private List<MoraCom> moraCom;

	@NotEmpty(message = "Campo obrigatório")
	private String nomeMae;

	@NotEmpty(message = "Campo obrigatório")
	private String nomePai;

	@NotEmpty(message = "Campo obrigatório")
	private String ruaSedeCurso;

	@NotNull(message = "Campo obrigatório")
	private Integer numeroSedeCurso;

	@NotEmpty(message = "Campo obrigatório")
	private String bairroSedeCurso;

	@NotEmpty(message = "Campo obrigatório")
	private String ruaOrigem;

	@NotNull(message = "Campo obrigatório")
	private Integer numeroOrigem;

	@NotEmpty(message = "Campo obrigatório")
	private String bairroOrigem;

	private String complementoOrigem;

	@NotEmpty(message = "Campo obrigatório")
	private String cidadeOrigem;

	@NotEmpty(message = "Campo obrigatório")
	private String cepOrigem;

	@NotEmpty(message = "Campo obrigatório")
	private String pontoReferenciaOrigem;

	@NotEmpty(message = "Campo obrigatório")
	private String telefoneOrigem;

	@Enumerated(EnumType.STRING)
	private Estado estadoOrigem;

	private Date dataInscricao;

	@Enumerated(EnumType.STRING)
	private SituacaoImovel situacaoImovel;

	private double valorMensalFinanciamento;

	@Enumerated(EnumType.STRING)
	private GrauParentescoImovelRural grauParentescoImovelRural;

	private double areaPropriedadeRural;

	private String cidadePropriedadeRural;

	@Enumerated(EnumType.STRING)
	private Estado estadoPropriedadeRural;

	@Enumerated(EnumType.STRING)
	private GrauParentescoVeiculos grauParentescoVeiculos;

	private String tipoVeiculo;

	private String marcaVeiculo;

	private String modeloVeiculo;

	private String anoVeiculo;

	@Enumerated(EnumType.STRING)
	private FinalidadeVeiculo finalidadeVeiculo;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoEnsinoFundamental ensinoFundamental;

	private Integer percentualParticularFundamental;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoEnsinoMedio ensinoMedio;

	private Integer percentualParticularMedio;

	@NotNull(message = "Campo obrigatório")
	private boolean cursinho;

	private String nomeCursinho;

	@NotNull(message = "Campo obrigatório")
	private double rendaMediaFamilia;

	@NotNull(message = "Campo obrigatório")
	private double rendaMediaPessoa;

	@NotNull(message = "Campo obrigatório")
	private boolean bolsista;

	private String tipoBolsa;

	@NotNull(message = "Campo obrigatório")
	private boolean possuiGraduacao;

	private String descricaoGraduacao;

	@NotEmpty(message = "Campo obrigatório")
	private String justificativa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SelecaoBolsa getSelecaoBolsa() {
		return selecaoBolsa;
	}

	public void setSelecaoBolsa(SelecaoBolsa selecaoBolsa) {
		this.selecaoBolsa = selecaoBolsa;
	}

	public List<PessoaFamilia> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaFamilia> pessoas) {
		this.pessoas = pessoas;
	}

	public List<MoraCom> getMoraCom() {
		return moraCom;
	}

	public void setMoraCom(List<MoraCom> moraCom) {
		this.moraCom = moraCom;
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

	public String getRuaSedeCurso() {
		return ruaSedeCurso;
	}

	public void setRuaSedeCurso(String ruaSedeCurso) {
		this.ruaSedeCurso = ruaSedeCurso;
	}

	public Integer getNumeroSedeCurso() {
		return numeroSedeCurso;
	}

	public void setNumeroSedeCurso(Integer numeroSedeCurso) {
		this.numeroSedeCurso = numeroSedeCurso;
	}

	public String getBairroSedeCurso() {
		return bairroSedeCurso;
	}

	public void setBairroSedeCurso(String bairroSedeCurso) {
		this.bairroSedeCurso = bairroSedeCurso;
	}

	public String getRuaOrigem() {
		return ruaOrigem;
	}

	public void setRuaOrigem(String ruaOrigem) {
		this.ruaOrigem = ruaOrigem;
	}

	public Integer getNumeroOrigem() {
		return numeroOrigem;
	}

	public void setNumeroOrigem(Integer numeroOrigem) {
		this.numeroOrigem = numeroOrigem;
	}

	public String getBairroOrigem() {
		return bairroOrigem;
	}

	public void setBairroOrigem(String bairroOrigem) {
		this.bairroOrigem = bairroOrigem;
	}

	public String getComplementoOrigem() {
		return complementoOrigem;
	}

	public void setComplementoOrigem(String complementoOrigem) {
		this.complementoOrigem = complementoOrigem;
	}

	public String getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getPontoReferenciaOrigem() {
		return pontoReferenciaOrigem;
	}

	public void setPontoReferenciaOrigem(String pontoReferenciaOrigem) {
		this.pontoReferenciaOrigem = pontoReferenciaOrigem;
	}

	public String getTelefoneOrigem() {
		return telefoneOrigem;
	}

	public void setTelefoneOrigem(String telefoneOrigem) {
		this.telefoneOrigem = telefoneOrigem;
	}

	public Estado getEstadoOrigem() {
		return estadoOrigem;
	}

	public void setEstadoOrigem(Estado estadoOrigem) {
		this.estadoOrigem = estadoOrigem;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public SituacaoImovel getSituacaoImovel() {
		return situacaoImovel;
	}

	public void setSituacaoImovel(SituacaoImovel situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}

	public double getValorMensalFinanciamento() {
		return valorMensalFinanciamento;
	}

	public void setValorMensalFinanciamento(double valorMensalFinanciamento) {
		this.valorMensalFinanciamento = valorMensalFinanciamento;
	}

	public GrauParentescoImovelRural getGrauParentescoImovelRural() {
		return grauParentescoImovelRural;
	}

	public void setGrauParentescoImovelRural(
			GrauParentescoImovelRural grauParentescoImovelRural) {
		this.grauParentescoImovelRural = grauParentescoImovelRural;
	}

	public double getAreaPropriedadeRural() {
		return areaPropriedadeRural;
	}

	public void setAreaPropriedadeRural(double areaPropriedadeRural) {
		this.areaPropriedadeRural = areaPropriedadeRural;
	}

	public String getCidadePropriedadeRural() {
		return cidadePropriedadeRural;
	}

	public void setCidadePropriedadeRural(String cidadePropriedadeRural) {
		this.cidadePropriedadeRural = cidadePropriedadeRural;
	}

	public Estado getEstadoPropriedadeRural() {
		return estadoPropriedadeRural;
	}

	public void setEstadoPropriedadeRural(Estado estadoPropriedadeRural) {
		this.estadoPropriedadeRural = estadoPropriedadeRural;
	}

	public GrauParentescoVeiculos getGrauParentescoVeiculos() {
		return grauParentescoVeiculos;
	}

	public void setGrauParentescoVeiculos(
			GrauParentescoVeiculos grauParentescoVeiculos) {
		this.grauParentescoVeiculos = grauParentescoVeiculos;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getMarcaVeiculo() {
		return marcaVeiculo;
	}

	public void setMarcaVeiculo(String marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;
	}

	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	public String getAnoVeiculo() {
		return anoVeiculo;
	}

	public void setAnoVeiculo(String anoVeiculo) {
		this.anoVeiculo = anoVeiculo;
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

	public Integer getPercentualParticularFundamental() {
		return percentualParticularFundamental;
	}

	public void setPercentualParticularFundamental(
			Integer percentualParticularFundamental) {
		this.percentualParticularFundamental = percentualParticularFundamental;
	}

	public TipoEnsinoMedio getEnsinoMedio() {
		return ensinoMedio;
	}

	public void setEnsinoMedio(TipoEnsinoMedio ensinoMedio) {
		this.ensinoMedio = ensinoMedio;
	}

	public Integer getPercentualParticularMedio() {
		return percentualParticularMedio;
	}

	public void setPercentualParticularMedio(Integer percentualParticularMedio) {
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
		return "QuestionarioAuxilioMoradia [id=" + id + "]";
	}

}
