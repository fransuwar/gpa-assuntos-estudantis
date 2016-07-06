package br.ufc.quixada.npi.gpa.service;


import br.ufc.quixada.npi.gpa.model.PessoaFamilia;

public interface InscricaoService{
	
	public abstract PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa);

}

