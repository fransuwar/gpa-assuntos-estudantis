package br.ufc.npi.auxilio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.service.PessoaService;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {

	@Autowired
	PessoaService pessoaService;
	
	@GetMapping("{idSelecao}")
	public ModelAndView inscrever(ModelAndView mav, Authentication auth, @PathVariable("idSelecao") Selecao selecao){
		mav.addObject("aluno", pessoaService.getByCpf(auth.getName()));
		mav.addObject("selecao", selecao);
		mav.setViewName("inscricao/dados-basicos");
		return mav;
	}
	
//	@PostMapping("{idSelecao}")
//	public ModelAndView
	
	

}
