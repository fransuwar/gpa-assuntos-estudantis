package br.ufc.quixada.npi.gpa.utils;

public class Constants {

	/** Usuário */

	public static final String USUARIO_LOGADO = "usuario";

	public static final String USUARIO_ID = "id";

	/** Páginas */

	public static final String PAGINA_ATRIBUIR_COMISSAO = "coordenador/atribuirMembroComissao";

	public static final String PAGINA_INFORMACOES_SELECAO			= "selecao/detalhesSelecao";
	
	public static final String PAGINA_RANKING_CLASSIFICADOS			= "selecao/rankingClassificados";
	
	public static final String PAGINA_INFORMACOES_SELECAO_SERVIDOR	= "servidor/detalhesSelecaoServidor";
	
	public static final String PAGINA_LISTAR_INSCRITOS_SELECAO		= "selecao/listarInscritos";
	
	public static final String PAGINA_LISTAR_ALUNOS 				= "administrador/listarAlunos";
	
	public static final String PAGINA_FORMULARIO_PREENCHIDO_SELECAO	= "selecao/formularioInscricaoPreenchido";
	
	public static final String PAGINA_INFORMACOES_RELATORIO			= "servidor/informacoesRelatorio";
	
	public static final String PAGINA_RELATORIO_VISITA				= "servidor/relatorioVisita";
	
	public static final String PAGINA_CADASTRAR_SERVIDOR			= "administrador/cadastrarServidor";
	
	public static final String PAGINA_LISTAR_SERVIDOR				= "administrador/listarServidor";
	
	public static final String PAGINA_CADASTRAR_ALUNO				= "administrador/cadastrarAluno";
	
	public static final String PAGINA_REALIZAR_ENTREVISTA			= "servidor/realizarEntrevista";
	
	public static final String PAGINA_CADASTRAR_SELECAO = "coordenador/cadastrarSelecao";

	public static final String PAGINA_LISTAR_SELECAO = "selecao/listarSelecao";

	public static final String PAGINA_LISTAR_SELECAO_SERVIDOR = "servidor/listaSelecoes";
	
	public static final String PAGINA_SELECIONAR_CLASSIFICADOS = "selecao/selecionarClassificados";
	
	public static final String PAGINA_ADICIONAR_ARQUIVO = "coordenador/adicionarArquivoSelecao";

	public static final String PAGINA_INSCREVER_INICIACAO_ACADEMICA = "aluno/inscricaoIniciacaoAcademica";

	public static final String PAGINA_DETALHES_AUXILIO_MORADIA = "aluno/detalhesAuxilioMoradia";

	public static final String PAGINA_DETALHES_INICIACAO_ACADEMICA = "aluno/detalhesIniciacaoAcademica";

	public static final String PAGINA_INSCREVER_AUXILIO_MORADIA = "aluno/inscricaoAuxilio";

	public static final String PAGINA_DETALHES_INSCRICAO = "inscricao/detalhesInscricao";

	public static final String PAGINA_INSCRICOES_ALUNO = "aluno/minhasInscricoes";

	public static final String PAGINA_SELECOES_ABERTAS = "aluno/listarSelecoes";
	
	public static final String PAGINA_AVALIAR_DOCUMENTACAO = "servidor/avaliarDocumentacao";

	// public static final String PAGINA_DETALHES_SELECAO_SERVIDOR =
	// "servidor/detalhesSelecao";

	/** Redirecionamentos */

	public static final String REDIRECT_PAGINA_EDITAR_SELECAO = "redirect:/coordenador/selecao/editar/";
	
	public static final String REDIRECT_PAGINA_ADICIONAR_ARQUIVO = "redirect:/coordenador/selecao/adicionar-documento/";

	public static final String REDIRECT_PAGINA_ATRIBUIR_COMISSAO = "redirect:/coordenador/comissao/atribuir/";

	public static final String REDIRECT_PAGINA_LISTAR_SELECAO = "redirect:/selecao/listar";
	
	public static final String REDIRECT_PAGINA_MINHAS_INSCRICOES = "redirect:/aluno/inscricao/listar";

	public static final String REDIRECT_PAGINA_INSCRITOS_SELECAO = "redirect:/servidor/detalhes/";

	public static final String REDIRECT_PAGINA_LISTAR_SERVIDOR = "redirect:/administrador/listar";

	public static final String REDIRECT_PAGINA_LISTAR_ALUNOS = "redirect:/administrador/listar/alunos";

