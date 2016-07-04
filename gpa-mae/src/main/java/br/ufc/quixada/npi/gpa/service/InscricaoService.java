package br.ufc.quixada.npi.gpa.service;


import java.util.List;

import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;

public interface InscricaoService{

	
	public abstract void update(Integer idInscricao,boolean classificado);
	
	public abstract void consolidacaoDeTodos(Integer idSelecao,boolean consolidacao);
	
	public abstract void consolidar(Integer idInscricao,boolean consolidacao);
	
	public abstract List<HorarioDisponivel> getHorariosDisponiveisIniciacaoAcademica(Integer idIniciacaoAcademica);

	public abstract ComQuemMora getComQuemMora(GrauParentesco comQuemMora);	

	public abstract PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa);

}

