package br.ufc.npi.auxilio.test;

import org.jbehave.core.annotations.Then;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroSelecaoThenSteps {

	protected WebDriver driver;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	@Then("o sistema exibir a mensagem $mensagem")
	public void cadastroSelecaoComSucesso(String mensagem) {
		
	}
	
	@Then("o sistema redireciona para a página de listagem das seleções cadastradas pelo endereço $url")
	public void cancelarCadastroSelecao(String url) {
		
	}
	
	@Then("o sistema deve exibir a mensagem $mensagem")
	public void cadastrarSelecaoSemPreencherCamposObrigatorios(String mensagem) {
		
	}
	
	@Then("o sistema deve exibir a mensagem $mensagem")
	public void cadastrarSelecaoExistente(String mensagem) {
		
	}

}
