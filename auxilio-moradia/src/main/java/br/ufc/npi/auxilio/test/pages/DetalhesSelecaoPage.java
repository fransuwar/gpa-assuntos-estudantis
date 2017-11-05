package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static br.ufc.npi.auxilio.utils.ConstantsTest.ID_SPAN_MENSAGEM;
import static br.ufc.npi.auxilio.utils.ConstantsTest.URL_PAGE_DETALHES;

public class DetalhesSelecaoPage {

	private WebDriver driver;
	
	private By span = By.id(ID_SPAN_MENSAGEM);
	
	public DetalhesSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(URL_PAGE_DETALHES);
	}
	
	public String exibirMensagem() {
		return driver.findElement(span).getText();
	}
	
}
