package br.com.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Servidor;
import br.com.ufc.quixada.npi.gpa.repository.ServidorRepository;
import br.com.ufc.quixada.npi.gpa.service.ServidorService;

@Named
public class ServidorServiceImpl extends GenericServiceImpl<Servidor> implements ServidorService{

	
	
	@Inject
	ServidorRepository servidorRepository;
	
	@Override
	public List<Servidor> findAll() {
		
		return servidorRepository.find(Servidor.class);
	}
	
	
}
