package br.ufc.quixada.npi.gpa.utils;

public class Constants {

	/** Usuário */

	public static final String USUARIO_LOGADO = "usuario";

	public static final String USUARIO_ID = "id";

	/** Páginas */
	
	public static final String PAGINA_ATRIBUIR_COMISSAO 			= "coordenador/atribuirMembroComissao";

	public static final String PAGINA_CADASTRAR_SELECAO 			= "coordenador/cadastrarSelecao";
	
	public static final String PAGINA_LISTAR_SELECAO				= "selecao/listarSelecao";

	public static final String PAGINA_INFORMACOES_SELECAO			= "selecao/detalhesSelecao";
	
	public static final String PAGINA_LISTAR_INSCRITOS_SELECAO		= "selecao/listarInscritos";
	
	public static final String PAGINA_LISTAR_ALUNOS 				= "selecao/listarAlunos";
	
	public static final String PAGINA_FORMULARIO_PREENCHIDO_SELECAO	= "selecao/formularioInscricaoPreenchido";
	
	public static final String PAGINA_INFORMACOES_RELATORIO			= "servidor/informacoesRelatorio";
	
	public static final String PAGINA_RELATORIO_VISITA				= "servidor/relatorioVisita";
	
	public static final String PAGINA_CADASTRAR_SERVIDOR			= "servidor/cadastrarServidor";
	
	public static final String PAGINA_LISTAR_SERVIDOR				= "servidor/listarServidor";
	
	public static final String PAGINA_CADASTRAR_ALUNO				= "servidor/cadastrarAluno";
	
	public static final String PAGINA_REALIZAR_ENTREVISTA			= "servidor/realizarEntrevista";
	
	public static final String PAGINA_INSCREVER_INICIACAO_ACADEMICA = "aluno/inscricaoIniciacaoAcademica";

	public static final String PAGINA_VISUALIZAR_INSC_AUX_MOR 		= "aluno/detalhesInscAuxMor";

	public static final String PAGINA_INSCREVER_AUXILIO_MORADIA 	= "aluno/inscricaoAuxilio";
	
	public static final String PAGINA_DETALHES_INSCRICAO			= "aluno/detalhesInscricao";
	
	public static final String PAGINA_INSCRICOES_ALUNO				= "aluno/minhasInscricoes";
	
	public static final String PAGINA_SELECOES_ABERTAS				= "aluno/listarSelecoes";

	/** Redirecionamentos */

	public static final String REDIRECT_PAGINA_ATRIBUIR_COMISSAO 	= "redirect:/coordenador/comissao/atribuir/";
	
	public static final String REDIRECT_PAGINA_LISTAR_SELECAO 		= "redirect:/selecao/listar";
	
	public static final String REDIRECT_PAGINA_INSCRITOS_SELECAO	= "redirect:/selecao/inscritos";
	
	public static final String REDIRECT_PAGINA_LISTAR_SERVIDOR		= "redirect:/servidor/listar";
	
	public static final String REDIRECT_PAGINA_LISTAR_ALUNOS		= "redirect:/servidor/listar/alunos";
	
	/** Mensagens */
	
	public static final String MENSAGEM_PERMISSAO_NEGADA			= "Permissão negada.";
	
	public static final String MENSAGEM_CAMPO_OBRIGATORIO			= "Campo obrigatório.";
	
	public static final String MENSAGEM_ALUNO_CADASTRADO			= "Aluno cadastrado com sucesso.";
	
	public static final String MENSAGEM_ALUNO_ATUALIZADO			= "Aluno atualizado com sucesso.";
	
	public static final String MENSAGEM_ALUNO_EXCLUIDO				= "Aluno removido com sucesso.";
	
	public static final String MENSAGEM_ALUNO_ENCONTRADO			= "Aluno não encontrado";
	
	public static final String MENSAGEM_SERVIDOR_CADASTRADO			= "Servidor cadastrado com sucesso.";
	
	public static final String MENSAGEM_SERVIDOR_ATUALIZADO			= "Servidor atualizado com sucesso.";
	
	public static final String MENSAGEM_SERVIDOR_EXCLUIDO			= "Servidor excluído com sucesso.";
	
	public static final String MENSAGEM_SERVIDOR_NAO_ENCONTRADO		= "Servidor não encontrado";
	
	public static final String MENSAGEM_SERVIDOR_NAO_ASSOCIADO 		= "Você não está associado a nenhuma seleção.";
	
	public static final String MENSAGEM_VISITA_CADASTRADA			= "Relatório da visita cadastrado com sucesso.";
	
	public static final String MENSAGEM_ERRO_ALUNO_INEXISTENTE		= "Aluno inexistente.";
	
	public static final String MENSAGEM_ERRO_SERVIDOR_INEXISTENTE	= "Servidor inexistente.";
	
	public static final String MENSAGEM_ERRO_SIAPE_EXISTENTE		= "Não é possível cadastrar um SIAPE já existente.";
	
	public static final String MENSAGEM_ERRO_MATRICULA_EXISTENTE	= "Matrícula já existente.";
	
	public static final String MENSAGEM_ERRO_MATRICULA_DIGITOS		= "A matrícula deve possuir pelo menos seis dígitos";
	
	public static final String MENSAGEM_ERRO_VISITA_INEXISTENTE		= "Informações não encontradas. Relatório sobre esta visita não existem.";
	
	public static final String MENSAGEM_ERRO_ANO_INGRESSO_DIGITOS	= "O ano deve possuir pelo menos quatro dígitos";
	
	public static final String MENSAGEM_ERRO_ANO_INGRESSO			= "Informe um ano menor ou igual ao atual";
	
	public static final String MENSAGEM_ERRO_AGENCIA_DIGITOS		= "O número da agência deve possuir pelo menos seis dígitos";
	
	public static final String MENSAGEM_ERRO_CONTA_DIGITOS			= "O número da conta deve possuir pelo menos quatro dígitos";

	public static final String MENSAGEM_DE_SUCESSO_ENTREVISTA		= "Entrevista realizada com sucesso.";

	public static final String MENSAGEM_INSCRICAO_INEXISTENTE		= "Inscrição inexistente.";
	
	/** Perfis */
	
	public static final String STA 									= "STA";
	
	public static final String DOCENTE 								= "DOCENTE";
	
	public static final String DISCENTE 							= "DISCENTE";
	
	public static final String COORDENADOR_ASSUNTOS_ESTUDANTIS 		= "COORDENADOR_ASSUNTO_ESTUDANTIS";

}
