package br.ufc.quixada.npi.gpa.excecoes;

import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_RUNTIME_EXCEPTION_DOCUMENTO; 

public class DocumentoNaoEncontradoRuntimeException extends RuntimeException{

	public DocumentoNaoEncontradoRuntimeException(){
		super(MENSAGEM_RUNTIME_EXCEPTION_DOCUMENTO);
	}
	
}
