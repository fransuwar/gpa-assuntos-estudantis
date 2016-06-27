package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.repository.GenericRepository;


@Named
public class PessoaServiceImpl implements PessoaService {
	
	@Inject
	private GenericRepository<Pessoa> pessoaRepository;
	
	@Override
	public Pessoa getPessoaPorCpf(String cpf) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cpf", cpf);
		return (Pessoa) pessoaRepository.findFirst("Pessoa.findPessoaByCpf",map);

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
