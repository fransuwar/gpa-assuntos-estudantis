package br.com.ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.model.Servidor;
import br.com.ufc.quixada.npi.gpa.service.ServidorService;


@Controller
@RequestMapping ("servidor")
public class ServidorController {

	@Inject
	private ServidorService servidorService;
	
	@RequestMapping(value = "/cadastrarServidor", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("servidor", new Servidor());
		return "/servidor/cadastrarServidor";
	}

	@RequestMapping(value = "/cadastrarServidor", method = RequestMethod.POST)
	public String adcionaServidor(
			@Valid @ModelAttribute("servidor") Servidor servidor,
			BindingResult result,RedirectAttributes redirect) {
		
		if (result.hasErrors()) {
			return ("servidor/cadastrarServidor");
		}
		
		this.servidorService.save(servidor);
		this.servidorService.update(servidor);
		redirect.addFlashAttribute("info", "Servidor cadastrado com sucesso.");

		return "redirect:/servidor/cadastrarServidor";

	}

	
}
