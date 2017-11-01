package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;

public class VisualizarInscricoesSteps {
	private PageFactory pages;
	private WebDriver driver;
	private String pageAluno;
	
	public VisualizarInscricoesSteps() {
		System.setProperty("webdriver.firefox.bin", "/home/francisco.costa/firefox/firefox-bin");
		this.driver = new FirefoxDriver();
		this.pages = new PageFactory(driver);
	}
	
	@BeforeStory
	public void efetuarLogin() {
		pages.login().acessar("http://localhost:8080/login");
		pages.login().logar();
	}
	
	@Given("estou no site $site")
    public void givenPage(@Named("site") String site) {
        pages.principal().acessar(site);
    }

    @When("clico em visualizar_inscricoes $value")
    public void whenClick(@Named("value") int value) {
    	pages.principal().clicarButtonVisualizar(value);
    }

    @Then("a página $title é exibida")
    public void thenVisualize(@Named("title") String title) {
    	String titlePage = pages.visualizar().cabecalho();
        assertEquals("Auxílio - "+title, titlePage);
    }
    
    @Then("possui aluno(a) $aluno")
    public void andPossuiAluno(@Named("aluno") String aluno){
    	assertTrue(pages.visualizar().aluno(aluno));
    }
    @Then("Análise de Documentos $estado cor $cor")
    public void andAnalise(@Named("estado") String estado, @Named("cor") String cor){
    	assertTrue(pages.visualizar().analiseDocumento(estado, cor));
    }
    @Then("Entrevista $estado cor $cor")
    public void andEntrevista(@Named("estado") String estado, @Named("cor") String cor){
    	assertTrue(pages.visualizar().entrevista(estado, cor));
    }
    @Then("Visita Domiciliar $estado cor $cor")
    public void andVisita(@Named("estado") String estado, @Named("cor") String cor){
    	assertTrue(pages.visualizar().visita(estado, cor));
    }
    @Then("Parece Final $estado cor $cor")
    public void andParecer(@Named("estado") String estado, @Named("cor") String cor){
    	assertTrue(pages.visualizar().parecer(estado, cor));
    }
    
    @AfterStory
    public void closeTest(){
    	driver.quit();
    }
}
