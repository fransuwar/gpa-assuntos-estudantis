package br.ufc.npi.auxilio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.repository.PessoaRepository;

@Component
public class AuthenticationDatabaseProvider implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByCpf(username);
        if(pessoa == null) {
            throw new UsernameNotFoundException("Usuário e/ou senha inválidos");
        }
        return pessoa;
    }
}
