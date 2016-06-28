package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Selecao;

public interface SelecaoService {

	public abstract boolean SelecaoEstaCadastrada(Selecao selecao);
	
	public abstract Integer getUltimoSequencialPorAno(Selecao selecao);
	
}
