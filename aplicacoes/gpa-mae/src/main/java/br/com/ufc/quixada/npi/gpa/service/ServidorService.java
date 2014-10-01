package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Servidor;

public interface ServidorService extends GenericService<Servidor>{
	
	public abstract List<Servidor> findAll();

}
