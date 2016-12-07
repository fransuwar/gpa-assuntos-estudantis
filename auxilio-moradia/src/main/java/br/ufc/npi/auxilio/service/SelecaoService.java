package br.ufc.npi.auxilio.service;

import java.util.List;

import br.ufc.npi.auxilio.model.Selecao;

public interface SelecaoService {
	
	List<Selecao> getAll();
	
	List<Selecao> getByMembroComissao(String cpf);
	
	void cadastrar(Selecao selecao);
	
	void atualizar(Selecao selecao);
	
	void excluir(Selecao selecao);
	
	Selecao getById(Integer id);

	
}
