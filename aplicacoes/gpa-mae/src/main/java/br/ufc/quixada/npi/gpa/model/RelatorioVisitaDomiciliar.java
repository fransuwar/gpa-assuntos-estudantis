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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"aluno_id"}))
public class RelatorioVisitaDomiciliar {
	
	public RelatorioVisitaDomiciliar() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Aluno aluno;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private String formaAcessoCasa;
	
	@Enumerated(EnumType.STRING)
	private Curso curso;
	
	@Column(nullable = false)
	private int semestre;
	
	@Column(name="dataVisita", columnDefinition="DATE")
	@Temporal(TemporalType.DATE) 
	private Date dataVisita;
	
	private double despesaMoradia;
	private double despesaEnergia;
	private double despesaTelefone;
	private double despesaEducacao;
	private double despesaSaude;
	private double despesaAlimentacao;
	private double despesaOutros;
	
	@Column(nullable = false)
	private int qtdPessoasResidentes;
	private double rendaTrabalhoFormal;
	private double rendaTrabalhoInformal;
	private double rendaAposentadoria;
	private double rendaBeneficioSocial;
	private double rendaAuxilioParente;
	private double rendaOutros;
	
	private int moradiaVarandaQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaVarandaEstado;
	private int moradiaSalaQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaSalaEstado;
	private int moradiaBanheiroQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaBanheiroEstado;
	private int moradiaQuartoQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaQuartoEstado;
	private int moradiaCozinhaQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaCozinhaEstado;
	private int moradiaQuintalQtd;
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaQuintalEstado;
	
	@NotNull(message = "Campo Obrigatório")
	private int utensilioTvQtd;
	private String utensilioTvObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioSomQtd;
	private String utensilioSomObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioComputadorQtd;
	private String utensilioComputadorObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioFogaoQtd;
	private String utensilioFogaoObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioGeladeiraQtd;
	private String utensilioGeladeiraObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioFreezerQtd;
	private String utensilioFreezerObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioLavadoraQtd;
	private String utensilioLavadoraObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioDvdQtd;
	private String utensilioDvdObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int utensilioOutrosQtd;
	private String utensilioOutrosObservacao;
	
	@NotNull(message = "Campo Obrigatório")
	private int bemMovelMotoQtd;
	private String bemMovelMotoObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int bemMovelBicicletaQtd;
	private String bemMovelBicicletaObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int bemMovelCarroQtd;
	private String bemMovelCarroObservacao;
	@NotNull(message = "Campo Obrigatório")
	private int bemMovelOutrosQtd;
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
	
