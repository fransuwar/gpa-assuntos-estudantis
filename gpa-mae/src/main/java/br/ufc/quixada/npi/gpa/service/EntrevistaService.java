package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Entrevista;

public interface EntrevistaService {
	
	public Entrevista findById(Integer idEntrevista);
	public void update(Entrevista entrevista);
}