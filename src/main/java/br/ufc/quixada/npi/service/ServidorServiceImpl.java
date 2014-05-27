package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.model.Servidor;
import br.ufc.quixada.npi.repository.ServidorRepository;

@Named
public class ServidorServiceImpl implements ServidorService {

	@Inject
	private ServidorRepository servidorRepository;

	public ServidorServiceImpl() {
	}

	@Transactional
	public void save(Servidor servidor) {
		servidorRepository.save(servidor);

	}

	@Transactional
	public void update(Servidor servidor) {
		servidorRepository.update(servidor);

	}

	@Transactional
	public Servidor findById(int id) {
		return servidorRepository.find(id);

	}

	@Transactional
	public List<Servidor> findAll() {
		List<Servidor> l = servidorRepository.find();
		return l;
	}

	@Transactional
	public void delete(Servidor servidor) {
		servidorRepository.delete(servidor);

	}

}