	@Column(nullable = false)
	private String analiseDescricaoRealidade;
	@Column(nullable = false)
	private boolean parecerFinalDeferido;
	@Column(name="dataRelatorio", columnDefinition="DATE")
	@Temporal(TemporalType.DATE) 
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
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public Date getDataVisita() {
		return dataVisita;
	}
	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}
	public double getDespesaMoradia() {
		return despesaMoradia;
	}
	public void setDespesaMoradia(double despesaMoradia) {
		this.despesaMoradia = despesaMoradia;
	}
	public double getDespesaEnergia() {
		return despesaEnergia;
	}
	public void setDespesaEnergia(double despesaEnergia) {
		this.despesaEnergia = despesaEnergia;
	}
	public double getDespesaTelefone() {
		return despesaTelefone;
	}
	public void setDespesaTelefone(double despesaTelefone) {
		this.despesaTelefone = despesaTelefone;
	}
	public double getDespesaEducacao() {
		return despesaEducacao;
	}
	public void setDespesaEducacao(double despesaEducacao) {
		this.despesaEducacao = despesaEducacao;
	}
	public double getDespesaSaude() {
		return despesaSaude;
	}
	public void setDespesaSaude(double despesaSaude) {
		this.despesaSaude = despesaSaude;
	}
	public double getDespesaAlimentacao() {
		return despesaAlimentacao;
	}
	public void setDespesaAlimentacao(double despesaAlimentacao) {
		this.despesaAlimentacao = despesaAlimentacao;
	}
	public double getDespesaOutros() {
		return despesaOutros;
	}
	public void setDespesaOutros(double despesaOutros) {
		this.despesaOutros = despesaOutros;
	}
	public int getQtdPessoasResidentes() {
		return qtdPessoasResidentes;
	}
	public void setQtdPessoasResidentes(int qtdPessoasResidentes) {
		this.qtdPessoasResidentes = qtdPessoasResidentes;
	}
	public double getRendaTrabalhoFormal() {
		return rendaTrabalhoFormal;
	}
	public void setRendaTrabalhoFormal(double rendaTrabalhoFormal) {
		this.rendaTrabalhoFormal = rendaTrabalhoFormal;
	}
	public double getRendaTrabalhoInformal() {
		return rendaTrabalhoInformal;
	}
	public void setRendaTrabalhoInformal(double rendaTrabalhoInformal) {
		this.rendaTrabalhoInformal = rendaTrabalhoInformal;
	}
	public double getRendaAposentadoria() {
		return rendaAposentadoria;
	}
	public void setRendaAposentadoria(double rendaAposentadoria) {
		this.rendaAposentadoria = rendaAposentadoria;
	}
	public double getRendaBeneficioSocial() {
		return rendaBeneficioSocial;
	}
	public void setRendaBeneficioSocial(double rendaBeneficioSocial) {
		this.rendaBeneficioSocial = rendaBeneficioSocial;
	}
	public double getRendaAuxilioParente() {
		return rendaAuxilioParente;
	}
	public void setRendaAuxilioParente(double rendaAuxilioParente) {
		this.rendaAuxilioParente = rendaAuxilioParente;
	}
	public double getRendaOutros() {
		return rendaOutros;
	}
	public void setRendaOutros(double rendaOutros) {
		this.rendaOutros = rendaOutros;
	}
	public int getMoradiaVarandaQtd() {
		return moradiaVarandaQtd;
	}
	public void setMoradiaVarandaQtd(int moradiaVarandaQtd) {
		this.moradiaVarandaQtd = moradiaVarandaQtd;
	}
	public EstadoMoradia getMoradiaVarandaEstado() {
		return moradiaVarandaEstado;
	}
	public void setMoradiaVarandaEstado(EstadoMoradia moradiaVarandaEstado) {
		this.moradiaVarandaEstado = moradiaVarandaEstado;
	}
	public int getMoradiaSalaQtd() {
		return moradiaSalaQtd;
	}
	public void setMoradiaSalaQtd(int moradiaSalaQtd) {
		this.moradiaSalaQtd = moradiaSalaQtd;
	}
	public EstadoMoradia getMoradiaSalaEstado() {
		return moradiaSalaEstado;
	}
	public void setMoradiaSalaEstado(EstadoMoradia moradiaSalaEstado) {
		this.moradiaSalaEstado = moradiaSalaEstado;
	}
	public int getMoradiaBanheiroQtd() {
		return moradiaBanheiroQtd;
	}
	public void setMoradiaBanheiroQtd(int moradiaBanheiroQtd) {
		this.moradiaBanheiroQtd = moradiaBanheiroQtd;
	}
	public EstadoMoradia getMoradiaBanheiroEstado() {
		return moradiaBanheiroEstado;
	}
	public void setMoradiaBanheiroEstado(EstadoMoradia moradiaBanheiroEstado) {
		this.moradiaBanheiroEstado = moradiaBanheiroEstado;
	}
	public int getMoradiaQuartoQtd() {
		return moradiaQuartoQtd;
	}
	public void setMoradiaQuartoQtd(int moradiaQuartoQtd) {
		this.moradiaQuartoQtd = moradiaQuartoQtd;
	}
	public EstadoMoradia getMoradiaQuartoEstado() {
		return moradiaQuartoEstado;
	}
	public void setMoradiaQuartoEstado(EstadoMoradia moradiaQuartoEstado) {
		this.moradiaQuartoEstado = moradiaQuartoEstado;
	}
	public int getMoradiaCozinhaQtd() {
		return moradiaCozinhaQtd;
	}
	public void setMoradiaCozinhaQtd(int moradiaCozinhaQtd) {
		this.moradiaCozinhaQtd = moradiaCozinhaQtd;
	}
	public EstadoMoradia getMoradiaCozinhaEstado() {
		return moradiaCozinhaEstado;
	}
	public void setMoradiaCozinhaEstado(EstadoMoradia moradiaCozinhaEstado) {
		this.moradiaCozinhaEstado = moradiaCozinhaEstado;
	}
	public int getMoradiaQuintalQtd() {
		return moradiaQuintalQtd;
	}
	public void setMoradiaQuintalQtd(int moradiaQuintalQtd) {
		this.moradiaQuintalQtd = moradiaQuintalQtd;
	}
	public EstadoMoradia getMoradiaQuintalEstado() {
		return moradiaQuintalEstado;
	}
	public void setMoradiaQuintalEstado(EstadoMoradia moradiaQuintalEstado) {
		this.moradiaQuintalEstado = moradiaQuintalEstado;
	}
	public int getUtensilioTvQtd() {
		return utensilioTvQtd;
	}
	public void setUtensilioTvQtd(int utensilioTvQtd) {
		this.utensilioTvQtd = utensilioTvQtd;
	}
	public String getUtensilioTvObservacao() {
		return utensilioTvObservacao;
	}
	public void setUtensilioTvObservacao(String utensilioTvObservacao) {
		this.utensilioTvObservacao = utensilioTvObservacao;
	}
	public int getUtensilioSomQtd() {
		return utensilioSomQtd;
	}
	public void setUtensilioSomQtd(int utensilioSomQtd) {
		this.utensilioSomQtd = utensilioSomQtd;
	}
	public String getUtensilioSomObservacao() {
		return utensilioSomObservacao;
	}
	public void setUtensilioSomObservacao(String utensilioSomObservacao) {
		this.utensilioSomObservacao = utensilioSomObservacao;
	}
	public int getUtensilioComputadorQtd() {
		return utensilioComputadorQtd;
	}
	public void setUtensilioComputadorQtd(int utensilioComputadorQtd) {
		this.utensilioComputadorQtd = utensilioComputadorQtd;
	}
	public String getUtensilioComputadorObservacao() {
		return utensilioComputadorObservacao;
	}
	public void setUtensilioComputadorObservacao(
			String utensilioComputadorObservacao) {
		this.utensilioComputadorObservacao = utensilioComputadorObservacao;
	}
	public int getUtensilioFogaoQtd() {
		return utensilioFogaoQtd;
	}
	public void setUtensilioFogaoQtd(int utensilioFogaoQtd) {
		this.utensilioFogaoQtd = utensilioFogaoQtd;
	}
	public String getUtensilioFogaoObservacao() {
		return utensilioFogaoObservacao;
	}
	public void setUtensilioFogaoObservacao(String utensilioFogaoObservacao) {
		this.utensilioFogaoObservacao = utensilioFogaoObservacao;
	}
	public int getUtensilioGeladeiraQtd() {
		return utensilioGeladeiraQtd;
	}
	public void setUtensilioGeladeiraQtd(int utensilioGeladeiraQtd) {
		this.utensilioGeladeiraQtd = utensilioGeladeiraQtd;
	}
	public String getUtensilioGeladeiraObservacao() {
		return utensilioGeladeiraObservacao;
	}
	public void setUtensilioGeladeiraObservacao(String utensilioGeladeiraObservacao) {
		this.utensilioGeladeiraObservacao = utensilioGeladeiraObservacao;
	}
	public int getUtensilioFreezerQtd() {
		return utensilioFreezerQtd;
	}
	public void setUtensilioFreezerQtd(int utensilioFreezerQtd) {
		this.utensilioFreezerQtd = utensilioFreezerQtd;
	}
	public String getUtensilioFreezerObservacao() {
		return utensilioFreezerObservacao;
	}
	public void setUtensilioFreezerObservacao(String utensilioFreezerObservacao) {
		this.utensilioFreezerObservacao = utensilioFreezerObservacao;
	}
	public int getUtensilioLavadoraQtd() {
		return utensilioLavadoraQtd;
	}
	public void setUtensilioLavadoraQtd(int utensilioLavadoraQtd) {
		this.utensilioLavadoraQtd = utensilioLavadoraQtd;
	}
	public String getUtensilioLavadoraObservacao() {
		return utensilioLavadoraObservacao;
	}
	public void setUtensilioLavadoraObservacao(String utensilioLavadoraObservacao) {
		this.utensilioLavadoraObservacao = utensilioLavadoraObservacao;
	}
	public int getUtensilioDvdQtd() {
		return utensilioDvdQtd;
	}
	public void setUtensilioDvdQtd(int utensilioDvdQtd) {
		this.utensilioDvdQtd = utensilioDvdQtd;
	}
	public String getUtensilioDvdObservacao() {
		return utensilioDvdObservacao;
	}
	public void setUtensilioDvdObservacao(String utensilioDvdObservacao) {
		this.utensilioDvdObservacao = utensilioDvdObservacao;
	}
	public int getUtensilioOutrosQtd() {
		return utensilioOutrosQtd;
	}
	public void setUtensilioOutrosQtd(int utensilioOutrosQtd) {
		this.utensilioOutrosQtd = utensilioOutrosQtd;
	}
	public String getUtensilioOutrosObservacao() {
		return utensilioOutrosObservacao;
	}
	public void setUtensilioOutrosObservacao(String utensilioOutrosObservacao) {
		this.utensilioOutrosObservacao = utensilioOutrosObservacao;
	}
	public int getBemMovelMotoQtd() {
		return bemMovelMotoQtd;
	}
	public void setBemMovelMotoQtd(int bemMovelMotoQtd) {
		this.bemMovelMotoQtd = bemMovelMotoQtd;
	}
	public String getBemMovelMotoObservacao() {
		return bemMovelMotoObservacao;
	}
	public void setBemMovelMotoObservacao(String bemMovelMotoObservacao) {
		this.bemMovelMotoObservacao = bemMovelMotoObservacao;
	}
	public int getBemMovelBicicletaQtd() {
		return bemMovelBicicletaQtd;
	}
	public void setBemMovelBicicletaQtd(int bemMovelBicicletaQtd) {
		this.bemMovelBicicletaQtd = bemMovelBicicletaQtd;
	}
	public String getBemMovelBicicletaObservacao() {
		return bemMovelBicicletaObservacao;
	}
	public void setBemMovelBicicletaObservacao(String bemMovelBicicletaObservacao) {
		this.bemMovelBicicletaObservacao = bemMovelBicicletaObservacao;
	}
	public int getBemMovelCarroQtd() {
		return bemMovelCarroQtd;
	}
	public void setBemMovelCarroQtd(int bemMovelCarroQtd) {
		this.bemMovelCarroQtd = bemMovelCarroQtd;
	}
	public String getBemMovelCarroObservacao() {
		return bemMovelCarroObservacao;
	}
	public void setBemMovelCarroObservacao(String bemMovelCarroObservacao) {
		this.bemMovelCarroObservacao = bemMovelCarroObservacao;
	}
	public int getBemMovelOutrosQtd() {
		return bemMovelOutrosQtd;
	}
	public void setBemMovelOutrosQtd(int bemMovelOutrosQtd) {
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
