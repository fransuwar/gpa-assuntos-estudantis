package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;

public interface InscricaoService {
	
	boolean estaInscrito(Pessoa pessoa, Selecao selecao);
	
	void salvar(Selecao selecao, Aluno aluno) throws AuxilioMoradiaException;

}

