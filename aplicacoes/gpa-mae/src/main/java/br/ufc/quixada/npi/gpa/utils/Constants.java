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

	public static final String PAGINA_VISUALIZAR_INSC_AUX_MOR = "aluno/detalhesInscAuxMor";

	public static final String PAGINA_INSCREVER_AUXILIO_MORADIA 	= "aluno/inscricaoAuxilio";
	
	public static final String PAGINA_DETALHES_INSCRICAO			= "aluno/detalhesInscricao";
	
	public static final String PAGINA_INSCRICOES_ALUNO				= "aluno/minhasInscricoes";
	
	public static final String PAGINA_SELECOES_ABERTAS				= "aluno/listarSelecoesAbertas";

	/** Redirecionamentos */

	public static final String REDIRECT_PAGINA_ATRIBUIR_COMISSAO 	= "redirect:/coordenador/comissao/atribuir/";
	
	public static final String REDIRECT_PAGINA_LISTAR_SELECAO 		= "redirect:/selecao/listar";
	
	public static final String REDIRECT_PAGINA_INSCRITOS_SELECAO	= "redirect:/selecao/inscritos";
	
	public static final String REDIRECT_PAGINA_LISTAR_SERVIDOR		= "redirect:/servidor/listar";
	
	public static final String REDIRECT_PAGINA_LISTAR_ALUNOS		= "redirect:/servidor/listar/alunos";
	
	/** Mensagens */
	
	public static final String MENSAGEM_OK							= "OK";
	
	public static final String MENSAGEM_RUNTIME_EXCEPTION_DOCUMENTO	= "IOError writing file to output stream";
	
	public static final String MENSAGEM_PERMISSAO_NEGADA			= "Você não tem permissão para acessar essa página!";
	
	public static final String MENSAGEM_PERMISSAO_NEGADA_USUARIO	= ", você não tem permissão para acessar essa página!";
	
	public static final String MENSAGEM_CAMPO_OBRIGATORIO			= "Campo obrigatório.";
	
	public static final String MENSAGEM_ALUNO_CADASTRADO			= "Aluno cadastrado com sucesso.";
	
	public static final String MENSAGEM_ALUNO_ATUALIZADO			= "Aluno atualizado com sucesso.";
	
	public static final String MENSAGEM_ALUNO_EXCLUIDO				= "Aluno removido com sucesso.";
	
	public static final String MENSAGEM_ALUNO_NAO_ENCONTRADO		= "Aluno não encontrado";
	
	public static final String MENSAGEM_SERVIDOR_CADASTRADO			= "Servidor cadastrado com sucesso.";
	
	public static final String MENSAGEM_SERVIDOR_ATUALIZADO			= "Servidor atualizado com sucesso.";
	
	public static final String MENSAGEM_SERVIDOR_EXCLUIDO			= "Servidor excluído com sucesso.";
	
	public static final String MENSAGEM_DE_SUCESSO_ENTREVISTA		= "Entrevista realizada com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_PARECER_EMITIDO		= "Parecer emitido com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_INSCRICAO_REALIZADA	= "Inscrição realizada com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_INSCRICAO_EDITADA	= "Inscrição editada com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA	= "Inscrição Excluída com Sucesso.";
		
	public static final String MENSAGEM_SUCESSO_SELECAO_CADASTRADA	= "Nova seleção cadastrada com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_SELECAO_ATUALIZADA	= "Seleção atualizada com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_SELECAO_REMOVIDA	= "Seleção removida com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_DOWNLOAD_DOCUMENTO	= "Download do Documento realizado com sucesso";
	
	public static final String MENSAGEM_SUCESSO_COMISSAO_FORMADA	= "Informe pelo menos um membro.";
	
	public static final String MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO		= "Membro excluído com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_LOGIN				= "Logado com sucesso!";
	
	public static final String MENSAGEM_SERVIDOR_NAO_ENCONTRADO		= "Servidor não encontrado";
	
	public static final String MENSAGEM_SERVIDOR_NAO_ASSOCIADO 		= "Você não está associado a nenhuma seleção.";
	
	public static final String MENSAGEM_VISITA_CADASTRADA			= "Relatório de visita cadastrado com sucesso.";
	
	public static final String MENSAGEM_ERRO_SIAPE_EXISTENTE		= "Não é possível cadastrar um SIAPE já existente.";
	
	public static final String MENSAGEM_ERRO_MATRICULA_EXISTENTE	= "Matrícula já existente.";
	
	public static final String MENSAGEM_ERRO_MATRICULA_DIGITOS		= "A matrícula deve possuir pelo menos seis dígitos";
	
	public static final String MENSAGEM_ERRO_VISITA_INEXISTENTE		= "Informações não encontradas. Relatório sobre esta visita não existe.";
	
	public static final String MENSAGEM_ERRO_ANO_INGRESSO_DIGITOS	= "O ano deve possuir pelo menos quatro dígitos";
	
	public static final String MENSAGEM_ERRO_ANO_INGRESSO			= "Informe um ano menor ou igual ao atual";
	
	public static final String MENSAGEM_ERRO_AGENCIA_DIGITOS		= "O número da agência deve possuir pelo menos seis dígitos";
	
	public static final String MENSAGEM_ERRO_CONTA_DIGITOS			= "O número da conta deve possuir pelo menos quatro dígitos";
	
	public static final String MENSAGEM_ERRO_INSCRICAO_INEXISTENTE	= "Inscrição inexistente.";
	
	public static final String MENSAGEM_ERRO_SELECAO_INEXISTENTE	= "Seleção Inexistente.";
	
	public static final String MENSAGEM_ERRO_SALVAR_DOCUMENTOS		= "Não foi possível salvar os documentos.";
	
	public static final String MENSAGEM_DOCUMENTO_INEXISTENTE		= "Documento não existe";
	
	public static final String MENSAGEM_ERRO_ANEXO					= "Adicione anexo a seleção.";
	
	public static final String MENSAGEM_ERRO_SELECAO_REMOVER		= "Permissão negada. Só é possível remover uma seleção enquanto seu status é nova." ;
	
	public static final String MENSAGEM_ERRO_ANEXO_EXCLUIR			= "Não foi possível excluir seu(s) anexo(s), pois não é possível salvar a seleção sem nenhum anexo.";
	
	public static final String MENSAGEM_ERRO_MEMBRO_BANCA_ATRIBUIR	= "Informe pelo menos um membro.";
	
	public static final String MENSAGEM_ERRO_MEMBRO_BANCA_REPETICAO	= "Não é permitida repetição de membros na comissão.";
	
	public static final String MENSAGEM_ERRO_LOGIN					= "Usuário e/ou senha inválidos!";
		
	public static final String MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR	= "Digite um ano maior ou igual ao atual.";
	
	public static final String MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR = "A data de término não pode ser anterior a data de início.";
	
	public static final String MENSAGEM_ERRO_SEQUENCIAL_SELECAO_CADASTRAR = "Número do edital com esse tipo de bolsa já existente";
	
	public static final String MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR = "Não é possivel excluir o Coordenador da Comissão";
	
	/** Perfis */
	
	public static final String STA = "STA";
	
	public static final String DOCENTE = "DOCENTE";
	
	public static final String DISCENTE = "DISCENTE";
	
	public static final String COORDENADOR_ASSUNTOS_ESTUDANTIS = "COORDENADOR_ASSUNTO_ESTUDANTIS";

}
