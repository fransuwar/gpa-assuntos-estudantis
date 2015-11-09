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
			Model model, BindingResult result, RedirectAttributes redirect) throws IOException {

		if(aluno.getId()!=null){
			return atualizarAluno(aluno.getId(), aluno, result, model, redirect);
		}else{
			return adicionarAluno(aluno, result, redirect, model);
		}

	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("action", "cadastrar");
		model.addAttribute("banco", Banco.values());
		model.addAttribute("curso", Curso.values());
		model.addAttribute("aluno", new Aluno());
		return "servidor/cadastrarAluno";
	}

	public String adicionarAluno(
			@Valid @ModelAttribute("aluno") Aluno aluno,
			BindingResult result,RedirectAttributes redirect, Model model) {


		if(aluno.getAnoIngresso() != null && !aluno.getAnoIngresso().equals("")){
			if(aluno.getAnoIngresso().length() < 4){
				result.rejectValue("anoIngresso", "aluno.anoIngresso", "O ano deve possuir pelo menos quatro dígitos");
			} else {
				DateTime anoIngresso = DateTime.parse(aluno.getAnoIngresso());
				if(anoIngresso.isAfterNow()){
					result.rejectValue("anoIngresso", "aluno.anoIngresso", "Informe um ano menor ou igual ao atual");
				}
			}
		}
		
		if(aluno.getMatricula() != null && !aluno.getMatricula().equals("") && aluno.getMatricula().length() < 6){
			result.rejectValue("matricula", "aluno.matricula", "A matrícula deve possuir pelo menos seis dígitos");
		}
		
		if(aluno.getAgencia() != null && !aluno.getAgencia().equals("") && aluno.getAgencia().length() < 6){
			result.rejectValue("agencia", "aluno.agencia", "O número da agência deve possuir pelo menos seis dígitos");
		}
		
		if(aluno.getConta() != null && !aluno.getConta().equals("") && aluno.getConta().length() < 4){
			result.rejectValue("conta", "aluno.conta", "O número da conta deve possuir pelo menos quatro dígitos");
		}

		if (result.hasErrors()) {
			model.addAttribute("banco", Banco.values());
			model.addAttribute("curso", Curso.values());
			return ("servidor/cadastrarAluno");
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
		return "selecao/listarAlunos";
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
		return "/selecao/listarAlunos";
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, Model model) {
		Aluno aluno = alunoService.find(Aluno.class, id);
		//chamamos a tela de cadastro/editar
		model.addAttribute("aluno", aluno);
		model.addAttribute("banco", Banco.values());
		model.addAttribute("curso", Curso.values());
		model.addAttribute("action", "editar");
		return "servidor/cadastrarAluno";		
	}

	public String atualizarAluno(Integer id, Aluno alunoAtualizado,
			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {
		
		if(alunoAtualizado.getAnoIngresso() != null && !alunoAtualizado.getAnoIngresso().equals("")){
			if(alunoAtualizado.getAnoIngresso().length() < 4){
				result.rejectValue("anoIngresso", "aluno.anoIngresso", "O ano deve possuir pelo menos quatro dígitos");
			} else {
				DateTime anoIngresso = DateTime.parse(alunoAtualizado.getAnoIngresso());
				if(anoIngresso.isAfterNow()){
					result.rejectValue("anoIngresso", "aluno.anoIngresso", "Informe um ano menor ou igual ao atual");
				}
			}
		}
		
		if(alunoAtualizado.getMatricula() != null && !alunoAtualizado.getMatricula().equals("") && alunoAtualizado.getMatricula().length() < 6){
			result.rejectValue("matricula", "aluno.matricula", "A matrícula deve possuir pelo menos seis dígitos");
		}
		
		if(alunoAtualizado.getAgencia() != null && !alunoAtualizado.getAgencia().equals("") && alunoAtualizado.getAgencia().length() < 6){
			result.rejectValue("agencia", "aluno.agencia", "O número da agência deve possuir pelo menos seis dígitos");
		}
		
		if(alunoAtualizado.getConta() != null && !alunoAtualizado.getConta().equals("") && alunoAtualizado.getConta().length() < 4){
			result.rejectValue("conta", "aluno.conta", "O número da conta deve possuir pelo menos quatro dígitos");
		}

		if (result.hasErrors()) {
			model.addAttribute("action", "editar"); 
			return "servidor/cadastrarAluno";
		}


		Aluno aluno = alunoService.find(Aluno.class, id);	

		alunoAtualizado.setAuxilioMoradia(aluno.getAuxilioMoradia());
		alunoAtualizado.setIniciacaoAcademica(aluno.getIniciacaoAcademica());
		alunoAtualizado.setPessoa(aluno.getPessoa());

		this.alunoService.update(alunoAtualizado);
		redirect.addFlashAttribute("info", "Aluno atualizado com sucesso.");
		return "redirect:/aluno/listar";
	}

	@RequestMapping(value = "/excluir/{id}")
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
