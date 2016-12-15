package br.ufc.npi.auxilio.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.auxilio.enums.GrauParentesco;
import br.ufc.npi.auxilio.enums.TipoEnsino;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.BemMovel;
import br.ufc.npi.auxilio.model.DadosBancarios;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.PropriedadeRural;
import br.ufc.npi.auxilio.model.QuestionarioAuxilioMoradia;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.QuestionarioAuxilioMoradiaService;
import br.ufc.npi.auxilio.utils.api.Response;

@RestController
@RequestMapping("inscricao")
public class InscricaoController {

	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private AlunoService alunoService;

	@Autowired
	private QuestionarioAuxilioMoradiaService questionarioAuxilioMoradiaService;
	
	@GetMapping("{idSelecao}")
	public ModelAndView inscrever(ModelAndView mav, Authentication auth, @PathVariable("idSelecao") Selecao selecao){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		mav.addObject("aluno", aluno);
		mav.addObject("selecao", selecao);
		mav.setViewName("inscricao/dados-basicos");
		return mav;
	}
	
	@PostMapping("{idSelecao}")
	public ModelAndView inscreverDadosBasico(ModelAndView mav,  @PathVariable("idSelecao") Selecao selecao, Authentication auth){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		Inscricao inscricao = inscricaoService.salvar(selecao, aluno);
		mav.setViewName(String.format("redirect:/inscricao/%1d/dados-bancarios", inscricao.getId()));
		return mav;
	}
	
	@GetMapping("{idInscricao}/dados-bancarios")
	public ModelAndView inscreverDadosBancarios(@PathVariable("idInscricao") Inscricao inscricao,  Authentication auth, ModelAndView mav){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		aluno.setDadosBancarios(new DadosBancarios());
		mav.addObject("inscricao", inscricao);
		mav.addObject("aluno", aluno);
		mav.setViewName("inscricao/dados-bancarios");
		return mav;
	}
	
	@PostMapping("{idInscricao}/dados-bancarios")
	public ModelAndView inscreverDadosBancarios(@PathVariable("idInscricao") Inscricao inscricao, 
			Aluno aluno, ModelAndView mav, Authentication auth){
		Aluno alunoOld = alunoService.buscarPorCpf(auth.getName());
		alunoOld.setIra(aluno.getIra());
		alunoOld.setDadosBancarios(aluno.getDadosBancarios());
		alunoService.salvar(alunoOld);
		mav.setViewName(String.format("redirect:/inscricao/%1d/questionario", inscricao.getId()));
		return mav;
	}
	
	
	@GetMapping("{idInscricao}/questionario")
	public ModelAndView inscreverQuestionario(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao){
		mav.addObject("questionarioAuxilioMoradia", inscricao.getQuestionarioAuxilioMoradia());
		mav.addObject("inscricao", inscricao);
		mav.addObject("moraCom", GrauParentesco.getTodosExcetoEu());
		mav.addObject("propriedadeRural", new PropriedadeRural());
		mav.setViewName("inscricao/moradia");
		return mav;
	}
	
	@PostMapping("{idInscricao}/questionario")
	public ModelAndView inscreverQuestionario(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao, 
			QuestionarioAuxilioMoradia questionarioAuxilioMoradia){
		inscricao.setQuestionarioAuxilioMoradia(questionarioAuxilioMoradia);
		inscricaoService.salvar(inscricao);
		mav.setViewName(String.format("redirect:/inscricao/%1d/historico", inscricao.getId()));
		return mav;
	}
	
	@GetMapping("{idInscricao}/historico")
	public ModelAndView inscreverHistorico(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao){
		mav.addObject("inscricao", inscricao);
		mav.addObject("questionarioAuxilioMoradia", inscricao.getQuestionarioAuxilioMoradia());
		mav.addObject("tipoEnsino", TipoEnsino.values());
		mav.setViewName("inscricao/historico");
		return mav;		
	}
	
	@PostMapping("{idInscricao}/historico")
	public ModelAndView inscreverHistorico(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao, 
			QuestionarioAuxilioMoradia questionarioAuxilioMoradia){
		inscricao.getQuestionarioAuxilioMoradia().merge(questionarioAuxilioMoradia);
		inscricaoService.salvar(inscricao);
		mav.setViewName(String.format("redirect:/inscricao/%1d/situacao-socio-economica", inscricao.getId()));
		return mav;		
	}
	
	@GetMapping("{idInscricao}/situacao-socio-economica")
	public ModelAndView inscreverSituacaoSocioEconomica(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao){
		mav.addObject("inscricao", inscricao);
		mav.addObject("questionarioAuxilioMoradia", inscricao.getQuestionarioAuxilioMoradia());
		mav.setViewName("inscricao/situacao-socio-economica");
		return mav;		
	}
	
	@PostMapping("{idInscricao}/situacao-socio-economica")
	public ModelAndView inscreverSituacaoSocioEconomica(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao, 
			QuestionarioAuxilioMoradia questionarioAuxilioMoradia){
		inscricao.getQuestionarioAuxilioMoradia().merge(questionarioAuxilioMoradia);
		inscricaoService.salvar(inscricao);
		mav.setViewName(String.format("redirect:/documentacao/%1d", inscricao.getId()));
		return mav;		
	}
	

	
	@PostMapping("api/{idInscricao}/propriedade-rural")
	public Response inscreverPropriedade(@PathVariable("idInscricao") Inscricao inscricao, PropriedadeRural propriedadeRural){
		QuestionarioAuxilioMoradia questionario = inscricao.getQuestionarioAuxilioMoradia();
		List<PropriedadeRural> propriedades = questionario.getPropriedadeRural(); 
		if (propriedades != null){
			propriedades.add(propriedadeRural);
		}else {
			propriedades = new ArrayList<>();
			propriedades.add(propriedadeRural);
		}
		questionario.setPropriedadeRural(propriedades);
		questionarioAuxilioMoradiaService.salvar(questionario);
		return new Response().withObject(propriedadeRural);
	}
	
	@PostMapping("api/{idInscricao}/bem-movel")
	public Response inscreverBemMovel(@PathVariable("idInscricao") Inscricao inscricao, BemMovel bemMovel){
		QuestionarioAuxilioMoradia questionario = inscricao.getQuestionarioAuxilioMoradia();
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

























