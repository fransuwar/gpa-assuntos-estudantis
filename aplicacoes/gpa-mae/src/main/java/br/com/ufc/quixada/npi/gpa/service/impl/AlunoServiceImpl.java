package br.com.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.com.ufc.quixada.npi.gpa.repository.AlunoRepository;
import br.com.ufc.quixada.npi.gpa.repository.QueryType;
import br.com.ufc.quixada.npi.gpa.service.AlunoService;

@Named
public class AlunoServiceImpl extends GenericServiceImpl<Aluno> implements AlunoService{

	@Inject
	AlunoRepository alunoRepository;

	@Override
	public List<Aluno> findAll() {
		return alunoRepository.find(Aluno.class);
	}
	
	@Override
	public Aluno getAlunoByMatricula(String matricula) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "matricula", matricula);
		List<Aluno> usuarios = alunoRepository.find(QueryType.JPQL, "from Aluno where matricula = :matricula", params);
		
		if(usuarios.isEmpty()){
			return null;
		}else{ 
			return usuarios.get(0);						
		}
	}
}
