package br.ufc.quixada.npi.gpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;

@Entity
public class RelatorioVisitaDomiciliar {
	
	public RelatorioVisitaDomiciliar() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private SelecaoBolsa selecaoBolsa;
	
	@NotNull(message = "Campo obrigatório")
	private String endereco;
	
	@NotNull(message = "Campo obrigatório")
	private String formaAcessoCasa;
	
	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Curso curso;
	
	@NotNull(message = "Campo obrigatório")
	private Integer semestre;
	
	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date dataVisita;
	
	@NotNull(message = "Campo obrigatório")
	private Double despesaMoradia;
	@NotNull(message = "Campo obrigatório")
	private Double despesaEnergia;
	@NotNull(message = "Campo obrigatório")
	private Double despesaTelefone;
	@NotNull(message = "Campo obrigatório")
	private Double despesaEducacao;
	@NotNull(message = "Campo obrigatório")
	private Double despesaSaude;
	@NotNull(message = "Campo obrigatório")
	private Double despesaAlimentacao;
	@NotNull(message = "Campo obrigatório")
	private Double despesaOutros;
	
	@NotNull(message = "Campo obrigatório")
	private Integer qtdPessoasResidentes;
	@NotNull(message = "Campo obrigatório")
	private Double rendaTrabalhoFormal;
	@NotNull(message = "Campo obrigatório")
	private Double rendaTrabalhoInformal;
	@NotNull(message = "Campo obrigatório")
	private Double rendaAposentadoria;
	@NotNull(message = "Campo obrigatório")
	private Double rendaBeneficioSocial;
	@NotNull(message = "Campo obrigatório")
	private Double rendaAuxilioParente;
	@NotNull(message = "Campo obrigatório")
	private Double rendaOutros;
	
	@NotNull(message = "Campo obrigatório")
	private Integer moradiaVarandaQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaVarandaEstado;
	@NotNull(message = "Campo obrigatório")
	private Integer moradiaSalaQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaSalaEstado;
	@NotNull(message = "Campo obrigatório")
	private Integer moradiaBanheiroQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaBanheiroEstado;
	@NotNull(message = "Campo obrigatório")
	private Integer moradiaQuartoQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaQuartoEstado;
	@NotNull(message = "Campo obrigatório")
	private Integer moradiaCozinhaQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaCozinhaEstado;
	@NotNull(message = "Campo obrigatório")
	private Integer moradiaQuintalQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaQuintalEstado;
	
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioTvQtd;
	private String utensilioTvObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioSomQtd;
	private String utensilioSomObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioComputadorQtd;
	private String utensilioComputadorObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioFogaoQtd;
	private String utensilioFogaoObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioGeladeiraQtd;
	private String utensilioGeladeiraObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioFreezerQtd;
	private String utensilioFreezerObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioLavadoraQtd;
	private String utensilioLavadoraObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioDvdQtd;
	private String utensilioDvdObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer utensilioOutrosQtd;
	private String utensilioOutrosObservacao;
	
	@NotNull(message = "Campo Obrigatório")
	private Integer bemMovelMotoQtd;
	private String bemMovelMotoObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer bemMovelBicicletaQtd;
	private String bemMovelBicicletaObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer bemMovelCarroQtd;
	private String bemMovelCarroObservacao;
	@NotNull(message = "Campo Obrigatório")
	private Integer bemMovelOutrosQtd;
	private String bemMovelOutrosObservacao;
	
	@Column(nullable = false)
	private boolean perfilCompativelUtensilioDomestico;
	@Column(nullable = false)
	private boolean perfilCompativelBensMoveis;
	@Column(nullable = false)
	private boolean perfilCompativelMaquinario;
	@Column(nullable = false)
	private boolean perfilCompativelAspectoFisicoResidencia;
	@Column(nullable = false)
	private boolean perfilCompativelOutros;
	
