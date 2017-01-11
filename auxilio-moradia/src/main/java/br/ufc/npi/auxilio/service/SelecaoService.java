package br.ufc.npi.auxilio.service;

import java.io.IOException;
import java.util.List;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SelecaoService {
	
	List<Selecao> getAll();
	
	void cadastrar(Selecao selecao) throws AuxilioMoradiaException;

	void excluir(Selecao selecao) throws AuxilioMoradiaException;

	void adicionarDocumento(Selecao selecao, MultipartFile file) throws IOException, AuxilioMoradiaException;

	void removerDocumento(Selecao selecao, Documento documento) throws AuxilioMoradiaException;

	Documento buscarDocumento(Documento documento) throws AuxilioMoradiaException;

	void adicionarMembroComissao(Servidor servidor, Selecao selecao) throws AuxilioMoradiaException;

	void removerMembroComissao(Servidor servidor, Selecao selecao) throws AuxilioMoradiaException;

	void adicionarTipoDocumento(Selecao selecao, TipoDocumento tipoDocumento) throws AuxilioMoradiaException;

	void removerTipoDocumento(TipoDocumento tipoDocumento) throws AuxilioMoradiaException;

	DocumentoDownload downloadDocumento(Documento documento, String procedimento);
}
