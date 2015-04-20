package br.ufc.quixada.npi.gpa.controller;

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

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.PessoaService;

@Controller
@RequestMapping("documento")
public class DocumentoController {
	
	@Inject
	private DocumentoService serviceDocumento;
	
	@Inject
	private PessoaService PessoaService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getFile(@PathVariable("id") Long id, HttpServletResponse response) {
		try {
			Documento documento = serviceDocumento.find(Documento.class, id);
			if(documento != null) {

				InputStream is = new ByteArrayInputStream(documento.getArquivo());
				response.setContentType(documento.getTipo());
				response.setHeader("Content-Disposition", "attachment; filename="
						+ documento.getNomeOriginal().replace(" ", "_"));
				IOUtils.copy(is, response.getOutputStream());
				
				response.flushBuffer();
			}
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}

	}
	
	@RequestMapping(value = "/ajax/remover/{id}", method = RequestMethod.POST)
	@ResponseBody public  ModelMap excluirDocumento(@PathVariable("id") Long id) {
		ModelMap map = new ModelMap();
		Documento documento = serviceDocumento.find(Documento.class, id);
		if(documento == null) {
			map.addAttribute("result", "erro");
			map.addAttribute("mensagem", "Documento não existe");
			return map;
		}
		serviceDocumento.delete(documento);
		map.addAttribute("result", "ok");
		return map;
	}

	public PessoaService getPessoaService() {
		return PessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		PessoaService = pessoaService;
	}

}
