package br.quixada.ufc.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.npi.model.QuestionarioAuxilioMoradia;
import br.quixada.ufc.npi.repository.QuestionarioAuxilioMoradiaRepository;

@Named
public class QuestionarioAuxilioMoradiaServiceImpl implements QuestionarioAuxilioMoradiaService {

	@Inject
	private QuestionarioAuxilioMoradiaRepository questionarioAuxilioMoradiaRepository;

	public QuestionarioAuxilioMoradiaServiceImpl() {
	}

	@Transactional
	public void save(QuestionarioAuxilioMoradia questionarioAuxilioMoradia) {
		questionarioAuxilioMoradiaRepository.save(questionarioAuxilioMoradia);

	}

	@Transactional
	public void update(QuestionarioAuxilioMoradia questionarioAuxilioMoradia) {
		questionarioAuxilioMoradiaRepository.update(questionarioAuxilioMoradia);

	}

	@Transactional
	public QuestionarioAuxilioMoradia findById(int id) {
		return questionarioAuxilioMoradiaRepository.find(id);

	}

	@Transactional
	public List<QuestionarioAuxilioMoradia> findAll() {
		List<QuestionarioAuxilioMoradia> l = questionarioAuxilioMoradiaRepository.find();
		return l;
	}

	@Transactional
	public void delete(QuestionarioAuxilioMoradia questionarioAuxilioMoradia) {
		questionarioAuxilioMoradiaRepository.delete(questionarioAuxilioMoradia);

	}

}
