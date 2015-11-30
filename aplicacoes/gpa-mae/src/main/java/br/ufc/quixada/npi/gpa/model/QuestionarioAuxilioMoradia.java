package br.ufc.quixada.npi.gpa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@NamedQueries({
		@NamedQuery(name = "AuxMor.findAuxMorById", 
					query = "SELECT DISTINCT am FROM QuestionarioAuxilioMoradia am WHERE am.id = :idInscricao") 
			})
public class QuestionarioAuxilioMoradia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoEnsinoFundamental ensinoFundamental;

	private boolean bolsaEnsinoFundamental;

	private int percentualParticularFundamental;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoEnsinoMedio ensinoMedio;
	
	private boolean bolsaEnsinoMedio;
	
	private int percentualParticularMedio;

	@NotNull(message = "Campo obrigatório")
	private boolean cursinho;
	
	private String nomeCursinho;
	
	@NotNull(message = "Campo obrigatório")
	private boolean bolsistaUfc;
	
	private String descricaoBolsa;
	
	@NotNull(message = "Campo obrigatório")
	private boolean graduacao;
	
	private String descricaoGraduacao;
	
	@NotEmpty(message = "Campo obrigatório")
	private String justificativa;
	
	@Enumerated(EnumType.STRING)
	private MoraCom comQuemMora;
	
	private String comQuemMoraOutros;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nomePai;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nomeMae;
	
	@NotNull(message = "Campo obrigatório")
	private String endereco;
	
	@NotNull(message = "Campo obrigatório")
	private String numero;
	
	@NotNull(message = "Campo obrigatório")
	private String complemento;
	
	@NotNull(message = "Campo obrigatório")
	private String bairro;
	
	@NotNull(message = "Campo obrigatório")
	private String cep;
	
	@NotNull(message = "Campo obrigatório")
	private String cidade;
	
	@NotNull(message = "Campo obrigatório")
	private String estado;
	
	@NotNull(message = "Campo obrigatório")
	private String referencia;
	
	@NotNull(message = "Campo obrigatório")
	private String enderecoOrigem;
	
	@NotNull(message = "Campo obrigatório")
	private Long numeroOrigem;
	
	private String complementoOrigem;
	
	@NotEmpty(message = "Campo obrigatório")
	private String bairroOrigem;
	
	@NotEmpty(message = "Campo obrigatório")
	private String cepOrigem;
	
	@NotEmpty(message = "Campo obrigatório")
	private String cidadeOrigem;
	
	@Enumerated(EnumType.STRING)
	private Estado estadoOrigem;
	
	@NotEmpty(message = "Campo obrigatório")
	private String referenciaOrigem;
	
	@NotEmpty(message = "Campo obrigatório")
	private String telefoneOrigem;
	
	@Enumerated(EnumType.STRING)
	private SituacaoImovel situacaoImovel;
	
	@Enumerated(EnumType.STRING)
	private GrauParentescoImovelRural grauParentescoImovelRural;
	
	private double areaPropriedadeRural;
	
	private String cidadeEstadoImovelRural;
	
	@Enumerated(EnumType.STRING)
	private GrauParentescoVeiculos grauParentescoVeiculos;
	
	private String veiculo;
	
	@Enumerated(EnumType.STRING)
	
	private FinalidadeVeiculo finalidadeVeiculo;
	

	
	@NotEmpty(message = "Campo obrigatório")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "auxiliomoradia_id")
	private List<PessoaFamilia> pessoas;

	public QuestionarioAuxilioMoradia() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PessoaFamilia> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaFamilia> pessoas) {
		this.pessoas = pessoas;
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

	public Long getNumeroOrigem() {
		return numeroOrigem;
	}

	public void setNumeroOrigem(Long numeroOrigem) {
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
		return referenciaOrigem;
	}

	public void setPontoReferenciaOrigem(String pontoReferenciaOrigem) {
		this.referenciaOrigem = pontoReferenciaOrigem;
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

	public SituacaoImovel getSituacaoImovel() {
		return situacaoImovel;
	}

	public void setSituacaoImovel(SituacaoImovel situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}

	public GrauParentescoImovelRural getGrauParentescoImovelRural() {
		return grauParentescoImovelRural;
	}

	public void setGrauParentescoImovelRural(GrauParentescoImovelRural grauParentescoImovelRural) {
		this.grauParentescoImovelRural = grauParentescoImovelRural;
	}

	public double getAreaPropriedadeRural() {
		return areaPropriedadeRural;
	}

	public void setAreaPropriedadeRural(double areaPropriedadeRural) {
		this.areaPropriedadeRural = areaPropriedadeRural;
	}

	public String getCidadePropriedadeRural() {
		return cidadeEstadoImovelRural;
	}

	public void setCidadePropriedadeRural(String cidadePropriedadeRural) {
		this.cidadeEstadoImovelRural = cidadePropriedadeRural;
	}

	public GrauParentescoVeiculos getGrauParentescoVeiculos() {
		return grauParentescoVeiculos;
	}

	public void setGrauParentescoVeiculos(GrauParentescoVeiculos grauParentescoVeiculos) {
		this.grauParentescoVeiculos = grauParentescoVeiculos;
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

	public void setPercentualParticularFundamental(int percentualParticularFundamental) {
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

	public boolean isBolsista() {
		return bolsistaUfc;
	}

	public void setBolsista(boolean bolsista) {
		this.bolsistaUfc = bolsista;
	}

	public boolean isPossuiGraduacao() {
		return graduacao;
	}

	public void setPossuiGraduacao(boolean possuiGraduacao) {
		this.graduacao = possuiGraduacao;
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

	public boolean isBolsaEnsinoFundamental() {
		return bolsaEnsinoFundamental;
	}

	public void setBolsaEnsinoFundamental(boolean bolsaEnsinoFundamental) {
		this.bolsaEnsinoFundamental = bolsaEnsinoFundamental;
	}

	public boolean isBolsaEnsinoMedio() {
		return bolsaEnsinoMedio;
	}

	public void setBolsaEnsinoMedio(boolean bolsaEnsinoMedio) {
		this.bolsaEnsinoMedio = bolsaEnsinoMedio;
	}

	public String getDescricaoBolsa() {
		return descricaoBolsa;
	}

	public void setDescricaoBolsa(String descricaoBolsa) {
		this.descricaoBolsa = descricaoBolsa;
	}

	public MoraCom getComQuemMora() {
		return comQuemMora;
	}

	public void setComQuemMora(MoraCom comQuemMora) {
		this.comQuemMora = comQuemMora;
	}

	public String getComQuemMoraOutros() {
		return comQuemMoraOutros;
	}

	public void setComQuemMoraOutros(String comQuemMoraOutros) {
		this.comQuemMoraOutros = comQuemMoraOutros;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEnderecoDeOrigem() {
		return enderecoOrigem;
	}

	public void setEnderecoDeOrigem(String enderecoDeOrigem) {
		this.enderecoOrigem = enderecoDeOrigem;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public boolean isBolsistaUfc() {
		return bolsistaUfc;
	}

	public void setBolsistaUfc(boolean bolsistaUfc) {
		this.bolsistaUfc = bolsistaUfc;
	}

	public boolean isGraduacao() {
		return graduacao;
	}

	public void setGraduacao(boolean graduacao) {
		this.graduacao = graduacao;
	}

	public String getEnderecoOrigem() {
		return enderecoOrigem;
	}

	public void setEnderecoOrigem(String enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
	}

	public String getReferenciaOrigem() {
		return referenciaOrigem;
	}

	public void setReferenciaOrigem(String referenciaOrigem) {
		this.referenciaOrigem = referenciaOrigem;
	}

	public String getCidadeEstadoImovelRural() {
		return cidadeEstadoImovelRural;
	}

	public void setCidadeEstadoImovelRural(String cidadeEstadoImovelRural) {
		this.cidadeEstadoImovelRural = cidadeEstadoImovelRural;
	}

}
