package br.ufc.quixada.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.QuestionarioIniciacaoAcademica;


public interface QuestionarioIniciacaoAcademicaService {

	public abstract void save(QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica);
	
	public abstract void update(QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica);

	public abstract QuestionarioIniciacaoAcademica findById(int id);

	public abstract List<QuestionarioIniciacaoAcademica> findAll();

	public abstract void delete(QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica);

}