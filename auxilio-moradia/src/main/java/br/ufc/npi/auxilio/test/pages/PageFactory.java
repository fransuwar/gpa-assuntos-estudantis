package br.ufc.npi.auxilio.test.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;

public class PageFactory {
	
	private WebDriver driver;
	
	private CadastroSelecaoPage cadastroSelecaoPage;
	private DetalhesSelecaoPage detalhesSelecaoPage;
	private ListarSelecoesPage listarSelecoesPage;
	private LoginPage loginPage;
	private PrincipalPage principalPage;
	
	public PageFactory(WebDriverProvider driver) {
		System.setProperty("webdriver.firefox.bin", "/home/lucas.santos/Downloads/firefox/firefox");
		this.driver = driver.get();
	}
	
	public CadastroSelecaoPage cadastroSelecao() {
		if(cadastroSelecaoPage == null) {
			cadastroSelecaoPage = new CadastroSelecaoPage(driver);
		}
		
		return cadastroSelecaoPage;
	}
	
	public DetalhesSelecaoPage detalhesSelecao() {
		if(detalhesSelecaoPage == null) {
			detalhesSelecaoPage = new DetalhesSelecaoPage(driver);
		}
		
		return detalhesSelecaoPage;
	}
	
	public ListarSelecoesPage listarSelecoes() {
		if(listarSelecoesPage == null) {
			listarSelecoesPage = new ListarSelecoesPage(driver);
		}
		
		return listarSelecoesPage;
	}
	
	public LoginPage login() {
		if(loginPage == null) {
			loginPage = new LoginPage(driver);
		}
		
		return loginPage;
	}
	
	public PrincipalPage principal() {
		if(principalPage == null) {
			principalPage = new PrincipalPage(driver);
		}
		
		return principalPage;
	}
}
