package br.ufc.quixada.npi.gpa.service;

import java.util.List;
import java.util.Map;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Selecao;

public interface SelecaoService {

	public abstract boolean isSelecaoCadastrada(Selecao selecao);

	public abstract List<Selecao> getSelecoesComMembros();
	
	public abstract Integer getUltimoSequencialPorAno(Selecao selecao);
	
	@SuppressWarnings("rawtypes")
	public abstract List find(QueryType type,String consulta,Map<String, Object> parametros);
	
	@SuppressWarnings("rawtypes")
	public abstract List find(String consulta, Map<String, Object> parametros);
	
	public abstract List<Selecao> find(Class<Selecao> classe);
	
	public abstract Selecao find(Class<Selecao> classe, Integer id);
	
    public abstract void save(Selecao selecao);
	
	public abstract void update(Selecao selecao);
	
	public abstract void delete(Selecao selecao);

	
}
