package br.ufc.quixada.npi.repository.jpa;

import javax.inject.Named;

import br.quixada.ufc.npi.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.repository.QuestionarioAuxilioMoradiaRepository;

@Named
public class JpaQuestionarioAuxilioMoradiaRepositoryImpl extends JpaGenericRepositoryImpl<QuestionarioAuxilioMoradia>
		implements QuestionarioAuxilioMoradiaRepository {

	public JpaQuestionarioAuxilioMoradiaRepositoryImpl() {
		super();
		this.persistentClass = QuestionarioAuxilioMoradia.class;
	}
}
