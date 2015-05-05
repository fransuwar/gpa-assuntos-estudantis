package br.ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;

@Controller
@RequestMapping("iniciacaoAcademica")
@SessionAttributes({ "id", "usuario" })
public class IniciacaoAcademicaController {

	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;
	@Inject
	private AlunoService alunoService;

	@RequestMapping(value = "/inscricao", method = RequestMethod.GET)
	public String cadastro(Model modelo) {

		modelo.addAttribute("questionarioIniciacaoAcademica",
				new QuestionarioIniciacaoAcademica());

		modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		modelo.addAttribute("horarioDisponivel", HorarioDisponivel.toMap());
		modelo.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		modelo.addAttribute("totalEstado", Estado.toMap());
		modelo.addAttribute("grauParentesco", GrauParentesco.toMap());

		return "inscricao/iniciacaoAcademica";
	}

	@RequestMapping(value = "/inscricao", method = RequestMethod.POST)
	public String adicionaIniciacaoAcademica(
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica,
			BindingResult result, @ModelAttribute("id") Integer id,
			RedirectAttributes redirect, Model modelo) {

		if (result.hasErrors()) {

			modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			modelo.addAttribute("horarioDisponivel", HorarioDisponivel.toMap());
			modelo.addAttribute("situacaoResidencia",
					SituacaoResidencia.toMap());
			modelo.addAttribute("totalEstado", Estado.toMap());
			modelo.addAttribute("grauParentesco", GrauParentesco.toMap());

			return "inscricao/iniciacaoAcademica";

		} else {

			Aluno aluno = alunoService.getAlunoById(id);

			questionarioIniciacaoAcademica.setAluno(aluno);

			try {
				this.iniciacaoAcademicaService
						.save(questionarioIniciacaoAcademica);
			} catch (PersistenceException e) {
				if (e.getCause() instanceof ConstraintViolationException) {
					redirect.addFlashAttribute("erro",
							"Você já realizou cadastrao nessa seleção.");

					return "redirect:/selecao/listar";
				}
			}
			redirect.addFlashAttribute("info",
					"Cadastro realizado com sucesso.");
		}

		return "redirect:/selecao/listar";
	}
}
