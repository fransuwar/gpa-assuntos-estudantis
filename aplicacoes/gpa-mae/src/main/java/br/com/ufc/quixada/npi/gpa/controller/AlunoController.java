package br.com.ufc.quixada.npi.gpa.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.model.Aluno;
import br.com.ufc.quixada.npi.gpa.service.AlunoService;


@Controller
@RequestMapping("aluno")
public class AlunoController {

	

	@Inject
	private AlunoService alunoService;
	
	
	@RequestMapping(value = "/listarAluno", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value = "/listarAluno")
	public String listar(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("alunos", alunoService.find(Aluno.class));
		List<Aluno> alunos = alunoService.find(Aluno.class);
		for(Aluno a : alunos){
			System.out.println(a.getMatricula() + "/n");
			
		}
		return "aluno/listarAluno";
	}
	
	
	// Metodo Deletar um servidor
		@RequestMapping(value = "/{alunoId}", method = RequestMethod.DELETE)
		public @ResponseBody
		String deletarAluno(@PathVariable("alunoId") int alunoId) {
			Aluno aluno = this.alunoService.find(Aluno.class, alunoId);

			if (aluno == null) {
				
				return "erro";
			} else {
				this.alunoService.delete(aluno);
				return "ok";
			}
		}
	
	
}
