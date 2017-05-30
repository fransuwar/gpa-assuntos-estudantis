package br.ufc.npi.auxilio.service.impl;

import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_BUSCAR_ARQUIVO;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Documento;
import br.ufc.npi.auxilio.model.DocumentoDownload;
import br.ufc.npi.auxilio.model.VisitaDomiciliar;
import br.ufc.npi.auxilio.repository.DocumentoRepository;
import br.ufc.npi.auxilio.repository.VisitaDomiciliarRepository;
import br.ufc.npi.auxilio.service.VisitaService;

@Service
public class VisitaServiceImpl implements VisitaService{

	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private VisitaDomiciliarRepository visitaDomiciliarRepository;

	@Value("${documents.folder}")
	private String FOLDER_DOCUMENTOS;
	
	@Override
	public void salvar(VisitaDomiciliar visitaDomiciliar){
		visitaDomiciliarRepository.save(visitaDomiciliar);
	}
	
	@Override
	public VisitaDomiciliar buscar(Integer idVisita){
		return visitaDomiciliarRepository.findById(idVisita);
	}
	
	@Override
	public void adicionarFormulario(VisitaDomiciliar visitaDomiciliar, MultipartFile file) throws IOException, AuxilioMoradiaException {
		if (visitaDomiciliar!= null && file.getBytes() != null && file.getBytes().length != 0) {
			Documento documento = new Documento();
			try {
				documento.setNome(file.getOriginalFilename());
				documento.setCaminho(FOLDER_DOCUMENTOS + "formularioVisita" + visitaDomiciliar.getId());
				documento.setArquivo(file.getBytes());

				// Pega  o fomato do arquivo
				String extensao = documento.getNome().substring(documento.getNome().lastIndexOf('.') + 1);
				documento.setTipo(Documento.Tipo.valueOf(extensao.toUpperCase()));

				documentoRepository.save(documento);
				visitaDomiciliar.setFormulario(documento);
				salvarArquivoLocal(documento);
			} catch (Exception e) {
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
			}
		}
	}
	

	@Override
	public void adicionarImagens(VisitaDomiciliar visitaDomiciliar, MultipartFile file) throws IOException, AuxilioMoradiaException {
		if (visitaDomiciliar!= null && file.getBytes() != null && file.getBytes().length != 0) {
			Documento documento = new Documento();
			try {
				documento.setNome(file.getOriginalFilename());
				documento.setCaminho(FOLDER_DOCUMENTOS + "imagensVisita" +visitaDomiciliar.getId());
				documento.setArquivo(file.getBytes());

				// Pega  o fomato do arquivo
				String extensao = documento.getNome().substring(documento.getNome().lastIndexOf('.') + 1);
				documento.setTipo(Documento.Tipo.valueOf(extensao.toUpperCase()));

				documentoRepository.save(documento);
				visitaDomiciliar.getImagens().add(documento);

				salvarArquivoLocal(documento);
			} catch (IOException e) {
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
			} catch (AuxilioMoradiaException e){
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
			}
		}
	}

	@Override
	public DocumentoDownload downloadDocumento(Documento documento, String procedimento) {
		String extensao;
		if(documento.getTipo().equals(Documento.Tipo.PDF)) {
			extensao = "application/" + documento.getTipo().getNome();
		} else {
			extensao = "image/" + documento.getTipo().getNome();
		}

		return new DocumentoDownload(documento.getArquivo(), documento.getNome(), procedimento, extensao);
	}
	
	private void salvarArquivoLocal(Documento documento) throws AuxilioMoradiaException {
		File diretorio = new File(documento.getCaminho());
		diretorio.mkdirs();

		try{
			File arquivo = new File(diretorio, documento.getNome());
			FileOutputStream fop = new FileOutputStream(arquivo);
			arquivo.createNewFile();
			fop.write(documento.getArquivo());
			fop.flush();
			fop.close();
		} catch (IOException e) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
		}
	}
	
	@Override
	public Documento buscarDocumento(Documento documento) throws AuxilioMoradiaException {
		try {
			File file = new File(documento.getCaminho() + "/" + documento.getNome());
			byte[] bFile = new byte[(int) file.length()];
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			documento.setArquivo(bFile);
			return documento;
		} catch (IOException e) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_BUSCAR_ARQUIVO);
		}
	}


}
