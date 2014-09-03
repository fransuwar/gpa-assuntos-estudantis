package br.ufc.quixada.npi.service;

import java.util.List;


import br.ufc.quixada.npi.model.Selecao;

public interface SelecaoService extends GenericService<Selecao> {
	
	public abstract List<Selecao> getSelecao();

}
