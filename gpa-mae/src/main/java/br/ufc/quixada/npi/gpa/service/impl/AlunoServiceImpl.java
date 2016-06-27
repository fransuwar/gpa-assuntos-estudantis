package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.repository.GenericRepository;

@Named
public class AlunoServiceImpl implements AlunoService {
	
	@Inject
	private GenericRepository<Aluno> alunoRepository;

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoComInscricoes(String cpf) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cpf", cpf);
		return (Aluno) alunoRepository.findFirst("Aluno.findAlunoComInscricoesByCPF", map);
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoPorCPF(String cpf) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cpf", cpf);
		return (Aluno) alunoRepository.findFirst("Aluno.findAlunoByCPF", map);
	}


	@Override
	public void save(Aluno aluno) {
		alunoRepository.save(aluno);
		
	}
	
	public void update(Aluno aluno){
		alunoRepository.update(aluno);
	}

	@Override
	public void delete(Aluno aluno) {
		alunoRepository.delete(aluno);
		
	}
	
	@Override
	public Aluno getAlunoPorId(Integer idAluno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idAluno", idAluno);
		return (Aluno) alunoRepository.findFirst(QueryType.JPQL, "select a from Aluno as a where a.id = :idAluno", map, -1);
		
	}

	@Override
	public List<Aluno> ListarAlunos() {
		Class<Aluno> classe = null;
		return alunoRepository.find(classe);
	}

	

}
