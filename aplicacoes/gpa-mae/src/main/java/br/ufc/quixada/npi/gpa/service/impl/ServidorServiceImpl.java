package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class ServidorServiceImpl extends GenericServiceImpl<Servidor> implements ServidorService{

	
	@Override
	@Transactional(readOnly = true)
	public Servidor getServidor(String siape) {
		
		return (Servidor) findFirst("Servidor.findServidorBySiape", new SimpleMap<String, Object>("siape", siape));
	}

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidorByCpf(String cpf) {
			return (Servidor) findFirst("Servidor.findServidorByCpf", new SimpleMap<String, Object>("cpf", cpf));
	}

	@Override
	public Servidor getServidorComComissao(String CPF) {
		return (Servidor) findFirst("Servidor.findServidorComComissaoByCpf", new SimpleMap<String, Object>("cpf", CPF));

	}
}
