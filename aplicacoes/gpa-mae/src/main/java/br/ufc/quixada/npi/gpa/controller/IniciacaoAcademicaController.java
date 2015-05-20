package br.ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.DiasUteis;
import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("iniciacaoAcademica")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO })
public class IniciacaoAcademicaController {

	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;

	@Inject
	private AlunoService alunoService;

	@RequestMapping(value = "/inscricao", method = RequestMethod.GET)
	public String cadastro(Model modelo) {

		QuestionarioIniciacaoAcademica q = new QuestionarioIniciacaoAcademica();
		modelo.addAttribute("questionarioIniciacaoAcademica", q);
		modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		modelo.addAttribute("turno", Turno.toMap());
		modelo.addAttribute("diasUteis", DiasUteis.toMap());
		modelo.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		modelo.addAttribute("totalEstado", Estado.toMap());
		modelo.addAttribute("grauParentesco", GrauParentesco.toMap());
		modelo.addAttribute("action", "incricao");

		return "inscricao/iniciacaoAcademica";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(
			@Valid @ModelAttribute(value = "questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica,
			BindingResult result, @ModelAttribute("id") Integer id,
			RedirectAttributes redirect, Model modelo) {
		
		if (id != null) {

			if (result.hasErrors()) {
				modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
				modelo.addAttribute("turno", Turno.toMap());
				modelo.addAttribute("diasUteis", DiasUteis.toMap());
				modelo.addAttribute("situacaoResidencia",
						SituacaoResidencia.toMap());
				modelo.addAttribute("totalEstado", Estado.toMap());
				modelo.addAttribute("grauParentesco", GrauParentesco.toMap());
				return "inscricao/iniciacaoAcademica";
			}
			
			this.iniciacaoAcademicaService
				.update(questionarioIniciacaoAcademica);
			redirect.addFlashAttribute("info",
					"Inscrição atualizada com sucesso.");
			return "redirect:/selecao/listar";

		} else {
			modelo.addAttribute("action", "incricao");
			return adicionaIniciacaoAcademica(questionarioIniciacaoAcademica,
					result, id, redirect, modelo);
		}
	}

	public String adicionaIniciacaoAcademica(
			@Valid @ModelAttribute(value = "questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica,
			BindingResult result, @ModelAttribute("id") Integer id,
			RedirectAttributes redirect, Model modelo) {

		if (result.hasErrors()) {

			modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			modelo.addAttribute("turno", Turno.toMap());
			modelo.addAttribute("diasUteis", DiasUteis.toMap());
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

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id,
			RedirectAttributes redirect, Model model) {

		QuestionarioIniciacaoAcademica q = iniciacaoAcademicaService
				.getQuestIniAcadById(id);

		SelecaoBolsa selecao = q.getSelecaoBolsa();

		if (q.getSelecaoBolsa().getStatus() != null
				&& q.getSelecaoBolsa().getStatus().equals(Status.INSC_ABERTA)) {

			model.addAttribute("questionarioIniciacaoAcademica", q);
			model.addAttribute("selecao", selecao);
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute("diasUteis", DiasUteis.toMap());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			model.addAttribute("action", "editar");

		} else {
			redirect.addFlashAttribute("erro", "Só pode editar sua inscrição enquanto a seleção estiver aberta.");
			return "redirect:/selecao/listar";
		}

		return "inscricao/iniciacaoAcademica";
	}
}
