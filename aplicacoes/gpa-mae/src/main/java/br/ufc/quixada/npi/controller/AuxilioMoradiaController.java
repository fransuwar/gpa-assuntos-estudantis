package br.ufc.quixada.npi.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.service.AuxilioMoradiaService;


@Controller
@RequestMapping("auxilioMoradia")
public class AuxilioMoradiaController {
	
	@Inject
	private AuxilioMoradiaService auxilioMoradiaService;
	
	
	
	@RequestMapping(value="/auxilio", method = RequestMethod.GET)
	 public String cadastro(Model modelo){
		modelo.addAttribute("QuestionarioAuxilioMoradia", new QuestionarioAuxilioMoradia());
		return "inscricao/auxilio";
	}
	
	@RequestMapping(value="/auxilio", method = RequestMethod.POST)
     public String adicionaAuxilio()
}	 
	
	

