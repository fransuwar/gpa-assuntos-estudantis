package br.ufc.quixada.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.Servidor;


public interface ServidorService {

	public abstract void save(Servidor servidor);
	
	public abstract void update(Servidor servidor);

	public abstract Servidor findById(int id);

	public abstract List<Servidor> findAll();

	public abstract void delete(Servidor servidor);

}