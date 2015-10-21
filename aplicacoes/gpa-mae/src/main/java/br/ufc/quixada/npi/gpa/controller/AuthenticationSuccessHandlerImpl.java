package br.ufc.quixada.npi.gpa.controller;

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

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.gpa.utils.Constants;
import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Named
public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy;

	@Inject
	private UsuarioService serviceUsuario;
	
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

	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		usuarioLogado(request, authentication);
		redirectStrategy.sendRedirect(request, response, determineUrl(authentication));
	}

	private String determineUrl(Authentication authentication) {
		Usuario usuario = serviceUsuario.getByCpf(authentication.getName());
		
		for (GrantedAuthority grantedAuthority : usuario.getAuthorities()) {
			switch (grantedAuthority.getAuthority()) {
				case "ROLE_ADMIN":
					return "/servidor/listar";
	
				case "COORD_ASS_ESTUDANTIS":
					return "/selecao/listar";
	
				case "ROLE_ALUNO":
					return "/selecao/listar";
			}
		}
		return "/login";
	}
	
	private void usuarioLogado(HttpServletRequest request, Authentication authentication) {
		if (request.getSession().getAttribute(Constants.USUARIO_LOGADO) == null) {
			Pessoa pessoa = servicePessoa.getPessoaByCpf(authentication.getName());
			String nome = pessoa.getNome();
			Integer id = pessoa.getId();
			request.getSession().setAttribute(Constants.USUARIO_ID, id);
			request.getSession().setAttribute(Constants.USUARIO_LOGADO, nome);
		}
	}
	
}