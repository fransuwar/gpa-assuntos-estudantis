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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import br.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.ufc.quixada.npi.gpa.enums.TipoEnsino;

@Entity
@NamedQueries({
		@NamedQuery(name = "AuxMor.findAuxMorById", query = "SELECT DISTINCT am FROM QuestionarioAuxilioMoradia am WHERE am.id = :idQuest") })

public class QuestionarioAuxilioMoradia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private TipoEnsino ensinoFundamental;

	private int percentualParticularFundamental;

	@Enumerated(EnumType.STRING)
	private TipoEnsino ensinoMedio;

	private int percentualParticularMedio;

	private boolean cursinho;

	private String nomeCursinho;

	@NotNull
	private boolean bolsistaUfc;

	private String descricaoBolsa;

	@NotNull
	private boolean graduacao;

	private String descricaoGraduacao;

	private String justificativa;

	private String comQuemMoraOutros;

	private String nomePai;

	private String nomeMae;
	
	private String endereco;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private String cep;
	
	private String cidade;
	
	private String estado;
	
	private String referencia;
	
	private String enderecoOrigem;

	private Long numeroOrigem;

	private String complementoOrigem;

	private String bairroOrigem;

	private String cepOrigem;

	private String cidadeOrigem;

	private String estadoOrigem;

	private String referenciaOrigem;

	private String telefoneOrigem;

	@Enumerated(EnumType.STRING)
	private SituacaoImovel situacaoImovel;
	
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] foto;
	
	private double valorMensalFinanciamento;
	

	@OneToMany(cascade = CascadeType.ALL)
	private List<PropriedadeRural> propRural;


	@OneToMany(cascade = CascadeType.ALL)
	private List<BemMovel> bemMovel;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable( name = "inscricao_pessoa_familia", 
			   joinColumns = @JoinColumn(name = "questionario_id"), inverseJoinColumns = @JoinColumn(name = "pessoa_familia_id"))
	private List<PessoaFamilia> pessoas;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable( name = "inscricao_pessoa_familia_editado", 
	   joinColumns = @JoinColumn(name = "questionario_id"), inverseJoinColumns = @JoinColumn(name = "pessoa_familia_id"))
	private List<PessoaFamilia> pessoasEntrevista;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<ComQuemMora> comQuemMora;
	
	
	public double getValorMensalFinanciamento() {
		return valorMensalFinanciamento;
	}

	public void setValorMensalFinanciamento(double valorMensalFinanciamento) {
		this.valorMensalFinanciamento = valorMensalFinanciamento;
	}
	
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

	public String getEstadoOrigem() {
		return estadoOrigem;
	}

	public void setEstadoOrigem(String estadoOrigem) {
		this.estadoOrigem = estadoOrigem;
	}

	public SituacaoImovel getSituacaoImovel() {
		return situacaoImovel;
	}

	public void setSituacaoImovel(SituacaoImovel situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}
	public TipoEnsino getEnsinoFundamental() {
		return ensinoFundamental;
	}
	public void setEnsinoFundamental(TipoEnsino ensinoFundamental) {
		this.ensinoFundamental = ensinoFundamental;
	}

	public int getPercentualParticularFundamental() {
		return percentualParticularFundamental;
	}

	public void setPercentualParticularFundamental(int percentualParticularFundamental) {
		this.percentualParticularFundamental = percentualParticularFundamental;
	}

	public TipoEnsino getEnsinoMedio() {
		return ensinoMedio;
	}

	public void setEnsinoMedio(TipoEnsino ensinoMedio) {
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

	public List<PropriedadeRural> getPropRural() {
		return propRural;
	}

	public void setPropRural(List<PropriedadeRural> propRural) {
		this.propRural = propRural;
	}

	public List<BemMovel> getBemMovel() {
		return bemMovel;
	}

	public void setBemMovel(List<BemMovel> bemMovel) {
		this.bemMovel = bemMovel;
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

	public String getDescricaoBolsa() {
		return descricaoBolsa;
	}

	public void setDescricaoBolsa(String descricaoBolsa) {
		this.descricaoBolsa = descricaoBolsa;
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


	
	@Override
	public String toString() {
		return "QuestionarioAuxilioMoradia [id=" + id + ", justificativa=" + justificativa + "]";
	}
	
	public List<ComQuemMora> getComQuemMora() {
		return comQuemMora;
	}

	public void setComQuemMora(List<ComQuemMora> comQuemMora) {
		this.comQuemMora = comQuemMora;
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
		QuestionarioAuxilioMoradia other = (QuestionarioAuxilioMoradia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<PessoaFamilia> getPessoasEntrevista() {
		return pessoasEntrevista;
	}

	public void setPessoasEntrevista(List<PessoaFamilia> pessoasEntrevista) {
		this.pessoasEntrevista = pessoasEntrevista;
	}
	
	
	
}
