package br.com.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.com.ufc.quixada.npi.gpa.repository.AlunoRepository;

@Named
public class JpaAlunoRepositoryImpl extends JpaGenericRepositoryImpl<Aluno> implements AlunoRepository{

}
