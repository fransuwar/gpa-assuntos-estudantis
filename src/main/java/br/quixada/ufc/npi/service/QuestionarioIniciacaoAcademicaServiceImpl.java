package br.quixada.ufc.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.npi.model.QuestionarioIniciacaoAcademica;
import br.quixada.ufc.npi.repository.QuestionarioIniciacaoAcademicaRepository;

@Named
public class QuestionarioIniciacaoAcademicaServiceImpl implements
		QuestionarioIniciacaoAcademicaService {

	@Inject
	private QuestionarioIniciacaoAcademicaRepository questionarioIniciacaoAcademicaRepository;

	public QuestionarioIniciacaoAcademicaServiceImpl() {
	}

	@Transactional
	public void save(
			QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica) {
		questionarioIniciacaoAcademicaRepository
				.save(questionarioIniciacaoAcademica);

	}

	@Transactional
	public void update(
			QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica) {
		questionarioIniciacaoAcademicaRepository
				.update(questionarioIniciacaoAcademica);

	}

	@Transactional
	public QuestionarioIniciacaoAcademica findById(int id) {
		return questionarioIniciacaoAcademicaRepository.find(id);

	}

	@Transactional
	public List<QuestionarioIniciacaoAcademica> findAll() {
		List<QuestionarioIniciacaoAcademica> l = questionarioIniciacaoAcademicaRepository
				.find();
		return l;
	}

	@Transactional
	public void delete(
			QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica) {
		questionarioIniciacaoAcademicaRepository
				.delete(questionarioIniciacaoAcademica);

	}

}
