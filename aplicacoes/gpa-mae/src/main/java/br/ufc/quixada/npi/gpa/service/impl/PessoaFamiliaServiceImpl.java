package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Named;


import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.service.PessoaFamiliaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@SuppressWarnings("unchecked")
@Named
public class PessoaFamiliaServiceImpl extends GenericServiceImpl<PessoaFamilia> implements PessoaFamiliaService{

	
	@Override
	public List<PessoaFamilia> getPessoaFamiliaByIdQuestBIA(Integer idQuest) {
		return find("PessoaFamilia.findPessoaFamiliaByIdQuestBIA", 
					new SimpleMap<String, Object>("idQuest", idQuest));
	}

	@Override
	public List<PessoaFamilia> getPessoaFamiliaByIdQuestAMOR(Integer idQuest) {
		return find("PessoaFamilia.findPessoaFamiliaByIdQuestAMOR", 
				new SimpleMap<String, Object>("idQuest", idQuest));
	}

}
