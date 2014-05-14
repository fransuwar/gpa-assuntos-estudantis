package br.quixada.ufc.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.QuestionarioIniciacaoAcademica;
import br.quixada.ufc.npi.repository.QuestionarioIniciacaoAcademicaRepository;

@Named
public class JpaQuestionarioIniciacaoAcademicaRepositoryImpl extends JpaGenericRepositoryImpl<QuestionarioIniciacaoAcademica> implements
		QuestionarioIniciacaoAcademicaRepository {

	public JpaQuestionarioIniciacaoAcademicaRepositoryImpl() {
		super();
		this.persistentClass = QuestionarioIniciacaoAcademica.class;
	}
}
