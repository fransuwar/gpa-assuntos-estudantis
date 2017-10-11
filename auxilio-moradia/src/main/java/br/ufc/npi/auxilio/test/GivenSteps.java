package br.ufc.npi.auxilio.test;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class GivenSteps {

	private WebDriver driver = new FirefoxDriver();
	private WebElement title; 
	
	@Given("o usuário acessa a página de login do sistema")
	public void acessarPaginaLogin(String url) {
		driver.get(url);
		title = driver.findElement(By.tagName(ConstantsTest.ELEMENT_TITLE));
		assertEquals(title.getText(), ConstantsTest.TITLE_PAGE_LOGIN);
	}
	
	@Given("efetua o login no sistema com o login e senha")
	public void efetuarLogin(String login, String senha) {
		driver.findElement(By.id(ConstantsTest.ID_FIELD_USERNAME)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_USERNAME)).sendKeys(login);
		driver.findElement(By.id(ConstantsTest.ID_FIELD_PASSWORD)).clear();
		driver.findElement(By.id(ConstantsTest.ID_FIELD_PASSWORD)).sendKeys(senha);
		driver.findElement(By.id(ConstantsTest.ID_BUTTON_ENTRAR)).click();
	}
	
	@Given("o sistema redireciona para a página principal do sistema")
	public void acessarPaginaPrincipal(String url) {
		driver.get(url);
		title = driver.findElement(By.tagName(ConstantsTest.ELEMENT_TITLE));
		assertEquals(title.getText(), ConstantsTest.TITLE_PAGE_PRINCIPAL);
	}
	
	@Given("o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção")
	public void clicaBTNovaSelecao() {
		driver.findElement(By.id("btn-cadastrar")).click();
	}
	
	@Given("o sistema redireciona para a página de cadastro")
	public void acessarPaginaCadastrar(String url) {
		driver.get(url);
		title = driver.findElement(By.tagName(ConstantsTest.ELEMENT_TITLE));
		assertEquals(title.getText(), ConstantsTest.TITLE_PAGE_CADASTRAR);
	}
}
