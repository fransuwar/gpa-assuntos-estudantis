package br.ufc.npi.auxilio.model;

import br.ufc.npi.auxilio.enums.Moradores;
import br.ufc.npi.auxilio.enums.MoradoresOrigem;
import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.model.questionario.DadosBancarios;
import br.ufc.npi.auxilio.model.questionario.Moradia;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Inscricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		if (questionario == null) {
			questionario = new QuestionarioAuxilioMoradia();
		}
		this.questionario.setBanco(dadosBancarios.getBanco());
		this.questionario.setAgencia(dadosBancarios.getAgencia());
		this.questionario.setContaCorrente(dadosBancarios.getContaCorrente());
	}

	public DadosBancarios getDadosBancarios() {
		if (questionario == null) {
			return new DadosBancarios();
		}
		return new DadosBancarios(questionario.getBanco(),
				questionario.getAgencia(), questionario.getContaCorrente());

	}

	public Moradia getMoradia() {
		Moradia moradia = new Moradia();

		// Núcleo familiar
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
		if (moradia.getMoradoresOrigem().contains(MoradoresOrigem.OUTROS.name())) {
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
		questionario.setFinanciamento(moradia.getFinanciamento());
		questionario.setQuantidadeBemMovel(moradia.getQuantidadeBemMovel());
		questionario.setDescricaoBemMovel(moradia.getDescricaoBemMovel());

		// Moradia atual
		questionario.setMoradores(moradia.getMoradores());
		if (moradia.getMoradores().contains(Moradores.OUTROS.name())) {
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
}
