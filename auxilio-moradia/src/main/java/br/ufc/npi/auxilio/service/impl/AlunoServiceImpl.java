package br.ufc.npi.auxilio.service.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.repository.AlunoRepository;
import br.ufc.npi.auxilio.service.AlunoService;

@Named
public class AlunoServiceImpl implements AlunoService{
	@Autowired
	private AlunoRepository alunoRepository;
	

	@Override
	public Aluno buscarPorCpf(String cpf) {
		return alunoRepository.findByCpf(cpf);
	}


	@Override
	public Aluno salvar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	
	
}
