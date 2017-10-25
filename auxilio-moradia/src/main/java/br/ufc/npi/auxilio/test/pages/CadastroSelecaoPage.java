package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class CadastroSelecaoPage {

	private WebDriver driver;

	//private By fieldSelecao = By.id(ConstantsTest.ID_FIELD_TIPO);
	private By fieldAno = By.id(ConstantsTest.ID_FIELD_ANO);
	private By fieldInicio = By.id(ConstantsTest.ID_FIELD_INICIO);
	private By fieldTermino = By.id(ConstantsTest.ID_FIELD_TERMINO);
	private By fieldVagas = By.id(ConstantsTest.ID_FIELD_VAGAS);
	private By buttonSalvar = By.id(ConstantsTest.ID_BUTTON_SALVAR);
	private By buttonCancelar = By.id(ConstantsTest.ID_BUTTON_CANCELAR);

	public CadastroSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() throws InterruptedException {
		driver.get(ConstantsTest.URL_PAGE_CADASTRAR);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public void preencheCampoSelecao() {
		driver.findElement(By.cssSelector("input.select-dropdown")).click();
		driver.findElement(By.xpath("//div[@class='col s4 input-field']/div/ul/li[2]/span")).click();
	}

	public void preencheCampoAno() {
		driver.findElement(fieldAno).clear();
		driver.findElement(fieldAno).sendKeys(ConstantsTest.ANO_VALUE);
	}

	public void preencheCampoInicioInscricoes() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='inicio']")));

		driver.findElement(By.xpath("//table[@id='inicio_table']/tbody/tr[4]/td[3]/div")).click();
	}

	public void preencheCampoTerminoInscricoes() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='termino']")));

		driver.findElement(By.xpath("//table[@id='termino_table']/tbody/tr[4]/td[5]/div")).click();
	}

	public void preencheCampoVagas() {
		driver.findElement(fieldVagas).clear();
		driver.findElement(fieldVagas).sendKeys(ConstantsTest.VAGAS_VALUE);
	}

	public void naoPreencherCampoAno() {
		driver.findElement(fieldAno).clear();
		driver.findElement(fieldAno).sendKeys("");
	}

	public void naoPreencherCampoInicioInscricoes() {
		driver.findElement(fieldInicio).clear();
		driver.findElement(fieldInicio).sendKeys("");
	}

	public void naoPreencherCampoTerminoInscricoes() {
		driver.findElement(fieldTermino).clear();
		driver.findElement(fieldTermino).sendKeys("");
	}

	public void clicarButtonSalvar() {
		driver.findElement(buttonSalvar).click();
	}

	public void clicarButtonCancelar() {
		driver.findElement(buttonCancelar).click();
	}

}
