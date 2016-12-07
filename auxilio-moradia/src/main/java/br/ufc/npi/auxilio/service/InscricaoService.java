package br.ufc.npi.auxilio.service;


import br.ufc.npi.auxilio.model.PessoaFamilia;

public interface InscricaoService{
	
	public abstract PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa);

}

