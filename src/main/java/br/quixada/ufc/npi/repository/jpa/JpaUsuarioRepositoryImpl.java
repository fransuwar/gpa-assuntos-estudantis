package br.quixada.ufc.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.Usuario;
import br.quixada.ufc.npi.repository.UsuarioRepository;

@Named
public class JpaUsuarioRepositoryImpl extends JpaGenericRepositoryImpl<Usuario>
		implements UsuarioRepository {

	public JpaUsuarioRepositoryImpl() {
		super();
		this.persistentClass = Usuario.class;
	}
}
