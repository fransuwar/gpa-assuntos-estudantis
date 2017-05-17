package br.ufc.npi.auxilio.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Documento;
import br.ufc.npi.auxilio.model.DocumentoDownload;
import br.ufc.npi.auxilio.model.VisitaDomiciliar;

public interface VisitaService {
	
	void adicionarFormulario(VisitaDomiciliar visitaDomiciliar, MultipartFile file) throws IOException, AuxilioMoradiaException;
	
	void adicionarImagens(VisitaDomiciliar visitaDomiciliar, MultipartFile file) throws IOException, AuxilioMoradiaException;
	
	DocumentoDownload downloadDocumento(Documento documento, String procedimento);

}
