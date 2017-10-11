package br.ufc.npi.auxilio.test;

import org.junit.Test;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class CadastrarSelecaoStory extends StoryBase{
	
	private GivenSteps givenSteps = new GivenSteps();
	private WhenSteps whenSteps = new WhenSteps();
	private ThenSteps thenSteps = new ThenSteps();
	
	@Test
	public void realizarCadastroSelecao() {
		givenSteps.acessarPaginaLogin(ConstantsTest.URL_PAGE_LOGIN);
		givenSteps.efetuarLogin(ConstantsTest.LOGIN_VALUE, ConstantsTest.PASSWORD_VALUE);
		givenSteps.acessarPaginaInicial(ConstantsTest.URL_PAGE_PRINCIPAL);
		givenSteps.clicaBTNovaSelecao();
		givenSteps.acessarPaginaCadastrar(ConstantsTest.URL_PAGE_CADASTRAR);
		whenSteps.selecionarTipoSelecao(ConstantsTest.TIPO_SELECAO_VALUE);
		whenSteps.campoAnoPreenchido(ConstantsTest.ANO_VALUE);
		whenSteps.campoInicioInscricoesPreenchido(ConstantsTest.INICIO_INSCRICOES_VALUE);
		whenSteps.campoTerminoInscricoesPreenchido(ConstantsTest.TERMINO_INSCRICOES_VALUE);
		whenSteps.campoVagasPreenchido(ConstantsTest.VAGAS_VALUE);
		whenSteps.clicaBTSalvar();
		thenSteps.acessandoPaginaDetalhes(ConstantsTest.URL_PAGE_DETALHES, "165");
		thenSteps.exibirMensagem(ConstantsTest.MENSAGEM_SUCESSO_CADASTRO);
	}
}
