package br.ufc.npi.auxilio.test;

import org.jbehave.core.annotations.Given;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroSelecaoGivenSteps {

	protected WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	@Given("o usuário acessa a página inicial do sistema acessando pelo endereço $url")
	public void acessarPaginaInicial(String url) {
		driver.get(url);
	}
	
	@Given("efetua o login no sistema com o usuário $login e senha $senha")
	public void efetuarLogin(String login, String senha) {
		
	}
	
	@Given("o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção")
	public void clicaBTNovaSelecao() {
		driver.findElement(By.id("btn-cadastrar")).click();
	}
	
	@Given("o sistema redireciona para a página de cadastro pelo endereço $url")
	public void acessarPaginaCadastrar(String url) {
		driver.get(url);
	}
}
