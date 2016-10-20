package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.TipoDocumento;

public interface DocumentacaoService {
	
	// Tipos de Documentos
	List<TipoDocumento> getAllTipoDocumento();
	
	TipoDocumento getTipoDocumentoById(Integer id);
	
	void excluirTipoDocumento(Integer id);
	
	void salvar(TipoDocumento tipoDocumento);

}
