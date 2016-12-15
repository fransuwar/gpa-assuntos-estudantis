package br.ufc.quixada.npi.gpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.repository.PessoaRepository;
import br.ufc.quixada.npi.gpa.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa getByCpf(String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}

}
