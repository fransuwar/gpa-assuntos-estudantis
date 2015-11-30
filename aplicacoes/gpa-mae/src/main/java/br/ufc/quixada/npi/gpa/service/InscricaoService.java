package br.ufc.quixada.npi.gpa.service;


import java.util.List;

import br.ufc.quixada.npi.gpa.model.Inscricao;

public interface InscricaoService extends GenericService<Inscricao>{
	
	public List<Inscricao> listarInscricoesByIdAluno(Integer id);
	
}

