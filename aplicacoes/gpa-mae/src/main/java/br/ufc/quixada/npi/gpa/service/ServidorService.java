package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.service.GenericService;

public interface ServidorService extends GenericService<Servidor>{
	
	public abstract Servidor getServidor(String siape);
	public abstract Servidor getServidorByCpf(String cpf);
	public abstract Servidor getServidorComComissao(String CPF);
	public abstract List<Servidor> getServidorPertenceBanca(Integer idServidor, Integer idSelecao);	

}
