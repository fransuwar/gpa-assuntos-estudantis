package br.com.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.com.ufc.quixada.npi.gpa.repository.AlunoRepository;
import br.com.ufc.quixada.npi.gpa.repository.QueryType;
import br.com.ufc.quixada.npi.gpa.service.AlunoService;

@Named
public class AlunoServiceImpl extends GenericServiceImpl<Aluno> implements AlunoService{

	/*@Inject
	AlunoRepository alunoRepository;
	
	@Override
	public List<Aluno> listarAlnos() {
		return alunoRepository.find(QueryType.JPQL, "from Aluno", null);
	}
*/
	
}
