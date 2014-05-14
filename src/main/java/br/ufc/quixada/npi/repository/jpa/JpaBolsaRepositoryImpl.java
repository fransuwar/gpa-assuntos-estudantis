package br.ufc.quixada.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.Bolsa;
import br.ufc.quixada.npi.repository.BolsaRepository;

@Named
public class JpaBolsaRepositoryImpl extends JpaGenericRepositoryImpl<Bolsa>
		implements BolsaRepository {

	public JpaBolsaRepositoryImpl() {
		super();
		this.persistentClass = Bolsa.class;
	}
}
