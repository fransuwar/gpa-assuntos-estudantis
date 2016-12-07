package br.ufc.npi.auxilio.service;

import java.util.List;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Selecao;

public interface SelecaoService {
	
	List<Selecao> getAll();
	
	void cadastrar(Selecao selecao) throws AuxilioMoradiaException;
	
	void excluir(Selecao selecao) throws AuxilioMoradiaException;
	

}
