package br.ufc.quixada.npi.gpa.service;

import java.util.List;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;

public interface InscricaoService extends GenericService<Inscricao>{
	
	public abstract Inscricao getInscricaoId(Integer idInscricao);
	
	public abstract List<HorarioDisponivel> getHorariosDisponiveisByQuest(Integer idQuest);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdQuestBIA(Integer idQuest);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdQuestAMOR(Integer idQuest);
	
	public abstract QuestionarioAuxilioMoradia getQuestAuxMorById(Integer idQuest);
	
	public abstract QuestionarioIniciacaoAcademica getQuestIniAcadById(Integer idQuest);
	
	public abstract void saveEntrevista(Entrevista entrevista);
}
