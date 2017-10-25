package br.ufc.npi.auxilio.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static br.ufc.npi.auxilio.utils.ConstantsTest.SCRIPT_CLICK;
import static br.ufc.npi.auxilio.utils.ConstantsTest.TAG_TD;
import static br.ufc.npi.auxilio.utils.ConstantsTest.TAG_DIV;
import static br.ufc.npi.auxilio.utils.ConstantsTest.SELECAO_AUXILIO_EMERGENCIAL;
import static br.ufc.npi.auxilio.utils.ConstantsTest.SELECAO_AUXILIO_MORADIA;
import static br.ufc.npi.auxilio.utils.ConstantsTest.SELECAO_INICIAO_ACADEMICA;
import static br.ufc.npi.auxilio.utils.ConstantsTest.CSSSELECTOR_FIELD_SELECAO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_SELECAO_1;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_SELECAO_2;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_SELECAO_3;
import static br.ufc.npi.auxilio.utils.ConstantsTest.ID_FIELD_ANO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.ID_FIELD_VAGAS;
import static br.ufc.npi.auxilio.utils.ConstantsTest.ID_BUTTON_CANCELAR;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_DATA_INICIO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_FIELD_DATA_TERMINO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_SELECT_DATA_INICIO_MES;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_SELECT_DATA_TERMINO_MES;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_SELECT_DATA_INICIO_ANO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_SELECT_DATA_TERMINO_ANO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_TR_TABLE_DATA_INICIO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_TR_TABLE_DATA_TERMINO;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_INICIO_CLEAR;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_TERMINO_CLEAR;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_INICIO_CLOSE;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_DATA_TERMINO_CLOSE;
import static br.ufc.npi.auxilio.utils.ConstantsTest.XPATH_BUTTON_SALVAR;
import static br.ufc.npi.auxilio.utils.ConstantsTest.URL_PAGE_CADASTRAR;

public class CadastroSelecaoPage {

	private WebDriver driver;

	private By fieldSelecao = By.cssSelector(CSSSELECTOR_FIELD_SELECAO);
	private By fieldAno = By.id(ID_FIELD_ANO);
	private By fieldVagas = By.id(ID_FIELD_VAGAS);
	
	private By tagTd = By.tagName(TAG_TD);
	private By tagDiv = By.tagName(TAG_DIV);
	
	private By fieldSelecaoValueAE = By.xpath(XPATH_FIELD_SELECAO_1);
	private By fieldSelecaoValueAM = By.xpath(XPATH_FIELD_SELECAO_2);
	private By fieldSelecaoValueIA = By.xpath(XPATH_FIELD_SELECAO_3);
	
	private By fieldDataInicio = By.xpath(XPATH_FIELD_DATA_INICIO);
	private By fieldSelectMesDataInicio = By.xpath(XPATH_SELECT_DATA_INICIO_MES);
	private By fieldSelectAnoDataInicio = By.xpath(XPATH_SELECT_DATA_INICIO_ANO);
	private By trTableDataInicio = By.xpath(XPATH_TR_TABLE_DATA_INICIO);
	private By buttonCloseDataInicio = By.xpath(XPATH_BUTTON_DATA_INICIO_CLOSE);
	private By buttonClearDataInicio = By.xpath(XPATH_BUTTON_DATA_INICIO_CLEAR);
	
	private By fieldDataTermino = By.xpath(XPATH_FIELD_DATA_TERMINO);
	private By fieldSelectMesDataTermino = By.xpath(XPATH_SELECT_DATA_TERMINO_MES);
	private By fieldSelectAnoDataTermino = By.xpath(XPATH_SELECT_DATA_TERMINO_ANO);
	private By trTableDataTermino = By.xpath(XPATH_TR_TABLE_DATA_TERMINO);
	private By buttonCloseDataTermino = By.xpath(XPATH_BUTTON_DATA_TERMINO_CLOSE);
	private By buttonClearDataTermino = By.xpath(XPATH_BUTTON_DATA_TERMINO_CLEAR);
	
