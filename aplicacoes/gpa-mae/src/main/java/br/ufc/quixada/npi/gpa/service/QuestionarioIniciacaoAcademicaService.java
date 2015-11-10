package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.service.GenericService;

public interface QuestionarioIniciacaoAcademicaService extends GenericService<QuestionarioIniciacaoAcademica>{

	public abstract QuestionarioIniciacaoAcademica getQuestIniAcadById(Integer id);
}
