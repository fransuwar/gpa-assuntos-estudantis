package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;
import br.ufc.npi.auxilio.utils.ConstantsTest;


public class CadastroSelecaoSteps {
	
	private final PageFactory pages;
	private final WebDriver driver;
	
	public CadastroSelecaoSteps() {
		System.setProperty("webdriver.firefox.bin", "/home/amarildo.junior/Downloads/firefox/firefox");
		
		this.driver = new FirefoxDriver();
		this.pages = new PageFactory(driver);
	}
	
	@BeforeStory
	public void efetuarLogin() {
		pages.login().acessar();
		pages.login().logar();
	}
	
//	@BeforeStory
//	public void clicaBTNovaSelecao() {
//		pages.principal().clicarButtonCadastrar();
//	}
	
	@Given("o usuário acessa a página de cadastro de seleções")
	public void acessarPaginaCadastrar() throws InterruptedException {
		pages.cadastroSelecao().acessar();
	}
	
	@When("o usuário seleciona o tipo de seleção")
	public void selecionarTipoSelecao() {
		pages.cadastroSelecao().preencheCampoSelecao();
	}
	
	@When("preenche o campo Ano")
	public void campoAnoPreenchido() {
		pages.cadastroSelecao().preencheCampoAno();
	}
	
	@When("preenche o campo Início das Inscrições")
	public void campoInicioInscricoesPreenchido() {
		pages.cadastroSelecao().preencheCampoInicioInscricoes();
	}
	
	@When("preenche o campo Término das Inscrições")
	public void campoTerminoInscricoesPreenchido() {
		pages.cadastroSelecao().preencheCampoTerminoInscricoes();
	}
	
	@When("preenche o campo Vagas")
	public void campoVagasPreenchido() {
		pages.cadastroSelecao().preencheCampoVagas();
	}
	
	@When("não preenche o campo Ano")
	public void naoPreencheCampoAno() {
		pages.cadastroSelecao().naoPreencherCampoAno();
	}
	
	@When("não preenche o campo Início das Inscrições")
	public void naoPreencheCampoInicioInscricoes() {
		pages.cadastroSelecao().naoPreencherCampoInicioInscricoes();
	}
	
	@When("não preenche o campo Término das Inscrições")
	public void naoPreencheTerminoInscricoes() {
		pages.cadastroSelecao().naoPreencherCampoTerminoInscricoes();
	}
	
	@When("clica no botão salvar")
	public void clicaBTSalvar() {
		pages.cadastroSelecao().clicarButtonSalvar();
	}
	
	@When("o usuário cancela o cadastro clicando no botão cancelar")
	public void clicaBTCancelar() {
		pages.cadastroSelecao().clicarButtonCancelar();
	}
	
	@Then("o sistema redireciona para a página de detalhes")
	public void acesaarPaginaDetalhes() {
		pages.detalhesSelecao().acessar();
	}
	
	@Then("o sistema redireciona para a página de listagem das seleções cadastradas")
	public void acessarPaginaListar() {
		pages.listarSelecoes().acessar();
	}
	
	@Then("o sistema deve exibir uma mensagem")
	public void exibirMensagem() {
		assertEquals(pages.detalhesSelecao().exibirMensagem(), ConstantsTest.MENSAGEM_SUCESSO_CADASTRO);
	}
	
	
	@AfterStory
	public void close() {
		driver.close();
	}
}
