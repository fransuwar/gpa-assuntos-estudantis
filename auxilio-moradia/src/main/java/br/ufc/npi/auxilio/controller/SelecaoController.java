package br.ufc.npi.auxilio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.service.SelecaoService;

@Controller
@RequestMapping("/selecao")
public class SelecaoController {
	
	@Autowired
	private SelecaoService selecaoService;
	
	@GetMapping({"", "/", "/listar"})
	public String listarSelecoes(Model model) {
		model.addAttribute("selecoes", selecaoService.getAll());
		return "selecao/listar-selecao";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrarSelecaoForm(Model model) {
		model.addAttribute("selecao", new Selecao());
		return "selecao/cadastrar-selecao";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrarSelecao(Selecao selecao) {
		selecaoService.salvar(selecao);
		return "redirect:/selecao";
	}

}
