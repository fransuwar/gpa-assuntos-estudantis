package br.ufc.npi.auxilio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.PessoaService;
import br.ufc.npi.auxilio.utils.Constants;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping("{idSelecao}")
	public ModelAndView inscrever(ModelAndView mav, Authentication auth, @PathVariable("idSelecao") Selecao selecao){
		mav.addObject("aluno", pessoaService.getByCpf(auth.getName()));
		mav.addObject("selecao", selecao);
		mav.setViewName("inscricao/dados-basicos");
		return mav;
	}
	
	@PostMapping("{idSelecao}")
	public ModelAndView inscrever(ModelAndView mav,  @PathVariable("idSelecao") Selecao selecao, Authentication auth){
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		try{
			inscricaoService.salvar(selecao, aluno);
			mav.setViewName("inscricao/dados-bancarios");
		}catch (AuxilioMoradiaException e) {
			mav.addObject(Constants.ERRO, e.getMessage());
			mav.setViewName("/");
		}
		return mav;
	}
	
	

}
