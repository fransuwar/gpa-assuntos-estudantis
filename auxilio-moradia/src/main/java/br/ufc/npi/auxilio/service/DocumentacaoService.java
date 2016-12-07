package br.ufc.npi.auxilio.service;

import java.util.List;

import br.ufc.npi.auxilio.model.TipoDocumento;

public interface DocumentacaoService {
	
	// Tipos de Documentos
	List<TipoDocumento> getAllTipoDocumento();
	
	TipoDocumento getTipoDocumentoById(Integer id);
	
	void excluirTipoDocumento(Integer id);
	
	void salvar(TipoDocumento tipoDocumento);

}
