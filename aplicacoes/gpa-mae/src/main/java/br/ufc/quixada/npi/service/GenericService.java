package br.ufc.quixada.npi.service;

import java.util.List;

import br.ufc.quixada.npi.model.Selecao;

public interface GenericService<T> {

	public abstract void save(T entity);

	public abstract void update(T entity);

	public abstract T find(Class<T> entityClass, Integer id);
	
	public abstract T find1(Class<T> entityClass, Selecao membrosBanca);

	public abstract List<T> find(Class<T> entityClass);

	public abstract void delete(T entity);
	
	
	
	
}
