package br.ufc.quixada.npi.gpa.service.impl;


import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@SuppressWarnings("unchecked")
@Named

public class InscricaoServiceImpl extends GenericServiceImpl<Inscricao> implements InscricaoService {

	private GenericServiceImpl<Entrevista>  entrevistaService;
	
	@Override
	@Transactional(readOnly = true)
	public Inscricao getInscricaoId(Integer idInscricao) {
		return (Inscricao) findFirst("Incricao.findIncricaoId", new SimpleMap<String, Object>("idInscricao", idInscricao));
	}

	@Override
	@Transactional(readOnly = true)
	public List<HorarioDisponivel> getHorariosDisponiveisByQuest(Integer idQuest) {
		return find("HorarioDisponivel.findHorarioDisponivelByIdQuest",
				new SimpleMap<String, Object>("IdQuest", idQuest));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaByIdQuestBIA(Integer idQuest) {
		return find("PessoaFamilia.findPessoaFamiliaByIdQuestBIA", 
					new SimpleMap<String, Object>("idQuest", idQuest));
	}

	@Override
	public void saveEntrevista(Entrevista entrevista) {
			entrevistaService.save(entrevista);		
	}


	
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaByIdQuestAMOR(Integer idQuest) {
		return find("PessoaFamilia.findPessoaFamiliaByIdQuestAMOR", 
				new SimpleMap<String, Object>("idQuest", idQuest));
	}

	@Override
	public QuestionarioAuxilioMoradia getQuestAuxMorById(Integer idQuest) {
		return (QuestionarioAuxilioMoradia) findFirst("AuxMor.findAuxMorById", new SimpleMap<String, Object>("idQuest", idQuest));
	}

	@Override
	public QuestionarioIniciacaoAcademica getQuestIniAcadById(Integer idQuest) {
		return (QuestionarioIniciacaoAcademica) findFirst("IniAcad.findIniAcadById", new SimpleMap<String, Object>("idQuest", idQuest));
	}
}
