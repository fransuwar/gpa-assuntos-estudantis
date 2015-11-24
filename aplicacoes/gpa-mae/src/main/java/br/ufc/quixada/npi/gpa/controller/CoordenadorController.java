package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

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
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.service.ServidorService;

@Controller
@RequestMapping("coordenador")
public class CoordenadorController {

	@Inject
	private SelecaoService selecaoService;
	
	@Inject
	private DocumentoService documentoService;
	
	@Inject
	private ServidorService servidorService;
	
	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.GET)
	public String cadastroSelecao(Model model) {
		
		model.addAttribute("action", "cadastrar");
		model.addAttribute("tipoBolsa", TipoBolsa.values());
		model.addAttribute("selecao", new Selecao());
		
		return PAGINA_CADASTRAR_SELECAO;
	}
	
	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.POST)
	public String cadastroSelecao(@RequestParam("files") List<MultipartFile> files, Model model,
			@Valid @ModelAttribute("selecao") Selecao selecao, BindingResult result, 
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
			return PAGINA_CADASTRAR_SELECAO;
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
					
					
				} catch (IOException ioe) {
					model.addAttribute("erro", "Não foi possivel salvar os documentos.");
					return PAGINA_CADASTRAR_SELECAO;
				}
			} 
			
			if (!documentos.isEmpty()) {
				selecao.setDocumentos(documentos);
			}
			
		} else {
			
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			model.addAttribute("anexoError", "Adicione anexo a seleção.");
			return PAGINA_CADASTRAR_SELECAO;
		}
		
		this.selecaoService.save(selecao);
		redirect.addFlashAttribute("info", "Nova seleção cadastrada com sucesso.");
		return REDIRECT_PAGINA_LISTAR_SELECAO;
		
	}
	
	@RequestMapping(value = { "selecao/editar/{idSelecao}" }, method = RequestMethod.GET)
	public String editarSelecao(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		
		Selecao selecao = this.selecaoService.getSelecaoBolsaComDocumentos(idSelecao);

		if (selecao != null && selecao.getStatus() != null && selecao.getStatus().equals(Status.NOVA)) {
		
			model.addAttribute("action", "editar");
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			model.addAttribute("selecao", selecao);
			
		} else {
			redirect.addFlashAttribute("erro", "Permissão negada. Só é possível editar uma seleção enquanto seu status é nova.");
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		
		return PAGINA_CADASTRAR_SELECAO;
	}
	
	@RequestMapping(value = { "selecao/editar" }, method = RequestMethod.POST)
	public String editarSelecao(@RequestParam("files") List<MultipartFile> files,
			@Valid @ModelAttribute("selecao") Selecao selecao, Model model,
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
		
		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			return PAGINA_CADASTRAR_SELECAO;
		}
		
		String doc[] = request.getParameterValues("doc");

		if (doc != null) {

			if (selecaoService.getSelecaoBolsaComDocumentos(selecao.getId()).getDocumentos().size() == doc.length
				&& (files.isEmpty() || files.get(0).getSize() <= 0)) {
				model.addAttribute("action", "editar");
				redirect.addFlashAttribute("erro", "Não foi possível excluir seu(s) anexo(s), pois não é possível salvar a seleção sem nenhum anexo.");
				return PAGINA_CADASTRAR_SELECAO;
			}

			for (int k = 0; k < doc.length; k++) {
				Documento d = new Documento();
				d.setId(Integer.parseInt(doc[k]));
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
						documentoService.save(documento);
					}
				} catch (IOException ioe) {
					model.addAttribute("erro", "Não foi possivel salvar os documentos.");
					return PAGINA_CADASTRAR_SELECAO;
				}
			}
		} else {
			
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			model.addAttribute("anexoError", "Adicione anexo a seleção.");
			return PAGINA_CADASTRAR_SELECAO;
		}
		
		
		this.selecaoService.update(selecao);
		redirect.addFlashAttribute("info", "Seleção atualizada com sucesso.");
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	
	@RequestMapping(value = { "selecao/excluir/{idSelecao}" }, method = RequestMethod.GET)
	public String excluirSelecao(@PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect) {
		
		Selecao selecao = this.selecaoService.find(Selecao.class, idSelecao);
		
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
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	
	@RequestMapping(value = { "selecao/comissao/{idSelecao}" }, method = RequestMethod.GET)
	public String atribuirComissao(@PathVariable("idSelecao") Integer idSelecao, Model model,
			RedirectAttributes redirect) {
		
		List<Servidor> comissao = this.selecaoService.getSelecaoBolsaComMembros(idSelecao).getMembrosBanca();
		
		if (!comissao.isEmpty()) {
			model.addAttribute("m1", comissao.get(0).getId());
			model.addAttribute("m2", comissao.get(1).getId());
			model.addAttribute("m3", comissao.get(2).getId());
		}
		
		model.addAttribute("selecao", idSelecao);
		model.addAttribute("servidores", this.servidorService.find(Servidor.class));
		
		return "selecao/atribuir";
	}
	
	@RequestMapping(value = { "selecao/comissao" }, method = RequestMethod.POST)
	public String atribuirComissao(@RequestParam("id") Integer id,
			@RequestParam("id1") Integer id1, @RequestParam("id2") Integer id2,
			@RequestParam("id3") Integer id3, Model model,
			RedirectAttributes redirect) {
		
		if (id1 == null || id2 == null || id3 == null) {
			model.addAttribute("selecao", id);
			model.addAttribute("servidores", this.servidorService.find(Servidor.class));
			model.addAttribute("erroMembros", "Informe os três membros.");
			
			return "selecao/atribuir";
			
		} else if (id1.equals(id2) || id1.equals(id3) || id2.equals(id3)) {
			
			model.addAttribute("selecao", id);
			model.addAttribute("servidores",servidorService.find(Servidor.class));
			model.addAttribute("erroMembros","Não é permitida repetição de membros na banca.");
			
			return "selecao/atribuir";
			
		} else {
			
			Selecao selecao = selecaoService.find(Selecao.class, id);
			
			List<Servidor> list = new ArrayList<Servidor>();
			
			list.add(new Servidor(id1));
			list.add(new Servidor(id2));
			list.add(new Servidor(id3));
			
			selecao.setMembrosBanca(list);
			
			this.selecaoService.update(selecao);
			redirect.addFlashAttribute("info", "Comissão formada com sucesso.");
			
			return "redirect:/selecao/listar";
		}
	}
}
