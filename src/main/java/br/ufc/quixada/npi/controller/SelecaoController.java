package br.ufc.quixada.npi.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
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
		return "selecao/cadastrar";
		
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adicionarselecao( @Valid @ModelAttribute("selecao") Selecao selecao, BindingResult result, HttpSession session, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ("selecao/cadastrar");
		}
		this.serviceSelecao.save(selecao);
		
		
		redirect.addFlashAttribute("info", "selecao cadastrado com sucesso.");

		return "redirect:/selecao/listar";

	}
	
	
				
		
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		Selecao selecao = serviceSelecao.find(Selecao.class, (int) id);
		
		if (selecao == null) {
			redirectAttributes.addFlashAttribute("erro", "selecao inexistente.");
			return "redirect:/selecao/listar";
		}
		
		redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		return "redirect:/selecao/listar";
	}



	@RequestMapping(value = "/{id}/excluir")
	public String excluirselecao(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttributes,
			Model model) {
		Selecao selecao = serviceSelecao.find(Selecao.class, id);
		if (selecao == null) {
			redirectAttributes.addFlashAttribute("erro", "selecao inexistente.");
			return "redirect:/selecao/listar";
		}
		
		return "redirect:/selecao/listar";
		
	}
	
	
	
	
	@RequestMapping(value = "/listar")
	public String listar(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("selecoes", serviceSelecao.find(Selecao.class));
		
		return "selecao/listar";

	}

}
