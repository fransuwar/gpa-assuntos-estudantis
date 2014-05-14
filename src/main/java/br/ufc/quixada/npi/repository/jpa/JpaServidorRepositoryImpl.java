package br.ufc.quixada.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.Servidor;
import br.ufc.quixada.npi.repository.ServidorRepository;

@Named
public class JpaServidorRepositoryImpl extends JpaGenericRepositoryImpl<Servidor>
		implements ServidorRepository {

	public JpaServidorRepositoryImpl() {
		super();
		this.persistentClass = Servidor.class;
	}
}
