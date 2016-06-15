package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Servidor;

public interface ServidorService{
	
	public abstract Servidor getServidor(String siape);
	
	public abstract Servidor getServidorPorCpf(String cpf);
	
	public abstract Servidor getServidorComComissao(String CPF);

	public abstract List<Servidor> getServidorPertenceBanca(Integer idServidor, Integer idSelecao);	
	
    public abstract void save(Servidor servidor);
	
	public abstract void update(Servidor servidor);
	
	public abstract void delete(Servidor servidor);
	
	public abstract Servidor getServidorPorId(Integer id);
	
	public abstract List<Servidor> listarServidores();

}
