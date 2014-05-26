package br.quixada.ufc.npi.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.quixada.ufc.npi.model.Aluno;
import br.quixada.ufc.npi.service.AlunoService;

@Named
@RequestMapping("/alunos")
public class AlunoController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private AlunoService as;

	// Metodo listar alunos
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listaAlunos(Aluno aluno, BindingResult result,
			Map<String, Object> model) {

		try {
			List<Aluno> results = as.findAll();

			model.put("selections", results);
			return "aluno/alunosList";
		} catch (Exception e) {
			// Mensagem com erro, falta corrigir
			return "aluno/alunosList";
		}

	}
	
	// Metodo Deletar um aluno
	@RequestMapping(value = "/{alunoId}", method = RequestMethod.DELETE)
	public @ResponseBody String deletarAlunos(@PathVariable("alunoId") int alunoId) {
		Aluno aluno = this.as.findById(alunoId);
		
		if (aluno == null) {
			/*incluir erros*/
			return "erro";
		} else {
			this.as.delete(aluno);
			return "ok";
		}
	}	
	
	
}
