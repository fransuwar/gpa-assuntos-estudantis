package br.com.ufc.quixada.npi.gpa.service;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.service.GenericService;

public interface AlunoService extends GenericService<Aluno>{

	public abstract Aluno getAlunoByMatricula(String matricula);
	

}
