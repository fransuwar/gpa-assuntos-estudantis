package br.ufc.npi.auxilio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.repository.PessoaRepository;
import br.ufc.npi.auxilio.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa getByCpf(String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}

}
