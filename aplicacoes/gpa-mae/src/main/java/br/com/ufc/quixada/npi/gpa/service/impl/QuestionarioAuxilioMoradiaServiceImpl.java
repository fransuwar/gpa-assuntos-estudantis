package br.com.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.repository.QuestionarioAuxilioMoradiaRepository;
import br.com.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.com.ufc.quixada.npi.gpa.service.impl.GenericServiceImpl;

@Named
public class QuestionarioAuxilioMoradiaServiceImpl extends GenericServiceImpl<QuestionarioAuxilioMoradia> 
	implements QuestionarioAuxMoradiaService {

	@Inject
	private QuestionarioAuxilioMoradiaRepository questionarioAuxilioMoradiaRepository;
	
	
}
