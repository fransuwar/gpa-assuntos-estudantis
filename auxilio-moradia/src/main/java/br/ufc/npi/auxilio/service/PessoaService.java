package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.model.Pessoa;

public interface PessoaService {
	
	Pessoa getByCpf(String cpf);

}
