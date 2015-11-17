package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.service.GenericService;

public interface AlunoService extends GenericService<Aluno>{

	public abstract Aluno getAlunoByMatricula(String matricula);
	
	public abstract Aluno getAlunoById(Integer id);
	
	public abstract Aluno getAlunoComSelecoes(Integer id);

	public abstract Aluno getAlunoByCpf(String cpf);

}
