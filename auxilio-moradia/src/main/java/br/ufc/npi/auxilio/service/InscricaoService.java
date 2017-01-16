package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.questionario.Identificacao;

public interface InscricaoService {
	
	boolean isInscrito(Aluno aluno, Selecao selecao);
	
	Inscricao salvar(Selecao selecao, Aluno aluno, Identificacao identificacao) throws AuxilioMoradiaException;

	Inscricao get(Aluno aluno, Selecao selecao);
	
	Inscricao salvar(Inscricao inscricao);

	Inscricao atualizar(Inscricao inscricao) throws AuxilioMoradiaException;

}

