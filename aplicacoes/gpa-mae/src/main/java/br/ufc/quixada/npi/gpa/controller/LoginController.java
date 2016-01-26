package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio(){
		return "redirect:/servidor/selecao/listar";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", MENSAGEM_ERRO_LOGIN );
		}

		if (logout != null) {
			model.addObject("msg", MENSAGEM_SUCESSO_LOGIN);
		}
		model.setViewName("login");

		return model;

	}
	
	

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("erro", MENSAGEM_ERRO_LOGIN);
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		session.invalidate();

		return "login";

	}
	
	
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String acessoNegado(ModelMap model, Principal user) {
		if (user != null) {
			model.addAttribute("message", "Olá, " + user.getName() 
			+ MENSAGEM_PERMISSAO_NEGADA_USUARIO);
		} else {
			model.addAttribute("message", 
			MENSAGEM_PERMISSAO_NEGADA);
		}
		return "403";
	}
}