	public static final String REDIRECT_PAGINA_INSCRICOES_ALUNO = "redirect:/aluno/inscricao/listar";
	
	public static final String REDIRECT_PAGINA_INFORMACOES_SELECAO_SERVIDOR = "redirect:/servidor/detalhesSelecaoServidor";

	
	/** Mensagens */

	public static final String MENSAGEM_OK = "OK";

	public static final String MENSAGEM_RUNTIME_EXCEPTION_DOCUMENTO = "IOError writing file to output stream";

	public static final String MENSAGEM_PERMISSAO_NEGADA = "Você não tem permissão para acessar essa página!";

	public static final String MENSAGEM_PERMISSAO_NEGADA_USUARIO = ", você não tem permissão para acessar essa página!";

	public static final String MENSAGEM_CAMPO_OBRIGATORIO = "Campo obrigatório.";

	public static final String MENSAGEM_ALUNO_CADASTRADO = "Aluno cadastrado com sucesso.";

	public static final String MENSAGEM_ALUNO_ATUALIZADO = "Aluno atualizado com sucesso.";

	public static final String MENSAGEM_ALUNO_EXCLUIDO = "Aluno removido com sucesso.";

	public static final String MENSAGEM_ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";

	public static final String MENSAGEM_SERVIDOR_CADASTRADO = "Servidor cadastrado com sucesso.";

	public static final String MENSAGEM_SERVIDOR_ATUALIZADO = "Servidor atualizado com sucesso.";

	public static final String MENSAGEM_SERVIDOR_EXCLUIDO = "Servidor excluído com sucesso.";

	public static final String MENSAGEM_DE_SUCESSO_ENTREVISTA = "Entrevista realizada com sucesso.";

	public static final String MENSAGEM_SUCESSO_PARECER_EMITIDO = "Parecer emitido com sucesso.";

	public static final String MENSAGEM_SUCESSO_INSCRICAO_REALIZADA = "Inscrição realizada com sucesso.";

	public static final String MENSAGEM_SUCESSO_INSCRICAO_EDITADA = "Inscrição editada com sucesso.";

	public static final String MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA = "Inscrição Excluída com Sucesso.";

	public static final String MENSAGEM_SUCESSO_SELECAO_CADASTRADA = "Nova seleção cadastrada com sucesso.";

	public static final String MENSAGEM_SUCESSO_SELECAO_ATUALIZADA = "Seleção atualizada com sucesso.";

	public static final String MENSAGEM_SUCESSO_SELECAO_REMOVIDA = "Seleção removida com sucesso.";

	public static final String MENSAGEM_SUCESSO_DOWNLOAD_DOCUMENTO = "Download do Documento realizado com sucesso";

	public static final String MENSAGEM_SUCESSO_COMISSAO_FORMADA = "Membro inserido com sucesso.";

	public static final String MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO = "Membro excluído com sucesso.";

	public static final String MENSAGEM_SUCESSO_LOGIN = "Logado com sucesso!";

	public static final String MENSAGEM_SERVIDOR_NAO_ENCONTRADO = "Servidor não encontrado";

	public static final String MENSAGEM_SERVIDOR_NAO_ASSOCIADO = "Você não está associado a nenhuma seleção.";

	public static final String MENSAGEM_VISITA_CADASTRADA = "Relatório de visita cadastrado com sucesso.";

	public static final String MENSAGEM_ERRO_SIAPE_EXISTENTE = "Não é possível cadastrar um SIAPE já existente.";

	public static final String MENSAGEM_ERRO_MATRICULA_EXISTENTE = "Matrícula já existente.";

	public static final String MENSAGEM_ERRO_MATRICULA_DIGITOS = "A matrícula deve possuir pelo menos seis dígitos";

	public static final String MENSAGEM_ERRO_VISITA_INEXISTENTE = "Informações não encontradas. Relatório sobre esta visita não existe.";

	public static final String MENSAGEM_ERRO_ANO_INGRESSO_DIGITOS = "O ano deve possuir pelo menos quatro dígitos";

	public static final String MENSAGEM_ERRO_ANO_INGRESSO = "Informe um ano menor ou igual ao atual";

	public static final String MENSAGEM_ERRO_AGENCIA_DIGITOS = "O número da agência deve possuir pelo menos seis dígitos";

	public static final String MENSAGEM_ERRO_CONTA_DIGITOS = "O número da conta deve possuir pelo menos quatro dígitos";

