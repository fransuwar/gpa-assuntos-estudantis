package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.service.GenericService;

public interface AlunoService extends GenericService<Aluno>{

	public abstract Aluno getAlunoByMatricula(String matricula);
	
	public abstract Aluno getAlunoByIdPessoa(Integer id);
	
	public abstract Aluno getAlunoComInscricoes(Integer id);

	public abstract Aluno getAlunoComInscricoesCpf(String cpf);
	
	public abstract Aluno getAlunoByCPF(String cpf);
	
	public abstract boolean existsAlunoEquals(Aluno aluno);

}
