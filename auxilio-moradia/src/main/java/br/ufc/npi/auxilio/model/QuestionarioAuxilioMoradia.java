package br.ufc.npi.auxilio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import br.ufc.npi.auxilio.enums.Estado;
import br.ufc.npi.auxilio.enums.Moradores;
import br.ufc.npi.auxilio.enums.MoradoresOrigem;
import br.ufc.npi.auxilio.enums.ServicosProReitoria;
import br.ufc.npi.auxilio.enums.SituacaoImovel;
import br.ufc.npi.auxilio.enums.TipoEnsino;
import br.ufc.npi.auxilio.enums.Trajeto;

@Entity
public class QuestionarioAuxilioMoradia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// Dados Bancários
	private String banco;

	private String agencia;

	private String contaCorrente;
	
	// Núcleo Familiar
	private String nomePai;

	private String nomeMae;

	// Endereço origem
	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name = "moradores")
	private List<MoradoresOrigem> moradoresOrigem;

	private String outroMoradorOrigem;

	private String enderecoOrigem;

	private String numeroOrigem;

	private String complementoOrigem;

	private String bairroOrigem;

	private String cepOrigem;

	private String cidadeOrigem;

	@Enumerated(EnumType.STRING)
	private Estado estadoOrigem;

	private String referenciaOrigem;

	// Endereço de origem - outras informações
	@Enumerated(EnumType.STRING)
	private SituacaoImovel situacaoImovel;

	private Double financiamento;

	private Integer quantidadeBemMovel;

	private String descricaoBemMovel;

	// Endereço atual
	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name = "moradores")
	private List<Moradores> moradores;

	private String outroMorador;

	private String endereco;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String cidade;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	private String referencia;
	
	// Histórico escolar
	@Enumerated(EnumType.STRING)
	private TipoEnsino ensinoMedio;

	private boolean bolsistaEnsinoMedio;

	private Integer percentualEnsinoMedio;

	private Integer quantidadeParticipacaoAuxilio;

	private String bolsaAtual;

	private String outraGraduacao;

	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name = "servicos_pro_reitoria")
	private List<ServicosProReitoria> servicos;

	private String outroServico;

	// Trajeto até a universidade
	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name = "trajetos")
	private List<Trajeto> trajetos;

	private String outroTrajeto;

	private Double valorMensalTransporte;

	// Em KM
	private Integer distancia;

	// Em horas
	private Integer tempoGasto;

	// Com relação à saúde
	private boolean medicamento;

	private String doencaMedicamento;

	private boolean deficiencia;

	private String nomeDeficiencia;

	private boolean doencaGrave;

	private String membroDoencaGrave;

	private boolean membroDeficiencia;

	private String nomeMembroDeficiencia;

	private boolean assistenciaMedica;

	private Double valorAssistenciaMedica;

	private boolean despesaMedicamento;

	private String descricaoDespesaMedicamento;

	private boolean beneficio;

	private String descricaoBeneficio;

	// Outras informações
	@Column(columnDefinition="TEXT")
	private String justificativa;

	@Type(type="org.hibernate.type.BinaryType")
	private byte[] foto;
	
	@OneToMany(mappedBy = "questionario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PessoaFamilia> grupoFamiliar;
	
	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public Double getFinanciamento() {
		return financiamento;
	}

	public void setFinanciamento(Double financiamento) {
		this.financiamento = financiamento;
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

	public List<PessoaFamilia> getGrupoFamiliar() {
		return grupoFamiliar == null ? new ArrayList<PessoaFamilia>() : grupoFamiliar;
	}

	public void setGrupoFamiliar(List<PessoaFamilia> grupoFamiliar) {
		this.grupoFamiliar = grupoFamiliar;
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

	public String getNumeroOrigem() {
		return numeroOrigem;
	}

	public String getOutroMorador() {
		return outroMorador;
	}

	public void setOutroMorador(String outroMorador) {
		this.outroMorador = outroMorador;
	}

	public void setNumeroOrigem(String numeroOrigem) {
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

	public TipoEnsino getEnsinoMedio() {
		return ensinoMedio;
	}

	public void setEnsinoMedio(TipoEnsino ensinoMedio) {
		this.ensinoMedio = ensinoMedio;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
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

	public boolean isBolsistaEnsinoMedio() {
		return bolsistaEnsinoMedio;
	}

	public void setBolsistaEnsinoMedio(boolean bolsistaEnsinoMedio) {
		this.bolsistaEnsinoMedio = bolsistaEnsinoMedio;
	}

	public Integer getPercentualEnsinoMedio() {
		return percentualEnsinoMedio;
	}

	public void setPercentualEnsinoMedio(Integer percentualEnsinoMedio) {
		this.percentualEnsinoMedio = percentualEnsinoMedio;
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
	
	public List<Moradores> getMoradores() {
		return moradores == null ? new ArrayList<Moradores>() : moradores;
	}

	public void setMoradores(List<Moradores> moradores) {
		this.moradores = moradores;
	}

	public List<MoradoresOrigem> getMoradoresOrigem() {
		return moradoresOrigem == null ? new ArrayList<MoradoresOrigem>() : moradoresOrigem;
	}

	public void setMoradoresOrigem(List<MoradoresOrigem> moradoresOrigem) {
		this.moradoresOrigem = moradoresOrigem;
	}

	public Integer getQuantidadeBemMovel() {
		return quantidadeBemMovel;
	}

	public void setQuantidadeBemMovel(Integer quantidadeBemMovel) {
		this.quantidadeBemMovel = quantidadeBemMovel;
	}

	public String getDescricaoBemMovel() {
		return descricaoBemMovel;
	}

	public void setDescricaoBemMovel(String descricaoBemMovel) {
		this.descricaoBemMovel = descricaoBemMovel;
	}

	public String getOutroMoradorOrigem() {
		return outroMoradorOrigem;
	}

	public void setOutroMoradorOrigem(String outroMoradorOrigem) {
		this.outroMoradorOrigem = outroMoradorOrigem;
	}

	public Integer getQuantidadeParticipacaoAuxilio() {
		return quantidadeParticipacaoAuxilio;
	}

	public void setQuantidadeParticipacaoAuxilio(Integer quantidadeParticipacaoAuxilio) {
		this.quantidadeParticipacaoAuxilio = quantidadeParticipacaoAuxilio;
	}

	public String getBolsaAtual() {
		return bolsaAtual;
	}

	public void setBolsaAtual(String bolsaAtual) {
		this.bolsaAtual = bolsaAtual;
	}

	public String getOutraGraduacao() {
		return outraGraduacao;
	}

	public void setOutraGraduacao(String outraGraduacao) {
		this.outraGraduacao = outraGraduacao;
	}

	public List<ServicosProReitoria> getServicos() {
		return servicos == null ? new ArrayList<ServicosProReitoria>() : servicos;
	}

	public void setServicos(List<ServicosProReitoria> servicos) {
		this.servicos = servicos;
	}

	public String getOutroServico() {
		return outroServico;
	}

	public void setOutroServico(String outroServico) {
		this.outroServico = outroServico;
	}

	public List<Trajeto> getTrajetos() {
		return trajetos == null ? new ArrayList<Trajeto>() : trajetos;
	}

	public void setTrajetos(List<Trajeto> trajetos) {
		this.trajetos = trajetos;
	}

	public String getOutroTrajeto() {
		return outroTrajeto;
	}

	public void setOutroTrajeto(String outroTrajeto) {
		this.outroTrajeto = outroTrajeto;
	}

	public Double getValorMensalTransporte() {
		return valorMensalTransporte;
	}

	public void setValorMensalTransporte(Double valorMensalTransporte) {
		this.valorMensalTransporte = valorMensalTransporte;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Integer getTempoGasto() {
		return tempoGasto;
	}

	public void setTempoGasto(Integer tempoGasto) {
		this.tempoGasto = tempoGasto;
	}

	public boolean isMedicamento() {
		return medicamento;
	}

	public void setMedicamento(boolean medicamento) {
		this.medicamento = medicamento;
	}

	public String getDoencaMedicamento() {
		return doencaMedicamento;
	}

	public void setDoencaMedicamento(String doencaMedicamento) {
		this.doencaMedicamento = doencaMedicamento;
	}

	public boolean isDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(boolean deficiencia) {
		this.deficiencia = deficiencia;
	}

	public String getNomeDeficiencia() {
		return nomeDeficiencia;
	}

	public void setNomeDeficiencia(String nomeDeficiencia) {
		this.nomeDeficiencia = nomeDeficiencia;
	}

	public boolean isDoencaGrave() {
		return doencaGrave;
	}

	public void setDoencaGrave(boolean doencaGrave) {
		this.doencaGrave = doencaGrave;
	}

	public String getMembroDoencaGrave() {
		return membroDoencaGrave;
	}

	public void setMembroDoencaGrave(String membroDoencaGrave) {
		this.membroDoencaGrave = membroDoencaGrave;
	}

	public boolean isMembroDeficiencia() {
		return membroDeficiencia;
	}

	public void setMembroDeficiencia(boolean membroDeficiencia) {
		this.membroDeficiencia = membroDeficiencia;
	}

	public String getNomeMembroDeficiencia() {
		return nomeMembroDeficiencia;
	}

	public void setNomeMembroDeficiencia(String nomeMembroDeficiencia) {
		this.nomeMembroDeficiencia = nomeMembroDeficiencia;
	}

	public boolean isAssistenciaMedica() {
		return assistenciaMedica;
	}

	public void setAssistenciaMedica(boolean assistenciaMedica) {
		this.assistenciaMedica = assistenciaMedica;
	}

	public Double getValorAssistenciaMedica() {
		return valorAssistenciaMedica;
	}

	public void setValorAssistenciaMedica(Double valorAssistenciaMedica) {
		this.valorAssistenciaMedica = valorAssistenciaMedica;
	}

	public boolean isDespesaMedicamento() {
		return despesaMedicamento;
	}

	public void setDespesaMedicamento(boolean despesaMedicamento) {
		this.despesaMedicamento = despesaMedicamento;
	}

	public String getDescricaoDespesaMedicamento() {
		return descricaoDespesaMedicamento;
	}

	public void setDescricaoDespesaMedicamento(String descricaoDespesaMedicamento) {
		this.descricaoDespesaMedicamento = descricaoDespesaMedicamento;
	}

	public boolean isBeneficio() {
		return beneficio;
	}

	public void setBeneficio(boolean beneficio) {
		this.beneficio = beneficio;
	}

	public String getDescricaoBeneficio() {
		return descricaoBeneficio;
	}

	public void setDescricaoBeneficio(String descricaoBeneficio) {
		this.descricaoBeneficio = descricaoBeneficio;
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

	
}
