package br.ufc.npi.auxilio.service.impl;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.repository.DocumentoRepository;
import br.ufc.npi.auxilio.repository.SelecaoRepository;
import br.ufc.npi.auxilio.repository.TipoDocumentoRepository;
import br.ufc.npi.auxilio.service.SelecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.*;

@Service
public class SelecaoServiceImpl implements SelecaoService {
	
	@Autowired
	private SelecaoRepository selecaoRepository;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Value("${documents.folder}")
	private String FOLDER_DOCUMENTOS;
	
	@Override
	public void cadastrar(Selecao selecao) throws AuxilioMoradiaException {
		// Verifica se todos os campos obrigatórios foram preenchidos
		if (selecao.getAno() == null || selecao.getDataInicio() == null || selecao.getDataTermino() == null) {
			throw new AuxilioMoradiaException(CAMPOS_OBRIGATORIOS);
		}
		// Verifica se a data de término é posterior à data de início
		if (selecao.getDataTermino().isBefore(selecao.getDataInicio())) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_PERIODO_INSCRICAO);
		}
		// Inclui o responsável na comissão da seleção
		selecao.addMembroComissao(selecao.getResponsavel());

		selecaoRepository.save(selecao);
	}

	@Override
	public List<Selecao> getAll() {
		return selecaoRepository.findAll();
	}

	@Override
	public void excluir(Selecao selecao) throws AuxilioMoradiaException {
		if (selecao.hasInscricoes()) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_SELECAO_COM_INSCRICAO);
		}
		selecaoRepository.delete(selecao);
	}

	@Override
	public void adicionarDocumento(Selecao selecao, MultipartFile file) throws IOException, AuxilioMoradiaException {
		if (selecao != null && file.getBytes() != null && file.getBytes().length != 0) {
			Documento documento = new Documento();
			try {
				documento.setNome(file.getOriginalFilename());
				documento.setCaminho(FOLDER_DOCUMENTOS + selecao.getId());
				documento.setArquivo(file.getBytes());

				// Pega  o fomato do arquivo
				String extensao = documento.getNome().substring(documento.getNome().lastIndexOf('.') + 1);
				documento.setTipo(Documento.Tipo.valueOf(extensao.toUpperCase()));

				documentoRepository.save(documento);
				selecao.addDocumento(documento);
				this.cadastrar(selecao);

				salvarArquivoLocal(documento);
			} catch (Exception e) {
				if (documento.getId() != null) {
					removerDocumento(selecao, documento);
				}
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
			}
		}
	}

	@Override
	public void removerDocumento(Selecao selecao, Documento documento) throws AuxilioMoradiaException {
		if (selecao != null && documento != null && selecao.getDocumentos().contains(documento)) {
			selecao.removeDocumento(documento);
			this.cadastrar(selecao);
			File file = new File(documento.getCaminho() + "/" + documento.getNome());
			file.delete();
			documentoRepository.delete(documento);
		} else {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_EXCLUIR_ARQUIVO);
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

	@Override
	public void adicionarMembroComissao(Servidor servidor, Selecao selecao) throws AuxilioMoradiaException {
		if (servidor != null && selecao != null) {
			selecao.addMembroComissao(servidor);
			this.cadastrar(selecao);
		}
	}

	@Override
	public void removerMembroComissao(Servidor servidor, Selecao selecao) throws AuxilioMoradiaException {
		if (servidor != null && selecao != null) {
			if (servidor.equals(selecao.getResponsavel())) {
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR);
			}
			selecao.removeMembroComissao(servidor);
			this.cadastrar(selecao);
		}
	}

	@Override
	public void adicionarTipoDocumento(Selecao selecao, TipoDocumento tipoDocumento) throws AuxilioMoradiaException {
		if (selecao != null && tipoDocumento.getNome() != null) {
			tipoDocumento.setSelecao(selecao);
			tipoDocumentoRepository.save(tipoDocumento);
		} else {
			throw new AuxilioMoradiaException(CAMPOS_OBRIGATORIOS);
		}
	}

	@Override
	public void removerTipoDocumento(TipoDocumento tipoDocumento) throws AuxilioMoradiaException {
		if (tipoDocumento != null) {
			tipoDocumentoRepository.delete(tipoDocumento);
		} else {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_EXCLUIR_DOCUMENTACAO);
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

}
