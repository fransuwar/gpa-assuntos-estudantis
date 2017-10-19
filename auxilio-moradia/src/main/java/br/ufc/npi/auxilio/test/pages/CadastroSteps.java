package br.ufc.npi.auxilio.test.pages;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class CadastroSteps {

	private WebDriver driver; 
	private By fieldSelecao = By.id(ConstantsTest.ID_FIELD_TIPO);
	private By fieldAno = By.id(ConstantsTest.ID_FIELD_ANO);
	private By fieldInicio = By.id(ConstantsTest.ID_FIELD_INICIO);
	private By fieldTermino = By.id(ConstantsTest.ID_FIELD_TERMINO);
	private By fieldVagas = By.id(ConstantsTest.ID_FIELD_VAGAS);
	private By buttonSalvar = By.id(ConstantsTest.ID_BUTTON_SALVAR);
	
	@BeforeScenario
	public void acessar() {
		System.setProperty("webdriver.firefox.bin", "/home/lucas.santos/Downloads/firefox/firefox");
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/login");
	}
	
	@Given("o usuario se loga")
	public void logar() {
		driver.findElement(By.id(ConstantsTest.ID_FIELD_USERNAME)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_USERNAME)).sendKeys(ConstantsTest.LOGIN_VALUE);
		driver.findElement(By.id(ConstantsTest.ID_FIELD_PASSWORD)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_PASSWORD)).sendKeys(ConstantsTest.PASSWORD_VALUE);
		driver.findElement(By.id(ConstantsTest.ID_BUTTON_ENTRAR)).click();
		driver.findElement(By.id(ConstantsTest.ID_BUTTON_CADASTRAR)).click();
	}
	
	@When("o usuário seleciona o tipo de seleção")
	public void preencheCampoSelecao() {
		driver.findElement(fieldSelecao).clear();
		driver.findElement(fieldSelecao).sendKeys(ConstantsTest.TIPO_SELECAO_VALUE);
	}
	
	@When("preenche o campo Ano")
	public void preencheCampoAno() {
		driver.findElement(fieldAno).clear();
		driver.findElement(fieldAno).sendKeys(ConstantsTest.ANO_VALUE);
	}
	
	@When("preenche o campo Início das Inscrições")
	public void preencheCampoInicioInscricoes() {
		driver.findElement(fieldInicio).clear();
		driver.findElement(fieldInicio).sendKeys(ConstantsTest.INICIO_INSCRICOES_VALUE);
	}
	
	@When("preenche o campo Término das Inscrições")
	public void preencheCampoTerminoInscricoes() {
		driver.findElement(fieldTermino).clear();
		driver.findElement(fieldTermino).sendKeys(ConstantsTest.TERMINO_INSCRICOES_VALUE);
	
	}
	
	@When("preenche o campo Vagas")
	public void preencheCampoVagas() {
		driver.findElement(fieldVagas).clear();
		driver.findElement(fieldVagas).sendKeys(ConstantsTest.VAGAS_VALUE);
	
	}
	
	@When("clica no botão salvar")
	public void clicarButtonSalvar() {
		driver.findElement(buttonSalvar).click();
	}
	
	@Then("o sistema deve exibir uma mensagem")
	public void exibirMensagem() {
		assertEquals("Teste", ConstantsTest.MENSAGEM_SUCESSO_CADASTRO);
	}
	
	@AfterScenario
	public void close() {
		driver.close();
	}
}
