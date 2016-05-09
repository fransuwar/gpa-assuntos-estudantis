package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.repository.GenericRepository;

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
	public Documento find(Class<Documento> classe, Integer idDocumento) {
		return documentoRepository.find(classe,idDocumento);
	}

}
