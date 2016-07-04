package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.repository.GenericRepository;


@Named
public class SelecaoServiceImpl implements SelecaoService {
	
	@Inject
	private GenericRepository<Selecao> selecaoService;
	

	@Override
	@Transactional
	public boolean SelecaoEstaCadastrada(Selecao selecao) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tipo", selecao.getTipoSelecao());
		map.put("ano", selecao.getAno());
		map.put("sequencial", selecao.getSequencial());
		List<Selecao> selecoes = selecaoService.find(QueryType.JPQL,

				"from Selecao as p where p.tipoSelecao = :tipo and p.ano = :ano and p.sequencial = :sequencial",	map);
		if (selecoes == null || selecoes.isEmpty()) {
			return false;
		}
		return true;


	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Selecao> getSelecoesComMembros() {
		Map<String, Object> map = new HashMap<String, Object>();

		return ((List<Selecao>) selecaoService.find("Selecao.findSelecoesComMembros", map));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getUltimoSequencialPorAno(Selecao selecao) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tipoSelecao", selecao.getTipoSelecao());
		map.put("ano",selecao.getAno());
		List<Selecao> listSequencial = selecaoService.find(QueryType.JPQL,
				"select s from Selecao as s where s.tipoSelecao = :tipoSelecao and s.ano = :ano and s.sequencial = max(sequencial)",
			map);
		
		if (listSequencial == null || listSequencial.get(0) == null) {
			return 1;
		} else {
			return listSequencial.get(0).getSequencial() + 1;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecoes(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		return selecaoService.find(QueryType.JPQL,"select s from Selecao as s", 
				map);
	}
	
	@Override
	public Selecao getSelecaoPorId(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idSelecao", id);
		return (Selecao) selecaoService.findFirst(QueryType.JPQL,"select s from Selecao as s where s.id = :idSelecao", 
				map,-1);	
	}
	

	@Override
	public void save(Selecao selecao) {
		selecaoService.save(selecao);
		
	}


	@Override
	public void update(Selecao selecao) {
		selecaoService.update(selecao);
		
	}


	@Override
	public void delete(Selecao selecao) {
		selecaoService.delete(selecao);
		
	}
	
}
