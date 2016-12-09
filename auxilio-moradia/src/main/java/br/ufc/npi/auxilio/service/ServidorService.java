package br.ufc.npi.auxilio.service;

import java.util.List;

import br.ufc.npi.auxilio.model.Servidor;

public interface ServidorService {
	
	Servidor getByCpf(String cpf);

	List<Servidor> getAll();

}
