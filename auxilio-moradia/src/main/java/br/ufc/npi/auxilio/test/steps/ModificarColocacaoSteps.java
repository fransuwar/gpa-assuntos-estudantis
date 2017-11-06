package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;
import br.ufc.npi.auxilio.utils.ConstantsTest;


public class ModificarColocacaoSteps {
	
	private final PageFactory pages;
	private final WebDriver driver;
	
	public ModificarColocacaoSteps() {
		System.setProperty("webdriver.firefox.bin", "/home/francisco.goncalves/Downloads/firefox/firefox");
		
		this.driver = new FirefoxDriver();
		this.pages = new PageFactory(driver);
	}
	
	@BeforeStory
	public void efetuarLogin() {
		pages.login().acessar();
		pages.login().logar();
	}
	
	@Given("o usuário acessa a página visualizar inscrições da seleção $selecao")
	public void acessarPaginaVisualizarSelecoes(String selecao) throws InterruptedException {
		pages.visualizarInscricoes().acessar(selecao);
	}
	
	@When("o usuário seleciona um aluno na posição $posicao da lista com parecer final DEFERIDO")
	public void selecionarAlunoDeferido(String posicao){
		pages.visualizarInscricoes().verificarDeferido(posicao);
	}
	
	@When("o usuário seleciona um aluno na posição $posicao da lista com parecer final INDEFERIDO")
	public void selecionarAlunoIndeferido(String posicao){
		pages.visualizarInscricoes().verificarIndeferido(posicao);
	}
	
	@When("o usuário clica no toggle button de selecionado do aluno $posicao")
	public void clicaSelecionado(String posicao){
		pages.visualizarInscricoes().alterarSelecionado(posicao);
	}	
	
	@When("o usuário arrasta o aluno $posicao1 para a posição do aluno $posicao2")
	public void mudarPosicaoAlunos(String posicao1, String posicao2) {
		pages.visualizarInscricoes().alterarPosicaoAluno(posicao1, posicao2);
	}
	
	@Then("o sistema altera o status de selecionado do aluno $posicao")
	public void verificaAlteracaoSelecionado(String posicao) {
		pages.visualizarInscricoes().verificaToggleSelecionados(posicao);
	}
		
	@Then("o sistema pode alterar a posição do aluno $posicao")
	public void verificaMobilidade(String posicao){
		pages.visualizarInscricoes().verificarMovel(posicao);
	}
	
	@Then("o sistema não pode alterar a posição do aluno $posicao")
	public void verificaImobilidade(String posicao){
		pages.visualizarInscricoes().verificarImovel(posicao);
	}
	
	@Then("o sistema mostra como desabilitado o toggle button de selecionado do aluno $posicao")
	public void verificarSelecionadoDesabilitado(String posicao) {
		pages.visualizarInscricoes().verificaToggleDesativado(posicao);
	}
	
	@Then("o sistema deve deixar este aluno na posição $posicaoAtual")
	public void verificarPosicaoAlunos(String posicaoAtual) {
		pages.visualizarInscricoes().verificarMudancaPosicao( posicaoAtual);
	}
	
	@AfterStory
	public void close() {
		driver.close();
	}
}
