package br.ufc.quixada.npi.gpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.quixada.npi.gpa.utils.RedirectConstants;

@Controller
public class AssuntosEstudantisController {
	
	@GetMapping({"", "/"})
	public ModelAndView getIndex() {
		return new ModelAndView(RedirectConstants.REDIRECT_LISTAR_SELECAO);
	}

}
