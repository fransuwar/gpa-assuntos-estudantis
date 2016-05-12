package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Aluno;

public interface AlunoService{

	public abstract Aluno getAluno(String matricula);

	public abstract Aluno getAlunoComInscricoes(String cpf);
	
	public abstract Aluno getAlunoByCPF(String cpf);
	
	public abstract boolean isAlunoCadastrado(Aluno aluno);
	
	public abstract void save(Aluno aluno);
	
	public abstract void update(Aluno aluno);
	
	public abstract void delete(Aluno aluno);
	
	public abstract Aluno getAlunoPorId(Integer idAluno);
	
	public abstract List<Aluno> ListarAlunos();
	



}
