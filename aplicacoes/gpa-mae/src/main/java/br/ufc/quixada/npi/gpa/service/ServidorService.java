package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.service.GenericService;

public interface ServidorService extends GenericService<Servidor>{
	
	public abstract Servidor getServidorBySiape(String siape);
	public abstract Servidor getServidorComBancas(Integer id);
	public abstract Servidor getPessoaServidorComBancas(Integer id);
	public abstract Servidor getServidorByCpf(String cpf);

	

}
