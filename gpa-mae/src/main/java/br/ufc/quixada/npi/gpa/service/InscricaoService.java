package br.ufc.quixada.npi.gpa.service;

import java.util.List;
import java.util.Map;

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
	
	public abstract List<Inscricao> getInscricoesBySelecao(Integer idSelecao);
	
	public abstract List<Inscricao> getInscricoesBySelecaoByAluno(Integer idSelecao, Integer idAluno);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdIniciacaoAcademica(Integer idIniciacaoAcademica);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdAuxilioMoradia(Integer idAuxilioMoradia);
	
	public abstract VisitaDomiciliar getVisitaDocimiciliar(Integer idVisitaDomiciliar);
	
	public abstract void salvarVisitaDocimiciliar(VisitaDomiciliar visitaDocimiciliar);
	
	public abstract void atualizarVisitaDomiciliar(VisitaDomiciliar visitaDocimiciliar);

	public abstract ComQuemMora getComQuemMora(MoraCom comQuemMora);

	public abstract Inscricao getInscricao(Selecao selecao, Aluno aluno);
	
	public abstract Inscricao find(Class<Inscricao> classe, Integer id);
	
	public abstract Object findFirst(String consulta, Map<String, Object> parametros);
	
	public abstract void save(Inscricao inscricao);
	
	public abstract void update(Inscricao inscricao);
	
	public abstract void delete(Inscricao inscricao);
	
}

