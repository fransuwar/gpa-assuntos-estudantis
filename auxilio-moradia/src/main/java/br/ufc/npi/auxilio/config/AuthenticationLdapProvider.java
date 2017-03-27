package br.ufc.npi.auxilio.config;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.transaction.Transactional;

import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Papel;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.repository.AlunoRepository;
import br.ufc.npi.auxilio.repository.ServidorRepository;
import br.ufc.quixada.npi.ldap.model.Affiliation;
import br.ufc.quixada.npi.ldap.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.repository.PessoaRepository;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Named
public class AuthenticationLdapProvider implements AuthenticationProvider {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ServidorRepository servidorRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String cpf = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        Pessoa pessoa = pessoaRepository.findByCpf(cpf);
        
        if(pessoa == null) {
        	// Solução para cadastrar automaticamente as pessoas no sistema a partir do LDAP
			Usuario usuario = usuarioService.getByCpf(cpf);
			if (usuario != null) {
				pessoa = new Pessoa();
				pessoa.setCpf(cpf);
				pessoaRepository.save(pessoa);

				List<Affiliation> authorities = usuarioService.getByCpf(cpf).getAuthorities();
				for (Affiliation affiliation : authorities) {
					if ("discente".equalsIgnoreCase(affiliation.getNome())) {
						pessoa.addPapel(Papel.ALUNO);
						pessoaRepository.save(pessoa);
						Aluno aluno = new Aluno();
						aluno.setPessoa(pessoa);
						alunoRepository.save(aluno);
					}
					if("sta".equalsIgnoreCase(affiliation.getNome()) || "docente".equalsIgnoreCase(affiliation.getNome())) {
						pessoa.addPapel(Papel.SERVIDOR);
						pessoaRepository.save(pessoa);
						Servidor servidor = new Servidor();
						servidor.setPessoa(pessoa);
						servidorRepository.save(servidor);
					}
				}

			} else {
				throw new BadCredentialsException("Usuário e/ou senha inválidos");
			}
        }
        
        if (!usuarioService.autentica(cpf, password) || pessoa.getAuthorities() == null || pessoa.getAuthorities().isEmpty()) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }
        
        return new UsernamePasswordAuthenticationToken(cpf, password, pessoa.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
