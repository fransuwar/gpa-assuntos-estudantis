package br.ufc.quixada.npi.gpa.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import br.ufc.quixada.npi.ldap.model.Constants;
import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Named
public class LdapAuthentication implements AuthenticationProvider{

	@Inject
	private UsuarioService 	usuarioService;
	
	@Inject
	private PessoaService 	pessoaService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = (String) auth.getCredentials();
		
		Usuario usuario = usuarioService.getByCpf(username);
		
		Collection<? extends GrantedAuthority> authorities = pessoaService.getPapeis(username);
		
		if (usuario == null || !usuarioService.autentica(username, password) || authorities.isEmpty()) {
			throw new BadCredentialsException(Constants.LOGIN_INVALIDO);
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
