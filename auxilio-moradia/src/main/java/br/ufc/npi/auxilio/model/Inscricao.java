package br.ufc.npi.auxilio.model;

import br.ufc.npi.auxilio.enums.*;
import br.ufc.npi.auxilio.model.questionario.HistoricoEscolar;
import br.ufc.npi.auxilio.model.questionario.Identificacao;
import br.ufc.npi.auxilio.model.questionario.Moradia;
import br.ufc.npi.auxilio.model.questionario.SituacaoSocioeconomica;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Inscricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDateTime data;

	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	private boolean classificada;
	
	private boolean realizarVisita;
	
	private boolean consolidada;
	
	@OneToOne
	private Selecao selecao;
	
	@ManyToOne
	private Aluno aluno;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private QuestionarioAuxilioMoradia questionario;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private VisitaDomiciliar visitaDomiciliar;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Entrevista entrevista;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private AnaliseDocumentacao analiseDocumentacao;

	public boolean isConsolidada() {
		return consolidada;
	}

	public void setConsolidada(boolean consolidada) {
		this.consolidada = consolidada;
	}

	public boolean isRealizarVisita() {
		return realizarVisita;
	}

	public void setRealizarVisita(boolean realizarVisita) {
		this.realizarVisita = realizarVisita;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Selecao getSelecao() {
		return selecao;
	}

	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
	}

	public QuestionarioAuxilioMoradia getQuestionario() {
		if (questionario == null) {
			return new QuestionarioAuxilioMoradia();
		}
		return questionario;
	}

	public void setQuestionario(QuestionarioAuxilioMoradia questionario) {
		this.questionario = questionario;
	}

	public VisitaDomiciliar getVisitaDomiciliar() {
		if(visitaDomiciliar == null){
			visitaDomiciliar = new VisitaDomiciliar();
		}
		return visitaDomiciliar;
	}

	public void setVisitaDomiciliar(VisitaDomiciliar visitaDomiciliar) {
		this.visitaDomiciliar = visitaDomiciliar;
	}

	public Entrevista getEntrevista() {
		return entrevista;
	}

	public void setEntrevista(Entrevista entrevista) {
		this.entrevista = entrevista;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public boolean isClassificada() {
		return classificada;
	}

	public void setClassificada(boolean classificada) {
		this.classificada = classificada;
	}

	public AnaliseDocumentacao getAnaliseDocumentacao() {
		return analiseDocumentacao;
	}

	public void setAnaliseDocumentacao(AnaliseDocumentacao documentacao) {
		this.analiseDocumentacao = documentacao;
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
		Inscricao other = (Inscricao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setIdentificacao(Identificacao identificacao) {
		this.aluno.getPessoa().setRg(identificacao.getIdentidade());
		this.aluno.getPessoa().setOrgaoEmissorRg(identificacao.getOrgaoEmissor());
		this.aluno.getPessoa().setUfRg(identificacao.getUfIdentidade());
		this.aluno.getPessoa().setNaturalidade(identificacao.getNaturalidade());
		this.aluno.getPessoa().setUfNaturalidade(identificacao.getUfNaturalidade());
		this.aluno.getPessoa().setEstadoCivil(identificacao.getEstadoCivil());
		this.aluno.getPessoa().setTelefone(identificacao.getContato());
		this.aluno.setIra(identificacao.getIra());

		// Dados bancários
		if (questionario == null) {
			questionario = new QuestionarioAuxilioMoradia();
		}
		this.questionario.setBanco(identificacao.getBanco());
		this.questionario.setAgencia(identificacao.getAgencia());
		this.questionario.setContaCorrente(identificacao.getContaCorrente());
	}

	public Identificacao getIdentificacao() {
		Identificacao identificacao = new Identificacao();
		identificacao.setIdentidade(aluno.getPessoa().getRg());
		identificacao.setOrgaoEmissor(aluno.getPessoa().getOrgaoEmissorRg());
		identificacao.setUfIdentidade(aluno.getPessoa().getUfRg());
		identificacao.setNaturalidade(aluno.getPessoa().getNaturalidade());
		identificacao.setUfNaturalidade(aluno.getPessoa().getUfNaturalidade());
		identificacao.setEstadoCivil(aluno.getPessoa().getEstadoCivil());
		identificacao.setContato(aluno.getPessoa().getTelefone());
		identificacao.setIra(aluno.getIra());

		// Dados bancários
		if (questionario == null) {
			questionario = new QuestionarioAuxilioMoradia();
		}
		identificacao.setBanco(this.questionario.getBanco());
		identificacao.setAgencia(this.questionario.getAgencia());
		identificacao.setContaCorrente(this.questionario.getContaCorrente());
		return identificacao;
	}

	public Moradia getMoradia() {
		Moradia moradia = new Moradia();

		// Núcleo familiar
		if (questionario == null) {
			questionario = new QuestionarioAuxilioMoradia();
		}
		moradia.setMae(questionario.getNomeMae());
		moradia.setPai(questionario.getNomePai());

		// Moradia de origem
		moradia.setMoradoresOrigem(questionario.getMoradoresOrigem());
		moradia.setOutroMoradorOrigem(questionario.getOutroMoradorOrigem());
		moradia.setEnderecoOrigem(questionario.getEnderecoOrigem());
		moradia.setNumeroOrigem(questionario.getNumeroOrigem());
		moradia.setBairroOrigem(questionario.getBairroOrigem());
		moradia.setCidadeOrigem(questionario.getCidadeOrigem());
		moradia.setEstadoOrigem(questionario.getEstadoOrigem());
		moradia.setCepOrigem(questionario.getCepOrigem());
		moradia.setReferenciaOrigem(questionario.getReferenciaOrigem());

		// Moradia de origem - outras informações
		moradia.setSituacaoImovel(questionario.getSituacaoImovel());
		moradia.setFinanciamento(questionario.getFinanciamento());
		moradia.setQuantidadeBemMovel(questionario.getQuantidadeBemMovel());
		moradia.setDescricaoBemMovel(questionario.getDescricaoBemMovel());

		// Moradia atual;
		moradia.setMoradores(questionario.getMoradores());
		moradia.setOutroMorador(questionario.getOutroMorador());
		moradia.setEndereco(questionario.getEndereco());
		moradia.setNumero(questionario.getNumero());
		moradia.setBairro(questionario.getBairro());
		moradia.setCidade(questionario.getCidade());
		moradia.setEstado(questionario.getEstado());
		moradia.setCep(questionario.getCep());
		moradia.setReferencia(questionario.getReferencia());

		return moradia;
	}

	public void setMoradia(Moradia moradia) {
		// Núcleo familiar
		questionario.setNomeMae(moradia.getMae());
		questionario.setNomePai(moradia.getPai());

		// Moradia de origem
		questionario.setMoradoresOrigem(moradia.getMoradoresOrigem());
		if (moradia.getMoradoresOrigem() != null && moradia.getMoradoresOrigem().contains(MoradoresOrigem.OUTROS)) {
			questionario.setOutroMoradorOrigem(moradia.getOutroMoradorOrigem());
		} else {
			questionario.setOutroMoradorOrigem(null);
		}
		questionario.setEnderecoOrigem(moradia.getEnderecoOrigem());
		questionario.setNumeroOrigem(moradia.getNumeroOrigem());
		questionario.setBairroOrigem(moradia.getBairroOrigem());
		questionario.setCidadeOrigem(moradia.getCidadeOrigem());
		questionario.setEstadoOrigem(moradia.getEstadoOrigem());
		questionario.setCepOrigem(moradia.getCepOrigem());
		questionario.setReferenciaOrigem(moradia.getReferenciaOrigem());

		// Moradia de origem - outras informações
		questionario.setSituacaoImovel(moradia.getSituacaoImovel());
		if (moradia.getSituacaoImovel() != null && moradia.getSituacaoImovel().equals(SituacaoImovel.FINANCIADO)) {
			questionario.setFinanciamento(moradia.getFinanciamento());
		} else {
			questionario.setFinanciamento(null);
		}
		questionario.setQuantidadeBemMovel(moradia.getQuantidadeBemMovel());
		if (moradia.getQuantidadeBemMovel() != null && moradia.getQuantidadeBemMovel() > 0) {
			questionario.setDescricaoBemMovel(moradia.getDescricaoBemMovel());
		} else {
			questionario.setDescricaoBemMovel(null);
		}

		// Moradia atual
		questionario.setMoradores(moradia.getMoradores());
		if (moradia.getMoradores() != null && moradia.getMoradores().contains(Moradores.OUTROS)) {
			questionario.setOutroMorador(moradia.getOutroMorador());
		} else {
			questionario.setOutroMorador(null);
		}

		questionario.setEndereco(moradia.getEndereco());
		questionario.setNumero(moradia.getNumero());
		questionario.setBairro(moradia.getBairro());
		questionario.setCidade(moradia.getCidade());
		questionario.setEstado(moradia.getEstado());
		questionario.setCep(moradia.getCep());
		questionario.setReferencia(moradia.getReferencia());
	}

	public HistoricoEscolar getHistoricoEscolar() {
		HistoricoEscolar historico = new HistoricoEscolar();

		historico.setEnsinoMedio(questionario.getEnsinoMedio());
		historico.setBolsistaEnsinoMedio(questionario.isBolsistaEnsinoMedio());
		historico.setPercentualEnsinoMedio(questionario.getPercentualEnsinoMedio());
		historico.setQuantidadeParticipacaoAuxilio(questionario.getQuantidadeParticipacaoAuxilio());
		historico.setBolsaAtual(questionario.getBolsaAtual());
		historico.setOutraGraduacao(questionario.getOutraGraduacao());
		historico.setServicos(questionario.getServicos());
		historico.setOutroServico(questionario.getOutroServico());

		// Trajeto até a universidade
		historico.setTrajetos(questionario.getTrajetos());
		historico.setOutroTrajeto(questionario.getOutroTrajeto());
		historico.setValorMensalTransporte(questionario.getValorMensalTransporte());
		historico.setDistancia(questionario.getDistancia());
		historico.setTempoGasto(questionario.getTempoGasto());

		return historico;
	}

	public void setHistoricoEscolar(HistoricoEscolar historico) {
		questionario.setEnsinoMedio(historico.getEnsinoMedio());
		if(historico.getEnsinoMedio() != null && !historico.getEnsinoMedio().equals(TipoEnsino.PUBLICO)) {
			questionario.setBolsistaEnsinoMedio(historico.isBolsistaEnsinoMedio());
		} else {
			questionario.setBolsistaEnsinoMedio(false);
		}
		questionario.setPercentualEnsinoMedio(historico.isBolsistaEnsinoMedio() ? historico.getPercentualEnsinoMedio() : null);
		if(historico.getQuantidadeParticipacaoAuxilio() != null && historico.getQuantidadeParticipacaoAuxilio() > 0) {
			questionario.setQuantidadeParticipacaoAuxilio(historico.getQuantidadeParticipacaoAuxilio());
		} else {
			questionario.setQuantidadeParticipacaoAuxilio(null);
		}
		questionario.setBolsaAtual(historico.getBolsaAtual());
		questionario.setOutraGraduacao(historico.getOutraGraduacao());
		questionario.setServicos(historico.getServicos());
		if (historico.getServicos() != null && historico.getServicos().contains(ServicosProReitoria.OUTROS)) {
			questionario.setOutroServico(historico.getOutroServico());
		} else {
			questionario.setOutroServico(null);
		}

		// Trajeto até a universidade
		questionario.setTrajetos(historico.getTrajetos());
		if (historico.getTrajetos() != null && historico.getTrajetos().contains(Trajeto.OUTROS)) {
			questionario.setOutroTrajeto(historico.getOutroTrajeto());
		} else {
			questionario.setOutroTrajeto(null);
		}
		questionario.setValorMensalTransporte(historico.getValorMensalTransporte());
		questionario.setDistancia(historico.getDistancia());
		questionario.setTempoGasto(historico.getTempoGasto());
	}

	public SituacaoSocioeconomica getSituacaoSocioeconomica() {
		SituacaoSocioeconomica situacao = new SituacaoSocioeconomica();

		situacao.setMedicamento(questionario.isMedicamento());
		situacao.setDoencaMedicamento(questionario.getDoencaMedicamento());
		situacao.setDeficiencia(questionario.isDeficiencia());
		situacao.setNomeDeficiencia(questionario.getNomeDeficiencia());
		situacao.setDoencaGrave(questionario.isDoencaGrave());
		situacao.setMembroDoencaGrave(questionario.getMembroDoencaGrave());
		situacao.setMembroDeficiencia(questionario.isMembroDeficiencia());
		situacao.setNomeMembroDeficiencia(questionario.getNomeMembroDeficiencia());
		situacao.setAssistenciaMedica(questionario.isAssistenciaMedica());
		situacao.setValorAssistenciaMedica(questionario.getValorAssistenciaMedica());
		situacao.setDespesaMedicamento(questionario.isDespesaMedicamento());
		situacao.setDescricaoDespesaMedicamento(questionario.getDescricaoDespesaMedicamento());
		situacao.setBeneficio(questionario.isBeneficio());
		situacao.setDescricaoBeneficio(questionario.getDescricaoBeneficio());

		return situacao;
	}

	public void setSituacaoSocioEconomica(SituacaoSocioeconomica situacao) {
		questionario.setMedicamento(situacao.isMedicamento());
		questionario.setDoencaMedicamento(situacao.isMedicamento() ? situacao.getDoencaMedicamento() : null);
		questionario.setDeficiencia(situacao.isDeficiencia());
		questionario.setNomeDeficiencia(situacao.isDeficiencia() ? situacao.getNomeDeficiencia() : null);
		questionario.setDoencaGrave(situacao.isDoencaGrave());
		questionario.setMembroDoencaGrave(situacao.isDoencaGrave() ? situacao.getMembroDoencaGrave() : null);
		questionario.setMembroDeficiencia(situacao.isMembroDeficiencia());
		questionario.setNomeMembroDeficiencia(situacao.isMembroDeficiencia() ? situacao.getNomeMembroDeficiencia() : null);
		questionario.setAssistenciaMedica(situacao.getAssistenciaMedica());
		questionario.setValorAssistenciaMedica(situacao.getAssistenciaMedica() ? situacao.getValorAssistenciaMedica() : null);
		questionario.setDespesaMedicamento(situacao.isDespesaMedicamento());
		questionario.setDescricaoDespesaMedicamento(situacao.isDespesaMedicamento() ? situacao.getDescricaoDespesaMedicamento() : null);
		questionario.setBeneficio(situacao.isBeneficio());
		questionario.setDescricaoBeneficio(situacao.isBeneficio() ? situacao.getDescricaoBeneficio() : null);
	}

	public Double getRendaTotal() {
		Double total = 0.0;
		for (PessoaFamilia pessoa : questionario.getGrupoFamiliar()) {
			total += pessoa.getRendaMensal();
		}
		return total;
	}

	public Double getRendaPerCapita() {
		return questionario.getGrupoFamiliar().isEmpty() ? 0.0 : getRendaTotal()/questionario.getGrupoFamiliar().size();
	}

	public Double getRendaPai() {
		for (PessoaFamilia pessoa : questionario.getGrupoFamiliar()) {
			if (GrauParentesco.PAI.equals(pessoa.getParentesco())) {
				return pessoa.getRendaMensal();
			}
		}
		return 0.0;
	}

	public Double getRendaMae() {
		for (PessoaFamilia pessoa : questionario.getGrupoFamiliar()) {
			if (GrauParentesco.MAE.equals(pessoa.getParentesco())) {
				return pessoa.getRendaMensal();
			}
		}
		return 0.0;
	}

	// Retorna a soma da renda de todos os membros, exceto pai e mãe
	public Double getRendaOutros() {
		return getRendaTotal() - getRendaMae() - getRendaPai();
	}

	public String getProfissaoMae() {
		for (PessoaFamilia pessoa : questionario.getGrupoFamiliar()) {
			if (GrauParentesco.MAE.equals(pessoa.getParentesco())) {
				return pessoa.getProfissao();
			}
		}
		return null;
	}

	public String getProfissaoPai() {
		for (PessoaFamilia pessoa : questionario.getGrupoFamiliar()) {
			if (GrauParentesco.PAI.equals(pessoa.getParentesco())) {
				return pessoa.getProfissao();
			}
		}
		return null;
	}

}
