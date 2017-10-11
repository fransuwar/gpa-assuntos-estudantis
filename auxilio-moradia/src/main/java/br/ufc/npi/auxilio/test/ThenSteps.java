package br.ufc.npi.auxilio.test;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class ThenSteps {

	private WebDriver driver = new FirefoxDriver();
	private WebElement title;
	
	@Then("o sistema redireciona para a página de detalhes da seleção cadastrada")
	public void acesaarPaginaDetalhes(String url, String id) {
		driver.get(url.concat(id));
		title = driver.findElement(By.tagName(ConstantsTest.ELEMENT_TITLE));
		assertEquals(title.getText(), ConstantsTest.TITLE_PAGE_DETALHES);
	}
	
	@Then("o sistema deve exibir uma mensagem")
	public void exibirMensagem(String mensagem) {
		String mensagemElement = driver.findElement(By.id(ConstantsTest.ID_SPAN_MENSAGEM)).getText();
		assertEquals(mensagemElement, mensagem);
	}
	
	@Then("o sistema redireciona para a página de listagem das seleções cadastradas")
	public void acessarPaginaListar(String url) {
		driver.get(url);
		title = driver.findElement(By.tagName(ConstantsTest.ELEMENT_TITLE));
		assertEquals(title.getText(), ConstantsTest.TITLE_PAGE_PRINCIPAL);
		
	}

}
