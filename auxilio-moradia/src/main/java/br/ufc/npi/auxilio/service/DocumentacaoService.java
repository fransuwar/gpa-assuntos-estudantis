package br.ufc.npi.auxilio.service;

import java.util.List;

import br.ufc.npi.auxilio.model.TipoDocumento;

public interface DocumentacaoService {
	
	List<TipoDocumento> getAllTipoDocumento();
	
	void salvar(TipoDocumento tipoDocumento);

}
