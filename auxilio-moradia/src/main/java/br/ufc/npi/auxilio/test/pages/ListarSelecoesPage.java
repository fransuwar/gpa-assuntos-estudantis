package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import static br.ufc.npi.auxilio.utils.ConstantsTest.URL_PAGE_LISTAR;

public class ListarSelecoesPage {
	
	private WebDriver driver;
	
	public ListarSelecoesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(URL_PAGE_LISTAR);
	}
	
	public String titlePage() {
		return driver.getTitle();
	}
	
}
