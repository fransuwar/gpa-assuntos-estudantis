package br.ufc.quixada.npi.controller;

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

import br.ufc.quixada.npi.model.Aluno;
import br.ufc.quixada.npi.model.Servidor;
import br.ufc.quixada.npi.service.GenericService;

@Named
@RequestMapping("/alunos")
public class AlunoController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private GenericService<Aluno> genericService;
	
	@RequestMapping(value = "{alunoId}", method = RequestMethod.GET)
	public @ResponseBody Aluno getAlunoJson(@PathVariable("alunoId") Long alunoId) {

		return this.genericService.find(Aluno.class,alunoId);

	}

	// Metodo listar alunos
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listaAlunos(Aluno aluno, BindingResult result,
			Map<String, Object> model) {

		try {
			List<Aluno> results = genericService.find(Aluno.class);

			model.put("selections", results);
			return "aluno/alunosList";
		} catch (Exception e) {
			// Mensagem com erro, falta corrigir
			return "aluno/alunosList";
		}

	}
	
	// Metodo Deletar um servidor
		@RequestMapping(value = "/{alunoId}", method = RequestMethod.DELETE)
		public @ResponseBody
		String deletarAluno(@PathVariable("alunoId") Long alunoId) {
			Aluno aluno = this.genericService.find(Aluno.class, alunoId);

			if (aluno == null) {
				/* incluir erros */
				return "erro";
			} else {
				this.genericService.delete(aluno);
				return "ok";
			}

	}
	
	
}
