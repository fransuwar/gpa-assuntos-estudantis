package br.quixada.ufc.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.Bolsa;

public interface BolsaService {

	public abstract void save(Bolsa bolsa);

	public abstract void update(Bolsa bolsa);

	public abstract Bolsa findById(int id);

	public abstract List<Bolsa> findAll();

	public abstract void delete(Bolsa bolsa);

}