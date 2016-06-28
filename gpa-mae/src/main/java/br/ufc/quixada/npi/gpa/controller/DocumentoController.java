package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_DOCUMENTO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_OK;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufc.quixada.npi.gpa.excecoes.DocumentoNaoEncontradoRuntimeException;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.repository.DocumentoRepository;

@Controller
@RequestMapping("documento")
public class DocumentoController {
	
	@Inject
	private DocumentoRepository documentoRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getDocumento(@PathVariable("id") Integer id, HttpServletResponse response) {

		try {
			Documento documento = documentoRepository.findById(id);
			if(documento != null) {

				InputStream is = new ByteArrayInputStream(documento.getArquivo());
				response.setContentType(documento.getTipo());
				response.setHeader("Content-Disposition", "attachment; filename="
						+ documento.getNomeOriginal().replace(" ", "_"));
				IOUtils.copy(is, response.getOutputStream());
				
				response.flushBuffer();
			}
		} catch (IOException ex) {
			throw new DocumentoNaoEncontradoRuntimeException();
		}

	}
	
	@RequestMapping(value = "/ajax/remover/{id}", method = RequestMethod.POST)
	@ResponseBody public  ModelMap excluirDocumento(@PathVariable("id") Integer id) {
		ModelMap map = new ModelMap();
		Documento documento = documentoRepository.findById(id);
		if(documento == null) {
			map.addAttribute("result", "erro");
			map.addAttribute("mensagem", MENSAGEM_DOCUMENTO_INEXISTENTE);
			return map;
		}
		documentoRepository.delete(documento);
		map.addAttribute("result", MENSAGEM_OK);
		return map;
	}

}
