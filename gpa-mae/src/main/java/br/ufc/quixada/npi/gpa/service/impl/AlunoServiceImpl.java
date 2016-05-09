package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class AlunoServiceImpl implements AlunoService {
	
	@Inject
	private GenericRepository<Aluno> alunoRepository;


	@Override
	@Transactional(readOnly = true)
	public Aluno getAluno(String matricula) {
		return (Aluno) alunoRepository.findFirst("Aluno.findAlunoByMatricula", new SimpleMap<String, Object>("matricula", matricula));
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoComInscricoes(String cpf) {
		return (Aluno) alunoRepository.findFirst("Aluno.findAlunoComInscricoesByCPF", new SimpleMap<String, Object>("cpf", cpf));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isAlunoCadastrado(Aluno aluno) {
		@SuppressWarnings("unchecked")
		List<Aluno> alunos = alunoRepository.find(QueryType.JPQL, "from Aluno as a where a.matricula = :matricula",
				new SimpleMap<String, Object>("matricula", aluno.getMatricula()));
		if (alunos == null || alunos.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoByCPF(String cpf) {
		return (Aluno) alunoRepository.findFirst("Aluno.findAlunoByCPF", new SimpleMap<String, Object>("cpf", cpf));
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
	public Aluno find(Class<Aluno> classe, Integer idAluno) {
		return alunoRepository.find(classe,idAluno);
		
	}

	@Override
	public List<Aluno> find(Class<Aluno> classe) {
		return alunoRepository.find(classe);
	}

	

}
