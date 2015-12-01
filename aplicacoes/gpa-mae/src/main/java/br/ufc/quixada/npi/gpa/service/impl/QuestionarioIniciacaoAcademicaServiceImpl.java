package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.service.QuestionarioIniciacaoAcademicaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class QuestionarioIniciacaoAcademicaServiceImpl extends GenericServiceImpl<QuestionarioIniciacaoAcademica>
		implements QuestionarioIniciacaoAcademicaService {
	
	@Override
	@Transactional(readOnly = true)
	public QuestionarioIniciacaoAcademica getQuestIniAcadById(Integer idQuest) {
		return (QuestionarioIniciacaoAcademica) findFirst("IniAcad.findIniAcadById", new SimpleMap<String, Object>("idQuest", idQuest));
	}
}
