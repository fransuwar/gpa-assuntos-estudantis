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
	public List<Selecao> getSelecoesSubmetidos() {

		return find(QueryType.JPQL, "from Selecao as p where p.status != 'NOVO' ", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecoesAtribuidos() {
		return find(QueryType.JPQL, "from Selecao as p where p.status = 'AGUARDANDO_PARECER' ", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecoesByUsuario(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return find(QueryType.JPQL, "from Selecao where usuario.id = :id", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Selecao> getSelecoesAguardandoParecer() {
		return find(QueryType.JPQL, "from Selecao as p where p.status = 'AGUARDANDO_PARECER'", null);
	}

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

	@Override
	@Transactional
	public Selecao getSelecaoComDocumentos(Integer id) {
		return (Selecao) findFirst("Selecao.findSelecaoComDocumentos",
				new SimpleMap<String, Object>("selecaoId", id));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Selecao> getSelecoesComMembros() {
		return ((List<Selecao>) find("Selecao.findSelecoesComMembros", new SimpleMap<String, Object>()));
	}

	@Override
	@Transactional
	public Selecao getSelecaoComMembros(Integer id) {
		return (Selecao) findFirst("Selecao.findSelecaoIdComMembros",
				new SimpleMap<String, Object>("selecaoId", id));
	}

	@Override
	@Transactional(readOnly = true)
	public Selecao getSelecaoComAlunos(Integer id) {
		return (Selecao) findFirst("Selecao.findSelecaoIdComAlunos",
				new SimpleMap<String, Object>("selecaoId", id));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Selecao> getSelecoesComAlunos() {
		return ((List<Selecao>) find("Selecao.findSelecaoComAlunos", new SimpleMap<String, Object>()));

	}

}
