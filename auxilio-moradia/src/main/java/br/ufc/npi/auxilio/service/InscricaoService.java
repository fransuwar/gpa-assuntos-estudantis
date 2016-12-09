package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;

public interface InscricaoService {
	
	boolean estaInscrito(Pessoa pessoa, Selecao selecao);
	

}

