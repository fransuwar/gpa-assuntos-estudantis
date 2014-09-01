package br.com.ufc.quixada.npi.gpa.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/aluno")
public class AlunoController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private AlunoService alunoService;
	
	
	@RequestMapping(value = "/alunos", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "aluno/alunos";
	}

	
	
	
	
	
	/*@RequestMapping(value = "{alunoId}", method = RequestMethod.GET)
	public @ResponseBody
	Aluno getAlunoJson(@PathVariable("alunoId") int alunoId) {

		return this.alunoService.find(Aluno.class,alunoId);

	}


	
	@RequestMapping(value = "/alunos", method = RequestMethod.GET)
	public String listaAlunos() {

		//try {
			//List<Aluno> results = alunoService.find(Aluno.class);

			//mod	el.put("selections", results);
		System.out.println("controller aluno - listaAlunos");
			return "aluno/alunos";
		//} catch (Exception e) {
			// Mensagem com erro, falta corrigir
			//return "aluno/alunos";
		//}

	}
	*/
	
	// Metodo Deletar um servidor
		@RequestMapping(value = "/{alunoId}", method = RequestMethod.DELETE)
		public @ResponseBody
		String deletarAluno(@PathVariable("alunoId") int alunoId) {
			Aluno aluno = this.alunoService.find(Aluno.class, alunoId);

			if (aluno == null) {
				/* incluir erros */
				return "erro";
			} else {
				this.alunoService.delete(aluno);
				return "ok";
			}
		}
	
	
}
