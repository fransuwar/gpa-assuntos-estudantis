package br.ufc.npi.auxilio.model;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

public class AlunoEntityListener {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostLoad
	public void loadAluno(Aluno aluno) {
		AutowireHelper.autowire(this, this.usuarioService);
		Usuario usuario = usuarioService.getByCpf(aluno.getPessoa().getCpf());
		aluno.setMatricula(usuario.getMatricula());
		aluno.setCurso(usuario.getCurso());
	}

}
