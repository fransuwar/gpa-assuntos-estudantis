package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class SelecaoServiceImpl extends GenericServiceImpl<Selecao> implements SelecaoService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecaoBolsasSubmetidos() {

		return find(QueryType.JPQL, "from Selecao as p where p.status != 'NOVO' ", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecaoBolsasAtribuidos() {
		return find(QueryType.JPQL, "from Selecao as p where p.status = 'AGUARDANDO_PARECER' ", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecaoBolsasByUsuario(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return find(QueryType.JPQL, "from Selecao where usuario.id = :id", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecaoBolsasAguardandoParecer() {
		return find(QueryType.JPQL, "from Selecao as p where p.status = 'AGUARDANDO_PARECER'", null);
	}

	@Override
	@Transactional
	public boolean existsSelecaoEquals(Selecao selecaoBolsa) {
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
	public Selecao getSelecaoBolsaComDocumentos(Integer id) {
		return (Selecao) findFirst("Selecao.findSelecaoBolsaComDocumentos",
				new SimpleMap<String, Object>("selecaoBolsaId", id));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Selecao> getSelecaoBolsaComMembros() {
		return ((List<Selecao>) find("Selecao.findSelecaoBolsaComMembros", new SimpleMap<String, Object>()));
	}

	@Override
	@Transactional
	public Selecao getSelecaoBolsaComMembros(Integer id) {
		return (Selecao) findFirst("Selecao.findSelecaoBolsaIdComMembros",
				new SimpleMap<String, Object>("selecaoBolsaId", id));
	}

	@Override
	@Transactional(readOnly = true)
	public Selecao getSelecaoBolsaComAlunos(Integer id) {
		return (Selecao) findFirst("Selecao.findSelecaoBolsaIdComAlunos",
				new SimpleMap<String, Object>("selecaoBolsaId", id));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Selecao> getSelecaoComAlunos() {
		return ((List<Selecao>) find("Selecao.findSelecaoComAlunos", new SimpleMap<String, Object>()));

	}

}
