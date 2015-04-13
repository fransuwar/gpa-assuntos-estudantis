package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.service.GenericService;

public interface DocumentoService extends GenericService<Documento>{
	
	void salvar(Documento documento);
	
	void salvar(List<Documento> documentos);
	
	Documento getDocumentoById(Long id);
	
	void remover(Documento documento);
}
