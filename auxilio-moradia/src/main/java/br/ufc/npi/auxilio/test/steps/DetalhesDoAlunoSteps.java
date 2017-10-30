package br.ufc.npi.auxilio.test.steps;

import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;


public class DetalhesDoAlunoSteps {
    
    private final PageFactory pages;
    private final WebDriver driver;
        
    public DetalhesDoAlunoSteps() {
        System.setProperty("webdriver.firefox.bin", "/home/afonso.neto/Downloads/firefox-sdk/bin/firefox");
        this.driver = new FirefoxDriver();
        
        this.pages = new PageFactory(driver);
    }
        
    @BeforeStory
    public void inicio() {
        pages.login().acessar();
    }
    
    @Given("o usuário esteja logado no sistema com cpf $11111111101 e senha $1234")
    public void efetuarLogin() throws InterruptedException {
        pages.login().logar();
    }
    
    @When("clicar sobre a seleção $value" )
    public void selecionarSelecao(@Named("value") int value) throws InterruptedException {
    	pages.principal().clicarButtonVisualizar(value);
    }
    
    @When("seleção tenha pelo menos um aluno")
    public void visualizarAluno() throws InterruptedException {
        
    }
    @When("clicar sobre o aluno")
    public void selecionarAluno() throws InterruptedException {
        
    }
    @Then("as informações sobre Identificação do aluno são mostradas")
    public void mostrarIndentificacao() throws InterruptedException {
        
    }

    
        
}


