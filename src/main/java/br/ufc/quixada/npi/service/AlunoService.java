package br.ufc.quixada.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.Aluno;


public interface AlunoService {

	public abstract void save(Aluno aluno);
	
	public abstract void update(Aluno aluno);

	public abstract Aluno findById(int id);

	public abstract List<Aluno> findAll();

	public abstract void delete(Aluno aluno);

}