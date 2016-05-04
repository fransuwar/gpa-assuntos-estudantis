package br.ufc.quixada.npi.gpa.service;


import java.util.List;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.service.GenericService;

public interface DocumentoService extends GenericService<Documento>{
	
	public void salvarTipoDocumento(TipoDocumento tipoDocumento);
	public void deletarTipoDocumento(TipoDocumento tipoDocumento);
	public TipoDocumento BuscarTipoDocumentoById(Integer idTipoDocumento);
	public List<TipoDocumento>  BuscarTipoDocumento();
}
