package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;

public interface InscricaoService extends GenericService<Inscricao>{
	
	public abstract List<HorarioDisponivel> getHorariosDisponiveisIniciacaoAcademica(Integer idIniciacaoAcademica);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdIniciacaoAcademica(Integer idIniciacaoAcademica);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdAuxilioMoradia(Integer idAuxilioMoradia);

	public abstract void realizarInscricaoIniciacaoAcademica(Selecao selecao, Aluno aluno, QuestionarioIniciacaoAcademica iniciacaoAcademica);

	public abstract void atualizarInscricaoIniciacaoAcademica(QuestionarioIniciacaoAcademica iniciacaoAcademica);
	
}
