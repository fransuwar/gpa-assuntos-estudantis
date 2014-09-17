package br.ufc.quixada.npi.service;

import java.util.List;

public interface GenericService<T> {

	public abstract void save(T entity);

	public abstract void update(T entity);

	public abstract T find(Class<T> entityClass, Long id);

	public abstract List<T> find(Class<T> entityClass);

	public abstract void delete(T entity);
}
