package br.ufc.quixada.npi.gpa.service.impl;


import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class AlunoServiceImpl extends GenericServiceImpl<Aluno> implements AlunoService{

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoByMatricula(String matricula) {
		return (Aluno) findFirst("Aluno.findAlunoByMatricula", new SimpleMap<String, Object>("matricula", matricula));
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoById(Integer id) {
		return (Aluno) findFirst("Aluno.findAlunoById", new SimpleMap<String, Object>("idPessoa", id));
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoComSelecoes(Integer id) {
		return (Aluno) findFirst("Aluno.findAlunoComSelecoes", new SimpleMap<String, Object>("idPessoa", id));
	}
}
