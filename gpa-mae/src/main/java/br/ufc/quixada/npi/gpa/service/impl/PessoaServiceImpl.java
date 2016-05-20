package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class PessoaServiceImpl implements PessoaService {
	
	@Inject
	private GenericRepository<Pessoa> pessoaRepository;
	
	@Override
	public Pessoa getPessoaPorCpf(String cpf) {
		return (Pessoa) pessoaRepository.findFirst("Pessoa.findPessoaByCpf", new SimpleMap<String, Object>("cpf", cpf));

		}

	@Override
	public void save(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		
	}

	@Override
	public void update(Pessoa pessoa) {
		pessoaRepository.update(pessoa);
		
	}

	@Override
	public void delete(Pessoa pessoa) {
		pessoaRepository.delete(pessoa);
		
	}

	
}
