package br.ufc.quixada.npi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.ufc.quixada.npi.model.Selecao;
import br.ufc.quixada.npi.model.Selecao.Status;
import br.ufc.quixada.npi.model.Selecao.TipodeBolsa;
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
		//model.addAttribute("Tipodebolsa", TipodeBolsa.values());
		
		//List<TipodeBolsa> tipoDeBolsa = new ArrayList<TipodeBolsa>(Arrays.asList(TipodeBolsa.values()));
		model.addAttribute("tipoDeBolsa", TipodeBolsa.values());
		return "selecao/cadastrar";
		
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adicionarselecao( @Valid @ModelAttribute("selecao") Selecao selecao, BindingResult result, HttpSession session, RedirectAttributes redirect, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("tipoDeBolsa", TipodeBolsa.values());
			return ("selecao/cadastrar");
		}
		selecao.setStatus(Status.NOVO);
		//TipodeBolsa tipodeBolsa = selecao.getTipodeBolsa();
		//String tipo = tipodeBolsa.getTipo();
		
		selecao.setIdentificador(geraCodigoProjeto(selecao.getTipoDeBolsa(), selecao.getAno(), selecao.getSequencial()));
		this.serviceSelecao.save(selecao);
		
		
		redirect.addFlashAttribute("info", "selecao cadastrado com sucesso.");

		return "redirect:/selecao/listar";

	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		Selecao selecao = serviceSelecao.find(Selecao.class, id);
		
		if (selecao.getStatus().equals(Status.NOVO)) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("action", "editar");
			List<TipodeBolsa> tiposDeBolsa = new ArrayList<TipodeBolsa>(Arrays.asList(TipodeBolsa.values()));
			model.addAttribute("tiposDeBolsa", tiposDeBolsa);
			return "selecao/editar";
		}

		return "redirect:/selecao/listar";
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
	public String atualizarSelecao(@PathVariable("id") Long id, @Valid @ModelAttribute(value = "selecao") Selecao selecaoAtualizado, BindingResult result, Model model, HttpSession session, RedirectAttributes redirect) throws IOException {
		if (result.hasErrors()) {
			List<TipodeBolsa> tiposDeBolsa = new ArrayList<TipodeBolsa>(Arrays.asList(TipodeBolsa.values()));
			model.addAttribute("tiposDeBolsa", tiposDeBolsa);
			model.addAttribute("action", "editar");
			return ("selecao/editar");
		}
		Selecao selecao = serviceSelecao.find(Selecao.class, id);
		
		
		selecao.setTipoDeBolsa(selecaoAtualizado.getTipoDeBolsa());
		selecao.setDatadeInicio(selecaoAtualizado.getDatadeInicio());
		selecao.setDatadeTermino(selecaoAtualizado.getDatadeTermino());
		selecao.setQuantidadeVagas(selecaoAtualizado.getQuantidadeVagas());
		selecao.setComentarios(selecaoAtualizado.getComentarios());
		selecao.setAno(selecaoAtualizado.getAno());
		selecao.setSequencial(selecaoAtualizado.getSequencial());
		selecao.setDuracao(selecaoAtualizado.getDuracao());

		this.serviceSelecao.update(selecao);
		redirect.addFlashAttribute("info", "Projeto atualizado com sucesso.");
		return "redirect:/selecao/listar";
	}


	@RequestMapping(value = "/{id}/excluir")
	public String excluirProjeto(Selecao p, @PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes,
			Model model) {
		Selecao selecao = serviceSelecao.find(Selecao.class, id);
		if (selecao == null) {
			redirectAttributes.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		if (selecao.getStatus().equals(Status.NOVO)) {
			this.serviceSelecao.delete(selecao);
			redirectAttributes.addFlashAttribute("info", "Projeto excluído com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		}
		return "redirect:/selecao/listar";
		
	}
	
	private String geraCodigoProjeto(TipodeBolsa tipo, int ano, int sequencial) {
		
		return tipo +"_"+ ano +"_"+sequencial;
		
		
		
	}
	
	
	@RequestMapping(value = "/listar")
	public String listar(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("selecoes", serviceSelecao.find(Selecao.class));
		
		return "selecao/listar";

	}

}
