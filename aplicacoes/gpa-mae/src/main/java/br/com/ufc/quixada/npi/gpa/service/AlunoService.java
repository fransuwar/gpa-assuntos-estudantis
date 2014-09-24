package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Aluno;

public interface AlunoService extends GenericService<Aluno>{
	
	
	public abstract List<Aluno> findAll();
	
	
}
