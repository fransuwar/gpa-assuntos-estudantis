package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class DocumentoServiceImpl implements DocumentoService{
	
	@Inject
	private GenericRepository<Documento> documentoRepository; 

	@Override
	public void save(Documento documento) {
		documentoRepository.save(documento);
		
	}

	@Override
	public void update(Documento documento) {
		documentoRepository.update(documento);
		
	}

	@Override
	public void delete(Documento documento) {
		documentoRepository.delete(documento);
		
	}

	@Override
	public Documento getDocumentoPorId(Integer idDocumento) {
		return (Documento) documentoRepository.findFirst(QueryType.JPQL,"select d from Documento as d where d.id = :idDocumento", 
				new SimpleMap<String, Object>("idDocumento", idDocumento));
		
	}

}
