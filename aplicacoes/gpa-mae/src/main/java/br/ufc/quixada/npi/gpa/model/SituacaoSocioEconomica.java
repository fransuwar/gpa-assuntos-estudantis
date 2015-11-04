package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;

@Entity
public class SituacaoSocioEconomica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String comQuemReside;
	private String tipoResidencia;
	@Enumerated(EnumType.STRING)
	private SituacaoResidencia situacaoResidencia;
	private Integer qtdAparelhoSom;
	private Integer qtdTelevisao;
	private Integer qtdRadio;
	private Integer qtdAutomovel;
	private Integer qtdMotocicleta;
	private Integer qtdMaquinaLavar;
	private Integer qtdDvd;
	private Integer qtdGeladeira;
	private Integer qtdFreezer;
	private Integer qtdTelefoneFixo;
	private Integer qtdCelular;
	private Integer qtdComputador;
	private Integer qtdFogaoGas;
	private Integer qtdMaquinaDeCostura;
	private Integer qtdComodos;
	private Integer qtdBanheiros;
	private Integer qtdEmpregadosDomesticos;

	public SituacaoSocioEconomica() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComQuemReside() {
		return comQuemReside;
	}

	public void setComQuemReside(String comQuemReside) {
		this.comQuemReside = comQuemReside;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}

	public SituacaoResidencia getSituacaoResidencia() {
		return situacaoResidencia;
	}

	public void setSituacaoResidencia(SituacaoResidencia situacaoResidencia) {
		this.situacaoResidencia = situacaoResidencia;
	}

	public Integer getQtdAparelhoSom() {
		return qtdAparelhoSom;
	}

	public void setQtdAparelhoSom(Integer qtdAparelhoSom) {
		this.qtdAparelhoSom = qtdAparelhoSom;
	}

	public Integer getQtdTelevisao() {
		return qtdTelevisao;
	}

	public void setQtdTelevisao(Integer qtdTelevisao) {
		this.qtdTelevisao = qtdTelevisao;
	}

	public Integer getQtdRadio() {
		return qtdRadio;
	}

	public void setQtdRadio(Integer qtdRadio) {
		this.qtdRadio = qtdRadio;
	}

	public Integer getQtdAutomovel() {
		return qtdAutomovel;
	}

	public void setQtdAutomovel(Integer qtdAutomovel) {
		this.qtdAutomovel = qtdAutomovel;
	}

	public Integer getQtdMotocicleta() {
		return qtdMotocicleta;
	}

	public void setQtdMotocicleta(Integer qtdMotocicleta) {
		this.qtdMotocicleta = qtdMotocicleta;
	}

	public Integer getQtdMaquinaLavar() {
		return qtdMaquinaLavar;
	}

	public void setQtdMaquinaLavar(Integer qtdMaquinaLavar) {
		this.qtdMaquinaLavar = qtdMaquinaLavar;
	}

	public Integer getQtdDvd() {
		return qtdDvd;
	}

	public void setQtdDvd(Integer qtdDvd) {
		this.qtdDvd = qtdDvd;
	}

	public Integer getQtdGeladeira() {
		return qtdGeladeira;
	}

	public void setQtdGeladeira(Integer qtdGeladeira) {
		this.qtdGeladeira = qtdGeladeira;
	}

	public Integer getQtdFreezer() {
		return qtdFreezer;
	}

	public void setQtdFreezer(Integer qtdFreezer) {
		this.qtdFreezer = qtdFreezer;
	}

	public Integer getQtdTelefoneFixo() {
		return qtdTelefoneFixo;
	}

	public void setQtdTelefoneFixo(Integer qtdTelefoneFixo) {
		this.qtdTelefoneFixo = qtdTelefoneFixo;
	}

	public Integer getQtdCelular() {
		return qtdCelular;
	}

	public void setQtdCelular(Integer qtdCelular) {
		this.qtdCelular = qtdCelular;
	}

	public Integer getQtdComputador() {
		return qtdComputador;
	}

	public void setQtdComputador(Integer qtdComputador) {
		this.qtdComputador = qtdComputador;
	}

	public Integer getQtdFogaoGas() {
		return qtdFogaoGas;
	}

	public void setQtdFogaoGas(Integer qtdFogaoGas) {
		this.qtdFogaoGas = qtdFogaoGas;
	}

	public Integer getQtdMaquinaDeCostura() {
		return qtdMaquinaDeCostura;
	}

	public void setQtdMaquinaDeCostura(Integer qtdMaquinaDeCostura) {
		this.qtdMaquinaDeCostura = qtdMaquinaDeCostura;
	}

	public Integer getQtdComodos() {
		return qtdComodos;
	}

	public void setQtdComodos(Integer qtdComodos) {
		this.qtdComodos = qtdComodos;
	}

	public Integer getQtdBanheiros() {
		return qtdBanheiros;
	}

	public void setQtdBanheiros(Integer qtdBanheiros) {
		this.qtdBanheiros = qtdBanheiros;
	}

	public Integer getQtdEmpregadosDomesticos() {
		return qtdEmpregadosDomesticos;
	}

	public void setQtdEmpregadosDomesticos(Integer qtdEmpregadosDomesticos) {
		this.qtdEmpregadosDomesticos = qtdEmpregadosDomesticos;
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
		SituacaoSocioEconomica other = (SituacaoSocioEconomica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SituacaoSocioEconomica [id=" + id + "]";
	}
	
}
