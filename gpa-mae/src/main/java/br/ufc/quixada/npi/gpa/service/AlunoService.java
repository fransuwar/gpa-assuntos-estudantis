package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.service.GenericService;

public interface AlunoService extends GenericService<Aluno>{

	public abstract Aluno getAluno(String matricula);

	public abstract Aluno getAlunoComInscricoes(String cpf);
	
	public abstract Aluno getAlunoPorCPF(String cpf);
	
	public abstract boolean AlunoEstaCadastrado(Aluno aluno);


}
