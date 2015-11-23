package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.service.GenericService;

public interface QuestionarioAuxMoradiaService extends GenericService<QuestionarioAuxilioMoradia>{

	public abstract QuestionarioAuxilioMoradia getQuestAuxMorById(Integer idInscricao);
}
