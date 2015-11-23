package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class QuestionarioAuxilioMoradiaServiceImpl extends GenericServiceImpl<QuestionarioAuxilioMoradia> 
	implements QuestionarioAuxMoradiaService {

	@Override
	@Transactional(readOnly = true)
	public QuestionarioAuxilioMoradia getQuestAuxMorById(Integer idInscricao) {
		return (QuestionarioAuxilioMoradia) findFirst("AuxMor.findAuxMorById", new SimpleMap<String, Object>("idInscricao", idInscricao));
	}

	
	
}
