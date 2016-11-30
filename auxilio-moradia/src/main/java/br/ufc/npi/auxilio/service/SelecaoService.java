package br.ufc.npi.auxilio.service;

import java.util.List;

import br.ufc.npi.auxilio.model.Selecao;

public interface SelecaoService {
	
	List<Selecao> getAll();
	
	void salvar(Selecao selecao);

}
