package br.ufc.quixada.npi.gpa.utils;

public class Constants {
	
	/** Controllers*/
	
	public static final String ALUNO = "aluno";
	public static final String NIVEL_INSTRUCAO = "nivelInstrucao";
	public static final String SITUACAO_RESIDENCIA = "situacaoResidencia";
	public static final String SELECOES = "selecoes";
	public static final String ID_INSCRICAO = "idInscricao";
	public static final String LOGIN = "login";
	public static final String ENTREVISTA = "entrevista";
	public static final String DOCUMENTACAO = "documentacao";
	public static final String INSCRICAO = "inscricao";
	public static final String MORADIA_ESTADO = "moradiaEstado";
	public static final String DIAS_UTEIS = "diasUteis";
	public static final String TOTAL_ESTADO = "totalEstado";
	public static final String GRAU_PARENTESCO = "grauParentesco";
	public static final String ERROR = "error";
	public static final String QUESTIONARIO_AUXILIO_MORADIA = "questionarioAuxilioMoradia";
	public static final String QUESTIONARIO_INICIACAO_ACADEMICA = "questionarioIniciacaoAcademica";
	public static final String TIPO_AUX_MORADIA = "aux_mor";
	public static final String TIPO_INIC_ACAD = "inic_acad";
	public static final String EDITAR = "editar";
	public static final String USUARIO_ATIVO = "usuarioAtivo";
	public static final String ESCONDER_BOTOES = "esconderBotoes";
	public static final String CLASSIFICADOS = "classificados";
	public static final String DATA_TERMINO = "dataTermino";
	public static final String TIPO_BOLSA = "tipoBolsa";
	public static final String MESSAGE = "message";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String CURSO = "curso";
	public static final String RELATORIO_VISITA_DOMICILIAR = "relatorioVisitaDomiciliar";
	public static final String TURNO = "turno";
	public static final String ESCOLARIDADE = "escolaridade";
	public static final String TIPO_SELECAO = "tipoSelecao";
	public static final String RESULT = "result";

	/** Páginas */
	
	public static final String PAGINA_CADASTRAR_SELECAO = "coordenador/cadastrar-selecao";
	
	public static final String PAGINA_GERENCIAR_DOCUMENTOS = "coordenador/gerenciar-documentos";
	
	public static final String PAGINA_DETALHE_SELECAO_COMISSAO = "servidor/detalhe-selecao";
	
	
	
	
	// old
	public static final String PAGINA_LISTAR_SELECAO_SERVIDOR 		= "servidor/listar-selecao";
	
	public static final String PAGINA_ATRIBUIR_COMISSAO = "coordenador/atribuirMembroComissao";

	public static final String PAGINA_RANKING_CLASSIFICADOS			= "selecao/rankingClassificados";
	
	public static final String PAGINA_LISTAR_ALUNOS 				= "administrador/listarAlunos";
	
	public static final String PAGINA_FORMULARIO_PREENCHIDO_SELECAO	= "selecao/formularioInscricaoPreenchido";
	
	public static final String PAGINA_INFORMACOES_RELATORIO			= "servidor/informacoesRelatorio";
	
	public static final String PAGINA_RELATORIO_VISITA				= "servidor/relatorioVisita";
	
	public static final String PAGINA_CADASTRAR_SERVIDOR			= "administrador/cadastrarServidor";
	
	public static final String PAGINA_LISTAR_SERVIDOR				= "administrador/listarServidor";
	
	public static final String PAGINA_CADASTRAR_ALUNO				= "administrador/cadastrarAluno";
	
	public static final String PAGINA_REALIZAR_ENTREVISTA			= "servidor/realizarEntrevista";
	
	public static final String PAGINA_LISTAR_SELECAO 				= "selecao/listarSelecao";

	public static final String PAGINA_SELECIONAR_CLASSIFICADOS 		= "selecao/selecionarClassificados";
	
	public static final String PAGINA_ADICIONAR_ARQUIVO 			= "coordenador/adicionarArquivoSelecao";
	
	public static final String PAGINA_ADICIONAR_ARQUIVO_INSCRICAO 	= "aluno/uploadDocumentos";

	public static final String PAGINA_INSCREVER_INICIACAO_ACADEMICA = "aluno/inscricaoIniciacaoAcademica";

