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
	public Servidor getServidorBySiape(String siape) {
		return (Servidor) findFirst("Servidor.findServidorBySiape", new SimpleMap<String, Object>("siape", siape));
	}

	@Override
	@Transactional
	public Servidor getServidorComComissao(Integer id) {
		
		return (Servidor) findFirst("Servidor.findServidorComComissao", new SimpleMap<String, Object>("servidorId", id));
		
	}

	@Override
	@Transactional
	public Servidor getPessoaServidorComComissao(Integer id) {
		return (Servidor) findFirst("Servidor.findPessoaServidorComComissao", new SimpleMap<String, Object>("pessoaId", id));
	}

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidorByCpf(String cpf) {
			return (Servidor) findFirst("Servidor.findServidorByCpf", new SimpleMap<String, Object>("cpf", cpf));
	}

	@Override
	public Servidor getServidorByCPFComComissao(String CPF) {
		return (Servidor) findFirst("Servidor.findServidorByCPFComComissao", new SimpleMap<String, Object>("cpf", CPF));
	}
}
