package br.ufc.npi.auxilio.model;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

public class ServidorEntityListener {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostLoad
	public void loadServidor(Servidor servidor) {
		AutowireHelper.autowire(this, this.usuarioService);
		Usuario usuario = usuarioService.getByCpf(servidor.getPessoa().getCpf());
		servidor.setSiape(usuario.getSiape());
	}

}
