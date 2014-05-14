package br.quixada.ufc.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.Papel;

public interface PapelService {

	public abstract void save(Papel papel);

	public abstract void update(Papel papel);

	public Papel findById(int id);

	public abstract List<Papel> findAll();

	public abstract void delete(Papel papel);

}