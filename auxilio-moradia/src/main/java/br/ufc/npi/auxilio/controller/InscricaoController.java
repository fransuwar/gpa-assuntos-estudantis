package br.ufc.npi.auxilio.controller;


import br.ufc.npi.auxilio.enums.*;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.model.questionario.*;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.QuestionarioAuxilioMoradiaService;
import br.ufc.npi.auxilio.utils.api.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_ALUNO;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_INSCRICAO_EXISTENTE;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO;
import static br.ufc.npi.auxilio.utils.PageConstants.*;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_DETALHES_SELECAO;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_LISTAR_SELECAO;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {

	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private AlunoService alunoService;

	@Autowired
	private QuestionarioAuxilioMoradiaService questionarioAuxilioMoradiaService;

	/**
	 * Formulário de dados básicos
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("{selecao}")
	public String inscreverDadosBasicosForm(@PathVariable Selecao selecao, Model model, Authentication auth, RedirectAttributes redirect){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		model.addAttribute("aluno", aluno);
		model.addAttribute("selecao", selecao);
		model.addAttribute("estados", Estado.values());
		model.addAttribute("estadosCivis", EstadoCivil.values());
		Inscricao inscricao = inscricaoService.get(aluno, selecao);

		// Se o aluno não estiver inscrito, inicia a inscrição
		if (inscricao == null) {
			model.addAttribute("identificacao", new Identificacao());
		} else if (inscricao.getSelecao().isInscricaoAberta()) {
			// Se o aluno ainda não finalizou a inscrição, retorna página de identificação para atualização dos dados
			if (!inscricao.isConsolidada()) {
				model.addAttribute("identificacao", inscricao.getIdentificacao());
			} else {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_EXISTENTE);
				return REDIRECT_DETALHES_SELECAO + selecao.getId();
			}
		} else {
			// O período de inscrição já encerrou e não é possível atualizar a inscrição
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return REDIRECT_DETALHES_SELECAO + selecao.getId();
		}

		return INSCRICAO_DADOS_BASICOS;
	}

	/**
	 * Dados básicos no aluno
	 */
	@PreAuthorize(PERMISSAO_ALUNO)
	@PostMapping("{selecao}")
	public String inscreverDadosBasicos(@PathVariable Selecao selecao, Identificacao identificacao,
		   Model model, Authentication auth, RedirectAttributes redirect){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		try {
			Inscricao inscricao = inscricaoService.get(aluno, selecao);
			// Cria uma nova inscrição se o aluno ainda não estiver inscrito
			if (inscricao == null) {
				inscricao = inscricaoService.salvar(selecao, aluno, identificacao);
			} else if (inscricao.getSelecao().isInscricaoAberta()) {
				// Atualiza a inscrição existente
				inscricao.setIdentificacao(identificacao);
				inscricaoService.atualizar(inscricao);
			} else {
				// O período de inscrição já encerrou e não é possível atualizar a inscrição
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
				return REDIRECT_DETALHES_SELECAO + selecao.getId();
			}
			// Redireciona para a página de moradia
			model.addAttribute("moradia", inscricao.getMoradia());
			model.addAttribute("opcoesMoradoresOrigem", MoradoresOrigem.values());
			model.addAttribute("opcoesMoradores", Moradores.values());
			model.addAttribute("estados", Estado.values());
			model.addAttribute("imoveis", SituacaoImovel.values());
			return INSCRICAO_MORADIA;
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	/**
	 * Moradia de origem e moradia atual
	 */
	@PostMapping("moradia/{selecao}")
	public String inscreverMoradia(@PathVariable Selecao selecao, Moradia moradia,
			Model model, Authentication auth, RedirectAttributes redirect){
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		inscricao.setMoradia(moradia);
		try {
			inscricaoService.atualizar(inscricao);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
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
	public String inscreverQuestionario(@PathVariable Selecao selecao, HistoricoEscolar historico,
		  	Model model, Authentication auth, RedirectAttributes redirect){
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		inscricao.setHistoricoEscolar(historico);
		try {
			inscricaoService.atualizar(inscricao);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}

		model.addAttribute("situacao", inscricao.getSituacaoSocioeconomica());
		model.addAttribute("opcoesParentesco", GrauParentesco.values());
		model.addAttribute("opcoesEscolaridade", Escolaridade.values());
		model.addAttribute("inscricao", inscricao);
		return INSCRICAO_SITUACAO_SOCIOECONOMICA;
	}

	/**
	 * Situação Socioeconômica
	 */
	@PostMapping("situacao-socioeconomica/{selecao}")
	public String inscreverSituacaoSocioeconomica(@PathVariable Selecao selecao, SituacaoSocioeconomica situacao,
			Model model, Authentication auth, RedirectAttributes redirect){
		Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
		inscricao.setSituacaoSocioEconomica(situacao);
		try {
			inscricaoService.atualizar(inscricao);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}

		return INSCRICAO_OUTRAS_INFORMACOES;
	}

	@PostMapping("situacao-socioeconomica/{selecao}/adicionar")
	public @ResponseBody PessoaFamilia adicionarMembroFamilia(@PathVariable Selecao selecao,
		  	@RequestBody PessoaFamilia pessoa, Authentication auth) {
		if(selecao != null && selecao.isInscricaoAberta()) {
			Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
			if (inscricao != null) {
				pessoa.setQuestionario(inscricao.getQuestionario());
				pessoa = inscricaoService.adicionarMembroFamilia(pessoa);
			}
		}
		return pessoa;
	}

	@PostMapping("situacao-socioeconomica/{selecao}/remover/{membro}")
	public @ResponseBody Status removerMembroFamilia(@PathVariable Selecao selecao, @PathVariable PessoaFamilia membro,
			  Authentication auth) {
		if(selecao != null && membro != null && selecao.isInscricaoAberta()) {
			Inscricao inscricao = inscricaoService.get(alunoService.buscarPorCpf(auth.getName()), selecao);
			if (inscricao != null && inscricao.getQuestionario().getGrupoFamiliar().contains(membro)) {
				inscricao.getQuestionario().getGrupoFamiliar().remove(membro);
				inscricaoService.removerMembroFamilia(membro);
				return Status.OK;
			}
		}
		return Status.ERROR;
	}
}

























