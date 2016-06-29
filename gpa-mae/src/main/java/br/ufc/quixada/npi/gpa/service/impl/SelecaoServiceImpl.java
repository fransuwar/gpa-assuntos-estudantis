package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.util.SimpleMap;

import static br.ufc.quixada.npi.gpa.utils.Constants.ANO;

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
				new SimpleMap<String, Object>("tipo", selecao.getTipoSelecao(), ANO, selecao.getAno(),
						"sequencial", selecao.getSequencial()));

		return !(selecoes == null || selecoes.isEmpty());
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Integer getUltimoSequencialPorAno(Selecao selecao) {
		List<Integer> listSequencial = selecaoService.find(QueryType.JPQL,"select max(s.sequencial)from Selecao as s where s.tipoSelecao = :tipoSelecao and s.ano = :ano",
				new SimpleMap<String,Object>("tipoSelecao", selecao.getTipoSelecao(), ANO,selecao.getAno()));
		
		if (listSequencial == null || listSequencial.get(0) == null) {
			return 1;
		}else{
		return listSequencial.get(0)+1;
		}
	}
	
}
