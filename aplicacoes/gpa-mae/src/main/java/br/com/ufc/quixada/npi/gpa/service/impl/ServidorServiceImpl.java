package br.com.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Servidor;
import br.com.ufc.quixada.npi.gpa.repository.QueryType;
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
	
	@Override
	public Servidor getServidorBySiape(String siape) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "siape", siape);
		List<Servidor> usuarios = servidorRepository.find(QueryType.JPQL, "from Servidor where siape = :siape", params);
		
		if(usuarios.isEmpty()){
			return null;
		}else{ 
			return usuarios.get(0);						
		}
	}
	
	
}
