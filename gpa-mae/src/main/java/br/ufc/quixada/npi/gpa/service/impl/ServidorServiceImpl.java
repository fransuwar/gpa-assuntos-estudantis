package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.repository.ServidorRepository;
import br.ufc.quixada.npi.gpa.service.ServidorService;

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

	@Override
	public Servidor getById(Integer id) {
		return servidorRepository.findOne(id);
	}

}
