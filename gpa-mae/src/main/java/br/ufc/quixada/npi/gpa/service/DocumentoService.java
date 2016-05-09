package br.ufc.quixada.npi.gpa.service;


import java.util.List;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.service.GenericService;

public interface DocumentoService extends GenericService<Documento>{
	
	public void save(TipoDocumento tipoDocumento);
	public void delete(TipoDocumento tipoDocumento);
	public TipoDocumento findById(Integer idTipoDocumento);
	public List<TipoDocumento>  find();
}
