package br.ufc.npi.auxilio.test.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ufc.npi.auxilio.utils.ConstantsTest;
import static br.ufc.npi.auxilio.utils.ConstantsTest.SELECAO_AUXILIO_EMERGENCIAL;
import static br.ufc.npi.auxilio.utils.ConstantsTest.SELECAO_AUXILIO_MORADIA;
import static br.ufc.npi.auxilio.utils.ConstantsTest.SELECAO_INICIAO_ACADEMICA;
import static br.ufc.npi.auxilio.utils.ConstantsTest.CSSSELECTOR_FIELD_SELECAO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_SELECAO_1;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_SELECAO_2;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_SELECAO_3;
import static br.ufc.npi.auxilio.utils.ConstantsTest.ID_FIELD_ANO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.ID_FIELD_VAGAS;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_DATA_INICIO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_DATA_TERMINO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.CSSSELECTOR_FIELD_DATA_MES;
import static br.ufc.npi.auxilio.utils.ConstantsTest.CSSSELECTOR_FIELD_DATA_ANO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_SELECT_DATA_INICIO_DIA;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_SELECT_DATA_TERMINO_DIA;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_INICIO_CLEAR;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_TERMINO_CLEAR;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_INICIO_CLOSE;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_TERMINO_CLOSE;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_SALVAR;

public class CadastroSelecaoPage {

	private WebDriver driver;

	public CadastroSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() throws InterruptedException {
		driver.get(ConstantsTest.URL_PAGE_CADASTRAR);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public void preencheCampoSelecao(String selecao) {
		
		switch(selecao) {
			case SELECAO_AUXILIO_EMERGENCIAL:
				driver.findElement(By.cssSelector(CSSSELECTOR_FIELD_SELECAO)).click();
				driver.findElement(By.xpath(XPATH_FIELD_SELECAO_1)).click();
				break;
			case SELECAO_AUXILIO_MORADIA:
				driver.findElement(By.cssSelector(CSSSELECTOR_FIELD_SELECAO)).click();
				driver.findElement(By.xpath(XPATH_FIELD_SELECAO_2)).click();
				break;
			case SELECAO_INICIAO_ACADEMICA:
				driver.findElement(By.cssSelector(CSSSELECTOR_FIELD_SELECAO)).click();
				driver.findElement(By.xpath(XPATH_FIELD_SELECAO_3)).click();
				break;
		}
		
	}

	public void preencheCampoAno(String ano) {
		driver.findElement(By.id(ID_FIELD_ANO)).clear();
		driver.findElement(By.id(ID_FIELD_ANO)).sendKeys(ano);
	}

	public void preencheCampoInicioInscricoes(String dataInicio) {
		
		String dia = dataInicio.substring(0, 2);
		int mes = Integer.parseInt(dataInicio.substring(3, 5));
		String ano = dataInicio.substring(6, dataInicio.length());
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(XPATH_FIELD_DATA_INICIO)));
		
		Select selectMes = new Select(driver.findElement(By.cssSelector(CSSSELECTOR_FIELD_DATA_MES)));
		selectMes.selectByIndex(mes-1);
		
		Select selectAno = new Select(driver.findElement(By.cssSelector(CSSSELECTOR_FIELD_DATA_ANO)));
		selectAno.selectByValue(ano);
		
		List<WebElement> linhas = driver.findElements(By.xpath("//table[@id='inicio_table']/tbody/tr"));
		
		for(WebElement linha : linhas) {
			List<WebElement> celulas = linha.findElements(By.tagName("td"));
			
			for(WebElement celula : celulas) {
				WebElement div = celula.findElement(By.tagName("div"));
				
				if(div.getText().equalsIgnoreCase(dia)) {
					div.click();
					break;
				}
			}
					
		}
		
		driver.findElement(By.xpath(XPATH_BUTTON_DATA_INICIO_CLOSE)).click();
	}

	public void preencheCampoTerminoInscricoes(String dataTermino) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(XPATH_FIELD_DATA_TERMINO)));
		
		String dia = dataTermino.substring(0, 2);
		int mes = Integer.parseInt(dataTermino.substring(3, 5));
		String ano = dataTermino.substring(6, dataTermino.length());
		
		Select selectMes = new Select(driver.findElement(By.cssSelector(CSSSELECTOR_FIELD_DATA_MES)));
		selectMes.selectByIndex(mes);
		
		Select selectAno = new Select(driver.findElement(By.cssSelector(CSSSELECTOR_FIELD_DATA_ANO)));
		selectAno.selectByValue(ano);
		
		List<WebElement> linhas = driver.findElements(By.xpath("//table[@id='inicio_table']/tbody/tr"));
		
		for(WebElement linha : linhas) {
			List<WebElement> celulas = linha.findElements(By.tagName("td"));
			
			for(WebElement celula : celulas) {
				WebElement div = celula.findElement(By.xpath("//div[@class='picker__day picker__day--infocus']"));
				
				if(div.getText().equalsIgnoreCase(dia)) {
					div.click();
					break;
				}
			}
					
		}
		
		driver.findElement(By.xpath(XPATH_BUTTON_DATA_TERMINO_CLOSE)).click();
	}

	public void preencheCampoVagas(String vagas) {
		driver.findElement(By.id(ID_FIELD_VAGAS)).clear();
		driver.findElement(By.id(ID_FIELD_VAGAS)).sendKeys(vagas);
	}

	public void naoPreencherCampoAno() {
		driver.findElement(By.id(ID_FIELD_ANO)).clear();
		driver.findElement(By.id(ID_FIELD_ANO)).sendKeys("");
	}

	public void naoPreencherCampoInicioInscricoes() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(XPATH_FIELD_DATA_INICIO)));
		
		driver.findElement(By.xpath(XPATH_BUTTON_DATA_INICIO_CLEAR)).click();
	}

	public void naoPreencherCampoTerminoInscricoes() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(XPATH_FIELD_DATA_TERMINO)));
		
		driver.findElement(By.xpath(XPATH_BUTTON_DATA_TERMINO_CLEAR)).click();
	}

	public void clicarButtonSalvar() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(XPATH_BUTTON_SALVAR)));
	}

	public void clicarButtonCancelar() {
		driver.findElement(By.id(ConstantsTest.ID_BUTTON_CANCELAR)).click();
	}

}
