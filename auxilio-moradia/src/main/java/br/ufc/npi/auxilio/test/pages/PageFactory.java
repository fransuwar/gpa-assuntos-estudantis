package br.ufc.npi.auxilio.test.pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
	
	private WebDriver driver;
	
	private CadastroSelecaoPage cadastroSelecaoPage;
	private DetalhesSelecaoPage detalhesSelecaoPage;
	private ListarSelecoesPage listarSelecoesPage;
	private LoginPage loginPage;
	private PrincipalPage principalPage;
	private AnaliseDocumentoPage analiseDocumentoPage;

	private VisualizarSelecaoPage visualizarSelecaoPage;
	
	public PageFactory(WebDriver driver) {
		this.driver = driver;
	}
		
	public AnaliseDocumentoPage analiseDocumentoPage() {
		if(analiseDocumentoPage == null) {
			analiseDocumentoPage = new AnaliseDocumentoPage(driver);
		}
		return analiseDocumentoPage;
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
	
	public VisualizarSelecaoPage visualizar() {
		if(visualizarSelecaoPage == null) {
			visualizarSelecaoPage = new VisualizarSelecaoPage(driver);
		}
		
		return visualizarSelecaoPage;
	}
}
