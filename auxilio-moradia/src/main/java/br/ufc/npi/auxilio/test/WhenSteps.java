package br.ufc.npi.auxilio.test;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class WhenSteps {

	private WebDriver driver = new FirefoxDriver();
	
	private WebElement fieldAno;
	private WebElement fieldInicio;
	private WebElement fieldTermino;
	
	@When("o usuário seleciona o tipo de seleção")
	public void selecionarTipoSelecao(String tipoSelecao) {
		driver.findElement(By.id(ConstantsTest.ID_FIELD_TIPO)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_TIPO)).sendKeys(tipoSelecao);
	}
	
	@When("preenche o campo Ano")
	public void campoAnoPreenchido(String ano) {
		driver.findElement(By.id(ConstantsTest.ID_FIELD_ANO)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_ANO)).sendKeys(ano);
	}
	
	@When("preenche o campo Início das Inscrições")
	public void campoInicioInscricoesPreenchido(String dataInicio) {
		driver.findElement(By.id(ConstantsTest.ID_FIELD_INICIO)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_INICIO)).sendKeys(dataInicio);
	}
	
	@When("preenche o campo Término das Inscrições")
	public void campoTerminoInscricoesPreenchido(String dataTermino) {
		driver.findElement(By.id(ConstantsTest.ID_FIELD_TERMINO)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_TERMINO)).sendKeys(dataTermino);
	}
	
	@When("preenche o campo Vagas")
	public void campoVagasPreenchido(String vagas) {
		driver.findElement(By.id(ConstantsTest.ID_FIELD_VAGAS)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_VAGAS)).sendKeys(vagas);
	}
	
	@When("não preenche o campo Ano")
	public void campoAnoNaoPreenchido() {
		fieldAno = driver.findElement(By.id(ConstantsTest.ID_FIELD_ANO));
		assertEquals(fieldAno.getText(), "");
	}
	
	@When("não preenche o campo Início das Inscrições")
	public void campoInicioInscricoesNaoPreenchido() {
		fieldInicio = driver.findElement(By.id(ConstantsTest.ID_FIELD_INICIO));
		assertEquals(fieldInicio.getText(), "");
	}
	
	@When("não preenche o campo Término das Inscrições")
	public void campoTerminoInscricoesNaoPreenchido() {
		fieldTermino = driver.findElement(By.id(ConstantsTest.ID_FIELD_TERMINO));
		assertEquals(fieldTermino.getText(), "");
	}
	
	@When("clica no botão salvar")
	public void clicaBTSalvar() {
		driver.findElement(By.id(ConstantsTest.ID_BUTTON_SALVAR)).click();
	}
	
	@When("o usuário cancela o cadastro clicando no botão cancelar")
	public void clicaBTCancelar() {
		driver.findElement(By.id(ConstantsTest.ID_BUTTON_CANCELAR)).click();
	}
	
	
}
