package br.quixada.ufc.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.QuestionarioAuxilioMoradia;


public interface QuestionarioAuxilioMoradiaService {

	public abstract void save(QuestionarioAuxilioMoradia questionarioAuxilioMoradia);
	
	public abstract void update(QuestionarioAuxilioMoradia questionarioAuxilioMoradia);

	public abstract QuestionarioAuxilioMoradia findById(int id);

	public abstract List<QuestionarioAuxilioMoradia> findAll();

	public abstract void delete(QuestionarioAuxilioMoradia questionarioAuxilioMoradia);

}