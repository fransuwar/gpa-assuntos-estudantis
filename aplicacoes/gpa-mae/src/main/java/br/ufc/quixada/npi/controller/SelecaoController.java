package br.ufc.quixada.npi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.model.Selecao;
import br.ufc.quixada.npi.model.Selecao.Status;
import br.ufc.quixada.npi.model.Selecao.TipoBolsa;
import br.ufc.quixada.npi.service.SelecaoService;
@Named
@RequestMapping("/selecao")
public class SelecaoController {
	@Inject
	private SelecaoService serviceSelecao;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "redirect:/selecao/listar";
	}
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("selecao", new Selecao());
		model.addAttribute("tipoBolsa", TipoBolsa.values());
		return "selecao/cadastrar";
	}
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adicionarselecao( @Valid @ModelAttribute("selecao") Selecao selecao, BindingResult result, RedirectAttributes redirect, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			return ("selecao/cadastrar");
		}
		selecao.setStatus(Status.NOVA);
		this.serviceSelecao.save(selecao);
		redirect.addFlashAttribute("info", "seleção cadastrada com sucesso.");
		return "redirect:/selecao/listar";
	}
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, Model model) {
		Selecao selecao = serviceSelecao.find(Selecao.class, id);
		if (selecao.getStatus().equals(Status.NOVA)) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("action", "editar");
			List<TipoBolsa> tiposBolsa = new ArrayList<TipoBolsa>(Arrays.asList(TipoBolsa.values()));
			model.addAttribute("tiposBolsa", tiposBolsa);
			return "selecao/editar";
		}
		return "redirect:/selecao/listar";
	}
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
	public String atualizarSelecao(@PathVariable("id") Integer id, @Valid @ModelAttribute(value = "selecao") Selecao selecaoAtualizado, BindingResult result, Model model)  {
		if (result.hasErrors()) {
			List<TipoBolsa> tiposBolsa = new ArrayList<TipoBolsa>(Arrays.asList(TipoBolsa.values()));
			model.addAttribute("tiposBolsa", tiposBolsa);
			model.addAttribute("action", "editar");
			return ("selecao/editar");
		}
	
		this.serviceSelecao.update(selecaoAtualizado);
		return "redirect:/selecao/listar";
	}
	@RequestMapping(value = "/{id}/excluir")
	public String excluirSelecao(Selecao p, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Selecao selecao = serviceSelecao.find(Selecao.class, id);
		if (selecao == null) {
			redirectAttributes.addFlashAttribute("erro", "Seleção inexistente.");
			return "redirect:/selecao/listar";
		}
		if (selecao.getStatus().equals(Status.NOVA)) {
			this.serviceSelecao.delete(selecao);
			redirectAttributes.addFlashAttribute("info", "Seleção excluído com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		}
		return "redirect:/selecao/listar";
		
	}
	@RequestMapping(value = "/listar")
	public String listar(ModelMap modelMap) {
		modelMap.addAttribute("selecoes", serviceSelecao.find(Selecao.class));
		return "selecao/listar";
	}
	
	@RequestMapping(value = "/{id}/atribuirBanca", method = RequestMethod.GET)
	public String atribuirParecerista(
			@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Selecao selecao = serviceSelecao.find(Selecao.class, id);

		model.addAttribute("selecao", "id");
		model.addAttribute("usuarios", serviceSelecao.find(Selecao.class));
		return "selecao/atribuirBanca";
	}

	
	@RequestMapping(value = "/{id}/atribuirBanca", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(HttpServletRequest request,
			Model model, RedirectAttributes redirect) throws IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));
		

		Selecao selecao = serviceSelecao.find(Selecao.class, id);
		redirect.addFlashAttribute("selecao", id);
		redirect.addFlashAttribute("membrosBanca", (selecao.getId()));

		redirect.addFlashAttribute("info",
				"O parecerista foi atribuído ao projeto com sucesso.");

		return "redirect:/projeto/listar";
	}
	
	
	
	
	
	
	
	
	
}
