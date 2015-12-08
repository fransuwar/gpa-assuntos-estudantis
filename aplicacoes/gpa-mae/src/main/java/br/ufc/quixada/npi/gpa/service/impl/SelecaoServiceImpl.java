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
	public boolean isSelecaoCadastrada(Selecao selecaoBolsa) {
		@SuppressWarnings("unchecked")
		List<Selecao> selecoes = find(QueryType.JPQL,
				"from Selecao as p where p.tipoBolsa = :tipo and p.ano = :ano and p.sequencial = :sequencial",
				new SimpleMap<String, Object>("tipo", selecaoBolsa.getTipoBolsa(), "ano", selecaoBolsa.getAno(),
						"sequencial", selecaoBolsa.getSequencial()));
		if (selecoes == null || selecoes.isEmpty()) {
			return false;
		}
		return true;

	}

	@Override
	@Transactional
	public Selecao getSelecaoBolsaComDocumentos(Integer IdSelecao) {
		return (Selecao) findFirst("Selecao.findSelecaoBolsaComDocumentosByIdSelecao", new SimpleMap<String, Object>("IdSelecao", IdSelecao));
	}

}
