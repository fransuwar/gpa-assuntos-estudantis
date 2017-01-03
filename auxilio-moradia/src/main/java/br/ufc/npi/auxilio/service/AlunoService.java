package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.model.Aluno;

public interface AlunoService {
	
	public Aluno buscarPorCpf(String cpf);
	
	public Aluno salvar(Aluno aluno);
}
