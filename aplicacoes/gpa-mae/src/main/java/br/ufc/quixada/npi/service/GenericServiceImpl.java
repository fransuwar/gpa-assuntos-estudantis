package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.repository.GenericRepository;


@Named
public class GenericServiceImpl<T> implements GenericService<T> {

	@Inject
	private GenericRepository<T> genericRepository;
	
	protected Class<T> persistentClass;

	public GenericServiceImpl() {
	}

	@Transactional
	public void save(T entity) {
		genericRepository.save(entity);

	}

	@Transactional
	public void update(T entity) {
		genericRepository.update(entity);

	}

	@Transactional(readOnly=true)
	public List<T> find(Class<T> entityClass) {
		List<T> l = genericRepository.find(entityClass);
		return l;
	}
	
	@Transactional
	public void delete(T entity) {
		genericRepository.delete(entity);

	}

	@Transactional
	public T find(Class<T> entityClass, Long id) {
		return (T) genericRepository.find(entityClass, id);
	}

}
