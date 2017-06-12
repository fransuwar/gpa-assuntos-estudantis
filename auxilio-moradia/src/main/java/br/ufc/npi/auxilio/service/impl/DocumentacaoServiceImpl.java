package br.ufc.npi.auxilio.service.impl;

import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_BUSCAR_ARQUIVO;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Documentacao;
import br.ufc.npi.auxilio.model.Documento;
import br.ufc.npi.auxilio.model.DocumentoDownload;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.TipoDocumento;
import br.ufc.npi.auxilio.repository.DocumentacaoRepository;
import br.ufc.npi.auxilio.repository.DocumentoRepository;
import br.ufc.npi.auxilio.repository.TipoDocumentoRepository;
import br.ufc.npi.auxilio.service.DocumentacaoService;

@Service
public class DocumentacaoServiceImpl implements DocumentacaoService {

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private DocumentacaoRepository documentacaoRepository;
	
	@Value("${documents.folder}")
	private String FOLDER_DOCUMENTOS;
	
	@Override
	public List<TipoDocumento> getAllTipoDocumento() {
		return tipoDocumentoRepository.findAll();
	}

	@Override
	public void salvar(TipoDocumento tipoDocumento) {
		tipoDocumentoRepository.save(tipoDocumento);
	}
	
	@Override
	public void salvar(Documentacao documentacao){
		documentacaoRepository.save(documentacao);
	}

	public void adicionarDocumentos(Inscricao inscricao, Documentacao documentacao, MultipartFile multipartFile) throws AuxilioMoradiaException, IOException{
		if (inscricao!= null && multipartFile.getBytes() != null && multipartFile.getBytes().length != 0) {
			Documento documento = new Documento();
			try {
				String homeDir = System.getProperty("user.home");
				documento.setNome(multipartFile.getOriginalFilename());
				documento.setCaminho(homeDir + FOLDER_DOCUMENTOS + "analiseDocumentacao" +inscricao.getId());
				documento.setArquivo(multipartFile.getBytes());

				// Pega  o fomato do arquivo
				String extensao = documento.getNome().substring(documento.getNome().lastIndexOf('.') + 1);
				documento.setTipo(Documento.Tipo.valueOf(extensao.toUpperCase()));

				documentoRepository.save(documento);
				documentacao.getDocumentos().add(documento);
				
				salvarArquivoLocal(documento);
			} catch (IOException e) {
				e.printStackTrace();
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
			} catch (AuxilioMoradiaException e){
				e.printStackTrace();
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
