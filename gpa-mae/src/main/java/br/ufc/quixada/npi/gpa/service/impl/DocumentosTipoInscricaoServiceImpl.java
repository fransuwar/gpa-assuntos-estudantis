package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.DocumentosTipoInscricao;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.service.DocumentosTipoInscricaoService;
import br.ufc.quixada.npi.repository.GenericRepository;

@Named
public class DocumentosTipoInscricaoServiceImpl implements DocumentosTipoInscricaoService {
	
	@Inject
	private GenericRepository<DocumentosTipoInscricao> dtiRepository; 
	
	@Override
	public void salvarDocumentosTipoInscricao(DocumentosTipoInscricao documento) {
		dtiRepository.save(documento);
		
	}

	@Override
	public void atualizarDocumentosTipoInscricao(DocumentosTipoInscricao documento) {
		dtiRepository.update(documento);
		
	}

	@Override
	public void deletarDocumentosTipoInscricao(DocumentosTipoInscricao documento) {
		dtiRepository.delete(documento);
		
	}

	
	
}