	private By buttonSalvar = By.xpath(XPATH_BUTTON_SALVAR);
	private By buttonCancelar = By.id(ID_BUTTON_CANCELAR);
	
	
	public CadastroSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}


	public void clicarButtonCadastrar() throws InterruptedException {
		driver.get(URL_PAGE_CADASTRAR);
	}
	
	public void preencheCampoSelecao(String selecao) {
		
		switch(selecao) {
			case SELECAO_AUXILIO_EMERGENCIAL:
				driver.findElement(fieldSelecao).click();
				driver.findElement(fieldSelecaoValueAE).click();
				break;
			case SELECAO_AUXILIO_MORADIA:
				driver.findElement(fieldSelecao).click();
				driver.findElement(fieldSelecaoValueAM).click();
				break;
			case SELECAO_INICIAO_ACADEMICA:
				driver.findElement(fieldSelecao).click();
				driver.findElement(fieldSelecaoValueIA).click();
				break;
		}
		
	}

	public void preencheCampoAno(String ano) {
		driver.findElement(fieldAno).clear();
		driver.findElement(fieldAno).sendKeys(ano);
	}

	public void preencheCampoInicioInscricoes(String dataInicio) {
		
		String dia = dataInicio.substring(0, 2);
		int mes = Integer.parseInt(dataInicio.substring(3, 5));
		String ano = dataInicio.substring(6, dataInicio.length());
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(SCRIPT_CLICK, driver.findElement(fieldDataInicio));		

		Select selectMes = new Select(driver.findElement(fieldSelectMesDataInicio));
		selectMes.selectByIndex(mes-1);
		
		Select selectAno = new Select(driver.findElement(fieldSelectAnoDataInicio));
		selectAno.selectByValue(ano);
		
		List<WebElement> linhasTable = driver.findElements(trTableDataInicio);
		
		loop: {
			for(WebElement linha : linhasTable) {
				List<WebElement> celulasTable = linha.findElements(tagTd);
				
				for(WebElement celula : celulasTable) {
					WebElement diaTable = celula.findElement(tagDiv);
					
					if(diaTable.getText().equalsIgnoreCase(dia)) {
						diaTable.click();
						break loop;
					}
				}
						
			}
		}
		
		driver.findElement(buttonCloseDataInicio).click();
	}

	public void preencheCampoTerminoInscricoes(String dataTermino) {
		
		String dia = dataTermino.substring(0, 2);
		int mes = Integer.parseInt(dataTermino.substring(3, 5));
		String ano = dataTermino.substring(6, dataTermino.length());
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(SCRIPT_CLICK, driver.findElement(fieldDataTermino));
		
		Select selectMes = new Select(driver.findElement(fieldSelectMesDataTermino));
		selectMes.selectByIndex(mes-1);
		
		Select selectAno = new Select(driver.findElement(fieldSelectAnoDataTermino));
		selectAno.selectByValue(ano);
		
		List<WebElement> linhas = driver.findElements(trTableDataTermino);
		
		loop: {
			for(WebElement linha : linhas) {
				List<WebElement> celulas = linha.findElements(tagTd);
				
				for(WebElement celula : celulas) {
					WebElement div = celula.findElement(tagDiv);
					
					if(div.getText().equalsIgnoreCase(dia)) {
						div.click();
						break loop;
					}
				}
						
			}
		}
		
		driver.findElement(buttonCloseDataTermino).click();
	}

	public void preencheCampoVagas(String vagas) {
		driver.findElement(fieldVagas).clear();
		driver.findElement(fieldVagas).sendKeys(vagas);
	}

	public void naoPreencherCampoAno() {
		driver.findElement(fieldAno).clear();
		driver.findElement(fieldAno).sendKeys("");
	}

	public void naoPreencherCampoInicioInscricoes() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(SCRIPT_CLICK, driver.findElement(fieldDataInicio));
		
		driver.findElement(buttonClearDataInicio).click();
	}

	public void naoPreencherCampoTerminoInscricoes() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(SCRIPT_CLICK, driver.findElement(fieldDataTermino));
		
		driver.findElement(buttonClearDataTermino).click();
	}

	public void clicarButtonSalvar() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(SCRIPT_CLICK, driver.findElement(buttonSalvar));
	}

	public void clicarButtonCancelar() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(SCRIPT_CLICK, driver.findElement(buttonCancelar));
	}

}
