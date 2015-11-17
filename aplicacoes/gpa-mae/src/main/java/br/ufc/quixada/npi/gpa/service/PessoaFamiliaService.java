package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.PessoaFamilia;

public interface PessoaFamiliaService extends GenericService<PessoaFamilia> {

	public abstract List<PessoaFamilia> getPessoaFamiliaByIdQuestBIA(Integer idQuest);
	
	public abstract List<PessoaFamilia> getPessoaFamiliaByIdQuestAMOR(Integer idQuest);
}
