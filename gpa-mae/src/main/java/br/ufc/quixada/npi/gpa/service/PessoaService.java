package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Pessoa;

public interface PessoaService {
	
	Pessoa getByCpf(String cpf);

}
