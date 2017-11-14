package br.ufc.npi.auxilio.test.steps;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;

public class ExportarResultadoSteps {

	private final PageFactory pages;
	private final WebDriver driver;
	
	public ExportarResultadoSteps() {
		System.setProperty("webdriver.firefox.bin", "/home/joao.monteiro/Downloads/firefox/firefox");
		
		this.driver = new FirefoxDriver();
		this.pages = new PageFactory(driver);
	}
	
	@BeforeStory
	public void efetuarLogin() {
		pages.login().acessar();
		pages.login().logar();
	}
	
	@Given("o usuário acessa a página de visualizar inscrição da seleção")
	public void acessarPaginaSelecao() throws InterruptedException{
		pages.visualizarSelecao().acessar();
	}
	
	@When("o usuário clica no botão Menu")
	public void clicaBTMenu(){
		pages.visualizarSelecao().clicarButtonMenu();
	}
	
	@When("clico no botão Exibir Resultado Final")
	public void clicaBTExibirResultado() {
		pages.visualizarSelecao().clicarButtonExibirResultado();
	}
	
	//Botão da tabela, onde mostra os alunos: deferidos, indeferidos, reservas
	
	@When("clico no botão Deferido")
	public void clicaBTDeferido() {
		pages.visualizarSelecao().clicarButtonDeferido();
	}
	
	@When("clico no botão Indeferido")
	public void clicaBTIndeferido() {
		pages.visualizarSelecao().clicarButtonIndeferido();
	}
	
	@When("clico no botão Reserva")
	public void clicaBTReserva() {
		pages.visualizarSelecao().clicarButtonReserva();
	}
	
	//Quando clica no botão para exportar pdf de cada aluno: deferido, indeferido, reserva
	
	@When("clico no botão Exportar para pdf dos alunos deferidos")
	public void clicaBTExportarPDFDeferido(){
		pages.visualizarSelecao().clicarButtonMenuFinalDeferido();
	}
	
	@When("clico no botão Exportar para pdf dos alunos indeferidos")
	public void clicaBTExportarPDFIndeferido(){
		pages.visualizarSelecao().clicarButtonMenuFinalIndeferido();
	}
	
	@When("clico no botão Exportar para pdf dos alunos reservas")
	public void clicaBTExportarPDFReserva(){
		pages.visualizarSelecao().clicarButtonMenuFinalReserva();
	}
	
	//Parte que ele faz o download
	
	@Then("o sistema baixa um arquivo 'Resultado Final.pdf' dos alunos deferidos para o computador")
	public void baixarPDFDeferidos(){
		pages.visualizarSelecao().downloadDocumentoDeferidos();
	}
	
	@Then("o sistema baixa um arquivo 'Resultado Final.pdf' dos alunos indeferidos para o computador")
	public void baixarPDFIndeferidos(){
		pages.visualizarSelecao().downloadDocumentoIndeferidos();
	}
	
	@Then("o sistema baixa um arquivo 'Resultado Final.pdf' dos alunos reservas para o computador")
	public void baixarPDFReservas(){
		pages.visualizarSelecao().downloadDocumentoReservas();
	}
	
	@AfterStory
	public void encerrar(){
		driver.close();
	}
	
}
