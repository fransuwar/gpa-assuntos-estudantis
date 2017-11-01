package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class DetalhesSelecaoPage {

	private WebDriver driver;
	
	private By span = By.id(ConstantsTest.ID_SPAN_MENSAGEM);
	private By nome = By.id(ConstantsTest.ID_SPAN_NOME);
	private By cpf = By.id(ConstantsTest.ID_SPAN_CPF);
	private By email = By.id(ConstantsTest.ID_SPAN_EMAIL);
	
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
	
	public boolean tituloDaPagina(String value) {
		String title = driver.getTitle();
		if(title.equals(value)) return true;
		return false;
	}
	
	public boolean nomeCompleto(String value) {
		String nome = driver.findElement(this.nome).getText();
		if(nome.equals(value)) return true;
		return false;
	}
	
	public boolean cpf(String value) {
		String cpf = driver.findElement(this.cpf).getText();
		if(cpf.equals(value)) return true;
		return false;
	}
	
	public boolean email(String value) {
		String email = driver.findElement(this.email).getText();
		if(email.equals(value)) return true;
		return false;
	}
}
