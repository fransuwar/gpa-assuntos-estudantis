package br.ufc.npi.auxilio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.QuestionarioAuxilioMoradia;
import br.ufc.npi.auxilio.repository.QuestionarioAuxilioMoradiaRepository;
import br.ufc.npi.auxilio.service.QuestionarioAuxilioMoradiaService;

@Service
public class QuestionarioAuxilioMoradiaServiceImpl implements QuestionarioAuxilioMoradiaService{

	@Autowired
	QuestionarioAuxilioMoradiaRepository questionarioAuxilioMoradiaRepository;
	
	@Override
	public QuestionarioAuxilioMoradia salvar(QuestionarioAuxilioMoradia questionarioAuxilioMoradia) {
		return questionarioAuxilioMoradiaRepository.save(questionarioAuxilioMoradia);
	}

	
}
