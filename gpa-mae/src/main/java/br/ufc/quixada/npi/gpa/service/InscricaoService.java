package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.enums.MoraCom;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;

public interface InscricaoService{
	
	
	public abstract List<HorarioDisponivel> getHorariosDisponiveisIniciacaoAcademica(Integer idIniciacaoAcademica);

	public abstract List<Inscricao> getInscricoes(Integer idAluno);
	
	public abstract List<Inscricao> getInscricoesPorSelecao(Integer idSelecao);
	
	public abstract List<Inscricao> getInscricoesPorSelecaoPorAluno(Integer idSelecao, Integer idAluno);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaPorIdIniciacaoAcademica(Integer idIniciacaoAcademica);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaPorIdAuxilioMoradia(Integer idAuxilioMoradia);
	
	public abstract VisitaDomiciliar getVisitaDocimiciliar(Integer idVisitaDomiciliar);
	
	public abstract void salvarVisitaDocimiciliar(VisitaDomiciliar visitaDocimiciliar);
	
	public abstract void atualizarVisitaDomiciliar(VisitaDomiciliar visitaDocimiciliar);

	public abstract ComQuemMora getComQuemMora(MoraCom comQuemMora);

	public abstract Inscricao getInscricao(Selecao selecao, Aluno aluno);
	
	public abstract Inscricao getInscricaoPorId(Integer id);
	
	public abstract void save(Inscricao inscricao);
	
	public abstract void update(Inscricao inscricao);
	
	public abstract void delete(Inscricao inscricao);
	
	public abstract void excluirPessoaFamiliaPorId(Integer idPessoa);

	public abstract List<Inscricao> getDeferidosBySelecao(Selecao selecao);
	
	public abstract List<Inscricao> getClassificadosPorSelecao(Selecao selecao);
	
	public abstract List<Inscricao> getClassificaveisPorSelecao(Selecao selecao);
	
	public abstract void update(Integer idInscricao,boolean classificado);
	
	public abstract void consolidacaoDeTodos(Integer idSelecao,boolean consolidacao);
	
	public abstract void consolidar(Integer idInscricao,boolean consolidacao);	


}

