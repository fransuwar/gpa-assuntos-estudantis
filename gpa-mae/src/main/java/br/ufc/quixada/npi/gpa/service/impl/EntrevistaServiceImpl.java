package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.service.EntrevistaService;
import br.ufc.quixada.npi.repository.GenericRepository;

@Named
public class EntrevistaServiceImpl  implements EntrevistaService {
	
	
	@Inject
	private GenericRepository<Entrevista> entrevistaRepository;	
	
	@Override
	public Entrevista findById(Integer idEntrevista) {
		return entrevistaRepository.find(Entrevista.class, idEntrevista);
	}

	@Override
	public void update(Entrevista entrevista) {
		entrevistaRepository.update(entrevista);
	}

}
