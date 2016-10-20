package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Servidor;

public interface ServidorService {
	
	Servidor getByCpf(String cpf);
	
	Servidor getById(Integer id);
	
	List<Servidor> getAll();

}
