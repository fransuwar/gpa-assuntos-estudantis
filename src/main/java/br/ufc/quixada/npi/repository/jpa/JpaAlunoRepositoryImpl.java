package br.ufc.quixada.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.Aluno;
import br.ufc.quixada.npi.repository.AlunoRepository;

@Named
public class JpaAlunoRepositoryImpl extends JpaGenericRepositoryImpl<Aluno>
		implements AlunoRepository {

	public JpaAlunoRepositoryImpl() {
		super();
		this.persistentClass = Aluno.class;
	}
}
