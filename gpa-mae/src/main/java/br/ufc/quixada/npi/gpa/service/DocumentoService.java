package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Documento;

public interface DocumentoService{
	
	public abstract void save(Documento documento);
	
	public abstract void update(Documento documento);
	
	public abstract void delete(Documento documento);
	
	public abstract Documento getDocumentoPorId(Integer idDocumento);

}
