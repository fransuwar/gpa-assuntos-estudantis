package br.ufc.npi.auxilio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.service.PessoaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuxilioMoradiaController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String index(Authentication auth) {	    
		return "redirect:/selecao";
	}

}
