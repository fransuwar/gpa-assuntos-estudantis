package br.ufc.npi.auxilio.utils;

public class RedirectConstants {
	
	// Seleção
	public static final String REDIRECT_LISTAR_SELECAO = "redirect:/selecao";
	public static final String REDIRECT_CADASTRAR_SELECAO = "redirect:/selecao/cadastrar";
	public static final String REDIRECT_DETALHES_SELECAO = "redirect:/selecao/detalhes/";


	// Outros
	public static final String REDIRECT_ERROR = "redirect:/error";
	public static final String REDIRECT_ERROR_TAMANHO_ARQUIVO_EXCEDIDO = "forward:/error/selecao/limite-excedido/";


	// Inscrição
	public static final String REDIRECT_INSCRICAO_MORADIA = "redirect:/inscricao/moradia/";
	public static final String REDIRECT_INSCRICAO_HISTORICO = "redirect:/inscricao/historico/";
	public static final String REDIRECT_INSCRICAO_SITUACAO_SOCIO = "redirect:/inscricao/situacao-socioeconomica/";
	public static final String REDIRECT_INSCRICAO_OUTROS = "redirect:/inscricao/outras-informacoes/";
	public static final String REDIRECT_INSCRICAO_DOCUMENTACAO = "redirect:/documentacao/";
	public static final String REDIRECT_ERROR_TAMANHO_ARQUIVO_EXCEDIDO_INSCRICAO = "forward:/error/inscricao/limite-excedido/";
	public static final String REDIRECT_PAGINA_ENTREVISTA = "redirect:/entrevista/";
	public static final String REDIRECT_DETALHES_INSCRICAO = "redirect:/inscricao/detalhes/";
	
}