	public static final String MENSAGEM_ERRO_INSCRICAO_INEXISTENTE = "Inscrição inexistente.";

	public static final String MENSAGEM_ERRO_SELECAO_INEXISTENTE = "Seleção Inexistente.";

	public static final String MENSAGEM_ERRO_SALVAR_DOCUMENTOS = "Não foi possível salvar os documentos.";

	public static final String MENSAGEM_DOCUMENTO_INEXISTENTE = "Documento não existe";

	public static final String MENSAGEM_ERRO_ANEXO = "Adicione anexo a seleção.";

	public static final String MENSAGEM_ERRO_SELECAO_REMOVER = "Permissão negada. Só é possível remover uma seleção enquanto seu status é nova.";

	public static final String MENSAGEM_ERRO_ANEXO_EXCLUIR = "Não foi possível excluir seu(s) anexo(s), pois não é possível salvar a seleção sem nenhum anexo.";

	public static final String MENSAGEM_ERRO_MEMBRO_COMISSAO_ATRIBUIR = "Informe pelo menos um membro.";

	public static final String MENSAGEM_ERRO_MEMBRO_COMISSAO_REPETICAO = "Não é permitida repetição de membros na comissão.";

	public static final String MENSAGEM_ERRO_LOGIN = "Usuário e/ou senha inválidos!";

	public static final String MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR = "Digite um ano maior ou igual ao atual.";

	public static final String MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR = "A data de término não pode ser anterior a data de início.";

	public static final String MENSAGEM_ERRO_SEQUENCIAL_SELECAO_CADASTRAR = "Número do edital com esse tipo de Seleção já existente";

	public static final String MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR = "Não é possivel excluir o Coordenador da Comissão";

	public static final String MENSAGEM_DOCUMENTO_REMOVIDO                = "Documento removido com Sucesso";
	
	public static final String MENSAGEM_ERRO_TIPO_BOLSA					  = "Informe o tipo de Bolsa";
	
	public static final String MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_ENTREVISTA = "Acesso não autorizado! Você não pode realizar a entrevista pois não faz parte da comissão";
	
	public static final String MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_VISITA = "Acesso não autorizado! Você não pode realizar a visita pois não faz parte da comissão";

	public static final String MENSAGEM_ERRO_EXCLUIR_SELECAO_COM_INSCRITOS 	= "Seleção possui inscritos e não pode ser excluída";

	public static final String MENSAGEM_ERRO_INSCRICAO_EXISTENTE_NA_SELECAO = "Você já está inscrito nesta seleção";
	
	public static final String MENSAGEM_DE_SUCESSO_AVALIAR_DOCUMENTACAO = "Documentação avaliada com sucesso.";

	public static final String MENSAGEM_ERRO_ALUNO_INDEFERIDO = "Você não pode realizar a entrevista porque o aluno foi indeferido na análise da documentação!";

	public static final String MENSAGEM_ERRO_REALIZACAO_DE_VISITA_DOMICILIAR = "Inscrição não foi deferida na entrevista";
	
	public static final String MENSAGEM_ERRO_VISITA_DOMICILIAR_JA_EXISTENTE = "Já foi realizado uma visita para essa inscrição";
	
	public static final String MENSAGEM_ERRO_EXCLUIR_INSCRICAO = "Você não pode excluir esta inscrição fora do período da seleção";
	
	public static final String MENSAGEM_ERRO_EDITAR_INSCRICAO = "Você não pode editar esta inscrição fora do período da seleção";
	
	public static final String MENSAGEM_ERRO_UPLOAD_FOTO = "Não foi possível fazer o upload da sua foto";
	
	public static final String MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO = "Extensão da foto inválida. Extensões aceitas (jpeg, jpg, png)";
	
	public static final String MENSAGEM_ERRO_QTD_VAGAS = "A quantidade de vagas já foram preenchidas";
	
	public static final String MENSAGEM_ERRO_SELECIONE_UM_CLASSIFICADO = "Selecione pelo menos um aluno classificado";
	
	/** Perfis */

	public static final String STA = "STA";

	public static final String DOCENTE = "DOCENTE";

	public static final String DISCENTE = "DISCENTE";

	public static final String COORDENADOR_ASSUNTOS_ESTUDANTIS = "COORDENADOR_ASSUNTO_ESTUDANTIS";

	public static final String ADMINISTRADOR = "ADMINISTRADOR_GPA";

	

}