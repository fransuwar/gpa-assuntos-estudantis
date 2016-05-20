package br.ufc.quixada.npi.gpa.service;
import java.util.List;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;

public interface DocumentoService{
	
	public abstract void salvarDocumento(Documento documento);
	
	public abstract void atualizarDocumento(Documento documento);
	
	public abstract void deletarDocumento(Documento documento);
	
	public abstract void salvarTipoDocumento(TipoDocumento tipoDocumento);
	
	public abstract void deletarTipoDocumento(TipoDocumento tipoDocumento);
	
	public abstract Documento getDocumentoPorId(Integer id);
	
	public TipoDocumento findById(Integer idTipoDocumento);
	
	public List<TipoDocumento>  find();

}
