package br.ufc.quixada.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.PessoaFamilia;

public interface PessoaFamiliaService {

	public abstract void save(PessoaFamilia pessoaFamilia);

	public abstract void update(PessoaFamilia pessoaFamilia);

	public PessoaFamilia findById(int id);

	public abstract List<PessoaFamilia> findAll();

	public abstract void delete(PessoaFamilia pessoaFamilia);

}