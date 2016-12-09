package br.ufc.npi.auxilio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.repository.ServidorRepository;
import br.ufc.npi.auxilio.service.ServidorService;

@Service
public class ServidorServiceImpl implements ServidorService {

	@Autowired
	private ServidorRepository servidorRepository;
	
	@Override
	public Servidor getByCpf(String cpf) {
		return servidorRepository.findByPessoaCpf(cpf);
	}

	@Override
	public List<Servidor> getAll() {
		return servidorRepository.findAll();
	}


}
