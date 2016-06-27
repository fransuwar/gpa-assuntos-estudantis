package br.ufc.quixada.npi.gpa.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.repository.GenericRepository;

@Named
public class DocumentoServiceImpl implements DocumentoService{
	
	@Inject
	private GenericRepository<Documento> documentoRepository; 
	
	@Inject
	private GenericRepository<TipoDocumento> tipoDocumentacaoRepository;

	@Override
	public void salvarDocumento(Documento documento) {
		documentoRepository.save(documento);
		
	}

	@Override
	public void atualizarDocumento(Documento documento) {
		documentoRepository.update(documento);
		
	}

	@Override
	public void deletarDocumento(Documento documento) {
		documentoRepository.delete(documento);
		
	}

	@Override
	public Documento getDocumentoPorId(Integer idDocumento) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idDocumento", idDocumento);
		return (Documento) documentoRepository.findFirst(QueryType.JPQL,"select d from Documento as d where d.id = :idDocumento", map, -1);
	}
	
	@Override
	public void salvarTipoDocumento(TipoDocumento tipoDocumento) {
		tipoDocumentacaoRepository.save(tipoDocumento);
		
	}

	@Override
	public void deletarTipoDocumento(TipoDocumento tipoDocumento) {
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
