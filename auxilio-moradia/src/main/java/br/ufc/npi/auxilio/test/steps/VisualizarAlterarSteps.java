package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.LoginPage;

public class VisualizarAlterarSteps {
	 
	private WebDriver driver;
	
	public VisualizarAlterarSteps(){
		System.setProperty("webdriver.firefox.bin", "/home/amarildo.junior/Downloads/firefox/firefox");
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/login");
	}
	
	@BeforeStories
	public void acessar() {
		//cenários iniciam com o usuário já logado
		LoginPage acesso = new LoginPage(driver);
		acesso.logar();
	}
	
	@Given("um servidor visualize inscrições de uma seleção")
	public void paginaVisualizarIncricoes(){
		driver.get("http://localhost:8080/selecao/inscricoes/52");
	}
	
	@Given("um servidor possa clicar no botão referente ao formulário de parecer final")
	public void clicarBotaoParcerFinal(){
		driver.get("http://localhost:8080/selecao/inscricoes/52");
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
	
	@When("o resultado de Análise Documentação, Entrevista e Visita Domiciliar forem $valor")
	public void valorDeferido(String valor) {
		WebElement documentacao = driver.findElement(By.xpath(".//a[@href='/documentacao/inscricao/27']/div[@id='inscDocumentacao']"));
		WebElement entrevista = driver.findElement(By.xpath(".//a[@href='/entrevista/27']/div[@id='inscEntrevista']"));
		WebElement visita = driver.findElement(By.xpath(".//a[@href='/visita/27']/div[@id='inscVisita']"));
		
		assertEquals(documentacao.getText(), valor);
		assertEquals(entrevista.getText(), valor);
		assertEquals(visita.getText(), valor);
	}
	
	@When("o resultado de $documentação seja $valor")
	public void umDos3Resultados(String documentacao, String valor){
		WebElement iddoc;
		switch(documentacao){
			case "AnaliseDocumentacao":
				iddoc= driver.findElement(By.xpath("//a[@href='/documentacao/inscricao/28']/div[@id='inscDocumentacao']"));
				assertEquals(iddoc.getText(), valor);
				break;
			case "Entrevista":
				iddoc= driver.findElement(By.xpath("//a[@href='/entrevista/28']/div[@id='inscEntrevista']"));
				assertEquals(iddoc.getText(), valor);
				break;
			case "VisitaDomiciliar":
				iddoc= driver.findElement(By.xpath("//a[@href='/visita/28']/div[@id='inscVisita']"));
				assertEquals(iddoc.getText(), valor);
				break;
		}
	}
	
	@When("o resultado do parecer final seja deferido")
	public void botaoParecerFinalDeferido(){
		WebElement botaoParecerFinal = driver.findElement(By.xpath(".//a[@data-id='27']/div[@id='inscResult']"));
		assertEquals(botaoParecerFinal.getText(), "Deferido");
	}
	
	@When("o resultado do parecer final seja indeferido")
	public void botaoParecerFinalIndeferido(){
		WebElement botaoParecerFinal = driver.findElement(By.xpath(".//a[@data-id='28']/div[@id='inscResult']"));
		assertEquals(botaoParecerFinal.getText(), "Indeferido");
	}
	
	@When("o resultado da AnaliseDocumentação seja deferido")
	public void botaoAnaliseDocumentacaoDeferido(){
		WebElement botaoDocumentacao = driver.findElement(By.xpath("//a[@href='/documentacao/inscricao/27']/div[@id='inscDocumentacao']"));
		assertEquals(botaoDocumentacao.getText(), "Deferido");
	}
	
	@When("o resultado da AnaliseDocumentação seja indeferido")
	public void botaoAnaliseDocumentacaoIndeferido(){
		WebElement botaoDocumentacao = driver.findElement(By.xpath("//a[@href='/documentacao/inscricao/26']/div[@id='inscDocumentacao']"));
		assertEquals(botaoDocumentacao.getText(), "Indeferido");
	}
	
	@When("o resultado da Entrevista seja deferido")
	public void botaoEntrevistaDeferido(){
		WebElement botaoEntrevista = driver.findElement(By.xpath("//a[@href='/entrevista/27']/div[@id='inscEntrevista']"));
		assertEquals(botaoEntrevista.getText(), "Deferido");
	}
	
	@When("o resultado da Entrevista seja indeferido")
	public void botaoEntrevistaIndeferido(){
		WebElement botaoEntrevista = driver.findElement(By.xpath("//a[@href='/entrevista/28']/div[@id='inscEntrevista']"));
		assertEquals(botaoEntrevista.getText(), "Indeferido");
	}
	
	@When("o resultado da VisitaDomiciliar seja deferido")
	public void botaoVisitaDomiciliarDeferido(){
		WebElement botaoVisita = driver.findElement(By.xpath("//a[@href='/visita/27']/div[@id='inscVisita']"));
		assertEquals(botaoVisita.getText(), "Deferido");
	}
	
	@When("o resultado da VisitaDomiciliar seja indeferido")
	public void botaoVisitaDomiciliarIndeferido(){
		WebElement botaoVisita = driver.findElement(By.xpath("//a[@href='/visita/28']/div[@id='inscVisita']"));
		assertEquals(botaoVisita.getText(), "Indeferido");
	}
	
	@When("o botão referente ao formulário de parecer final é clicado")
	public void botaoParecerFinalClicado(){
		WebElement botaoParecer = driver.findElement(By.xpath(".//div[@id='inscResult']"));
		botaoParecer.click();
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
	
	
	@Then("o resultado do Parecer Final será $valorParecer")
	public void resultadoParecerFinal(String valorParecer){
		WebElement idParecer;
		
		switch(valorParecer){
			case "Deferido":
				idParecer = driver.findElement(By.xpath(".//a[@data-id='27']/div[@id='inscResult']"));
				assertEquals(idParecer.getText(), valorParecer);
				break;
			case "Indeferido":
				idParecer = driver.findElement(By.xpath(".//a[@data-id='28']/div[@id='inscResult']"));
				assertEquals(idParecer.getText(), valorParecer);
				break;
		}
	}
	
	@Then("a cor do ícone será $verde")
	public void corVerde(String verde){
		WebElement botao = driver.findElement(By.xpath(".//div[@class='chip green lighten-1']"));
		WebElement cor = driver.findElement(By.cssSelector(".green.lighten-1"));
		assertEquals(botao, cor);
	}
	
	@Then("a cor do ícone deve ser $vermelho")
	public void corVermelho(String vermelho){
		WebElement botao = driver.findElement(By.xpath(".//div[@class='chip red lighten-1']"));
		WebElement cor = driver.findElement(By.cssSelector(".red.lighten-1"));
		assertEquals(botao, cor);
	}
	//ainda não está passando por aqui
	@Then("uma tela é exibida referente ao formulário de parecer final possibilitando alterações sobre o mesmo")
	public void exibirFormulario(){
		WebElement modal = driver.findElement(By.xpath(".//div[@id='modal1']"));
		assertEquals(modal.getClass(), "modal open");
		
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
