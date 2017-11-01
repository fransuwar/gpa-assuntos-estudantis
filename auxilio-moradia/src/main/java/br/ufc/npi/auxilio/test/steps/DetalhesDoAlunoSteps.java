package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;
import br.ufc.npi.auxilio.utils.ConstantsTest;


public class DetalhesDoAlunoSteps {
    
    private final PageFactory pages;
    private final WebDriver driver;
        
    public DetalhesDoAlunoSteps() {
        System.setProperty("webdriver.firefox.bin", "/home/afonso.neto/Downloads/firefox-sdk/bin/firefox-bin");
        this.driver = new FirefoxDriver();
        
        this.pages = new PageFactory(driver);
    }
    @BeforeStory
	public void inicio() {
		pages.login().acessar("http://localhost:8080/login");
		pages.login().logar();
	}
    @Given("o usuário esteja logado no sistema")
    public void efetuarLogin() throws InterruptedException {
        pages.login().acessar("http://localhost:8080/selecao");
    }
    
    @When("clicar sobre a seleção $value" )
    public void selecionarSelecao(@Named("value") int value) throws InterruptedException {
    	pages.principal().clicarButtonVisualizar(value);
    }
    
    @When("clicar sobre o aluno $value")
    public void selecionarAluno(@Named("value") int value) throws InterruptedException {
    	pages.principal().clicarButtonAluno(value);
    }
    
    @Then("titulo da pagina é $value")
    public void mostrarTituloDaPagina(@Named("value") String value) throws InterruptedException {
        assertTrue(pages.detalhesSelecao().tituloDaPagina(value));
    }
    
    @Then("nome completo é $value")
    public void mostrarNomeCompleto(@Named("value") String value) throws InterruptedException {
        assertTrue(pages.detalhesSelecao().nomeCompleto(value));
    }
    
    @Then("cpf é email é $value")
    public void mostrarCpf(@Named("value") String value) throws InterruptedException {
        assertTrue(pages.detalhesSelecao().cpf(value));
    }
   
    @Then("email é $value")
    public void mostrarEmail(@Named("value") String value) throws InterruptedException {
        assertTrue(pages.detalhesSelecao().email(value));
    } 
   
    @When ("clicar sobre análise dos documento $value")
    public void analiseDocumento(@Named("value") Integer value) throws InterruptedException  {
    	pages.analiseDocumentoPage().clicarAnaliseDocumento(value);
    }
    @Then ("os documentos $value1 e $value2 são visiveis") 
    public void DocumentosVisiveis(@Named("value1") String value1,@Named("value2") String value2) throws InterruptedException  {
    	assertTrue(pages.analiseDocumentoPage().documentosVisiveis(value1, value2));
    }
        
    @When("clicar sobre download do arquivo Identidade $value")
    public void downloadArquivo(@Named("value") int value) throws InterruptedException  {
    	pages.analiseDocumentoPage().downloadDocumento(value);
    }
    
    @Then("é baixado o arquivo $value")
    public void arquivoBaixado(@Named("value") String value) throws InterruptedException  {
    	assertTrue(pages.analiseDocumentoPage().arquivoBaixado(value));
    }
    
  /*  @AfterScenario()
    public void closeScenario() {
    	pages.login().logout(); 	
    }*/
    @AfterStory
    public void closeTest(){
    	driver.quit();
    }
    
    
    
        
}


