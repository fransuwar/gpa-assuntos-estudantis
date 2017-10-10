package br.ufc.npi.auxilio.test;

import org.jbehave.core.annotations.When;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroSelecaoWhenSteps {

	protected WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	@When("o usuário seleciona o tipo de seleção $tipoSelecao")
	public void selecionarTipoSelecao(String tipoSelecao) {
		
	}
	
	@When("preenche o campo Ano")
	public void campoAnoPreenchido() {
		
	}
	
	@When("preenche o campo Início das Inscrições")
	public void campoInicioInscricoesPreenchido() {
		
	}
	
	@When("preenche o campo Término das Inscrições")
	public void campoTerminoInscricoesPreenchido() {
		
	}
	
	@When("preenche o campo Vagas")
	public void campoVagasPreenchido() {
		
	}
	
	@When("não preenche o campo Ano")
	public void campoAnoNaoPreenchido() {
		
	}
	
	@When("não preenche o campo Início das Inscrições")
	public void campoInicioInscricoesNaoPreenchido() {
		
	}
	
	@When("não preenche o campo Término das Inscrições")
	public void campoTerminoInscricoesNaoPreenchido() {
		
	}
	
	@When("preenche o campo Ano com o valor $ano")
	public void preencherCampoAno(String ano) {
		
	}
	
	@When("preenche o campo Início das Inscrições com o valor $inicioInscricoes")
	public void preencherCampoInicioInscricoes(String inicioInscricoes) {
		
	}
	
	@When("preenche o campo Término das Inscrições com o valor $terminoInscricoes")
	public void preencherCampoTerminoInscricoes(String terminoInscricoes) {
		
	}
	
	@When("preenche o campo Vagas com o valor $vagas")
	public void preencherCampoVagas(String vagas) {
		
	}
	
	@When("clica no botão salvar")
	public void clicaBTSalvar() {
		driver.findElement(By.id("btn-salvar")).click();
	}
	
	@When("o usuário cancela o cadastro clicando no botão cancelar")
	public void clicaBTCancelar() {
		driver.findElement(By.id("btn-cancelar")).click();
	}
	
	
}
