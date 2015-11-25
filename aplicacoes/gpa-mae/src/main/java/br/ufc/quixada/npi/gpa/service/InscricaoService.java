package br.ufc.quixada.npi.gpa.service;

import java.util.List;
import java.util.Map;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Inscricao;

public interface InscricaoService extends GenericService<Inscricao>{
	
	public List<Inscricao> listarInscricoesByIdAluno(Integer id);
	

}
