package br.ufc.npi.auxilio.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.npi.auxilio.enums.Modalidades;
import br.ufc.npi.auxilio.enums.ParticipouSelecaoResultado;
import br.ufc.npi.auxilio.enums.Resultado;

@Entity
public class Entrevista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private Resultado parecer;
	
	private String endereco;
	
	private String contato;
	
	private Boolean renovacao;
	
	private Boolean aproveitamento;
	
	private String cursoSemestre;
	
	private Boolean concluiuOutra;
	
	private Boolean moraEmQuixada;
	
	private String comQuem;
	
	private Boolean temParente;
	
	private Boolean divideHabitacional;
	
	private Double valorAluguel;
	
	private Integer qtColegas;
	
	private Boolean provedor;
	
	private Boolean maeChefe;
	
	private Boolean temFilhos;
	
	private Boolean provedorComDespesas;
	
	private Integer qtIdosos;
	
	private Integer qtCriancas;
	
	private Boolean medicamento;
	
	private String qualMedicacento;
	
	private Boolean deficienteFisico;
	
	private String qualDeficiencia;
	
	private Boolean apresentaDoencaGrave;
	
	private String quemDoencaGrave;
	
	private Boolean possuiPlanoMedico;
	
	private Boolean apresentaDeficiencia;
	
	private String quemDeficiencia;
	
	private Boolean gastosComMedicamento;
	
	private String participacaoEmProgramas;
	
	private String atendidoCRAS;
	
	private String dinamicaFamiliar;
	
	private String relacoesAbitoAcademico;
	
	private Modalidades modalidade;
	
	private Boolean egressoPublicaSemCotas;
	
	private Boolean egressoParticularIntegral;
	
	private Boolean egressoParticularParcial;
	
	private ParticipouSelecaoResultado participouSelecao;
	
	private String principaisInteresses;
	
	private Boolean gostavaTrabalhoBIA;
	
	private Boolean interesseMesmoTrabalho;
	
	private String habilidadesDesenvolvidas;
	
	private String motivoProjeto;
	
	private String representaBIA;
	
	private String observacao;
	
	private boolean vaiVisitar;
	
	@ManyToOne
	private Servidor responsavel;
	
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getMotivoProjeto() {
		return motivoProjeto;
	}

	public void setMotivoProjeto(String motivoProjeto) {
		this.motivoProjeto = motivoProjeto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Boolean getRenovacao() {
		return renovacao;
	}

	public void setRenovacao(Boolean renovacao) {
		this.renovacao = renovacao;
	}

	public Boolean getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(Boolean aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public String getCursoSemestre() {
		return cursoSemestre;
	}

	public void setCursoSemestre(String cursoSemestre) {
		this.cursoSemestre = cursoSemestre;
	}

	public Boolean getConcluiuOutra() {
		return concluiuOutra;
	}

	public void setConcluiuOutra(Boolean concluiuOutra) {
		this.concluiuOutra = concluiuOutra;
	}

	public Boolean getMoraEmQuixada() {
		return moraEmQuixada;
	}

	public void setMoraEmQuixada(Boolean moraEmQuixada) {
		this.moraEmQuixada = moraEmQuixada;
	}

	public String getComQuem() {
		return comQuem;
	}

	public void setComQuem(String comQuem) {
		this.comQuem = comQuem;
	}

	public Boolean getTemParente() {
		return temParente;
	}

	public void setTemParente(Boolean temParente) {
		this.temParente = temParente;
	}

	public Boolean getDivideHabitacional() {
		return divideHabitacional;
	}

	public void setDivideHabitacional(Boolean divideHabitacional) {
		this.divideHabitacional = divideHabitacional;
	}

	public Double getValorAluguel() {
		return valorAluguel;
	}
	
	public void setValorAluguel(Double valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public Integer getQtColegas() {
		return qtColegas;
	}

	public void setQtColegas(Integer qtColegas) {
		this.qtColegas = qtColegas;
	}

	public Boolean getProvedor() {
		return provedor;
	}

	public void setProvedor(Boolean provedor) {
		this.provedor = provedor;
	}

	public Boolean getMaeChefe() {
		return maeChefe;
	}

	public void setMaeChefe(Boolean maeChefe) {
		this.maeChefe = maeChefe;
	}

	public Boolean getTemFilhos() {
		return temFilhos;
	}

	public void setTemFilhos(Boolean temFilhos) {
		this.temFilhos = temFilhos;
	}

	public Boolean getProvedorComDespesas() {
		return provedorComDespesas;
	}

	public void setProvedorComDespesas(Boolean provedorComDespesas) {
		this.provedorComDespesas = provedorComDespesas;
	}

	public Integer getQtIdosos() {
		return qtIdosos;
	}

	public void setQtIdosos(Integer qtIdosos) {
		this.qtIdosos = qtIdosos;
	}

	public Integer getQtCriancas() {
		return qtCriancas;
	}

	public void setQtCriancas(Integer qtCriancas) {
		this.qtCriancas = qtCriancas;
	}

	public Boolean getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Boolean medicamento) {
		this.medicamento = medicamento;
	}

	public String getQualMedicacento() {
		return qualMedicacento;
	}

	public void setQualMedicacento(String qualMedicacento) {
		this.qualMedicacento = qualMedicacento;
	}

	public Boolean getDeficienteFisico() {
		return deficienteFisico;
	}

	public void setDeficienteFisico(Boolean deficienteFisico) {
		this.deficienteFisico = deficienteFisico;
	}

	public String getQualDeficiencia() {
		return qualDeficiencia;
	}

	public void setQualDeficiencia(String qualDeficiencia) {
		this.qualDeficiencia = qualDeficiencia;
	}

	public Boolean getApresentaDoencaGrave() {
		return apresentaDoencaGrave;
	}

	public void setApresentaDoencaGrave(Boolean apresentaDoencaGrave) {
		this.apresentaDoencaGrave = apresentaDoencaGrave;
	}

	public String getQuemDoencaGrave() {
		return quemDoencaGrave;
	}

	public void setQuemDoencaGrave(String quemDoencaGrave) {
		this.quemDoencaGrave = quemDoencaGrave;
	}

	public Boolean getPossuiPlanoMedico() {
		return possuiPlanoMedico;
	}

	public void setPossuiPlanoMedico(Boolean possuiPlanoMedico) {
		this.possuiPlanoMedico = possuiPlanoMedico;
	}

	public Boolean getApresentaDeficiencia() {
		return apresentaDeficiencia;
	}

	public void setApresentaDeficiencia(Boolean apresentaDeficiencia) {
		this.apresentaDeficiencia = apresentaDeficiencia;
	}

	public String getQuemDeficiencia() {
		return quemDeficiencia;
	}

	public void setQuemDeficiencia(String quemDeficiencia) {
		this.quemDeficiencia = quemDeficiencia;
	}

	public Boolean getGastosComMedicamento() {
		return gastosComMedicamento;
	}

	public void setGastosComMedicamento(Boolean gastosComMedicamento) {
		this.gastosComMedicamento = gastosComMedicamento;
	}

	public String getParticipacaoEmProgramas() {
		return participacaoEmProgramas;
	}

	public void setParticipacaoEmProgramas(String participacaoEmProgramas) {
		this.participacaoEmProgramas = participacaoEmProgramas;
	}

	public String getAtendidoCRAS() {
		return atendidoCRAS;
	}

	public void setAtendidoCRAS(String atendidoCRAS) {
		this.atendidoCRAS = atendidoCRAS;
	}

	public String getDinamicaFamiliar() {
		return dinamicaFamiliar;
	}

	public void setDinamicaFamiliar(String dinamicaFamiliar) {
		this.dinamicaFamiliar = dinamicaFamiliar;
	}

	public String getRelacoesAbitoAcademico() {
		return relacoesAbitoAcademico;
	}

	public void setRelacoesAbitoAcademico(String relacoesAbitoAcademico) {
		this.relacoesAbitoAcademico = relacoesAbitoAcademico;
	}

	public Modalidades getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidades modalidade) {
		this.modalidade = modalidade;
	}

	public Boolean getEgressoPublicaSemCotas() {
		return egressoPublicaSemCotas;
	}

	public void setEgressoPublicaSemCotas(Boolean egressoPublicaSemCotas) {
		this.egressoPublicaSemCotas = egressoPublicaSemCotas;
	}

	public Boolean getEgressoParticularIntegral() {
		return egressoParticularIntegral;
	}

	public void setEgressoParticularIntegral(Boolean egressoParticularIntegral) {
		this.egressoParticularIntegral = egressoParticularIntegral;
	}

	public Boolean getEgressoParticularParcial() {
		return egressoParticularParcial;
	}

	public void setEgressoParticularParcial(Boolean egressoParticularParcial) {
		this.egressoParticularParcial = egressoParticularParcial;
	}

	public ParticipouSelecaoResultado getParticipouSelecao() {
		return participouSelecao;
	}

	public void setParticipouSelecao(ParticipouSelecaoResultado participouSelecao) {
		this.participouSelecao = participouSelecao;
	}

	public String getPrincipaisInteresses() {
		return principaisInteresses;
	}

	public void setPrincipaisInteresses(String principaisInteresses) {
		this.principaisInteresses = principaisInteresses;
	}

	public Boolean getGostavaTrabalhoBIA() {
		return gostavaTrabalhoBIA;
	}

	public void setGostavaTrabalhoBIA(Boolean gostavaTrabalhoBIA) {
		this.gostavaTrabalhoBIA = gostavaTrabalhoBIA;
	}

	public Boolean getInteresseMesmoTrabalho() {
		return interesseMesmoTrabalho;
	}

	public void setInteresseMesmoTrabalho(Boolean interesseMesmoTrabalho) {
		this.interesseMesmoTrabalho = interesseMesmoTrabalho;
	}

	public String getHabilidadesDesenvolvidas() {
		return habilidadesDesenvolvidas;
	}

	public void setHabilidadesDesenvolvidas(String habilidadesDesenvolvidas) {
		this.habilidadesDesenvolvidas = habilidadesDesenvolvidas;
	}

	public String getRepresentaBIA() {
		return representaBIA;
	}

	public void setRepresentaBIA(String representaBIA) {
		this.representaBIA = representaBIA;
	}
	
	public boolean isVaiVisitar() {
		return vaiVisitar;
	}

	public void setVaiVisitar(boolean vaiVisitar) {
		this.vaiVisitar = vaiVisitar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Resultado getParecer() {
		return parecer;
	}

	public void setParecer(Resultado parecer) {
		this.parecer = parecer;
	}

	public Servidor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
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
		Entrevista other = (Entrevista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
