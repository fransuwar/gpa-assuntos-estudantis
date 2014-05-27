package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.model.PessoaFamilia;
import br.ufc.quixada.npi.repository.PessoaFamiliaRepository;

@Named
public class PessoaFamiliaServiceImpl implements PessoaFamiliaService {

	@Inject
	private PessoaFamiliaRepository pessoaFamiliaRepository;

	public PessoaFamiliaServiceImpl() {
	}

	@Transactional
	public void save(PessoaFamilia pessoaFamilia) {
		pessoaFamiliaRepository.save(pessoaFamilia);

	}

	@Transactional
	public void update(PessoaFamilia pessoaFamilia) {
		pessoaFamiliaRepository.update(pessoaFamilia);

	}

	@Transactional
	public PessoaFamilia findById(int id) {
		return pessoaFamiliaRepository.find(id);

	}

	@Transactional
	public List<PessoaFamilia> findAll() {
		List<PessoaFamilia> l = pessoaFamiliaRepository.find();
		return l;
	}

	@Transactional
	public void delete(PessoaFamilia pessoaFamilia) {
		pessoaFamiliaRepository.delete(pessoaFamilia);

	}

}
