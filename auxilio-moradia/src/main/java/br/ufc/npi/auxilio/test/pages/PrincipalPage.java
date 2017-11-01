package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class PrincipalPage {

	private WebDriver driver;
	
	private By buttonCadastrar = By.id(ConstantsTest.ID_BUTTON_CADASTRAR);
	public PrincipalPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(ConstantsTest.URL_PAGE_PRINCIPAL);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
	public void acessar(String site) {
		driver.get(site);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
	public void clicarButtonVisualizar(Integer value){
		driver.findElement(By.cssSelector("a[href*='/selecao/inscricoes/"+value+"']")).click();
	}
	
	public void clicarButtonCadastrar() {
		driver.findElement(buttonCadastrar).click();
	}
	public void clicarButtonAluno(Integer value) {
		driver.findElement(By.cssSelector("a[href*='/inscricao/detalhes/"+value+"']")).click();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
}
