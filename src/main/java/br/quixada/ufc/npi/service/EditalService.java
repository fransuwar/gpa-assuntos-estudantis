package br.quixada.ufc.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.Edital;

public interface EditalService {

	public abstract void save(Edital edital);

	public abstract void update(Edital edital);

	public abstract Edital findById(int id);

	public abstract List<Edital> findAll();

	public abstract void delete(Edital edital);

}