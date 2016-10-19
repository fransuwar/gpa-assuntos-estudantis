package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.ADMINISTRADOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.DISCENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.DOCENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.STA;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;
@Named
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy;

	@Inject
	private UsuarioService serviceUsuario;
	
	public AuthenticationSuccessHandlerImpl() {
		redirectStrategy = new DefaultRedirectStrategy();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		handle(request, response, authentication);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		redirectStrategy.sendRedirect(request, response, determineUrl(authentication));
	}

	private String determineUrl(Authentication authentication) {
		Usuario usuario = serviceUsuario.getByCpf(authentication.getName());
		
		for (GrantedAuthority grantedAuthority : usuario.getAuthorities()) {
			
			if (grantedAuthority.getAuthority().equalsIgnoreCase(STA) || grantedAuthority.getAuthority().equalsIgnoreCase(DOCENTE)){

				return "/servidor/selecao/listar";
			}
			else if (grantedAuthority.getAuthority().equalsIgnoreCase(DISCENTE)){
				
				return "/aluno/selecao/listar";
				
			}else if (grantedAuthority.getAuthority().equalsIgnoreCase(ADMINISTRADOR)){
				
				return "/administrador/listar";
			}
		}
		
		return "/login";
	}
	
}