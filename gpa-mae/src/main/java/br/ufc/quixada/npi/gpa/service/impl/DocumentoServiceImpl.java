package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;

@Named
public class DocumentoServiceImpl extends GenericServiceImpl<Documento> implements DocumentoService{
	
	@Inject
	private GenericRepository<TipoDocumento> tipoDocumentacaoRepository;
	
	
	@Override
	public void save(TipoDocumento tipoDocumento) {
		tipoDocumentacaoRepository.save(tipoDocumento);
		
	}

	@Override
	public void delete(TipoDocumento tipoDocumento) {
		tipoDocumentacaoRepository.delete(tipoDocumento);
		
	}

	@Override
	public TipoDocumento findById(Integer idTipoDocumento) {
		return tipoDocumentacaoRepository.find(TipoDocumento.class, idTipoDocumento);
	}

	@Override
	public List<TipoDocumento> find() {
		return tipoDocumentacaoRepository.find(TipoDocumento.class);
	}

}
