package br.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.TipoBolsa;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.SelecaoBolsaService;

@Controller
@RequestMapping("coordenador")
public class CoordenadorController {

	@Inject
	private SelecaoBolsaService selecaoService;
	
	@Inject
	private DocumentoService documentoService;
	
	@RequestMapping(value = { "cadastrar-selecao" }, method = RequestMethod.GET)
	public String cadastroSelecao(Model model) {
		
		model.addAttribute("action", "cadastrar");
		model.addAttribute("tipoBolsa", TipoBolsa.values());
		model.addAttribute("selecao", new SelecaoBolsa());
		
		return "selecao/cadastrar";
	}
	
	@RequestMapping(value = { "cadastrar-selecao" }, method = RequestMethod.POST)
	public String cadastroSelecao(@RequestParam("files") List<MultipartFile> files, Model model,
			@Valid @ModelAttribute("selecao") SelecaoBolsa selecao, BindingResult result, 
			RedirectAttributes redirect) {
		
		model.addAttribute("action", "cadastrar");

		if (selecao != null && selecao.getAno() != null) {
			if (selecao.getAno() < DateTime.now().getYear()) {
				result.rejectValue("ano", "selecao.ano", "Digite um ano maior ou igual ao atual.");
			}
		}
		
		if (selecao != null && selecao.getDataInicio() != null && selecao.getDataTermino() != null) {
			if ((new DateTime(selecao.getDataTermino())).isBefore(new DateTime(selecao.getDataInicio()))) {
				result.rejectValue("dataTermino", "selecao.dataTermino", "A data de término não pode ser anterior a data de início.");
			}
		}
		
		if (selecao != null)  {
			if (selecaoService.existsSelecaoEquals(selecao)) {
				result.rejectValue("sequencial", "selecao.sequencial", "Número do edital com esse tipo de bolsa já existente");
			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			return "selecao/cadastrar";
		}
		
		List<Documento> documentos = new ArrayList<Documento>();
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			for (MultipartFile mfiles : files) {
				try {
					
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						
						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						documento.setSelecaoBolsa(selecao);
						documentos.add(documento);
					}
					
					if (!documentos.isEmpty()) {
						selecao.setDocumentos(documentos);
					}
					
				} catch (IOException ioe) {
					model.addAttribute("erro", "Não foi possivel salvar os documentos.");
					return "selecao/cadastrar";
				}
			} 
		} else {
			
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			model.addAttribute("anexoError", "Adicione anexo a seleção.");
			return "selecao/cadastrar";
		}
		
		this.selecaoService.save(selecao);
		redirect.addFlashAttribute("info", "Nova seleção cadastrada com sucesso.");
		return "redirect:/selecao/listar";
		
	}
	
	@RequestMapping(value = { "editar-selecao/{idSelecao}" }, method = RequestMethod.GET)
	public String editarSelecao(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		
		SelecaoBolsa selecao = this.selecaoService.find(SelecaoBolsa.class, idSelecao);

		if (selecao != null && selecao.getStatus() != null && selecao.getStatus().equals(Status.NOVA)) {
		
			model.addAttribute("action", "editar");
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			model.addAttribute("selecao", selecao);
			
		} else {
			redirect.addFlashAttribute("erro", "Permissão negada. Só é possível editar uma seleção enquanto seu status é nova.");
			return "redirect:/selecao/listar";
		}
		
		return "selecao/cadastrar";
	}
	
	@RequestMapping(value = { "editar-selecao/{idSelecao}" }, method = RequestMethod.POST)
	public String editarSelecao(@RequestParam("files") List<MultipartFile> files, @PathVariable("idSelecao") Integer idSelecao,
			@Valid @ModelAttribute("selecao") SelecaoBolsa selecao, Model model,
			BindingResult result, RedirectAttributes redirect, HttpServletRequest request) {
		
		model.addAttribute("action", "editar");
		
		if (selecao != null && selecao.getAno() != null) {
			if (selecao.getAno() < DateTime.now().getYear()) {
				result.rejectValue("ano", "selecao.ano", "Digite um ano maior ou igual ao atual.");
			}
		}
		
		if (selecao != null && selecao.getDataInicio() != null && selecao.getDataTermino() != null) {
			if ((new DateTime(selecao.getDataTermino())).isBefore(new DateTime(selecao.getDataInicio()))) {
				result.rejectValue("dataTermino", "selecao.dataTermino", "A data de término não pode ser anterior a data de início.");
			}
		}
		
		if (selecao != null)  {
			if (selecaoService.existsSelecaoEquals(selecao)) {
				result.rejectValue("sequencial", "selecao.sequencial", "Número do edital com esse tipo de bolsa já existente");
			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			return "selecao/cadastrar";
		}
		
		String doc[] = request.getParameterValues("doc");

		if (doc != null) {

			if (selecaoService.getSelecaoBolsaComDocumentos(selecao.getId()).getDocumentos().size() == doc.length
				&& (files.isEmpty() || files.get(0).getSize() <= 0)) {
				model.addAttribute("action", "editar");
				redirect.addFlashAttribute("erro", "Não foi possível excluir seu(s) anexo(s), pois não é possível salvar a seleção sem nenhum anexo.");
				return "redirect:/selecao/cadastrar";
			}

			for (int k = 0; k < doc.length; k++) {
				Documento d = new Documento();
				d.setId(Long.parseLong(doc[k]));
				documentoService.delete(d);
			}
		}
		
		List<Documento> documentos = new ArrayList<Documento>();
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			for (MultipartFile mfiles : files) {
				try {
					
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						
						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						documento.setSelecaoBolsa(selecao);
						documentos.add(documento);
					}
					
					if (!documentos.isEmpty()) {
						selecao.setDocumentos(documentos);
					}
					
				} catch (IOException ioe) {
					model.addAttribute("erro", "Não foi possivel salvar os documentos.");
					return "selecao/cadastrar";
				}
			} 
		} else {
			
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			model.addAttribute("anexoError", "Adicione anexo a seleção.");
			return "selecao/cadastrar";
		}
		
		this.selecaoService.update(selecao);
		redirect.addFlashAttribute("info", "Seleção atualizada com sucesso.");
		
		return "redirect:/selecao/listar";
	}
	
	@RequestMapping(value = { "excluir-selecao/{idSelecao}" }, method = RequestMethod.GET)
	public String excluirSelecao(@PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect) {
		
		SelecaoBolsa selecao = this.selecaoService.find(SelecaoBolsa.class, idSelecao);
		
		if (selecao != null) {
			if (selecao.getStatus().equals(Status.NOVA)) {
				this.selecaoService.delete(selecao);
				redirect.addFlashAttribute("info", "Seleção removida com sucesso.");
			} else {
				redirect.addFlashAttribute("erro", "Permissão negada. Só é possível remover uma seleção enquanto seu status é nova.");
			}
		} else {
			redirect.addFlashAttribute("erro", "Seleção inexistente.");
		}
		
		return "redirect:/selecao/listar";
	}
}
