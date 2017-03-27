package br.ufc.npi.auxilio.model;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

import java.time.LocalDate;

public class PessoaEntityListener {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostLoad
	public void loadPessoa(Pessoa pessoa) {
		AutowireHelper.autowire(this, this.usuarioService);
		Usuario usuario = usuarioService.getByCpf(pessoa.getCpf());
		pessoa.setNome(usuario.getNome());
		pessoa.setEmail(usuario.getEmail());
		pessoa.setDataNascimento(new java.sql.Date(usuario.getNascimento().getTime()).toLocalDate());
		pessoa.setTelefone(usuario.getTelefone());
		
	}

}
