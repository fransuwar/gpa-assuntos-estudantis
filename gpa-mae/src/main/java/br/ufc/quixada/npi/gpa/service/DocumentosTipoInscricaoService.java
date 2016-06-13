package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.DocumentosTipoInscricao;

public interface DocumentosTipoInscricaoService {

	public abstract void salvarDocumentosTipoInscricao(DocumentosTipoInscricao documento);
	
	public abstract void atualizarDocumentosTipoInscricao(DocumentosTipoInscricao documento);
	
	public abstract void deletarDocumentosTipoInscricao(DocumentosTipoInscricao documento);
	
}
