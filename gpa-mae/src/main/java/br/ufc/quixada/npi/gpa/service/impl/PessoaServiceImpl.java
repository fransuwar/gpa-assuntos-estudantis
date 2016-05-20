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
	private GenericRepository<Pessoa> pessoaService;
	
	@Override
	public Pessoa getPessoaByCpf(String cpf) {
		return (Pessoa) pessoaService.findFirst("Pessoa.findPessoaByCpf", new SimpleMap<String, Object>("cpf", cpf));
		}

	@Override
	public void save(Pessoa pessoa) {
		pessoaService.save(pessoa);
		
	}

	@Override
	public void update(Pessoa pessoa) {
		pessoaService.update(pessoa);
		
	}

	@Override
	public void delete(Pessoa pessoa) {
		pessoaService.delete(pessoa);
		
	}

	
}