	public static final String PAGINA_DETALHES_AUXILIO_MORADIA 		= "aluno/detalhesAuxilioMoradia";

	public static final String PAGINA_DETALHES_INICIACAO_ACADEMICA 	= "aluno/detalhesIniciacaoAcademica";

	public static final String PAGINA_INSCREVER_AUXILIO_MORADIA 	= "aluno/inscricaoAuxilio";

	public static final String PAGINA_DETALHES_INSCRICAO 			= "inscricao/detalhesInscricao";

	public static final String PAGINA_INSCRICOES_ALUNO 				= "aluno/minhasInscricoes";

	public static final String PAGINA_SELECOES_ABERTAS 				= "aluno/listarSelecoes";
	
	public static final String PAGINA_AVALIAR_DOCUMENTACAO 			= "servidor/avaliarDocumentacao";
	
	public static final String PAGINA_RELATORIO_VISITAS 			= "servidor/relatorioVisitasCidades";
	
	public static final String PAGINA_RELATORIO_FINAL 				= "selecao/resultadoFinal";
	
	public static final String PAGINA_RELATORIO_VISITA_SELECAO 		= "/selecao/relatorioVisita";

	

	/** Redirecionamentos */

	public static final String REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR = "redirect:/servidor/selecao/listar";
	
	public static final String REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO = "redirect:/aluno/selecao/listar";
	
	public static final String REDIRECT_PAGINA_GERENCIAR_DOCUMENTOS = "redirect:/coordenador/tipo-documento";
	
	public static final String REDIRECT_PAGINA_DETALHES_SELECAO = "redirect:/servidor/detalhe-selecao/";
	
	
	
	// old
	public static final String REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS = "redirect:/selecao/selecionarClassificados/";

	public static final String REDIRECT_PAGINA_EDITAR_SELECAO = "redirect:/coordenador/selecao/editar/";
	
	public static final String REDIRECT_PAGINA_ADICIONAR_ARQUIVO = "redirect:/coordenador/selecao/adicionar-documento/";
	
	public static final String REDIRECT_PAGINA_ADICIONAR_ARQUIVO_INSCRICAO = "redirect:/aluno/inscricao/adicionar-documento/";

	public static final String REDIRECT_PAGINA_ATRIBUIR_COMISSAO = "redirect:/coordenador/comissao/atribuir/";

	public static final String REDIRECT_PAGINA_LISTAR_SELECAO = "redirect:/selecao/listar";
	
	public static final String REDIRECT_PAGINA_MINHAS_INSCRICOES = "redirect:/aluno/inscricao/listar";

	public static final String REDIRECT_PAGINA_LISTAR_SERVIDOR = "redirect:/administrador/listar";

	public static final String REDIRECT_PAGINA_LISTAR_ALUNOS = "redirect:/administrador/listar/alunos";
	
	public static final String REDIRECT_PAGINA_INFORMACOES_SELECAO_SERVIDOR = "redirect:/servidor/detalhesSelecaoServidor";

	public static final String REDIRECT_PAGINA_DETALHES_INSCRICAO = "redirect:/inscricao/detalhes/inscricao/";
	
	public static final String REDIRECT_PAGINA_SELECAO_INSCRITOS = "redirect:/selecao/inscritos/";
	
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
	
	public static final String MENSAGEM_DE_SUCESSO_ENTREVISTA_ATUALIZAR = "Entrevista atualizada com sucesso.";

	public static final String MENSAGEM_SUCESSO_PARECER_EMITIDO = "Parecer emitido com sucesso.";

	public static final String MENSAGEM_SUCESSO_INSCRICAO_REALIZADA = "Inscrição realizada com sucesso.";

	public static final String MENSAGEM_SUCESSO_INSCRICAO_EDITADA = "Inscrição editada com sucesso.";
	
	public static final String MENSAGEM_SUCESSO_INSCRICAO_CONSOLIDADA = "Inscrição consolidada com sucesso.";
	
	public static final String MENSAGEM_FALTA_DE_PERMISSAO = "Você não tem permissão para acessar este recurso";

	public static final String MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA = "Inscrição excluída com sucesso.";

