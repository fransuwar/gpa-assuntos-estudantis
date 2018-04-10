package br.ufc.npi.auxilio.controller;


import br.ufc.npi.auxilio.enums.*;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.model.questionario.HistoricoEscolar;
import br.ufc.npi.auxilio.model.questionario.Identificacao;
import br.ufc.npi.auxilio.model.questionario.Moradia;
import br.ufc.npi.auxilio.model.questionario.SituacaoSocioeconomica;
import br.ufc.npi.auxilio.service.*;
import br.ufc.npi.auxilio.utils.alert.Alert;
import br.ufc.npi.auxilio.utils.alert.Type;
import br.ufc.npi.auxilio.utils.api.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static br.ufc.npi.auxilio.utils.Constants.COORDENADOR;
import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_ALUNO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_SERVIDOR;
import static br.ufc.npi.auxilio.utils.Constants.NUM_CARACTERES;

import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.*;
import static br.ufc.npi.auxilio.utils.PageConstants.*;
import static br.ufc.npi.auxilio.utils.RedirectConstants.*;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_MEMBRO_FAMILIA_ADICIONADO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_MEMBRO_FAMILIA_REMOVIDO;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {

	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ServidorService servidorService;

	@Autowired
	private PessoaService pessoaService;

	//Variável Log de Registro
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Lista das inscrições de um aluno
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping({"", "/"})
	public String listarInscricoes(Model model, Authentication auth) {
		model.addAttribute("inscricoes", inscricaoService.getAll(alunoService.buscarPorCpf(auth.getName())));
		return MINHAS_INSCRICOES;
	}


	/**
	 * Formulário de dados básicos
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("{selecao}")
	public String inscreverDadosBasicosForm(@PathVariable Selecao selecao, Model model, Authentication auth, RedirectAttributes redirect){
		// Verifica se está no período de inscrição
		if (selecao == null || !selecao.isInscricaoAberta()) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_LISTAR_SELECAO;
		}
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		Inscricao inscricao = inscricaoService.get(aluno, selecao);

		// Verifica se o aluno já possui inscrição
		if (inscricao == null) {
			// Realiza uma nova inscrição
			model.addAttribute("identificacao", new Identificacao());
			log.info(aluno.getPessoa().getPapeis()+" de CPF: "+
			aluno.getPessoa().getCpf()+" está realizando Identificação (Passo1/5) na seleção de id: "+ selecao.getId());
		} else {
			// Atualiza inscrição existente
			model.addAttribute("identificacao", inscricao.getIdentificacao());
		}
		model.addAttribute("aluno", aluno);
		model.addAttribute("selecao", selecao);
		model.addAttribute("estados", Estado.values());
		model.addAttribute("estadosCivis", EstadoCivil.values());

		return INSCRICAO_DADOS_BASICOS;
	}

	/**
	 * Dados básicos no aluno
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@PostMapping("{selecao}")
	public String inscreverDadosBasicos(@PathVariable Selecao selecao, Identificacao identificacao,
		   Authentication auth, RedirectAttributes redirect){
		try {
			Aluno aluno = alunoService.buscarPorCpf(auth.getName());
			Inscricao inscricao = inscricaoService.get(aluno, selecao);
			// Verifica se o aluno já possui inscrição
			if (inscricao == null) {
				// Cria uma nova inscrição
				inscricao = inscricaoService.salvar(selecao, aluno, identificacao);
				log.info(aluno.getPessoa().getPapeis()+" de CPF: "+
				aluno.getPessoa().getCpf()+" está realizando Núcleo familiar da moradia de origem (Passo 2/5)"
				+ " na seleção de id: "+ selecao.getId());
				
			} else {
				// Atualiza a inscrição existente
				inscricao.setIdentificacao(identificacao);
				inscricaoService.atualizar(inscricao);
			}
			// Redireciona para a página de moradia
			return REDIRECT_INSCRICAO_MORADIA + selecao.getId();
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("moradia/{selecao}")
	public String inscreverMoradiaForm(@PathVariable Selecao selecao, Model model, Authentication auth,
		   RedirectAttributes redirect) {
		// Verifica se está no período de inscrição
		if(selecao == null || !selecao.isInscricaoAberta()) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_LISTAR_SELECAO;
		}

		// Verifica se já existe uma inscrição
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		if (inscricao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_REALIZAR_INSCRICAO);
			return REDIRECT_DETALHES_SELECAO + selecao.getId();
		}

		model.addAttribute("moradia", inscricao.getMoradia());
		model.addAttribute("opcoesMoradoresOrigem", MoradoresOrigem.values());
		model.addAttribute("opcoesMoradores", Moradores.values());
		model.addAttribute("estados", Estado.values());
		model.addAttribute("imoveis", SituacaoImovel.values());
		return INSCRICAO_MORADIA;
	}

	/**
	 * Moradia de origem e moradia atual
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@PostMapping("moradia/{selecao}")
	public String inscreverMoradia(@PathVariable Selecao selecao, Moradia moradia,
			Authentication auth, RedirectAttributes redirect, @RequestParam String action){
		try {
			Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
			if (inscricao == null) {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
				return REDIRECT_LISTAR_SELECAO;

			}
			log.info(inscricao.getAluno().getPessoa().getPapeis()+" de CPF: "+
					inscricao.getAluno().getPessoa().getCpf()+" está realizando Histórico Escolar (Passo 3/5)"
					+ " na seleção de id: "+ selecao.getId());
			inscricao.setMoradia(moradia);
			inscricaoService.atualizar(inscricao);
			if ("avancar".equals(action))
				return REDIRECT_INSCRICAO_HISTORICO + selecao.getId();
			else if ("voltar".equals(action))
				return REDIRECT_INSCRICAO_DADOS_BASICOS + selecao.getId();
			else
				return REDIRECT_LISTAR_SELECAO;
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("historico/{selecao}")
	public String inscreverHistoricoForm(@PathVariable Selecao selecao, Model model, Authentication auth,
									   RedirectAttributes redirect) {
		// Verifica se está no período de inscrição
		if(selecao == null || !selecao.isInscricaoAberta()) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_LISTAR_SELECAO;
			
		}
		// Verifica se já existe uma inscrição
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		if (inscricao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_REALIZAR_INSCRICAO);
			return REDIRECT_DETALHES_SELECAO + selecao.getId();
		}

		model.addAttribute("historico", inscricao.getHistoricoEscolar());
		model.addAttribute("opcoesEnsinoMedio", TipoEnsino.values());
		model.addAttribute("opcoesServicos", ServicosProReitoria.values());
		model.addAttribute("opcoesTrajetos", Trajeto.values());
		return INSCRICAO_HISTORICO;
	}

	/**
	 * Histórico escolar
	 */
	@PostMapping("historico/{selecao}")
	public String inscreverHistorico(@PathVariable Selecao selecao, HistoricoEscolar historico,
		  	Model model, Authentication auth, RedirectAttributes redirect, @RequestParam String action){
		try {
			Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
			if (inscricao == null) {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
				return REDIRECT_LISTAR_SELECAO;
			}
			log.info(inscricao.getAluno().getPessoa().getPapeis()+" de CPF: "+
					inscricao.getAluno().getPessoa().getCpf()+" está realizando Situação socioeconômica (Passo 4/5)"
					+ " na seleção de id: "+ selecao.getId());
			inscricao.setHistoricoEscolar(historico);
			inscricaoService.atualizar(inscricao);
			if ("avancar".equals(action))
				return REDIRECT_INSCRICAO_SITUACAO_SOCIO + selecao.getId();
			else if ("voltar".equals(action))
				return REDIRECT_INSCRICAO_MORADIA + selecao.getId();
			else
				return REDIRECT_LISTAR_SELECAO;
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	/**
	 * Formulário de situação socioeconômica
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("situacao-socioeconomica/{selecao}")
	public String inscreverSituacaoSocioeconomicaForm(@PathVariable Selecao selecao, Model model, Authentication auth,
			 RedirectAttributes redirect) {
		// Verifica se está no período de inscrição
		if(selecao == null || !selecao.isInscricaoAberta()) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_LISTAR_SELECAO;
		}

		// Verifica se já existe uma inscrição
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		if (inscricao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_REALIZAR_INSCRICAO);
			return REDIRECT_DETALHES_SELECAO + selecao.getId();
		}

		model.addAttribute("situacao", inscricao.getSituacaoSocioeconomica());
		model.addAttribute("opcoesParentesco", GrauParentesco.values());
		model.addAttribute("opcoesEscolaridade", Escolaridade.values());
		model.addAttribute("inscricao", inscricao);
		model.addAttribute("pessoa", new PessoaFamilia());
		return INSCRICAO_SITUACAO_SOCIOECONOMICA;
	}

	/**
	 * Situação Socioeconômica
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@PostMapping("situacao-socioeconomica/{selecao}")
	public String inscreverSituacaoSocioeconomica(@PathVariable Selecao selecao, SituacaoSocioeconomica situacao,
			Authentication auth, RedirectAttributes redirect, @RequestParam String action){
		try {
			Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
			if (inscricao == null) {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
				return REDIRECT_LISTAR_SELECAO;
			}
			log.info(inscricao.getAluno().getPessoa().getPapeis()+" de CPF: "+
					inscricao.getAluno().getPessoa().getCpf()+" está realizando Outras informações (Passo 5/5)"
					+ " na seleção de id: "+ selecao.getId());
			inscricao.setSituacaoSocioEconomica(situacao);
			inscricaoService.atualizar(inscricao);
			if ("avancar".equals(action))
				return REDIRECT_INSCRICAO_OUTROS + selecao.getId();
			else if ("voltar".equals(action))
				return REDIRECT_INSCRICAO_HISTORICO + selecao.getId();
			else
				return REDIRECT_LISTAR_SELECAO;
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	/**
	 * Inclui membro da família na situação socioeconômica
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@PostMapping("situacao-socioeconomica/{selecao}/adicionar")
	public String adicionarMembroFamilia(@PathVariable Selecao selecao, PessoaFamilia pessoa,
										 Model model, RedirectAttributes redirect, Authentication auth) {
		if(selecao == null || !selecao.isInscricaoAberta()) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_LISTAR_SELECAO;
		}
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		if (inscricao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_DETALHES_SELECAO + selecao.getId();
		}
		pessoa.setQuestionario(inscricao.getQuestionario());
		inscricaoService.adicionarMembroFamilia(pessoa);
		model.addAttribute(INFO, MSG_SUCESSO_MEMBRO_FAMILIA_ADICIONADO);
		return REDIRECT_INSCRICAO_SITUACAO_SOCIO + selecao.getId();
	}

	/**
	 * Remove membro da família na situação socioeconômica
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("situacao-socioeconomica/{selecao}/remover/{membro}")
	public String removerMembroFamilia(@PathVariable Selecao selecao, @PathVariable PessoaFamilia membro,
													 Model model, RedirectAttributes redirect, Authentication auth) {
		if(selecao == null || !selecao.isInscricaoAberta()) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_LISTAR_SELECAO;
		}
		log.info("teste 1");
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		if (inscricao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_DETALHES_SELECAO + selecao.getId();
		}
		if(membro != null && inscricao.getQuestionario().getGrupoFamiliar().contains(membro)) {
			inscricao.getQuestionario().getGrupoFamiliar().remove(membro);
			inscricaoService.removerMembroFamilia(membro);
			model.addAttribute(INFO, MSG_SUCESSO_MEMBRO_FAMILIA_REMOVIDO);
			return REDIRECT_INSCRICAO_SITUACAO_SOCIO + selecao.getId();
		} else {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_REMOVER_MEMBRO);
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	/**
	 * Formulário de outras informações
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("outras-informacoes/{selecao}")
	public String inscreverOutrasInformacoesForm(@PathVariable Selecao selecao, Authentication auth,
													  Model model, RedirectAttributes redirect) {
		// Verifica se está no período de inscrição
		if(selecao == null || !selecao.isInscricaoAberta()) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_LISTAR_SELECAO;
		}

		// Verifica se já existe uma inscrição
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		if (inscricao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_REALIZAR_INSCRICAO);
			return REDIRECT_DETALHES_SELECAO + selecao.getId();
		}

		model.addAttribute("justificativa", inscricao.getQuestionario().getJustificativa());
		model.addAttribute("inscricao", inscricao); // joao
		return INSCRICAO_OUTRAS_INFORMACOES;
	}

	/**
	 * Outras informações
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@PostMapping("outras-informacoes/{selecao}")
	public String inscreverOutrasInformacoesForm(@PathVariable Selecao selecao, @RequestParam String justificativa,
												  Authentication auth, RedirectAttributes redirect, @RequestParam String action){	
		try {
			Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
			if (inscricao == null) {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
				return REDIRECT_LISTAR_SELECAO;
			}
			log.info(inscricao.getAluno().getPessoa().getPapeis()+" de CPF: "+
					inscricao.getAluno().getPessoa().getCpf()+"  Finalizou a Inscrição"
					+ " na seleção de id: "+ selecao.getId());
			inscricao.getQuestionario().setJustificativa(justificativa);
			List<Inscricao> inscricoes = inscricaoService.getAllOrdenado(selecao);
			Collections.sort(inscricoes);
			for(int i = 0; i < inscricoes.size(); i++){
				inscricoes.get(i).setPosicaoRanking(i+1);
			}
			inscricaoService.atualizar(inscricao);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_CADASTRO_INSCRICAO);
			if ("finalizar".equals(action))
				return REDIRECT_DETALHES_INSCRICAO + inscricao.getId();			
			else if ("voltar".equals(action))
				return REDIRECT_INSCRICAO_SITUACAO_SOCIO + selecao.getId();
			else
				return REDIRECT_LISTAR_SELECAO;
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	/**
	 * Visualizar detalhes da inscricao
	 */

	@GetMapping("detalhes/{inscricao}")
	public String visualizarInscricao(@PathVariable Inscricao inscricao, RedirectAttributes redirect,
			Model model, Authentication auth) {
		Pessoa pessoa = pessoaService.getByCpf(auth.getName());

		if(pessoa.isAluno()){
			log.info(inscricao.getAluno().getPessoa().getPapeis()+" de CPF: "+
				inscricao.getAluno().getPessoa().getCpf()+" Finalizou a Inscrição de numero "
				+ inscricao.getId() +" e está vendo seus detalhes.");
		}
		
		if(inscricao != null || pessoa != null) {
			Servidor servidor = servidorService.getByCpf(auth.getName());
			Aluno aluno = alunoService.buscarPorCpf(auth.getName());
			if((pessoa.isServidor() && inscricao.getSelecao().getComissao().contains(servidor))
					|| (pessoa.isAluno() && inscricao.getAluno().equals(aluno))) {
				model.addAttribute("inscricao", inscricao);
				return INSCRICAO_DETALHES;
			}
		}
		redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_VISUALIZAR_INSCRICAO);
		
		return REDIRECT_LISTAR_SELECAO;
	}
	
	public Integer isSelecionado(boolean selecionar){
		if(selecionar == true){
			return 1;
		} else{
			return 0;
		}
	}	
	@PostMapping(value = "/selecionar")
	@ResponseBody
	public Response selecionarInscricao(Integer idInscricao, boolean selecionar){

		if (inscricaoService.selecionarInscricao(idInscricao, isSelecionado(selecionar)) ){
			Response r = new Response();
			return r;
		}
		else
			return new Response().withFailStatus().withErrorMessage("Error ao selecionar esta inscricao");
	}
	
    @Secured(COORDENADOR)
	@PostMapping("/parecerFinal")
	@ResponseBody
	public Response editarParecerFinal(Integer inscricaoId,
			String resultado, String observacao) throws AuxilioMoradiaException{
    	if (resultado.length() > NUM_CARACTERES) return new Response().withErrorMessage("Número de caracteres excedidos");
		Inscricao insc = inscricaoService.buscarInscricaoPorId(inscricaoId);
		insc.setObservacao(observacao);
		insc.setResultado(Resultado.valueOf(resultado));				
		
		if(Resultado.valueOf(resultado) != Resultado.DEFERIDO &&
				insc.getSelecionado()==1){
				log.info(insc.getAluno().getPessoa().getPapeis()+" de CPF: "+
					insc.getAluno().getPessoa().getCpf()+" TESTE");
				insc.setSelecionado(0);
		}
		
		inscricaoService.editar(insc);
		return new Response().withDoneStatus();
	}
	
	@PreAuthorize(PERMISSAO_SERVIDOR)
	@GetMapping("/resultadoSelecao/{selecao}")
	public String resultadoSelecao(@PathVariable Selecao selecao, Model model){
		model.addAttribute("deferidos", inscricaoService.getSelecionados(selecao));
		model.addAttribute("indeferidos", inscricaoService.getIndeferidos(selecao));
		model.addAttribute("reserva", inscricaoService.getReserva(selecao));
		return PAGINA_RESULTADO;
	}
	
	@PostMapping(value = "/ordernar")
	@ResponseBody
	public Response atualizarRank(String inscricoes, String posicoes) throws AuxilioMoradiaException{
		Response r = new Response();
		Integer delay = 3000;
		String[] idInscricoes = inscricoes.split(",");
		String[] posicaoRanking = posicoes.split(",");
		boolean result = false;
		for(int i = 0; i < idInscricoes.length; i++){
			result = inscricaoService.atualizarRank(Integer.parseInt(idInscricoes[i]), Integer.parseInt(posicaoRanking[i]));
		}
		
		if (result){
			r.withDoneStatus().setAlert(new Alert(Type.INFO, "Posição atualizada com sucesso", delay));
		}
		else{
			r.withFailStatus().setAlert(new Alert(Type.ERROR,"Desculpe não foi possível atualizar a posição selecionada", delay));
		}
		return r;
	}

}