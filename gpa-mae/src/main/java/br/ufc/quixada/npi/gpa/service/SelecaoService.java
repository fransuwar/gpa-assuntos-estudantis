package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoService extends GenericService<Selecao> {

	public abstract boolean SelecaoEstaCadastrada(Selecao selecao);

	public abstract List<Selecao> getSelecoesComMembros();

	public abstract Integer getUltimoSequencialPorAno(Selecao selecao);	
}
