package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.service.GenericService;

public interface PessoaService extends GenericService<Pessoa> {
	
	public abstract Pessoa getPessoaPorCpf(String cpf);

}
