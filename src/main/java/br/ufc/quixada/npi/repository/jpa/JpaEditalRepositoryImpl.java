package br.ufc.quixada.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.Edital;
import br.ufc.quixada.npi.repository.EditalRepository;

@Named
public class JpaEditalRepositoryImpl extends JpaGenericRepositoryImpl<Edital>
		implements EditalRepository {

	public JpaEditalRepositoryImpl() {
		super();
		this.persistentClass = Edital.class;
	}
}
