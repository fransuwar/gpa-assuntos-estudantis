package br.ufc.npi.auxilio.controller;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.ufc.npi.auxilio.utils.RedirectConstants;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	@ExceptionHandler( FileSizeLimitExceededException.class )
	public String excessao() {
		return RedirectConstants.REDIRECT_CADASTRAR_SELECAO;
	}
	
	@ExceptionHandler( MultipartException.class )
	public String arquivoTamanhoExcedido( MultipartException e ) {
		if( e.getRootCause() instanceof FileSizeLimitExceededException ) {
			return RedirectConstants.REDIRECT_ERROR_TAMANHO_ARQUIVO_EXCEDIDO;
		}
		return RedirectConstants.REDIRECT_ERROR;
	}
}
