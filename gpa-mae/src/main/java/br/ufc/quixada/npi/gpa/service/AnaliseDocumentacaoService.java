package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.AnaliseDocumentacao;

public interface AnaliseDocumentacaoService {
	
	public abstract void salvarAnaliseDocumentacao(AnaliseDocumentacao documentacao);
	
	public abstract void atualizarAnaliseDocumentacao(AnaliseDocumentacao documentacao);
	
	public abstract void deletarAnaliseDocumentacao(AnaliseDocumentacao documentacao);

}
