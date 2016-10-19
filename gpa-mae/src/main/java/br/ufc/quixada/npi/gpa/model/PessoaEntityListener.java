package br.ufc.quixada.npi.gpa.model;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

public class PessoaEntityListener {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostLoad
	public void loadPessoa(Pessoa pessoa) {
		AutowireHelper.autowire(this, this.usuarioService);
		Usuario usuario = usuarioService.getByCpf(pessoa.getCpf());
		pessoa.setNome(usuario.getNome());
		pessoa.setEmail(usuario.getEmail());
		pessoa.setDataNascimento(usuario.getNascimento());
		pessoa.setTelefone(usuario.getTelefone());
		
	}

}
