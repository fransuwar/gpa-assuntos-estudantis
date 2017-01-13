package br.ufc.npi.auxilio.controller;


import br.ufc.npi.auxilio.enums.Estado;
import br.ufc.npi.auxilio.enums.EstadoCivil;
import br.ufc.npi.auxilio.enums.GrauParentesco;
import br.ufc.npi.auxilio.enums.TipoEnsino;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.model.questionario.DadosBancarios;
import br.ufc.npi.auxilio.model.questionario.Identificacao;
import br.ufc.npi.auxilio.model.questionario.Moradia;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.QuestionarioAuxilioMoradiaService;
import br.ufc.npi.auxilio.utils.api.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_ALUNO;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_INSCRICAO_EXISTENTE;
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
	
	@PreAuthorize(PERMISSAO_ALUNO)
	@GetMapping("{selecao}")
	public String inscreverDadosBasicosForm(@PathVariable Selecao selecao, Model model, Authentication auth, RedirectAttributes redirect){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		model.addAttribute("aluno", aluno);
		model.addAttribute("selecao", selecao);
		model.addAttribute("estados", Estado.values());
		model.addAttribute("estadosCivis", EstadoCivil.values());
		Inscricao inscricao = inscricaoService.get(aluno, selecao);

		// Se o aluno já estiver inscrito e ainda não finalizou a inscrição, retorna página de identificação para atualização dos dados
		if (inscricao != null) {
			if (!inscricao.isConsolidada()) {
				model.addAttribute("identificacao", inscricao.getAluno().getIdentificacao());
			} else {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_EXISTENTE);
				return REDIRECT_DETALHES_SELECAO + selecao.getId();
			}
		} else {
			// Se o aluno não estiver inscrito, inicia a inscrição
			model.addAttribute("identificacao", new Identificacao());
		}

		return INSCRICAO_DADOS_BASICOS;
	}

	@PreAuthorize(PERMISSAO_ALUNO)
	@PostMapping("{selecao}")
	public String inscreverDadosBasicos(@PathVariable Selecao selecao, Identificacao identificacao,
		   Model model, Authentication auth, RedirectAttributes redirect){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		try {
			Inscricao inscricao = inscricaoService.get(aluno, selecao);
			// Verifica se o aluno já está inscrito
			if (inscricao == null) {
				inscricaoService.salvar(selecao, aluno, identificacao);
			} else {
				if (!inscricao.isConsolidada()) {
					aluno.setIdentificacao(identificacao);
					alunoService.salvar(aluno);
				} else {
					redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_EXISTENTE);
					return REDIRECT_DETALHES_SELECAO + selecao.getId();
				}
			}
			model.addAttribute("inscricao", inscricaoService.get(aluno, selecao));
			model.addAttribute("dadosBancarios", inscricao.getDadosBancarios());
			return INSCRICAO_DADOS_BANCARIOS;
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
	}

	@PostMapping("dados-bancarios/{selecao}")
	public String inscreverDadosBancarios(@PathVariable Selecao selecao, DadosBancarios dadosBancarios,
			Model model, Authentication auth, RedirectAttributes redirect){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		Inscricao inscricao = inscricaoService.get(aluno, selecao);
		// Verifica se o aluno possui inscrição na seleção
		if(inscricao == null) {
			return REDIRECT_LISTAR_SELECAO;
		}
		// Inclui os dados bancários e atualiza a inscrição
		inscricao.setDadosBancarios(dadosBancarios);
		inscricaoService.salvar(inscricao);
		model.addAttribute("moradia", inscricao.getMoradia());
		return INSCRICAO_MORADIA;
	}

	@PostMapping("moradia/{selecao}")
	public String inscreverMoradia(@PathVariable Selecao selecao, Moradia moradia,
			Model model, Authentication auth){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		Inscricao inscricao = inscricaoService.get(aluno, selecao);
		// Verifica se o aluno possui inscrição na seleção
		if(inscricao == null) {
			return REDIRECT_LISTAR_SELECAO;
		}
		// Inclui os dados da moradia e atualiza a inscrição
		inscricao.setMoradia(moradia);
		inscricaoService.salvar(inscricao);
		return INSCRICAO_HISTORICO;
	}
	
	@PostMapping("{idInscricao}/questionario")
	public ModelAndView inscreverQuestionario(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao, 
			QuestionarioAuxilioMoradia questionarioAuxilioMoradia){
		inscricao.setQuestionario(questionarioAuxilioMoradia);
		inscricaoService.salvar(inscricao);
		mav.setViewName(String.format("redirect:/inscricao/%1d/historico", inscricao.getId()));
		return mav;
	}
	
	@GetMapping("{idInscricao}/historico")
	public ModelAndView inscreverHistorico(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao){
		mav.addObject("inscricao", inscricao);
		mav.addObject("questionarioAuxilioMoradia", inscricao.getQuestionario());
		mav.addObject("tipoEnsino", TipoEnsino.values());
		mav.setViewName("inscricao/historico");
		return mav;		
	}
	
	@PostMapping("{idInscricao}/historico")
	public ModelAndView inscreverHistorico(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao, 
			QuestionarioAuxilioMoradia questionarioAuxilioMoradia){
		inscricao.getQuestionario().merge(questionarioAuxilioMoradia);
		inscricaoService.salvar(inscricao);
		mav.setViewName(String.format("redirect:/inscricao/%1d/situacao-socio-economica", inscricao.getId()));
		return mav;		
	}
	
	@GetMapping("{idInscricao}/situacao-socio-economica")
	public ModelAndView inscreverSituacaoSocioEconomica(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao){
		mav.addObject("inscricao", inscricao);
		mav.addObject("questionarioAuxilioMoradia", inscricao.getQuestionario());
		mav.setViewName("inscricao/situacao-socio-economica");
		return mav;		
	}
	
	@PostMapping("{idInscricao}/situacao-socio-economica")
	public ModelAndView inscreverSituacaoSocioEconomica(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao, 
			QuestionarioAuxilioMoradia questionarioAuxilioMoradia){
		inscricao.getQuestionario().merge(questionarioAuxilioMoradia);
		inscricaoService.salvar(inscricao);
		mav.setViewName(String.format("redirect:/documentacao/%1d", inscricao.getId()));
		return mav;		
	}
	

	
	@PostMapping("api/{idInscricao}/propriedade-rural")
	public Response inscreverPropriedade(@PathVariable("idInscricao") Inscricao inscricao, PropriedadeRural propriedadeRural){
		QuestionarioAuxilioMoradia questionario = inscricao.getQuestionario();
		List<PropriedadeRural> propriedades = questionario.getPropriedadeRural(); 
		if (propriedades != null){
			propriedades.add(propriedadeRural);
		}else {
			propriedades = new ArrayList<>();
			propriedades.add(propriedadeRural);
		}
		questionario.setPropriedadeRural(propriedades);
		questionarioAuxilioMoradiaService.salvar(questionario);
		return new Response().withObject(propriedadeRural).withSuccessMessage("Propriedade Adicionada!");
	}
	
	@PostMapping("api/{idInscricao}/bem-movel")
	public Response inscreverBemMovel(@PathVariable("idInscricao") Inscricao inscricao, BemMovel bemMovel){
		QuestionarioAuxilioMoradia questionario = inscricao.getQuestionario();
		List<BemMovel> bensMoveis = questionario.getBemMovel(); 
		if (bensMoveis != null){
			bensMoveis.add(bemMovel);
		}else {
			bensMoveis = new ArrayList<>();
			bensMoveis.add(bemMovel);
		}
		
		questionario.setBemMovel(bensMoveis);
		questionarioAuxilioMoradiaService.salvar(questionario);
		return new Response();
	}
}

























