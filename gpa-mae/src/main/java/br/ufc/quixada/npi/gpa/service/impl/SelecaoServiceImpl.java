package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class SelecaoServiceImpl extends GenericServiceImpl<Selecao> implements SelecaoService {

	@Override
	@Transactional
	public boolean isSelecaoCadastrada(Selecao selecao) {
		@SuppressWarnings("unchecked")
		List<Selecao> selecoes = find(QueryType.JPQL,
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
		return ((List<Selecao>) find("Selecao.findSelecoesComMembros", new SimpleMap<String, Object>()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getUltimoSequencialPorAno(Selecao selecao) {
		List<Integer> listSequencial = find(QueryType.JPQL,"select max(s.sequencial)from Selecao as s where s.tipoSelecao = :tipoSelecao and s.ano = :ano",
				new SimpleMap<String,Object>("tipoSelecao", selecao.getTipoSelecao(), "ano",selecao.getAno()));
		
		if (listSequencial == null || listSequencial.get(0) == null) {
			return 1;
		}else{
		return listSequencial.get(0)+1;
		}
	}
}