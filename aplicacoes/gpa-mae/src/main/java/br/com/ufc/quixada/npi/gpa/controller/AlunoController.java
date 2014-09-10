package br.com.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.com.ufc.quixada.npi.gpa.service.AlunoService;


@Controller
@RequestMapping("aluno")
public class AlunoController {

	

	@Inject
	private AlunoService alunoService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "aluno/listarAluno";
	}
	
	@RequestMapping(value = "/alunos", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "/aluno/alunos";
	}

	@RequestMapping(value = "/alunos", method = RequestMethod.POST)
	public String adicionarAluno(
			@Valid @ModelAttribute("aluno") Aluno aluno,
			BindingResult result, HttpSession session,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ("aluno/alunos");
		}
		//projeto.setAutor(getUsuarioLogado(session));
		//projeto.setStatus(StatusProjeto.NOVO);
		this.alunoService.save(aluno);

		//String codigo = geraCodigoProjeto(projeto.getId());
		//projeto.setCodigo(codigo);
		this.alunoService.update(aluno);
		redirect.addFlashAttribute("info", "Aluno cadastrado com sucesso.");

		return "redirect:/aluno/listarAluno";

	}

	
	@RequestMapping(value = "/listarAluno", method = RequestMethod.GET)
	public String listaAluno(Aluno aluno, BindingResult result,
			Map<String, Object> model) {
		
		try {
			List<Aluno> results = alunoService.findAll();	
			model.put("alunos", results);
			return "aluno/listarAluno";
		} catch (Exception e) {
			
		 	return "aluno/listarAluno";
			}
		
	}
	
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String atualizarAluno(
			@PathVariable("id") Long id,
			@Valid @ModelAttribute(value = "aluno") Aluno alunoAtualizado,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar");
			return "aluno/editar";
		}
		

		Aluno aluno = alunoService.find(Aluno.class, id);

		aluno.setMatricula(alunoAtualizado.getMatricula());
		

		this.alunoService.update(aluno);
		redirect.addFlashAttribute("info", "Aluno atualizado com sucesso.");
		return "redirect:/aluno/listar";
	}
	

	
}
