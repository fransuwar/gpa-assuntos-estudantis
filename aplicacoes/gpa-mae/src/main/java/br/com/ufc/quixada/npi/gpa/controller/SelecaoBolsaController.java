package br.com.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.model.Documento;
import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa.Status;
import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa.TipoBolsa;
import br.com.ufc.quixada.npi.gpa.model.Servidor;
import br.com.ufc.quixada.npi.gpa.service.DocumentoService;
import br.com.ufc.quixada.npi.gpa.service.SelecaoBolsaService;
import br.com.ufc.quixada.npi.gpa.service.ServidorService;

@Named
@RequestMapping({"/selecaoBolsa", "/selecoes"})
public class SelecaoBolsaController {
		
	@Inject
	private DocumentoService documentoService;
	@Inject
	private ServidorService servidorService;
	@Inject	
	private SelecaoBolsaService serviceSelecao;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "redirect:/selecaoBolsa/listarBolsa";
	}


	@RequestMapping(value="{id}/informacoes")
	public String getInformacoes(SelecaoBolsa selecao,  @PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
		SelecaoBolsa selecaoBolsa = serviceSelecao.find(SelecaoBolsa.class, id);
		System.out.println(selecaoBolsa.getComentarios());
		if(selecaoBolsa==null){
			redirectAttributes.addFlashAttribute("erro", "seleção Inexistente");
			return "redirect:/selecaoBolsa/listarBolsa";
		}
		model.addAttribute("selecao",selecaoBolsa);
		
		return "selecaoBolsa/informacoes";
	}
	
	@RequestMapping(value="/informacoesAuxilio", method=RequestMethod.GET)
	public String informacoesAuxilio(){
		return "selecaoBolsa/informacoesAuxilio";
	}
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/cadastrarBolsa", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("selecao", new SelecaoBolsa());
		model.addAttribute("tipoBolsa", TipoBolsa.values());
		return "selecaoBolsa/cadastrarBolsa";
	}



	@RequestMapping(value = "/cadastrarBolsa", method = RequestMethod.POST)
	public String adicionarselecao(
			@Valid @ModelAttribute("selecao") SelecaoBolsa selecao,
			BindingResult result, RedirectAttributes redirect, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			return ("selecaoBolsa/cadastrarBolsa");
		}
		selecao.setStatus(Status.NOVA);
		this.serviceSelecao.save(selecao);
		redirect.addFlashAttribute("info", "seleção cadastrada com sucesso.");
		return "redirect:/selecaoBolsa/listarBolsa";
	}


	@RequestMapping(value = "/{id}/editarBolsa", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, Model model) {
		SelecaoBolsa selecao = serviceSelecao.find(SelecaoBolsa.class, id);
		if (selecao.getStatus().equals(Status.NOVA)) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("action", "editar");
			List<TipoBolsa> tiposBolsa = new ArrayList<TipoBolsa>(
					Arrays.asList(TipoBolsa.values()));
			model.addAttribute("tiposBolsa", tiposBolsa);
			return "selecaoBolsa/editarBolsa";
		}
		return "redirect:/selecaoBolsa/listarBolsa";
	}


	@RequestMapping(value = "/{id}/editarBolsa", method = RequestMethod.POST)
	public String atualizarSelecao(
			@RequestParam("file") MultipartFile[] files, 
			@PathVariable("id") Integer id, @Valid 
			@ModelAttribute(value = "selecao") SelecaoBolsa selecaoAtualizado, BindingResult result, Model model
		) throws IOException  {
	
		if (result.hasErrors()) {
			List<TipoBolsa> tiposBolsa = new ArrayList<TipoBolsa>(Arrays.asList(TipoBolsa.values()));
			model.addAttribute("tiposBolsa", tiposBolsa);
			model.addAttribute("action", "editar");
			return ("selecaoBolsa/editarBolsa");
		}
		for (MultipartFile mpf : files) {
			if (mpf.getBytes().length > 0) {
				Documento documento = new Documento();
				documento.setNomeOriginal(mpf.getOriginalFilename());
				documento.setTipo(mpf.getContentType());
				documento.setSelecaoBolsa(selecaoAtualizado);
				documento.setArquivo(mpf.getBytes());

				documentoService.save(documento);
			}
	
		this.serviceSelecao.update(selecaoAtualizado);
	}
		return "redirect:/selecaoBolsa/listarBolsa";
	}
	
	@RequestMapping(value = "/{id}/excluir")
	public String excluirSelecao(SelecaoBolsa p,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {
		SelecaoBolsa selecao = serviceSelecao.find(SelecaoBolsa.class, id);
		if (selecao == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Seleção inexistente.");
			return "redirect:/selecaoBolsa/listarBolsa";
		}
		if (selecao.getStatus().equals(Status.NOVA)) {
			this.serviceSelecao.delete(selecao);
			redirectAttributes.addFlashAttribute("info",
					"Seleção excluída com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		}
		return "redirect:/selecaoBolsa/listarBolsa";

	}

	
	@RequestMapping(value = "/listarBolsa")
	public String listar(ModelMap model) {
		model.addAttribute("selecoes",
				serviceSelecao.find(SelecaoBolsa.class));
		return "selecaoBolsa/listarBolsa";
	}

	@RequestMapping(value = "/{id}/atribuirBanca", method = RequestMethod.GET)
	public String atribuirParecerista(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("selecao", id);
		model.addAttribute("servidores", servidorService.find(Servidor.class));
		return "selecaoBolsa/atribuirBanca";
	}

	@RequestMapping(value = "/atribuirBanca", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(
			@RequestParam("id1") Integer id1, @RequestParam("id2") Integer id2,
			@RequestParam("id3") Integer id3, @RequestParam("id") Integer id,
			RedirectAttributes redirect) {

		SelecaoBolsa selecao = serviceSelecao.find(SelecaoBolsa.class, id);
		redirect.addFlashAttribute("selecao", id);
		redirect.addFlashAttribute("membrosBanca", (selecao.getId()));

		List<Servidor> list = new ArrayList<Servidor>();
		list.add(servidorService.find(Servidor.class, id1));
		list.add(servidorService.find(Servidor.class, id2));
		list.add(servidorService.find(Servidor.class, id3));

		selecao.setMembrosBanca(list);

		serviceSelecao.update(selecao);
		redirect.addFlashAttribute("info",
				"O parecerista foi atribuído ao projeto com sucesso.");

		return "redirect:/selecaoBolsa/listarBolsa";
	}

}
