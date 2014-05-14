package br.quixada.ufc.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.PessoaFamilia;
import br.quixada.ufc.npi.repository.PessoaFamiliaRepository;

@Named
public class JpaPessoaFamiliaRepositoryImpl extends JpaGenericRepositoryImpl<PessoaFamilia>
		implements PessoaFamiliaRepository {

	public JpaPessoaFamiliaRepositoryImpl() {
		super();
		this.persistentClass = PessoaFamilia.class;
	}
}
