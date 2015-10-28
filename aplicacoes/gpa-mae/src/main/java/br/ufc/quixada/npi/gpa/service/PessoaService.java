package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Papel;
import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.service.GenericService;

public interface PessoaService extends GenericService<Pessoa> {

	public abstract List<Pessoa> getPareceristas(Long id);
	
	public abstract List<Papel> getPapeis(String cpf);
	
	public abstract Pessoa getPessoaByCpf(String cpf);

	public abstract boolean isCoordenador(Pessoa pessoa);
}
