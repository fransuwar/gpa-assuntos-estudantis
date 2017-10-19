package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class CadastroSelecaoPage {

	private WebDriver driver;
	
	private By fieldSelecao = By.id(ConstantsTest.ID_FIELD_TIPO);
	private By fieldAno = By.id(ConstantsTest.ID_FIELD_ANO);
	private By fieldInicio = By.id(ConstantsTest.ID_FIELD_INICIO);
	private By fieldTermino = By.id(ConstantsTest.ID_FIELD_TERMINO);
	private By fieldVagas = By.id(ConstantsTest.ID_FIELD_VAGAS);
	private By buttonSalvar = By.id(ConstantsTest.ID_BUTTON_SALVAR);
	
	public CadastroSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(ConstantsTest.URL_PAGE_CADASTRAR);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
	public void preencheCampoSelecao() {
		driver.findElement(fieldSelecao).clear();
		driver.findElement(fieldSelecao).sendKeys(ConstantsTest.TIPO_SELECAO_VALUE);
	}
	
	public void preencheCampoAno() {
		driver.findElement(fieldAno).clear();
		driver.findElement(fieldAno).sendKeys(ConstantsTest.ANO_VALUE);
	}
	
	public void preencheCampoInicioInscricoes() {
		driver.findElement(fieldInicio).clear();
		driver.findElement(fieldInicio).sendKeys(ConstantsTest.INICIO_INSCRICOES_VALUE);
	}
	
	public void preencheCampoTerminoInscricoes() {
		driver.findElement(fieldTermino).clear();
		driver.findElement(fieldTermino).sendKeys(ConstantsTest.TERMINO_INSCRICOES_VALUE);
	
	}
	
	public void preencheCampoVagas() {
		driver.findElement(fieldVagas).clear();
		driver.findElement(fieldVagas).sendKeys(ConstantsTest.VAGAS_VALUE);
	
	}
	
	public void clicarButtonSalvar() {
		driver.findElement(buttonSalvar).click();
	}

}
