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


import static br.ufc.quixada.npi.gpa.utils.Constants.ANO;

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
		map.put(ANO, selecao.getAno());
		map.put("sequencial", selecao.getSequencial());
		List<Selecao> selecoes = selecaoService.find(QueryType.JPQL,
				"from Selecao as p where p.tipoSelecao = :tipo and p.ano = :ano and p.sequencial = :sequencial",
				map);

		return !(selecoes == null || selecoes.isEmpty());
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
	

	
}
