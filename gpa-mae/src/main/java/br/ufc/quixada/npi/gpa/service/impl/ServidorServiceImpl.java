package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class ServidorServiceImpl implements ServidorService{
	
	@Inject
	private GenericRepository<Servidor> servidorService;

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidor(String siape) {
		
		return (Servidor) servidorService.findFirst("Servidor.findServidorBySiape", new SimpleMap<String, Object>("siape", siape));
	}

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidorByCpf(String cpf) {
			return (Servidor) servidorService.findFirst("Servidor.findServidorByCpf", new SimpleMap<String, Object>("cpf", cpf));
	}

	@Override
	public Servidor getServidorComComissao(String CPF) {
		return (Servidor) servidorService.findFirst("Servidor.findServidorComComissaoByCpf", new SimpleMap<String, Object>("cpf", CPF));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Servidor> getServidorPertenceBanca(Integer idServidor, Integer idSelecao) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("idServidor", idServidor);
		params.put("idSelecao", idSelecao);
		
		return (List<Servidor>) servidorService.find(QueryType.NAMED, "Servidor.findServidorPertenceSelecao", params);
	}

	@Override
	public void save(Servidor servidor) {
		servidorService.save(servidor);
		
	}

	@Override
	public void update(Servidor servidor) {
		servidorService.update(servidor);
		
	}

	@Override
	public void delete(Servidor servidor) {
		servidorService.delete(servidor);
		
	}

	@Override
	public Servidor getServidorPorId(Integer id) {
		return (Servidor) servidorService.findFirst(QueryType.JPQL,"select s from Servidor as s where s.id = :idServidor", 
				new SimpleMap<String, Object>("idServidor", id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Servidor> listarServidores() {
		return servidorService.find(QueryType.JPQL,"select s from Servidor as s", 
				new SimpleMap<String, Object>());
	}
}