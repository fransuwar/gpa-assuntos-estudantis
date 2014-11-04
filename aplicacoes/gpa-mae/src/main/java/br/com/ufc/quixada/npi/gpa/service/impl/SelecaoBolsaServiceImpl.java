package br.com.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.com.ufc.quixada.npi.gpa.repository.QueryType;
import br.com.ufc.quixada.npi.gpa.repository.SelecaoBolsaRepository;
import br.com.ufc.quixada.npi.gpa.service.SelecaoBolsaService;

@Named
public class SelecaoBolsaServiceImpl extends GenericServiceImpl<SelecaoBolsa> implements
		SelecaoBolsaService {

	@Inject
	private SelecaoBolsaRepository selecaoBolsaRepository;

	@Override
	public List<SelecaoBolsa> getSelecaoBolsasSubmetidos() {
		return selecaoBolsaRepository.find(QueryType.JPQL, "from SelecaoBolsa as p where p.status != 'NOVO' ", null);
	}
	
	@Override
	public List<SelecaoBolsa> getSelecaoBolsasAtribuidos() {
		return selecaoBolsaRepository.find(QueryType.JPQL, "from SelecaoBolsa as p where p.status = 'AGUARDANDO_PARECER' ", null);
	}

	@Override
	public List<SelecaoBolsa> getSelecaoBolsasByUsuario(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return selecaoBolsaRepository.find(QueryType.JPQL, "from SelecaoBolsa where usuario_id = :id", params);
	}
	
	@Override
	public List<SelecaoBolsa> getSelecaoBolsasAguardandoParecer() {
		return selecaoBolsaRepository.find(QueryType.JPQL, "from SelecaoBolsa as p where p.status = 'AGUARDANDO_PARECER'", null);
	}

	@Override
	@Transactional
	public boolean existsSelecaoEquals(SelecaoBolsa selecaoBolsa) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tipo", selecaoBolsa.getTipoBolsa());
		params.put("ano", selecaoBolsa.getAno());
		params.put("sequencial", selecaoBolsa.getSequencial());
		List<SelecaoBolsa> selecoes = selecaoBolsaRepository.find(QueryType.JPQL, "from SelecaoBolsa as p where p.tipoBolsa = :tipo and p.ano = :ano and p.sequencial = :sequencial", params);
		if(selecoes == null || selecoes.isEmpty()){
			return false;
		}
		return true;
	}


}
