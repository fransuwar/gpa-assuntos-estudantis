package br.com.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.com.ufc.quixada.npi.gpa.service.AlunoService;


@Controller
@RequestMapping("aluno")
public class AlunoController {

	@Inject
	private AlunoService alunoService;
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "/aluno/cadastrar";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adicionarAluno(
			@Valid @ModelAttribute("aluno") Aluno aluno,
			BindingResult result,RedirectAttributes redirect) {
		
		if (result.hasErrors()) {
			return ("aluno/cadastrar");
		}
		
		this.alunoService.save(aluno);
		this.alunoService.update(aluno);
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
	public String listarAluno(@RequestParam("matricula") String matricula, Model model) {
		List<Aluno> results = new ArrayList<Aluno>();
		results.add(alunoService.getAlunoByMatricula(matricula));
		model.addAttribute("alunos", results);
		
		return "/aluno/listar";
	}
	
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, Model model) {
		Aluno aluno = alunoService.find(Aluno.class, id);
		
		{
			model.addAttribute("aluno", aluno);
			model.addAttribute("action", "editar");
			return "aluno/editar";
		}
	}
	
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
	public String atualizarAluno(@PathVariable("id") Integer id,
			@Valid @ModelAttribute(value = "aluno") Aluno alunoAtualizado,
			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar");
			return "aluno/editar";
		}
	
		Aluno aluno = alunoService.find(Aluno.class, id);	
		aluno.setMatricula(alunoAtualizado.getMatricula());
		aluno.setAnoIngresso(alunoAtualizado.getAnoIngresso());
		aluno.setIra(alunoAtualizado.getIra());
		aluno.setCurso(alunoAtualizado.getCurso());
		aluno.setBanco(alunoAtualizado.getBanco());
		aluno.setAgencia(alunoAtualizado.getAgencia());
		aluno.setConta(alunoAtualizado.getConta());
		
		this.alunoService.update(aluno);
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
