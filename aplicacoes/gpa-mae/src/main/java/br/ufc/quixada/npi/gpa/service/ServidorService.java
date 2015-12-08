package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.service.GenericService;

public interface ServidorService extends GenericService<Servidor>{
	
	public abstract Servidor getServidor(String siape);
	
	public abstract Servidor getServidorByCPF(String CPF);
	
	public abstract Servidor getServidorComBancas(String CPF);

	

}
