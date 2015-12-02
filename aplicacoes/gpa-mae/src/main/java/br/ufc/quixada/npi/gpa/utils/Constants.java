package br.ufc.quixada.npi.gpa.utils;

public class Constants {
	
	/** Usuário */
	
	public static final String USUARIO_LOGADO 						= "usuario";
	
	public static final String USUARIO_ID 							= "id";
	
	/** Páginas */
	
	public static final String PAGINA_ATRIBUIR_COMISSAO 			= "coordenador/atribuirMembroComissao";

	public static final String PAGINA_CADASTRAR_SELECAO 			= "coordenador/cadastrarSelecao";
	
	public static final String PAGINA_LISTAR_SELECAO				= "selecao/listarSelecao";

	public static final String PAGINA_INFORMACOES_SELECAO			= "selecao/detalhesSelecao";
	
	public static final String PAGINA_LISTAR_INSCRITOS_SELECAO		= "selecao/listarInscritos";
	
	public static final String PAGINA_LISTAR_ALUNOS 				= "selecao/listarAlunos";
	
	public static final String PAGINA_FORMULARIO_PREENCHIDO_SELECAO	= "selecao/formularioInscricaoPreenchido";
	
	public static final String PAGINA_INFORMACOES_RELATORIO			= "selecao/informacoesRelatorio";
	
	public static final String PAGINA_RELATORIO_VISITA				= "servidor/relatorioVisita";
	
	public static final String PAGINA_CADASTRAR_SERVIDOR			= "servidor/cadastrarServidor";
	
	public static final String PAGINA_LISTAR_SERVIDOR				= "servidor/listarServidor";
	
	public static final String PAGINA_CADASTRAR_ALUNO				= "servidor/cadastrarAluno";
	
	public static final String PAGINA_INSCREVER_INICIACAO_ACADEMICA = "aluno/inscricaoIniciacaoAcademica";

	public static final String PAGINA_INSCREVER_AUXILIO_MORADIA 	= "aluno/inscricaoAuxilio";
	
	public static final String PAGINA_DETALHES_INSCRICAO			= "aluno/detalhesInscricao";
	
	public static final String PAGINA_INSCRICOES_ALUNO				= "aluno/minhasInscricoes";
	
	public static final String PAGINA_SELECOES_ABERTAS				= "aluno/listarSelecoesAbertas";
	
	/** Redirecionamentos */
	
	public static final String REDIRECT_PAGINA_ATRIBUIR_COMISSAO 	= "redirect:/coordenador/comissao/atribuir/";
	
	public static final String REDIRECT_PAGINA_LISTAR_SELECAO 		= "redirect:/selecao/listar";
	
	public static final String REDIRECT_PAGINA_INSCRITOS_SELECAO	= "redirect:/selecao/inscritos/";
	
	public static final String REDIRECT_PAGINA_LISTAR_SERVIDOR		= "redirect:/servidor/listar";
	
	public static final String REDIRECT_PAGINA_LISTAR_ALUNOS		= "redirect:/servidor/listar/alunos";
	
	
}
