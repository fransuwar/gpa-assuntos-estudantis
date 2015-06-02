package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
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
	public Servidor getServidorComBancas(Integer id) {
		
		return (Servidor) findFirst("Servidor.findServidorComBancas", new SimpleMap<String, Object>("servidorId", id));
		
	}

	@Override
	@Transactional
	public Servidor getPessoaServidorComBancas(Integer id) {
		return (Servidor) findFirst("Servidor.findPessoaServidorComBancas", new SimpleMap<String, Object>("pessoaId", id));
	}
}
