package br.ufc.quixada.npi.gpa.service;

import java.util.List;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;

import br.ufc.quixada.npi.service.GenericService;

public interface InscricaoService extends GenericService<Inscricao>{
	
	public abstract List<HorarioDisponivel> getHorariosDisponiveisIniciacaoAcademica(Integer idIniciacaoAcademica);

	public abstract List<Inscricao> getInscricoes(Integer idAluno); 
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdIniciacaoAcademica(Integer idIniciacaoAcademica);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdAuxilioMoradia(Integer idAuxilioMoradia);
	
	public abstract VisitaDomiciliar getVisitaDocimiciliar(Integer idVisitaDomiciliar);
	
	public abstract void salvarVisitaDocimiciliar(VisitaDomiciliar visitaDocimiciliar);
	
	public abstract void atualizarVisitaDomiciliar(VisitaDomiciliar visitaDocimiciliar);

	public abstract void salvarEntrevista(Entrevista entrevista);
}

