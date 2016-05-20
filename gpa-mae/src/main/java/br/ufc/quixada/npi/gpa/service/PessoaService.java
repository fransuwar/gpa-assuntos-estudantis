package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Pessoa;

public interface PessoaService {
	
	public abstract Pessoa getPessoaByCpf(String cpf);
	
    public abstract void save(Pessoa pessoa);
	
	public abstract void update(Pessoa pessoa);
	
	public abstract void delete(Pessoa pessoa);
	

}
