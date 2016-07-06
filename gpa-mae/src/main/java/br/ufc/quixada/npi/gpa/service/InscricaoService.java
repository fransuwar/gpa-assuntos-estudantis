package br.ufc.quixada.npi.gpa.service;


import br.ufc.quixada.npi.gpa.model.PessoaFamilia;

public interface InscricaoService{

	
	public abstract void update(Integer idInscricao,boolean classificado);
	
	public abstract PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa);

}

