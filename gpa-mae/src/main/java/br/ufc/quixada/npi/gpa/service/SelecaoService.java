package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Selecao;

public interface SelecaoService {
	
	List<Selecao> getByMembroComissao(String cpf);
	
	void cadastrar(Selecao selecao);
	
	Selecao getById(Integer id);

	
}
