package br.ufc.quixada.npi.gpa.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.ufc.quixada.npi.ldap.model.Constants;
import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Named
public class LdapAuthentication implements AuthenticationProvider{

	@Inject
	private UsuarioService 	usuarioService;
	@Inject
	private PessoaService servicePessoa;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = (String) auth.getCredentials();
		
		Usuario usuario = usuarioService.getByCpf(username);
		
		
		if (usuario == null || !usuarioService.autentica(username, password) || usuario.getAuthorities().isEmpty() || this.servicePessoa.getPessoaPorCpf(username) == null) {
			throw new BadCredentialsException(Constants.LOGIN_INVALIDO);
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, usuario.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
