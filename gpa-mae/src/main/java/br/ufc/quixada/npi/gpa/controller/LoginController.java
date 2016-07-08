package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.ADMINISTRADOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.DISCENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.DOCENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.LOGIN;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_LOGIN;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_PERMISSAO_NEGADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_PERMISSAO_NEGADA_USUARIO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_LOGIN;
import static br.ufc.quixada.npi.gpa.utils.Constants.MESSAGE;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.STA;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Controller
public class LoginController {
	@Inject
	private UsuarioService serviceUsuario;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio(Authentication authentication){

		if (authentication != null){
			Usuario usuario = serviceUsuario.getByCpf(authentication.getName());
			
			for (GrantedAuthority grantedAuthority : usuario.getAuthorities()) {

				if (grantedAuthority.getAuthority().equalsIgnoreCase(STA) || grantedAuthority.getAuthority().equalsIgnoreCase(DOCENTE)){

					return REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;
				}
				else if (grantedAuthority.getAuthority().equalsIgnoreCase(DISCENTE)){

					return REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO;

				}else if (grantedAuthority.getAuthority().equalsIgnoreCase(ADMINISTRADOR)){

					return REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;
				}
			}

		}

		return REDIRECT_PAGINA_LISTAR_SELECAO;
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
		model.setViewName(LOGIN);

		return model;

	}



	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginError(ModelMap model) {

		model.addAttribute("erro", MENSAGEM_ERRO_LOGIN);
		return LOGIN;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		session.invalidate();

		return LOGIN;

	}



	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String acessoNegado(ModelMap model, Principal user) {
		if (user != null) {
			model.addAttribute(MESSAGE, "Olá, " + user.getName() 
			+ MENSAGEM_PERMISSAO_NEGADA_USUARIO);
		} else {
			model.addAttribute(MESSAGE, 
					MENSAGEM_PERMISSAO_NEGADA);
		}
		return "403";
	}
}