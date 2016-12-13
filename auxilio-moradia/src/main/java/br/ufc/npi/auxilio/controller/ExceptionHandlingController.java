package br.ufc.npi.auxilio.controller;

import javax.servlet.http.HttpServletRequest;

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
	public String arquivoTamanhoExcedido( HttpServletRequest request, MultipartException e ) {
		if( e.getRootCause() instanceof FileSizeLimitExceededException ) {
			if( request.getRequestURL().toString().contains("/selecao/adicionar-documento") ) {
				String[] url = request.getRequestURL().toString().split("/");
				Integer idSelecao = Integer.valueOf( url[url.length-1] );
				return RedirectConstants.REDIRECT_ERROR_TAMANHO_ARQUIVO_EXCEDIDO + idSelecao;
			}
			if( request.getRequestURL().toString().contains("/documentacao") ) {
				String[] url = request.getRequestURL().toString().split("/");
				Integer idInscricao = Integer.valueOf( url[url.length-1] );
				return RedirectConstants.REDIRECT_ERROR_TAMANHO_ARQUIVO_EXCEDIDO_INSCRICAO + idInscricao;
			}
		}
		return RedirectConstants.REDIRECT_ERROR;
	}
}
