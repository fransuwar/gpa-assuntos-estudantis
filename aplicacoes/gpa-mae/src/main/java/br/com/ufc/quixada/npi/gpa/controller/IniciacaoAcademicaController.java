package br.com.ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.com.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;
import br.com.ufc.quixada.npi.gpa.enums.Estado;
import br.com.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.com.ufc.quixada.npi.gpa.enums.HorarioDisponivel;
import br.com.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.com.ufc.quixada.npi.gpa.enums.SituacaoResidencia;

@Controller
@RequestMapping("iniciacaoAcademica")
public class IniciacaoAcademicaController {

	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;

	@RequestMapping(value = "/inscricao", method = RequestMethod.GET)
	public String cadastro(Model modelo) {

		modelo.addAttribute("questionarioIniciacaoAcademica",
				new QuestionarioIniciacaoAcademica());

		modelo.addAttribute("NivelInstrucao", NivelInstrucao.toMap());
		modelo.addAttribute("HorarioDisponivel", HorarioDisponivel.toMap());
		modelo.addAttribute("SituacaoResidencia", SituacaoResidencia.toMap());
		modelo.addAttribute("TotalEstado", Estado.toMap());
		modelo.addAttribute("GrauParentesco", GrauParentesco.toMap());

		return "inscricao/iniciacaoAcademica";
	}

	@RequestMapping(value = "/inscricao", method = RequestMethod.POST)
	public String adicionaIniciacaoAcademica(
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica,
			BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {

			return "redirect:/iniciacaoAcademica/inscricao";

		} else {

			this.iniciacaoAcademicaService.save(questionarioIniciacaoAcademica);
			redirect.addFlashAttribute("info",
					"Projeto cadastrado com sucesso.");
		}

		return "redirect:/selecao/listar";
	}
}
