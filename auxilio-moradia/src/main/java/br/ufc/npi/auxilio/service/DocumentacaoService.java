package br.ufc.npi.auxilio.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Documentacao;
import br.ufc.npi.auxilio.model.Documento;
import br.ufc.npi.auxilio.model.DocumentoDownload;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.TipoDocumento;

public interface DocumentacaoService {
	
	List<TipoDocumento> getAllTipoDocumento();
	
	void salvar(Documentacao documentacao);
	
	void salvar(TipoDocumento tipoDocumento);
	
	boolean adicionarDocumentos(Inscricao inscricao, Documentacao documentacao, MultipartFile multipartFile) throws AuxilioMoradiaException, IOException;
	
	DocumentoDownload downloadDocumento(Documento documento, String procedimento);

	Documento buscarDocumento(Documento documento) throws AuxilioMoradiaException;

}
