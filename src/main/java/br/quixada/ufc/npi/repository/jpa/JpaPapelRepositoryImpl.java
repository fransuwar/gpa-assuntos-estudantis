package br.quixada.ufc.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.Papel;
import br.quixada.ufc.npi.repository.PapelRepository;

@Named
public class JpaPapelRepositoryImpl extends JpaGenericRepositoryImpl<Papel>
		implements PapelRepository {

	public JpaPapelRepositoryImpl() {
		super();
		this.persistentClass = Papel.class;
	}
}
