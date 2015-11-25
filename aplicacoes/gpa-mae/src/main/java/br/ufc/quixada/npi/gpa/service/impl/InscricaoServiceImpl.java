package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;

@Named
public class InscricaoServiceImpl extends GenericServiceImpl<Inscricao> implements InscricaoService{
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Inscricao> listarInscricoesByIdAluno(Integer id) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return find(QueryType.JPQL,"from Inscricao where aluno.id = :id",params);
	}

	

}
