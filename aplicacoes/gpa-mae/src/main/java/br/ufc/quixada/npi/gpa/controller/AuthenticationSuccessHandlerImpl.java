package br.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.gpa.utils.Constants;

public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy;

	@Inject
	private PessoaService servicePessoa;

	public AuthenticationSuccessHandlerImpl() {
		redirectStrategy = new DefaultRedirectStrategy();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		handle(request, response, authentication);
	}

	private void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		usuarioLogado(request, authentication);
		redirectStrategy.sendRedirect(request, response, determineUrl(authentication));
	}

	private String determineUrl(Authentication authentication) {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			switch (grantedAuthority.getAuthority()) {
			case "DISCENTE":
				return "/servidor/listar";

			case "ROLE_COORDENADOR":
				return "/selecao/listar";

			case "DOCENTE":
				return "/selecao/listar";

			default:
				return "/login";
			}
		}
		return "/login";
	}
	
	private void usuarioLogado(HttpServletRequest request, Authentication authentication) {
		if (request.getSession().getAttribute(Constants.USUARIO_LOGADO) == null) {
			Pessoa pessoa = servicePessoa.getPessoaByLogin(authentication.getName());
			String nome = pessoa.getNome();
			Integer id = pessoa.getId();
			request.getSession().setAttribute(Constants.USUARIO_ID, id);
			request.getSession().setAttribute(Constants.USUARIO_LOGADO, nome);
		}
	}
	
}