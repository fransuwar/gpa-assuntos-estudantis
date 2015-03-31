package br.com.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.enums.Cargo;
import br.com.ufc.quixada.npi.gpa.model.Servidor;
import br.com.ufc.quixada.npi.gpa.service.ServidorService;


@Controller
@RequestMapping ("servidor")
public class ServidorController {

	@Inject
	private ServidorService servidorService;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("cargos", Cargo.toMap());
		
		model.addAttribute("servidor", new Servidor());
		return "/servidor/cadastrar";
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adcionaServidor(
			@Valid @ModelAttribute("servidor") Servidor servidor,
			BindingResult result,RedirectAttributes redirect) {
	
		if (result.hasErrors()) {
			return ("servidor/cadastrar");
		}
		
		try{
			this.servidorService.save(servidor);
		} catch (PersistenceException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				redirect.addFlashAttribute("erro", "Não é possível cadastrar um siape já existente.");

				return "redirect:/servidor/listar";
			}
		}
		redirect.addFlashAttribute("info", "Servidor cadastrado com sucesso.");

		return "redirect:/servidor/listar";

	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaServidor(Servidor servidor, BindingResult result,
			Model model) {
			
			List<Servidor> results = servidorService.find(Servidor.class);	
			model.addAttribute("servidores", results);
			return "servidor/listar";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public String listarServidor(@RequestParam("siape") String siape, Model model, RedirectAttributes redirect) {
		List<Servidor> results = new ArrayList<Servidor>();
		Servidor servidor = servidorService.getServidorBySiape(siape);
		results.add(servidor);
		model.addAttribute("servidores", results);
		
		if(servidor == null){
			redirect.addFlashAttribute("erro", "Servidor não encontrado");
			redirect.addFlashAttribute("servidorEncontrado", false);
			return "redirect:/servidor/listar";
		}
		

		return "/servidor/listar";
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, Model model) {
		    
			Servidor servidor = servidorService.find(Servidor.class, id);
			model.addAttribute("cargos", Cargo.toMap());
			model.addAttribute("servidor", servidor);
			model.addAttribute("action", "editar");
			
			return "servidor/editar";
	}
	
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
	public String atualizarServidor(@PathVariable("id") Integer id,
			@Valid @ModelAttribute(value = "servidor") Servidor servidorAtualizado,
			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar");
			return "servidor/editar";
		}
	
		Servidor servidor = servidorService.find(Servidor.class, id);
		servidor.setSiape(servidorAtualizado.getSiape());
		servidor.setCargo(servidorAtualizado.getCargo());
				
		this.servidorService.update(servidor);
		redirect.addFlashAttribute("info", "Servidor atualizado com sucesso.");
		return "redirect:/servidor/listar";
	}
	
	@RequestMapping(value = "/{id}/excluir")
	public String excluirServidor(Servidor p, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes
			) {
		Servidor servidor = servidorService.find(Servidor.class, id);
		
		if (servidor == null) {
			redirectAttributes.addFlashAttribute("erro", "Servidor inexistente.");
		}else{
			
			this.servidorService.delete(servidor);
			redirectAttributes.addFlashAttribute("info", "Servidor excluído com sucesso.");
		}
		return "redirect:/servidor/listar";
	}
	
}
