package br.ufc.npi.auxilio.test.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;

public class VisualizarAlterarInscricoesSteps {

	private final PageFactory pages;

	public VisualizarAlterarInscricoesSteps(WebDriver driver) {
		this.pages = new PageFactory(driver);
	}

	@Given("um servidor visualize inscrições de uma seleção")
	public void paginaVisualizarIncricoes() {
		this.pages.visualizar().acessar();
	}

	@Given("um servidor possa clicar no botão referente ao formulário de parecer final")
	public void clicarBotaoParcerFinal() {
		this.pages.visualizar().acessar();
	}

	@Given("um servidor clique em parecer final")
	public void cliqueParecerFinal() {
		this.pages.visualizar().clickParecerFinal();
	}

	@Given("um servidor deseje alterar os dados do campo de observações do formulário")
	public void existeCompoObservacoes() {
		this.pages.visualizar().clickCampoObservacoes();
	}

	@Given("um servidor deseje alterar os dados do campo de resultado do formulário")
	public void existeCampoResultado() {
		this.pages.visualizar().verificaCampoResultado();
	}

	@Given("servidor deseje visualizar o formulário referente ao de parecer final")
	public void exibiuFormulario() {
		botaoParecerFinalClicado();
	}

	@When("o resultado de Análise Documentação, Entrevista e Visita Domiciliar forem $valor")
	public void valorDeferido(String valor) {
		this.pages.visualizar().verificaStatusDeferido(valor);
	}

	@When("o resultado de $documentação seja $valor")
	public void umDos3Resultados(String documentacao, String valor) {
		this.pages.visualizar().verificaStatus(documentacao, valor);
	}

	@When("o resultado do parecer final seja deferido")
	public void botaoParecerFinalDeferido() {
		this.pages.visualizar().verificaStatusDeferidoParecerFinal();
	}

	@When("o resultado do parecer final seja indeferido")
	public void botaoParecerFinalIndeferido() {
		this.pages.visualizar().verificaStatusIndeferidoParecerFinal();
	}

	@When("o resultado da AnaliseDocumentação seja deferido")
	public void botaoAnaliseDocumentacaoDeferido() {
		this.pages.visualizar().verificaStatusDeferidoAnaliseDocumentacao();
	}

	@When("o resultado da AnaliseDocumentação seja indeferido")
	public void botaoAnaliseDocumentacaoIndeferido() {
		this.pages.visualizar().verificaStatusIndeferidoAnaliseDocumentacao();
	}

	@When("o resultado da Entrevista seja deferido")
	public void botaoEntrevistaDeferido() {
		this.pages.visualizar().verificaStatusDeferidoEntrevista();
	}

	@When("o resultado da Entrevista seja indeferido")
	public void botaoEntrevistaIndeferido() {
		this.pages.visualizar().verificaStatusIndeferidoEntrevista();
	}

	@When("o resultado da VisitaDomiciliar seja deferido")
	public void botaoVisitaDomiciliarDeferido() {
		this.pages.visualizar().verificaStatusDeferidoVisitaDomiciliar();
	}

	@When("o resultado da VisitaDomiciliar seja indeferido")
	public void botaoVisitaDomiciliarIndeferido() {
		this.pages.visualizar().verificaStatusIndeferidoVisitaDomiciliar();
	}

	@When("o botão referente ao formulário de parecer final é clicado")
	public void botaoParecerFinalClicado() {
		this.pages.visualizar().clickButtonParecerFinal();
	}

	@When("o parecer final é clicado")
	public void parecerFinalClicado() {
		this.pages.visualizar().clickParecerFinal();
	}

	@When("o $texto no campo de observações é feita e o botão de salvar é clicado")
	public void alteracaoObservacoesEfeita(String texto) {
		this.pages.visualizar().alterarDadosCampoObservacoes(texto);
	}

	@When("a alteração no campo de resultado $alteracao é feita e o botão de salvar é clicado")
	public void alteracaoResultadoEfeita(String alteracao) {
		this.pages.visualizar().alterarCampoResultado(alteracao);
	}

	@When("o formulário é exibido")
	public void ehExibido() {
		this.pages.visualizar().verificaFormularioExibido();
	}

	@Then("o resultado do Parecer Final será $valorParecer")
	public void resultadoParecerFinal(String valorParecer) {
		this.pages.visualizar().verificaResultadoParecerFinal(valorParecer);
	}

	@Then("a cor do ícone será $verde")
	public void corVerde(String verde) {
		this.pages.visualizar().verificaCorIconeVerde();
	}

	@Then("a cor do ícone deve ser $vermelho")
	public void corVermelho(String vermelho) {
		this.pages.visualizar().verificaCorIconeVermelho();
	}

	@Then("uma tela é exibida referente ao formulário de parecer final possibilitando alterações sobre o mesmo")
	public void exibirFormulario() {
		this.pages.visualizar().verificaFormularioExibido();
	}

	@Then("nada é para ser exibido além do que já estava sendo exibido")
	public void naoExibirFormulario() {
		this.pages.visualizar().verificaFormularioNaoExibido();
	}

	@Then("o campo do formulário referente a observações é alterado de acordo com o $texto")
	public void alteracaoObservacoesConcluida(String texto) {
		this.pages.visualizar().verificarAlteracaoCampoObservacao(texto);
	}

	@Then("a tela de inscrições para aquela seleção é mostrada novamente")
	public void refreshFeito() {
		this.pages.visualizar().verificaPaginaVisualizarInscricoes();
	}

	@Then("o campo do formulário referente a resultado é alterado de acordo com a $alteracao")
	public void alteracaoResultadoConcluida(String alteracao) {
		this.pages.visualizar().alteracaoCampoResultado(alteracao);
	}

	@Then("os campos de edição que o servidor pode editar são resultado e observações")
	public void podeEditar() {
		this.pages.visualizar().verificaEditavel();
	}

}
