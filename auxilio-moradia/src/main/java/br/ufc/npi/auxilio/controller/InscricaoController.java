package br.ufc.npi.auxilio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.DadosBancarios;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.QuestionarioAuxilioMoradia;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.DadosBancariosService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.QuestionarioAuxilioMoradiaService;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {

	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired	
	private DadosBancariosService dadosBancariosService;
	
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
		mav.addObject("inscricao", inscricao);
		mav.addObject("dadosBancarios", new DadosBancarios());
		mav.setViewName("inscricao/dados-bancarios");
		return mav;
	}
	
	@PostMapping("{idInscricao}/dados-bancarios")
	public ModelAndView inscreverDadosBancarios(@PathVariable("idInscricao") Inscricao inscricao, DadosBancarios dadosBancarios, 
			Authentication auth, ModelAndView mav){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
//		dadosBancarios = dadosBancariosService.salvar(dadosBancarios);
		aluno.setDadosBancarios(dadosBancarios);
		alunoService.salvar(aluno);
		mav.setViewName(String.format("redirect:/inscricao/%1d/questionario", inscricao.getId()));
		return mav;
	}
	
	
	@GetMapping("{idInscricao}/questionario")
	public ModelAndView inscreverQuestionario(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao){
		mav.addObject("inscricao", inscricao);
		mav.setViewName("inscricao/questionario");
		return mav;
	}
	
	@PostMapping("{idInscricao}/questionario")
	public ModelAndView inscreverQuestionario(ModelAndView mav, @PathVariable("idInscricao") Inscricao inscricao, 
			QuestionarioAuxilioMoradia questionarioAuxilioMoradia){
		mav.addObject("inscricao", inscricao);
//		questionarioAuxilioMoradia = questionarioAuxilioMoradiaService.salvar(questionarioAuxilioMoradia);
		inscricao.setQuestionarioAuxilioMoradia(questionarioAuxilioMoradia);
		inscricaoService.salvar(inscricao);
		mav.setViewName(String.format("redirect:/inscricao/%1d/historico", inscricao.getId()));
		return mav;
	}

}
