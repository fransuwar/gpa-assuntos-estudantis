package br.ufc.quixada.npi.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.repository.QuestionarioAuxilioMoradiaRepository;
import br.ufc.quixada.npi.service.GenericServiceImpl;
import br.ufc.quixada.npi.service.QuestionarioAuxMoradiaService;

@Named
public class QuestionarioAuxilioMoradiaServiceImpl extends GenericServiceImpl<QuestionarioAuxilioMoradia> 
	implements QuestionarioAuxMoradiaService {

	@Inject
	private QuestionarioAuxilioMoradiaRepository questionarioAuxilioMoradiaRepository;
	
	
}
