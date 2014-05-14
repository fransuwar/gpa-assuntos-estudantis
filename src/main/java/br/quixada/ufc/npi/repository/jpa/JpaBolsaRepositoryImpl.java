package br.quixada.ufc.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.Bolsa;
import br.quixada.ufc.npi.repository.BolsaRepository;

@Named
public class JpaBolsaRepositoryImpl extends JpaGenericRepositoryImpl<Bolsa>
		implements BolsaRepository {

	public JpaBolsaRepositoryImpl() {
		super();
		this.persistentClass = Bolsa.class;
	}
}
