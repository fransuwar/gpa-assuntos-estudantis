package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.LoginPage;

public class VisualizarAlterarSteps {
private WebDriver driver;
	

	@BeforeStories
	public void acessar() {
		System.setProperty("webdriver.firefox.bin", "/home/amarildo.junior/Downloads/firefox/firefox");
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/login");
		//cenários iniciam com o usuário já logado
		LoginPage acesso = new LoginPage(driver);
		acesso.logar();
	}
	
	@Given("um servidor visualize inscrições de uma seleção")
	public void paginaVisualizarIncricoes(){
		driver.get("http://localhost:8080/selecao/inscricoes/7");
	}
	
	@Given("um servidor clique no botão referente ao formulário de parecer final")
	public void clicarBotaoParcerFinal(){
		driver.findElement(By.id("inscResult"));
	}

	@Given("um servidor clique em parecer final")
	public void cliqueParecerFinal(){
		driver.findElement(By.id("visualizar-inscricoes"));
		driver.findElement(By.linkText("Parecer Final"));
	}
	
	@Given("um servidor deseje alterar os dados do campo de observações do formulário")
	public void existeCompoObservacoes() {
		driver.findElement(By.id("observacao"));
	}
	
	@Given("um servidor deseje alterar os dados do campo de resultado do formulário")
	public void existeCampoResultado() {
		driver.findElement(By.id(""));
	}
	
	@Given("servidor deseje visualizar o formulário referente ao de parecer final")
	public void exibiuFormulario() {
		driver.findElement(By.id("modal1"));
	}
	
	@When("o resultado de analiseDocumentação seja deferido")
	public void resultadoDeAnaliseDocumentacaoDeferido(){
		driver.findElement(By.id("inscDocumentacao"));
		driver.findElement(By.linkText("Deferido"));
	}
	
	@When("entrevista seja deferido")
	public void resultadoDeEntrevistaDeferido(){
		driver.findElement(By.id("inscEntrevista"));
		driver.findElement(By.linkText("Deferido"));
	}
	
	@When("visitaDomiciliar seja deferido")
	public void resultadoDeVisitaDomiciliarDeferido(){
		driver.findElement(By.id("inscVisita"));
		driver.findElement(By.linkText("Deferido"));
	}
	
	@When("o resultado de AnaliseDocumentação seja indeferido Ou entrevista seja indeferido Ou visitaDomiciliar seja indeferido")
	public void umDos3ResultadosIndeferido(){
		
	}
	
	@When("o resultado de AnaliseDocumentação seja não avaliado Ou Entrevista seja não avaliado Ou VisitaDomiciliar seja não avaliado")
	public void umDos3ResultadosNaoAvaliados(){

	}
	
	@When("o resultado do parecer final seja deferido")
	public void botaoParecerFinalDeferido(){
		driver.findElement(By.id("inscResult"));
		driver.findElement(By.linkText("Deferido"));
	}
	
	@When("o resultado do parecer final seja indeferido")
	public void botaoParecerFinalIndeferido(){
		driver.findElement(By.id("inscResult"));
		driver.findElement(By.linkText("Indeferido"));
	}
	
	@When("o resultado da AnaliseDocumentação seja deferido")
	public void botaoAnaliseDocumentacaoDeferido(){
		driver.findElement(By.id("inscDocumentacao"));
		driver.findElement(By.linkText("Deferido"));
	}
	
	@When("o resultado da AnaliseDocumentação seja indeferido")
	public void botaoAnaliseDocumentacaoIndeferido(){
		driver.findElement(By.id("inscDocumentacao"));
		driver.findElement(By.linkText("Indeferido"));
	}
	
	@When("o resultado da Entrevista seja deferido")
	public void botaoEntrevistaDeferido(){
		driver.findElement(By.id("inscEntrevista"));
		driver.findElement(By.linkText("Deferido"));
	}
	
	@When("o resultado da Entrevista seja indeferido")
	public void botaoEntrevistaIndeferido(){
		driver.findElement(By.id("inscEntrevista"));
		driver.findElement(By.linkText("Indeferido"));
	}
	
	@When("o resultado da VisitaDomiciliar seja deferido")
	public void botaoVisitaDomiciliarDeferido(){
		driver.findElement(By.id("inscVisita"));
		driver.findElement(By.linkText("Deferido"));
	}
	
	@When("o resultado da VisitaDomiciliar seja indeferido")
	public void botaoVisitaDomiciliarIndeferido(){
		driver.findElement(By.id("inscVisita"));
		driver.findElement(By.linkText("Indeferido"));
	}
	
	@When("o botão referente ao formulário de parecer final é clicado")
	public void botaoParecerFinalClicado(){
		driver.findElement(By.id("inscResult")).click();
	}
	
	@When("o parecer final é clicado")
	public void parecerFinalClicado(){
		driver.findElement(By.linkText("Parecer Final")).click();
	}
	
	@When("as alterações no campo de observações é feita e o botão de salvar é clicado")
	public void alteracaoObservacoesEfeita(){
		
	}
	
	@When("a alteração no campo de resultado é feita e o botão de salvar é clicado")
	public void alteracaoResultadoEfeita(){
		
	}
	
	@When("o formulário é exibido")
	public void ehExibido(){
		
	}
	
	@Then("o resultado de parecer final seja deferido")
	public void resultadoParecerFinalDeferido(){
		assertEquals(driver.findElement(By.id("inscResult")), driver.findElement(By.linkText("deferido")));
		//driver.findElement(By.id("inscResult"));
		//driver.findElement(By.linkText("deferido"));
	}
	
	@Then("o resultado de parecer final seja indeferido")
	public void resultadoParecerFinalIndeferido(){
	
	}
	
	@Then("a cor do ícone deve ser verde")
	public void corVerde(){
		
	}
	
	@Then("a cor do ícone deve ser vermelho")
	public void corVermelho(){
		
	}
	
	@Then("uma tela é exibida referente ao formulário de parecer final possibilitando alterações sobre o mesmo")
	public void exibirFormulario(){
		
	}
	
	@Then("nada é para ser exibido além do que já estava sendo exibido")
	public void naoExibirFormulario(){
		
	}
	
	@Then("o campo do formulário referente a observações é alterado")
	public void alteracaoObservacoesConcluida(){
		
	}
	
	@Then("a tela de inscições para aquela seleção é mostrada novamente")
	public void refreshFeito(){
		
	}
	
	@Then("o campo do formulário referente a resultado não é alterado")
	public void alteracaoResultadoConcluida(){
		
	}

	@Then("os campos de edição que o servidor pode editar são resultado e observações")
	public void podeEditar(){
		
	}
	
	@AfterStory
	public void close(){
		driver.close();
	}

}
