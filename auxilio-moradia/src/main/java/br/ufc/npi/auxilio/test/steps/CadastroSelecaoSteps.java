package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;

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
		System.setProperty("webdriver.firefox.bin", "/home/lucas.santos/Downloads/firefox/firefox");
		
		this.driver = new FirefoxDriver();
		this.pages = new PageFactory(driver);
	}
	
	@BeforeStory
	public void efetuarLogin() {
		pages.login().acessar();
		pages.login().logar();
	}
	
	@Given("o usuário acessa a página de cadastro de seleções")
	public void acessarPaginaCadastrar() throws InterruptedException {
		pages.cadastroSelecao().acessar();
	}
	
	@When("o usuário seleciona o tipo de seleção $selecao")
	public void selecionarTipoSelecao(String selecao) {
		pages.cadastroSelecao().preencheCampoSelecao(selecao);
	}
	
	@When("preenche o campo Ano com o valor $ano")
	public void campoAnoPreenchido(String ano) {
		pages.cadastroSelecao().preencheCampoAno(ano);
	}
	
	@When("preenche o campo Início das Inscrições com a data $dataInicio")
	public void campoInicioInscricoesPreenchido(String dataInicio) {
		pages.cadastroSelecao().preencheCampoInicioInscricoes(dataInicio);
	}
	
//	@When("preenche o campo Término das Inscrições com a data $dataTermino")
//	public void campoTerminoInscricoesPreenchido(String dataTermino) {
//		pages.cadastroSelecao().preencheCampoTerminoInscricoes(dataTermino);
//	}
	
	@When("preenche o campo Vagas com o valor $vagas")
	public void campoVagasPreenchido(String vagas) {
		pages.cadastroSelecao().preencheCampoVagas(vagas);
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
	
	@Then("o sistema redireciona para a página de listagem das seleções cadastradas")
	public void acessarPaginaListar() {
		assertEquals(pages.listarSelecoes().titlePage(), ConstantsTest.TITLE_PAGE_LISTAR);
	}
	
	@Then("o sistema deve exibir a seguinte mensagem $mensagem")
	public void exibirMensagem(String mensagem) {
		assertEquals(pages.detalhesSelecao().exibirMensagem(), mensagem);
	}
	
	
//	@AfterStory
//	public void close() {
//		driver.close();
//	}
}
