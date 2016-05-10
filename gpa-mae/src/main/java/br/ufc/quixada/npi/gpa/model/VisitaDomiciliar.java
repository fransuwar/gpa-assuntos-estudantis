package br.ufc.quixada.npi.gpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;
import br.ufc.quixada.npi.gpa.enums.Resultado;

@Entity
public class VisitaDomiciliar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Servidor servidor;
	
	private String formaAcessoCasa;
	
	@Enumerated(EnumType.STRING)
	private Curso curso;
	
	private Integer semestre;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date dataVisita;
	
	private Integer qtdPessoasResidentes;
	
	private Integer moradiaVarandaQtd;
	
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaVarandaEstado;
	
	private Integer moradiaSalaQtd;
	
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaSalaEstado;
	
	private Integer moradiaBanheiroQtd;
	
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaBanheiroEstado;
	
	private Integer moradiaQuartoQtd;
	
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaQuartoEstado;
	
	private Integer moradiaCozinhaQtd;
	
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaCozinhaEstado;
	
	private Integer moradiaQuintalQtd;
	
	@Enumerated(EnumType.STRING)
	private EstadoMoradia moradiaQuintalEstado;
	
	private Integer utensilioTvQtd;
	
	private String utensilioTvObservacao;
	
	private Integer utensilioSomQtd;
	
	private String utensilioSomObservacao;
	
	private Integer utensilioComputadorQtd;
	
	private String utensilioComputadorObservacao;
	
	private Integer utensilioFogaoQtd;
	
	private String utensilioFogaoObservacao;
	
	private Integer utensilioGeladeiraQtd;
	
	private String utensilioGeladeiraObservacao;
	
	private Integer utensilioFreezerQtd;
	
	private String utensilioFreezerObservacao;
	
	private Integer utensilioLavadoraQtd;
	
	private String utensilioLavadoraObservacao;
	
	private Integer utensilioDvdQtd;
	
	private String utensilioDvdObservacao;
	
	private Integer utensilioOutrosQtd;
	
	private String utensilioOutrosObservacao;
	
	private Integer bemMovelMotoQtd;
	
	private String bemMovelMotoObservacao;
	
	private Integer bemMovelBicicletaQtd;
	
	private String bemMovelBicicletaObservacao;
	
	private Integer bemMovelCarroQtd;
	
	private String bemMovelCarroObservacao;
	
	private Integer bemMovelOutrosQtd;
	
	private String bemMovelOutrosObservacao;

	private boolean perfilCompativelUtensilioDomestico;
	
	private boolean perfilCompativelBensMoveis;
	
	private boolean perfilCompativelMaquinario;
	
	private boolean perfilCompativelAspectoFisicoResidencia;
	
	private boolean perfilCompativelOutros;
	
	private String analiseDescricaoRealidade;
	
	@Enumerated(EnumType.STRING)
	private Resultado deferimento;
	
	@OneToMany
	private List<Imagem> imagens;
	
	public List<Imagem> getImagens() {
		if(imagens == null){
			imagens = new ArrayList<>();
		}
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRelatorio;
	
	private String observacaoParecer;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Despesa despesa;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Receita receita;
	
	public VisitaDomiciliar() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getQtdPessoasResidentes() {
		return qtdPessoasResidentes;
	}
	
	public void setQtdPessoasResidentes(Integer qtdPessoasResidentes) {
		this.qtdPessoasResidentes = qtdPessoasResidentes;
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
	
	public Date getDataRelatorio() {
		return dataRelatorio;
	}
	
	public void setDataRelatorio(Date dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
	}

	public Despesa getDespesa() {
		return despesa;
	}
	
	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
	
	public Receita getReceita() {
		return receita;
	}
	
	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	
	public String getObservacaoParecer() {
		return observacaoParecer;
	}
	
	public void setObservacaoParecer(String observacaoParecer) {
		this.observacaoParecer = observacaoParecer;
	}
	
	public Servidor getServidor() {
		return servidor;
	}
	
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public Resultado getDeferimento() {
		return deferimento;
	}

	public void setDeferimento(Resultado deferimento) {
		this.deferimento = deferimento;
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
		VisitaDomiciliar other = (VisitaDomiciliar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
