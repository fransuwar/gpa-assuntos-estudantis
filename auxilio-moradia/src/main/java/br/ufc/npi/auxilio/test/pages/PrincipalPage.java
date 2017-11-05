package br.ufc.npi.auxilio.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static br.ufc.npi.auxilio.utils.ConstantsTest.ID_BUTTON_CADASTRAR;
import static br.ufc.npi.auxilio.utils.ConstantsTest.URL_PAGE_PRINCIPAL;
import static br.ufc.npi.auxilio.utils.ConstantsTest.TITLE_PAGE_LISTAR;

import static org.junit.Assert.assertEquals;



public class PrincipalPage {

	private WebDriver driver;
	
	private By buttonCadastrar = By.id(ID_BUTTON_CADASTRAR);
	
	public PrincipalPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(URL_PAGE_PRINCIPAL);
		assertEquals(driver.getTitle(), TITLE_PAGE_LISTAR);
	}
	
	public void clicarButtonVisualizar(String selecao){
		driver.findElement(By.cssSelector("a[href*='/selecao/inscricoes/"+selecao+"']")).click();
	}
	
	public void clicarButtonCadastrar() {
		driver.findElement(buttonCadastrar).click();
	}

}
