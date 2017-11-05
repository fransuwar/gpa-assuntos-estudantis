package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;

import static br.ufc.npi.auxilio.utils.ConstantsTest.TITLE_PAGE_VISUALIZAR;


import br.ufc.npi.auxilio.test.pages.PageFactory;

public class VisualizarInscricoesSteps {

	private PageFactory pages;
	
	public VisualizarInscricoesSteps(WebDriver driver) {
		this.pages = new PageFactory(driver);
	}
	
	@Given("estou na página principal")
    public void givenPage() {
        pages.principal().acessar();
    }

    @When("clico em visualizar Inscricoes da selecão $selecao")
    public void whenClick(String selecao) {
    	pages.principal().clicarButtonVisualizar(selecao);
    }

    @Then("a página Visualizar Inscrições é exibida")
    public void thenVisualize() {
        assertEquals(pages.visualizar().cabecalho(), TITLE_PAGE_VISUALIZAR);
    }
    
    @Then("possui aluno(a) $aluno")
    public void andPossuiAluno(String aluno){
    	assertTrue(pages.visualizar().aluno(aluno));
    }
    @Then("Análise de Documentos deve estar com o status $valor e da cor $cor")
    public void andAnalise(String valor, String cor){
    	assertTrue(pages.visualizar().analiseDocumento(valor, cor));
    }
    
    @Then("Entrevista deve estar com o status $valor e da cor $cor")
    public void andEntrevista(String valor, String cor){
    	assertTrue(pages.visualizar().entrevista(valor, cor));
    }
    @Then("Visita Domiciliar deve estar com o status $valor e da cor $cor")
    public void andVisita(String valor, String cor){
    	assertTrue(pages.visualizar().visita(valor, cor));
    }
    @Then("Parece Final deve estar com o status $valor e da cor $cor")
    public void andParecer(String valor, String cor){
    	assertTrue(pages.visualizar().parecer(valor, cor));
    }

}
