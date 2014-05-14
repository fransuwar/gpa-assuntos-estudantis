package br.quixada.ufc.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.npi.model.Aluno;
import br.quixada.ufc.npi.repository.AlunoRepository;

@Named
public class AlunoServiceImpl implements AlunoService {

	@Inject
	private AlunoRepository alunoRepository;

	public AlunoServiceImpl() {
	}

	@Transactional
	public void save(Aluno aluno) {
		alunoRepository.save(aluno);

	}

	@Transactional
	public void update(Aluno aluno) {
		alunoRepository.update(aluno);

	}

	@Transactional
	public Aluno findById(int id) {
		return alunoRepository.find(id);

	}

	@Transactional
	public List<Aluno> findAll() {
		List<Aluno> l = alunoRepository.find();
		return l;
	}

	@Transactional
	public void delete(Aluno aluno) {
		alunoRepository.delete(aluno);

	}

}