	public static final String MENSAGEM_SUCESSO_SELECAO_CADASTRADA = "Nova seleção cadastrada com sucesso.";

	public static final String MENSAGEM_SUCESSO_SELECAO_ATUALIZADA = "Seleção atualizada com sucesso.";

	public static final String MENSAGEM_SUCESSO_SELECAO_REMOVIDA = "Seleção removida com sucesso.";

	public static final String MENSAGEM_SUCESSO_DOWNLOAD_DOCUMENTO = "Download do documento realizado com sucesso";

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
	
	public static final String MENSAGEM_ERRO_REALIZAR_INSCRICAO = "Você não pode realizar esta inscrição fora do período da seleção";
	
	public static final String MENSAGEM_ERRO_UPLOAD_FOTO = "Não foi possível fazer o upload da sua foto";
	
	public static final String MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO = "Extensão da foto inválida. Extensões aceitas (jpeg, jpg, png)";
	
	public static final String MENSAGEM_ERRO_DOCUMENTO_FORMATO_INVALIDO = "O documento deve ser enviado no formato PDF.";
	
	public static final String MENSAGEM_ERRO_QTD_VAGAS = "Não restam vagas suficientes para esta quantidade de alunos";
	
	public static final String MENSAGEM_ERRO_SELECIONE_UM_CLASSIFICADO = "Selecione pelo menos um aluno classificado";

	public static final String MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO = "Não é possível excluir esse arquivo";
	
	public static final String MENSAGEM_TIPO_DOCUMENTO_EXCUIDO_COM_SUCESSO = "Tipo de documento excluído com sucesso";
	
	public static final String MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO_EM_USO = "Não é possível excluir este tipo de documento pois ele já está em uso";
	
	public static final String MENSAGEM_ERRO_DADOS_INSCRICAO= "Verifique os dados inseridos e tente novamente";
	
	public static final String MENSAGEM_ADICIONAR_DOCUMENTOS_INSCRICAO = "Adicione os documentos referentes a sua inscrição. Você deverá fazer isso até o final do período de inscrição.";
	
	/** Perfis */

	public static final String STA = "STA";

	public static final String DOCENTE = "DOCENTE";

	public static final String DISCENTE = "DISCENTE";

	public static final String COORDENADOR_ASSUNTOS_ESTUDANTIS = "COORDENADOR_ASSUNTO_ESTUDANTIS";

	public static final String ADMINISTRADOR = "ADMINISTRADOR_GPA";

	/** Resources */
	
	public static final String CAMINHO_IMAGEM_ALUNO_SEM_FOTO = "/MAE/resources/img/alunoImage.png";
	
	/** Comunicação com Javascript */
	
	public static final String ABA_SELECIONADA = "aba";
	
	public static final String DOCUMENTOS_TAB = "documentos-tab";
	
	public static final String INSCRICAO_TAB = "inscricao-tab";
	
	public static final String VISITA_TAB = "visita-tab";
	
	public static final String ENTREVISTA_TAB = "entrevista-tab";
	
	/** Comunicação Javascript com os cards**/
	
	public static final String CARD_SELECIONADO = "card";
	
	public static final String CARD_INSCRICAO = "card-inscricao";
	public static final String CARD_COMISSAO = "card-comissao";
	public static final String CARD_ARQUIVOS = "card-arquivos";
	public static final String CARD_RELATORIO = "card-relatorio";
	public static final String CARD_RANK = "card-rank";
	
	public static final String RESULTADO = "resultado";
	
	public static final String SUCESSO = "sucesso";
	
	/** Email**/
	public static final String FROM = "naoresponda@gpaassuntosestudantis.com";
	
	public static final String BODY = "Prezado(a),\n"+
			"Sua inscrição para a seleção de auxílio moradia foi consolidada com sucesso!\n"+
			"Atenciosamente,\n\n"+
			"Coordenação de Assuntos Estudantis\n"+
			"UFC – Campus Quixadá\n"+
			" E-mail enviado automaticamente, por gentileza, não responder.";
	
	public static final String ASSUNTO = "Consolidação Confirmada";
	
	
	/** Mensagens do sistema*/
	
	public static final String ERRO = "erro";
	
	public static final String INFO = "info";


}