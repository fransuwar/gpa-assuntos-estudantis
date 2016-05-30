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
	private GenericRepository<Servidor> servidorRepository;

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidor(String siape) {
		
		return (Servidor) servidorRepository.findFirst("Servidor.findServidorBySiape", new SimpleMap<String, Object>("siape", siape));
	}

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidorPorCpf(String cpf) {
			return (Servidor) servidorRepository.findFirst("Servidor.findServidorByCpf", new SimpleMap<String, Object>("cpf", cpf));
	}

	@Override
	public Servidor getServidorComComissao(String CPF) {
		return (Servidor) servidorRepository.findFirst("Servidor.findServidorComComissaoByCpf", new SimpleMap<String, Object>("cpf", CPF));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Servidor> getServidorPertenceBanca(Integer idServidor, Integer idSelecao) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("idServidor", idServidor);
		params.put("idSelecao", idSelecao);
		
		return (List<Servidor>) servidorRepository.find(QueryType.NAMED, "Servidor.findServidorPertenceSelecao", params);
	}

	@Override
	public void save(Servidor servidor) {
		servidorRepository.save(servidor);
		
	}

	@Override
	public void update(Servidor servidor) {
		servidorRepository.update(servidor);
		
	}

	@Override
	public void delete(Servidor servidor) {
		servidorRepository.delete(servidor);
		
	}

	@Override
	public Servidor getServidorPorId(Integer id) {
		return (Servidor) servidorRepository.findFirst(QueryType.JPQL,"select s from Servidor as s where s.id = :idServidor", 
				new SimpleMap<String, Object>("idServidor", id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Servidor> listarServidores() {
		return servidorRepository.find(QueryType.JPQL,"select s from Servidor as s", 
				new SimpleMap<String, Object>());
	}
}