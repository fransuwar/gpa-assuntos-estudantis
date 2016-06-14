package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.gpa.service.AnaliseDocumentacaoService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.gpa.model.AnaliseDocumentacao;

@Named
public class AnaliseDocumentacaoServiceImpl implements AnaliseDocumentacaoService{
	
	@Inject
	private GenericRepository<AnaliseDocumentacao> documentacaoRepository;

	@Override
	public void salvarAnaliseDocumentacao(AnaliseDocumentacao documentacao) {
		documentacaoRepository.save(documentacao);
		
	}

	@Override
	public void atualizarAnaliseDocumentacao(AnaliseDocumentacao documentacao) {
		documentacaoRepository.update(documentacao);
		
	}

	@Override
	public void deletarAnaliseDocumentacao(AnaliseDocumentacao documentacao) {
		documentacaoRepository.delete(documentacao);
		
	}

}
