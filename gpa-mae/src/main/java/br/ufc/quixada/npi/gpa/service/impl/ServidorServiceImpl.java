package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.repository.GenericRepository;


@Named
public class ServidorServiceImpl implements ServidorService{
	
	@Inject
	private GenericRepository<Servidor> servidorRepository;

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidor(String siape) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siape", siape);
		return (Servidor) servidorRepository.findFirst("Servidor.findServidorBySiape", map);
	}

	@Override
	@Transactional(readOnly = true)
	public Servidor getServidorPorCpf(String cpf) {
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("cpf", cpf);
			return (Servidor) servidorRepository.findFirst("Servidor.findServidorByCpf", map);
	}

	@Override
	public Servidor getServidorComComissao(String CPF) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("cpf", CPF);
		return (Servidor) servidorRepository.findFirst("Servidor.findServidorComComissaoByCpf",map);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Servidor> getServidorPertenceBanca(Integer idServidor, Integer idSelecao) {
		
		Map<String, Object> params = new HashMap<String, Object>();
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idServidor", id);
		return (Servidor) servidorRepository.findFirst(QueryType.JPQL,"select s from Servidor as s where s.id = :idServidor", 
				params,-1);
	}

	@SuppressWarnings("unchecked")
	@Override
	
	public List<Servidor> listarServidores() {
		Map<String, Object> params = new HashMap<String, Object>();
		return servidorRepository.find(QueryType.JPQL,"select s from Servidor as s", 
				params);
	}
}