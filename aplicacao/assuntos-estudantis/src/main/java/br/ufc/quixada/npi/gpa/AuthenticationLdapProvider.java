package br.ufc.quixada.npi.gpa;

import java.util.Collection;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.repository.PessoaRepository;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Named
public class AuthenticationLdapProvider implements AuthenticationProvider {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String cpf = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        Pessoa pessoa = pessoaRepository.findByCpf(cpf);
        
        if(pessoa == null) {
        	throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }
        
        Collection<? extends GrantedAuthority> authorities = usuarioService.getByCpf(cpf).getAuthorities();
        
        if (!usuarioService.autentica(cpf, password) || authorities == null || authorities.isEmpty()) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }
        
        return new UsernamePasswordAuthenticationToken(pessoa, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
