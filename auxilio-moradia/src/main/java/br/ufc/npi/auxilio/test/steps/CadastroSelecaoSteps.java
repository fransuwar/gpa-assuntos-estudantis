package br.ufc.npi.auxilio.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

import br.ufc.npi.auxilio.test.pages.PageFactory;
import br.ufc.npi.auxilio.utils.ConstantsTest;


public class CadastroSelecaoSteps {
	
	private final PageFactory pages;
	
	public CadastroSelecaoSteps(PageFactory pages) {
		this.pages = pages;
	}
	
	@BeforeScenario
	@Given("o usuário acessa a página de login do sistema")
	public void acessarPaginaLogin() {
		pages.login().acessar();
	}
	
	@Given("efetua o login no sistema com o login e senha")
	public void efetuarLogin() {
		pages.login().logar();
	}
	
	@Given("o sistema redireciona para a página principal do sistema")
	public void acessarPaginaPrincipal() {
		pages.principal().acessar();
	}
	
	@Given("o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção")
	public void clicaBTNovaSelecao() {
		pages.principal().clicarButtonCadastrar();
	}
	
	@Given("o sistema redireciona para a página de cadastro")
	public void acessarPaginaCadastrar() {
		pages.cadastroSelecao().acessar();
	}
	
	//@When("o usuário seleciona o tipo de seleção")
	public void selecionarTipoSelecao() {
		pages.cadastroSelecao().preencheCampoSelecao();
	}
	
	//@When("preenche o campo Ano")
	public void campoAnoPreenchido() {
		pages.cadastroSelecao().preencheCampoAno();
	}
	
	//@When("preenche o campo Início das Inscrições")
	public void campoInicioInscricoesPreenchido() {
		pages.cadastroSelecao().preencheCampoInicioInscricoes();
	}
	
	//@When("preenche o campo Término das Inscrições")
	public void campoTerminoInscricoesPreenchido() {
		pages.cadastroSelecao().preencheCampoTerminoInscricoes();
	}
	
	//@When("preenche o campo Vagas")
	public void campoVagasPreenchido() {
		pages.cadastroSelecao().preencheCampoVagas();
	}
	
	//@When("clica no botão salvar")
	public void clicaBTSalvar() {
		pages.cadastroSelecao().clicarButtonSalvar();
	}
	
	@Then("o sistema redireciona para a página de detalhes da seleção cadastrada")
	public void acesaarPaginaDetalhes() {
		pages.detalhesSelecao().acessar();
	}
	
	//@Then("o sistema deve exibir uma mensagem")
	public void exibirMensagem() {
		assertEquals(pages.detalhesSelecao().exibirMensagem(), ConstantsTest.MENSAGEM_SUCESSO_CADASTRO);
	}
	
	
}
