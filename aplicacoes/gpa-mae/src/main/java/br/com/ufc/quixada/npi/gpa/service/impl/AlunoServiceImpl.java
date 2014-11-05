package br.com.ufc.quixada.npi.gpa.service.impl;


import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.com.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.NamedParams;

@Named
public class AlunoServiceImpl extends GenericServiceImpl<Aluno> implements AlunoService{

	@Override
	public Aluno getAlunoByMatricula(String matricula) {
		return findFirst("from Aluno where matricula = :matricula", new NamedParams("matricula", matricula));
	}
}
