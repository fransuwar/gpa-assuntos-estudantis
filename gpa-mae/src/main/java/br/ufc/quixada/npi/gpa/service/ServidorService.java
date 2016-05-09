package br.ufc.quixada.npi.gpa.service;

import java.util.List;
import java.util.Map;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Servidor;

public interface ServidorService{
	
	public abstract Servidor getServidor(String siape);
	
	public abstract Servidor getServidorByCpf(String cpf);
	
	public abstract Servidor getServidorComComissao(String CPF);

	public abstract List<Servidor> getServidorPertenceBanca(Integer idServidor, Integer idSelecao);	
	
    public abstract void save(Servidor servidor);
	
	public abstract void update(Servidor servidor);
	
	public abstract void delete(Servidor servidor);
	
	@SuppressWarnings("rawtypes")
	public abstract List find(QueryType type,String consulta,Map<String, Object> parametros);
	
	public abstract Servidor find(Class<Servidor> classe, Integer id);
	
	public abstract Object findFirst(String consulta, Map<String, Object> parametros);
	
	public abstract List<Servidor> find(Class<Servidor> classe);

}
