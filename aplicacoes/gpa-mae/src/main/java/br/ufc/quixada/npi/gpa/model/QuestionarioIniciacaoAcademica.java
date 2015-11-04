package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;

@Entity
@NamedQueries({})
public class QuestionarioIniciacaoAcademica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String telefoneFixo;
	private String telefoneCelular;
	private int anosEstudoPrivado;
	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoMae;
	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoPai;
	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String justificativaPedido;
	@NotNull(message = "Campo Obrigatório")
	private String endereco;
	@NotNull(message = "Campo Obrigatório")
	private Integer numero;
	private String complemento;
	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String bairro;
	@NotEmpty(message = "Campo Obrigatório")
	private String cep;
	@NotNull
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidade;
	@NotEmpty(message = "Campo Obrigatório")
	private String estado;
	private String referencia;
	@NotNull(message = "Campo Obrigatório")
	private String enderecoFamilia;
	@NotNull(message = "Campo Obrigatório")
	private Integer numeroFamilia;
	private String complementoFamilia;
	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String bairroFamilia;
	@NotEmpty(message = "Campo Obrigatório")
	private String cepFamilia;
	@NotNull
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidadeFamilia;
	@NotEmpty(message = "Campo Obrigatório")
	private String estadoFamilia;
	private String referenciaFamilia;
	@OneToOne
	private SituacaoSocioEconomica situacaoSocioEconomica;

	public QuestionarioIniciacaoAcademica() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public int getAnosEstudoPrivado() {
		return anosEstudoPrivado;
	}

	public void setAnosEstudoPrivado(int anosEstudoPrivado) {
		this.anosEstudoPrivado = anosEstudoPrivado;
	}

	public NivelInstrucao getNivelInstrucaoMae() {
		return nivelInstrucaoMae;
	}

	public void setNivelInstrucaoMae(NivelInstrucao nivelInstrucaoMae) {
		this.nivelInstrucaoMae = nivelInstrucaoMae;
	}

	public NivelInstrucao getNivelInstrucaoPai() {
		return nivelInstrucaoPai;
	}

	public void setNivelInstrucaoPai(NivelInstrucao nivelInstrucaoPai) {
		this.nivelInstrucaoPai = nivelInstrucaoPai;
	}

	public String getJustificativaPedido() {
		return justificativaPedido;
	}

	public void setJustificativaPedido(String justificativaPedido) {
		this.justificativaPedido = justificativaPedido;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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

	public String getEstadoFamilia() {
		return estadoFamilia;
	}

	public void setEstadoFamilia(String estadoFamilia) {
		this.estadoFamilia = estadoFamilia;
	}

	public String getEnderecoFamilia() {
		return enderecoFamilia;
	}

	public void setEnderecoFamilia(String enderecoFamilia) {
		this.enderecoFamilia = enderecoFamilia;
	}

	public Integer getNumeroFamilia() {
		return numeroFamilia;
	}

	public void setNumeroFamilia(Integer numeroFamilia) {
		this.numeroFamilia = numeroFamilia;
	}

	public String getComplementoFamilia() {
		return complementoFamilia;
	}

	public void setComplementoFamilia(String complementoFamilia) {
		this.complementoFamilia = complementoFamilia;
	}

	public String getBairroFamilia() {
		return bairroFamilia;
	}

	public void setBairroFamilia(String bairroFamilia) {
		this.bairroFamilia = bairroFamilia;
	}

	public String getCepFamilia() {
		return cepFamilia;
	}

	public void setCepFamilia(String cepFamilia) {
		this.cepFamilia = cepFamilia;
	}

	public String getCidadeFamilia() {
		return cidadeFamilia;
	}

	public void setCidadeFamilia(String cidadeFamilia) {
		this.cidadeFamilia = cidadeFamilia;
	}

	public String getReferenciaFamilia() {
		return referenciaFamilia;
	}

	public void setReferenciaFamilia(String referenciaFamilia) {
		this.referenciaFamilia = referenciaFamilia;
	}

	public SituacaoSocioEconomica getSituacaoSocioEconomica() {
		return situacaoSocioEconomica;
	}

	public void setSituacaoSocioEconomica(SituacaoSocioEconomica situacaoSocioEconomica) {
		this.situacaoSocioEconomica = situacaoSocioEconomica;
	}

}
