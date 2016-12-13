package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;

public interface InscricaoService {
	
	boolean estaInscrito(Pessoa pessoa, Selecao selecao);
	
	Inscricao salvar(Selecao selecao, Aluno aluno);
	
	Inscricao salvar(Inscricao inscricao);

}

