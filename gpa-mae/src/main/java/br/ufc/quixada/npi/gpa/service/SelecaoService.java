package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Selecao;

public interface SelecaoService {

	public abstract boolean isSelecaoCadastrada(Selecao selecao);

	public abstract List<Selecao> getSelecoesComMembros();
	
	public abstract Integer getUltimoSequencialPorAno(Selecao selecao);
	
	public abstract List<Selecao> getSelecoes();
	
	public abstract Selecao getSelecaoPorId(Integer id);
	
    public abstract void save(Selecao selecao);
	
	public abstract void update(Selecao selecao);
	
	public abstract void delete(Selecao selecao);
	
}
