package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class IniciacaoAcademicaServiceImpl extends GenericServiceImpl<QuestionarioIniciacaoAcademica> implements IniciacaoAcademicaService {

	@Override
	@Transactional(readOnly = true)
	public QuestionarioIniciacaoAcademica getQuestIniAcadById(Integer id) {
		return (QuestionarioIniciacaoAcademica) findFirst("IniAcad.findIniAcadById", new SimpleMap<String, Object>("idAluno", id));
	}
	

}
