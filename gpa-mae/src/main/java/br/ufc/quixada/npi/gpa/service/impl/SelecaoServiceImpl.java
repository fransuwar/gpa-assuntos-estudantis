package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class SelecaoServiceImpl implements SelecaoService {
	
	@Inject
	private GenericRepository<Selecao> selecaoService;
	

	@Override
	@Transactional
	public boolean SelecaoEstaCadastrada(Selecao selecao) {
		@SuppressWarnings("unchecked")
		List<Selecao> selecoes = selecaoService.find(QueryType.JPQL,
				"from Selecao as p where p.tipoSelecao = :tipo and p.ano = :ano and p.sequencial = :sequencial",
				new SimpleMap<String, Object>("tipo", selecao.getTipoSelecao(), "ano", selecao.getAno(),
						"sequencial", selecao.getSequencial()));
		if (selecoes == null || selecoes.isEmpty()) {
			return false;
		}
		return true;

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Selecao> getSelecoesComMembros() {
		return ((List<Selecao>) selecaoService.find("Selecao.findSelecoesComMembros", new SimpleMap<String, Object>()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getUltimoSequencialPorAno(Selecao selecao) {
		List<Integer> listSequencial = selecaoService.find(QueryType.JPQL,"select max(s.sequencial)from Selecao as s where s.tipoSelecao = :tipoSelecao and s.ano = :ano",
				new SimpleMap<String,Object>("tipoSelecao", selecao.getTipoSelecao(), "ano",selecao.getAno()));
		
		if (listSequencial == null || listSequencial.get(0) == null) {
			return 1;
		}else{
		return listSequencial.get(0)+1;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecoes(){
		return selecaoService.find(QueryType.JPQL,"select s from Selecao as s", 
				new SimpleMap<String, Object>());
	}
	
	@Override
	public Selecao getSelecaoPorId(Integer id) {
		return (Selecao) selecaoService.findFirst(QueryType.JPQL,"select s from Selecao as s where s.id = :idSelecao", 
				new SimpleMap<String, Object>("idSelecao", id));	
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
