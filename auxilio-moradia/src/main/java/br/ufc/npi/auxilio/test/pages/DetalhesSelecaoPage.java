package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class DetalhesSelecaoPage {

	private WebDriver driver;
	
	private By span = By.id(ConstantsTest.ID_SPAN_MENSAGEM);
	
	public DetalhesSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(ConstantsTest.URL_PAGE_DETALHES);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
	public String exibirMensagem() {
		return driver.findElement(span).getText();
	}
	
}
