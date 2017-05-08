package br.ufc.npi.auxilio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("visita")
public class VisitaController {
	
	@GetMapping("/form")
	public String formVisitaTeste(){
		
		return "selecao/visita_domiciliar";
	}
}
