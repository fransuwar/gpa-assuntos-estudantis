package br.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Banco;
import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.service.AlunoService;


@Controller
@RequestMapping("aluno")
public class AlunoController {
	
	@Inject
	private AlunoService alunoService;
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarAluno(@Valid @ModelAttribute(value = "aluno") Aluno aluno,
			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {
		
		if(aluno.getId()!=null){
			return atualizarAluno(aluno.getId(), aluno, result, model, redirect);
		}else{
			return adicionarAluno(aluno, result, redirect, model);
		}
		
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("action", "cadastrar");
		model.addAttribute("banco", Banco.toMap());
		model.addAttribute("curso", Curso.toMap());
		model.addAttribute("aluno", new Aluno());
		return "/aluno/cadastrar";
	}

	public String adicionarAluno(
			@Valid @ModelAttribute("aluno") Aluno aluno,
			BindingResult result,RedirectAttributes redirect, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("banco", Banco.toMap());
			model.addAttribute("curso", Curso.toMap());
			return ("aluno/cadastrar");
		}

		DateTime anoIngresso = DateTime.parse(aluno.getAnoIngresso());
		if(anoIngresso.isAfterNow()){
			model.addAttribute("anoIngressoError", "Informe um ano menor ou igual ao atual");
			model.addAttribute("banco", Banco.toMap());
			model.addAttribute("curso", Curso.toMap());
			return "aluno/cadastrar";
		}
		
		try{
			this.alunoService.save(aluno);
		} catch (PersistenceException e){
			if(e.getCause() instanceof ConstraintViolationException){
				redirect.addFlashAttribute("erro", "Não é possível cadastrar uma matrícula já existente.");
				return "redirect:/aluno/listar";
			}
		} 
		redirect.addFlashAttribute("info", "Aluno cadastrado com sucesso.");
		return "redirect:/aluno/listar";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaAluno(Aluno aluno, Model model) {	
			List<Aluno> results = alunoService.find(Aluno.class);
			model.addAttribute("alunos", results);
			return "aluno/listar";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public String listarAluno(@RequestParam("matricula") String matricula, Model model, RedirectAttributes redirect) {
		List<Aluno> results = new ArrayList<Aluno>();
		Aluno aluno = alunoService.getAlunoByMatricula(matricula);
		results.add(aluno);
		model.addAttribute("alunos", results);
		
		if(aluno == null){
			redirect.addFlashAttribute("erro", "Aluno não encontrado");
			redirect.addFlashAttribute("alunoEncontrado", false);	
			return "redirect:/aluno/listar";
		}
		return "/aluno/listar";
	}
	
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, Model model) {
		Aluno aluno = alunoService.find(Aluno.class, id);
		//chamamos a tela de cadastro/editar
		model.addAttribute("aluno", aluno);
		model.addAttribute("banco", Banco.toMap());
		model.addAttribute("curso", Curso.toMap());
		model.addAttribute("action", "editar");
		return "aluno/cadastrar";		
	}
	
	public String atualizarAluno(Integer id,Aluno alunoAtualizado,
			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar"); 
			return "aluno/editar";
		}
		
		DateTime anoIngresso = DateTime.parse(alunoAtualizado.getAnoIngresso());
		if(anoIngresso.isAfterNow()){
			model.addAttribute("anoIngressoError", "Informe um ano menor ou igual ao atual");
			return "aluno/cadastrar";
		}
		
		Aluno aluno = alunoService.find(Aluno.class, id);	
		
		alunoAtualizado.setAuxilioMoradia(aluno.getAuxilioMoradia());
		alunoAtualizado.setIniciacaoAcademica(aluno.getIniciacaoAcademica());
		alunoAtualizado.setPessoa(aluno.getPessoa());
		
		this.alunoService.update(alunoAtualizado);
		redirect.addFlashAttribute("info", "Aluno atualizado com sucesso.");
		return "redirect:/aluno/listar";
	}
	
	@RequestMapping(value = "/{id}/excluir")
	public String excluirAluno(Aluno p, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes
			) {
		Aluno aluno = alunoService.find(Aluno.class, id);
		if (aluno == null) {
			redirectAttributes.addFlashAttribute("erro", "Aluno inexistente.");
		}else{
			
			this.alunoService.delete(aluno);
			redirectAttributes.addFlashAttribute("info", "Aluno excluído com sucesso.");
		}
		return "redirect:/aluno/listar";
	}	
}
