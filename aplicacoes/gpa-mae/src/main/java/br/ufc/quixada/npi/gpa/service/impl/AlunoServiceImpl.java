package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class AlunoServiceImpl extends GenericServiceImpl<Aluno> implements AlunoService {


	@Override
	@Transactional(readOnly = true)
	public Aluno getAluno(String matricula) {
		return (Aluno) findFirst("Aluno.findAlunoByMatricula", new SimpleMap<String, Object>("matricula", matricula));
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoComInscricoes(String cpf) {
		return (Aluno) findFirst("Aluno.findAlunoComInscricoesByCPF", new SimpleMap<String, Object>("cpf", cpf));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isAlunoCadastrado(Aluno aluno) {
		@SuppressWarnings("unchecked")
		List<Aluno> alunos = find(QueryType.JPQL, "from Aluno as a where a.matricula = :matricula",
				new SimpleMap<String, Object>("matricula", aluno.getMatricula()));
		if (alunos == null || alunos.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno getAlunoByCPF(String cpf) {
		return (Aluno) findFirst("Aluno.findAlunoByCPF", new SimpleMap<String, Object>("cpf", cpf));
	}

}