	@NotNull(message = "Campo obrigatório")
	private String analiseDescricaoRealidade;
	@Column(nullable = false)
	private boolean parecerFinalDeferido;
	
	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRelatorio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getFormaAcessoCasa() {
		return formaAcessoCasa;
	}
	public void setFormaAcessoCasa(String formaAcessoCasa) {
		this.formaAcessoCasa = formaAcessoCasa;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	public Date getDataVisita() {
		return dataVisita;
	}
	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}
	public Double getDespesaMoradia() {
		return despesaMoradia;
	}
	public void setDespesaMoradia(Double despesaMoradia) {
		this.despesaMoradia = despesaMoradia;
	}
	public Double getDespesaEnergia() {
		return despesaEnergia;
	}
	public void setDespesaEnergia(Double despesaEnergia) {
		this.despesaEnergia = despesaEnergia;
	}
	public Double getDespesaTelefone() {
		return despesaTelefone;
	}
	public void setDespesaTelefone(Double despesaTelefone) {
		this.despesaTelefone = despesaTelefone;
	}
	public Double getDespesaEducacao() {
		return despesaEducacao;
	}
	public void setDespesaEducacao(Double despesaEducacao) {
		this.despesaEducacao = despesaEducacao;
	}
	public Double getDespesaSaude() {
		return despesaSaude;
	}
	public void setDespesaSaude(Double despesaSaude) {
		this.despesaSaude = despesaSaude;
	}
	public Double getDespesaAlimentacao() {
		return despesaAlimentacao;
	}
	public void setDespesaAlimentacao(Double despesaAlimentacao) {
		this.despesaAlimentacao = despesaAlimentacao;
	}
	public Double getDespesaOutros() {
		return despesaOutros;
	}
	public void setDespesaOutros(Double despesaOutros) {
		this.despesaOutros = despesaOutros;
	}
	public Integer getQtdPessoasResidentes() {
		return qtdPessoasResidentes;
	}
	public void setQtdPessoasResidentes(Integer qtdPessoasResidentes) {
		this.qtdPessoasResidentes = qtdPessoasResidentes;
	}
	public Double getRendaTrabalhoFormal() {
		return rendaTrabalhoFormal;
	}
	public void setRendaTrabalhoFormal(Double rendaTrabalhoFormal) {
		this.rendaTrabalhoFormal = rendaTrabalhoFormal;
	}
	public Double getRendaTrabalhoInformal() {
		return rendaTrabalhoInformal;
	}
	public void setRendaTrabalhoInformal(Double rendaTrabalhoInformal) {
		this.rendaTrabalhoInformal = rendaTrabalhoInformal;
	}
	public Double getRendaAposentadoria() {
		return rendaAposentadoria;
	}
	public void setRendaAposentadoria(Double rendaAposentadoria) {
		this.rendaAposentadoria = rendaAposentadoria;
	}
	public Double getRendaBeneficioSocial() {
		return rendaBeneficioSocial;
	}
	public void setRendaBeneficioSocial(Double rendaBeneficioSocial) {
		this.rendaBeneficioSocial = rendaBeneficioSocial;
	}
	public Double getRendaAuxilioParente() {
		return rendaAuxilioParente;
	}
	public void setRendaAuxilioParente(Double rendaAuxilioParente) {
		this.rendaAuxilioParente = rendaAuxilioParente;
	}
	public Double getRendaOutros() {
		return rendaOutros;
	}
	public void setRendaOutros(Double rendaOutros) {
		this.rendaOutros = rendaOutros;
	}
	public Integer getMoradiaVarandaQtd() {
		return moradiaVarandaQtd;
	}
	public void setMoradiaVarandaQtd(Integer moradiaVarandaQtd) {
		this.moradiaVarandaQtd = moradiaVarandaQtd;
	}
	public EstadoMoradia getMoradiaVarandaEstado() {
		return moradiaVarandaEstado;
	}
	public void setMoradiaVarandaEstado(EstadoMoradia moradiaVarandaEstado) {
		this.moradiaVarandaEstado = moradiaVarandaEstado;
	}
	public Integer getMoradiaSalaQtd() {
		return moradiaSalaQtd;
	}
	public void setMoradiaSalaQtd(Integer moradiaSalaQtd) {
		this.moradiaSalaQtd = moradiaSalaQtd;
	}
	public EstadoMoradia getMoradiaSalaEstado() {
		return moradiaSalaEstado;
	}
	public void setMoradiaSalaEstado(EstadoMoradia moradiaSalaEstado) {
		this.moradiaSalaEstado = moradiaSalaEstado;
	}
	public Integer getMoradiaBanheiroQtd() {
		return moradiaBanheiroQtd;
	}
	public void setMoradiaBanheiroQtd(Integer moradiaBanheiroQtd) {
		this.moradiaBanheiroQtd = moradiaBanheiroQtd;
	}
	public EstadoMoradia getMoradiaBanheiroEstado() {
		return moradiaBanheiroEstado;
	}
	public void setMoradiaBanheiroEstado(EstadoMoradia moradiaBanheiroEstado) {
		this.moradiaBanheiroEstado = moradiaBanheiroEstado;
	}
	public Integer getMoradiaQuartoQtd() {
		return moradiaQuartoQtd;
	}
	public void setMoradiaQuartoQtd(Integer moradiaQuartoQtd) {
		this.moradiaQuartoQtd = moradiaQuartoQtd;
	}
	public EstadoMoradia getMoradiaQuartoEstado() {
		return moradiaQuartoEstado;
	}
	public void setMoradiaQuartoEstado(EstadoMoradia moradiaQuartoEstado) {
		this.moradiaQuartoEstado = moradiaQuartoEstado;
	}
	public Integer getMoradiaCozinhaQtd() {
		return moradiaCozinhaQtd;
	}
	public void setMoradiaCozinhaQtd(Integer moradiaCozinhaQtd) {
		this.moradiaCozinhaQtd = moradiaCozinhaQtd;
	}
	public EstadoMoradia getMoradiaCozinhaEstado() {
		return moradiaCozinhaEstado;
	}
	public void setMoradiaCozinhaEstado(EstadoMoradia moradiaCozinhaEstado) {
		this.moradiaCozinhaEstado = moradiaCozinhaEstado;
	}
	public Integer getMoradiaQuintalQtd() {
		return moradiaQuintalQtd;
	}
	public void setMoradiaQuintalQtd(Integer moradiaQuintalQtd) {
		this.moradiaQuintalQtd = moradiaQuintalQtd;
	}
	public EstadoMoradia getMoradiaQuintalEstado() {
		return moradiaQuintalEstado;
	}
	public void setMoradiaQuintalEstado(EstadoMoradia moradiaQuintalEstado) {
		this.moradiaQuintalEstado = moradiaQuintalEstado;
	}
	public Integer getUtensilioTvQtd() {
		return utensilioTvQtd;
	}
	public void setUtensilioTvQtd(Integer utensilioTvQtd) {
		this.utensilioTvQtd = utensilioTvQtd;
	}
	public String getUtensilioTvObservacao() {
		return utensilioTvObservacao;
	}
	public void setUtensilioTvObservacao(String utensilioTvObservacao) {
		this.utensilioTvObservacao = utensilioTvObservacao;
	}
	public Integer getUtensilioSomQtd() {
		return utensilioSomQtd;
	}
	public void setUtensilioSomQtd(Integer utensilioSomQtd) {
		this.utensilioSomQtd = utensilioSomQtd;
	}
	public String getUtensilioSomObservacao() {
		return utensilioSomObservacao;
	}
	public void setUtensilioSomObservacao(String utensilioSomObservacao) {
		this.utensilioSomObservacao = utensilioSomObservacao;
	}
	public Integer getUtensilioComputadorQtd() {
		return utensilioComputadorQtd;
	}
	public void setUtensilioComputadorQtd(Integer utensilioComputadorQtd) {
		this.utensilioComputadorQtd = utensilioComputadorQtd;
	}
	public String getUtensilioComputadorObservacao() {
		return utensilioComputadorObservacao;
	}
	public void setUtensilioComputadorObservacao(
			String utensilioComputadorObservacao) {
		this.utensilioComputadorObservacao = utensilioComputadorObservacao;
	}
	public Integer getUtensilioFogaoQtd() {
		return utensilioFogaoQtd;
	}
	public void setUtensilioFogaoQtd(Integer utensilioFogaoQtd) {
		this.utensilioFogaoQtd = utensilioFogaoQtd;
	}
	public String getUtensilioFogaoObservacao() {
		return utensilioFogaoObservacao;
	}
	public void setUtensilioFogaoObservacao(String utensilioFogaoObservacao) {
		this.utensilioFogaoObservacao = utensilioFogaoObservacao;
	}
	public Integer getUtensilioGeladeiraQtd() {
		return utensilioGeladeiraQtd;
	}
	public void setUtensilioGeladeiraQtd(Integer utensilioGeladeiraQtd) {
		this.utensilioGeladeiraQtd = utensilioGeladeiraQtd;
	}
	public String getUtensilioGeladeiraObservacao() {
		return utensilioGeladeiraObservacao;
	}
	public void setUtensilioGeladeiraObservacao(String utensilioGeladeiraObservacao) {
		this.utensilioGeladeiraObservacao = utensilioGeladeiraObservacao;
	}
	public Integer getUtensilioFreezerQtd() {
		return utensilioFreezerQtd;
	}
	public void setUtensilioFreezerQtd(Integer utensilioFreezerQtd) {
		this.utensilioFreezerQtd = utensilioFreezerQtd;
	}
	public String getUtensilioFreezerObservacao() {
		return utensilioFreezerObservacao;
	}
	public void setUtensilioFreezerObservacao(String utensilioFreezerObservacao) {
		this.utensilioFreezerObservacao = utensilioFreezerObservacao;
	}
	public Integer getUtensilioLavadoraQtd() {
		return utensilioLavadoraQtd;
	}
	public void setUtensilioLavadoraQtd(Integer utensilioLavadoraQtd) {
		this.utensilioLavadoraQtd = utensilioLavadoraQtd;
	}
	public String getUtensilioLavadoraObservacao() {
		return utensilioLavadoraObservacao;
	}
	public void setUtensilioLavadoraObservacao(String utensilioLavadoraObservacao) {
		this.utensilioLavadoraObservacao = utensilioLavadoraObservacao;
	}
	public Integer getUtensilioDvdQtd() {
		return utensilioDvdQtd;
	}
	public void setUtensilioDvdQtd(Integer utensilioDvdQtd) {
		this.utensilioDvdQtd = utensilioDvdQtd;
	}
	public String getUtensilioDvdObservacao() {
		return utensilioDvdObservacao;
	}
	public void setUtensilioDvdObservacao(String utensilioDvdObservacao) {
		this.utensilioDvdObservacao = utensilioDvdObservacao;
	}
	public Integer getUtensilioOutrosQtd() {
		return utensilioOutrosQtd;
	}
	public void setUtensilioOutrosQtd(Integer utensilioOutrosQtd) {
		this.utensilioOutrosQtd = utensilioOutrosQtd;
	}
	public String getUtensilioOutrosObservacao() {
		return utensilioOutrosObservacao;
	}
	public void setUtensilioOutrosObservacao(String utensilioOutrosObservacao) {
		this.utensilioOutrosObservacao = utensilioOutrosObservacao;
	}
	public Integer getBemMovelMotoQtd() {
		return bemMovelMotoQtd;
	}
	public void setBemMovelMotoQtd(Integer bemMovelMotoQtd) {
		this.bemMovelMotoQtd = bemMovelMotoQtd;
	}
	public String getBemMovelMotoObservacao() {
		return bemMovelMotoObservacao;
	}
	public void setBemMovelMotoObservacao(String bemMovelMotoObservacao) {
		this.bemMovelMotoObservacao = bemMovelMotoObservacao;
	}
	public Integer getBemMovelBicicletaQtd() {
		return bemMovelBicicletaQtd;
	}
	public void setBemMovelBicicletaQtd(Integer bemMovelBicicletaQtd) {
		this.bemMovelBicicletaQtd = bemMovelBicicletaQtd;
	}
	public String getBemMovelBicicletaObservacao() {
		return bemMovelBicicletaObservacao;
	}
	public void setBemMovelBicicletaObservacao(String bemMovelBicicletaObservacao) {
		this.bemMovelBicicletaObservacao = bemMovelBicicletaObservacao;
	}
	public Integer getBemMovelCarroQtd() {
		return bemMovelCarroQtd;
	}
	public void setBemMovelCarroQtd(Integer bemMovelCarroQtd) {
		this.bemMovelCarroQtd = bemMovelCarroQtd;
	}
	public String getBemMovelCarroObservacao() {
		return bemMovelCarroObservacao;
	}
	public void setBemMovelCarroObservacao(String bemMovelCarroObservacao) {
		this.bemMovelCarroObservacao = bemMovelCarroObservacao;
	}
	public Integer getBemMovelOutrosQtd() {
		return bemMovelOutrosQtd;
	}
	public void setBemMovelOutrosQtd(Integer bemMovelOutrosQtd) {
		this.bemMovelOutrosQtd = bemMovelOutrosQtd;
	}
	public String getBemMovelOutrosObservacao() {
		return bemMovelOutrosObservacao;
	}
	public void setBemMovelOutrosObservacao(String bemMovelOutrosObservacao) {
		this.bemMovelOutrosObservacao = bemMovelOutrosObservacao;
	}
	public boolean isPerfilCompativelUtensilioDomestico() {
		return perfilCompativelUtensilioDomestico;
	}
	public void setPerfilCompativelUtensilioDomestico(
			boolean perfilCompativelUtensilioDomestico) {
		this.perfilCompativelUtensilioDomestico = perfilCompativelUtensilioDomestico;
	}
	public boolean isPerfilCompativelBensMoveis() {
		return perfilCompativelBensMoveis;
	}
	public void setPerfilCompativelBensMoveis(boolean perfilCompativelBensMoveis) {
		this.perfilCompativelBensMoveis = perfilCompativelBensMoveis;
	}
	public boolean isPerfilCompativelMaquinario() {
		return perfilCompativelMaquinario;
	}
	public void setPerfilCompativelMaquinario(boolean perfilCompativelMaquinario) {
		this.perfilCompativelMaquinario = perfilCompativelMaquinario;
	}
	public boolean isPerfilCompativelAspectoFisicoResidencia() {
		return perfilCompativelAspectoFisicoResidencia;
	}
	public void setPerfilCompativelAspectoFisicoResidencia(
			boolean perfilCompativelAspectoFisicoResidencia) {
		this.perfilCompativelAspectoFisicoResidencia = perfilCompativelAspectoFisicoResidencia;
	}
	public boolean isPerfilCompativelOutros() {
		return perfilCompativelOutros;
	}
	public void setPerfilCompativelOutros(boolean perfilCompativelOutros) {
		this.perfilCompativelOutros = perfilCompativelOutros;
	}
	public String getAnaliseDescricaoRealidade() {
		return analiseDescricaoRealidade;
	}
	public void setAnaliseDescricaoRealidade(String analiseDescricaoRealidade) {
		this.analiseDescricaoRealidade = analiseDescricaoRealidade;
	}
	public boolean isParecerFinalDeferido() {
		return parecerFinalDeferido;
	}
	public void setParecerFinalDeferido(boolean parecerFinalDeferido) {
		this.parecerFinalDeferido = parecerFinalDeferido;
	}
	public Date getDataRelatorio() {
		return dataRelatorio;
	}
	public void setDataRelatorio(Date dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
	}
	public SelecaoBolsa getSelecaoBolsa() {
		return selecaoBolsa;
	}
	public void setSelecaoBolsa(SelecaoBolsa selecaoBolsa) {
		this.selecaoBolsa = selecaoBolsa;
	}
	@Override
	public String toString() {
		return "RelatorioVisitaDomiciliar [id=" + id + ", candidato="
				+ aluno + ", endereco=" + endereco + ", formaAcessoCasa="
				+ formaAcessoCasa + ", curso=" + curso + ", semestre="
				+ semestre + ", dataVisita=" + dataVisita + ", despesaMoradia="
				+ despesaMoradia + ", despesaEnergia=" + despesaEnergia
				+ ", despesaTelefone=" + despesaTelefone + ", despesaEducacao="
				+ despesaEducacao + ", despesaSaude=" + despesaSaude
				+ ", despesaAlimentacao=" + despesaAlimentacao
				+ ", despesaOutros=" + despesaOutros
				+ ", qtdPessoasResidentes=" + qtdPessoasResidentes
				+ ", rendaTrabalhoFormal=" + rendaTrabalhoFormal
				+ ", rendaTrabalhoInformal=" + rendaTrabalhoInformal
				+ ", rendaAposentadoria=" + rendaAposentadoria
				+ ", rendaBeneficioSocial=" + rendaBeneficioSocial
				+ ", rendaAuxilioParente=" + rendaAuxilioParente
				+ ", rendaOutros=" + rendaOutros + ", moradiaVarandaQtd="
				+ moradiaVarandaQtd + ", moradiaVarandaEstado="
				+ moradiaVarandaEstado + ", moradiaSalaQtd=" + moradiaSalaQtd
				+ ", moradiaSalaEstado=" + moradiaSalaEstado
				+ ", moradiaBanheiroQtd=" + moradiaBanheiroQtd
				+ ", moradiaBanheiroEstado=" + moradiaBanheiroEstado
				+ ", moradiaQuartoQtd=" + moradiaQuartoQtd
				+ ", moradiaQuartoEstado=" + moradiaQuartoEstado
				+ ", moradiaCozinhaQtd=" + moradiaCozinhaQtd
				+ ", moradiaCozinhaEstado=" + moradiaCozinhaEstado
				+ ", moradiaQuintalQtd=" + moradiaQuintalQtd
				+ ", moradiaQuintalEstado=" + moradiaQuintalEstado
				+ ", utensilioTvQtd=" + utensilioTvQtd
				+ ", utensilioTvObservacao=" + utensilioTvObservacao
				+ ", utensilioSomQtd=" + utensilioSomQtd
				+ ", utensilioSomObservacao=" + utensilioSomObservacao
				+ ", utensilioComputadorQtd=" + utensilioComputadorQtd
				+ ", utensilioComputadorObservacao="
				+ utensilioComputadorObservacao + ", utensilioFogaoQtd="
				+ utensilioFogaoQtd + ", utensilioFogaoObservacao="
				+ utensilioFogaoObservacao + ", utensilioGeladeiraQtd="
				+ utensilioGeladeiraQtd + ", utensilioGeladeiraObservacao="
				+ utensilioGeladeiraObservacao + ", utensilioFreezerQtd="
				+ utensilioFreezerQtd + ", utensilioFreezerObservacao="
				+ utensilioFreezerObservacao + ", utensilioLavadoraQtd="
				+ utensilioLavadoraQtd + ", utensilioLavadoraObservacao="
				+ utensilioLavadoraObservacao + ", utensilioDvdQtd="
				+ utensilioDvdQtd + ", utensilioDvdObservacao="
				+ utensilioDvdObservacao + ", utensilioOutrosQtd="
				+ utensilioOutrosQtd + ", utensilioOutrosObservacao="
				+ utensilioOutrosObservacao + ", bemMovelMotoQtd="
				+ bemMovelMotoQtd + ", bemMovelMotoObservacao="
				+ bemMovelMotoObservacao + ", bemMovelBicicletaQtd="
				+ bemMovelBicicletaQtd + ", bemMovelBicicletaObservacao="
				+ bemMovelBicicletaObservacao + ", bemMovelCarroQtd="
				+ bemMovelCarroQtd + ", bemMovelCarroObservacao="
				+ bemMovelCarroObservacao + ", bemMovelOutrosQtd="
				+ bemMovelOutrosQtd + ", bemMovelOutrosObservacao="
				+ bemMovelOutrosObservacao
				+ ", perfilCompativelUtensilioDomestico="
				+ perfilCompativelUtensilioDomestico
				+ ", perfilCompativelBensMoveis=" + perfilCompativelBensMoveis
				+ ", perfilCompativelMaquinario=" + perfilCompativelMaquinario
				+ ", perfilCompativelAspectoFisicoResidencia="
				+ perfilCompativelAspectoFisicoResidencia
				+ ", perfilCompativelOutros=" + perfilCompativelOutros
				+ ", analiseDescricaoRealidade=" + analiseDescricaoRealidade
				+ ", parecerFinalDeferido=" + parecerFinalDeferido
				+ ", dataRelatorio=" + dataRelatorio + "]";
	}
	
	
}
