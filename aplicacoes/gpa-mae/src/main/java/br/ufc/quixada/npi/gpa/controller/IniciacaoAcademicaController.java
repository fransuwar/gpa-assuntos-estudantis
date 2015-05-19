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

import br.ufc.quixada.npi.gpa.enums.DiaUtil;
import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;
import br.ufc.quixada.npi.gpa.service.SelecaoBolsaService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("iniciacaoAcademica")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO })
public class IniciacaoAcademicaController {

	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;

	@Inject
	private AlunoService alunoService;
	
	@Inject
	private SelecaoBolsaService selecaoBolsaService;
	
	@RequestMapping(value = "/{id}/inscricao", method = RequestMethod.GET)
	public String cadastro(@PathVariable("id") Integer id, Model modelo) {

		QuestionarioIniciacaoAcademica q = new QuestionarioIniciacaoAcademica();
		modelo.addAttribute("questionarioIniciacaoAcademica",
				q);
		modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		modelo.addAttribute("turno", Turno.toMap());
		modelo.addAttribute("diasUteis", DiaUtil.toMap());
		modelo.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		modelo.addAttribute("totalEstado", Estado.toMap());
		modelo.addAttribute("grauParentesco", GrauParentesco.toMap());
		
		modelo.addAttribute("selecaoBolsa", id);

		return "inscricao/iniciacaoAcademica";
	}

	@RequestMapping(value = "/{idselecao}/inscricao", method = RequestMethod.POST)
	public String adicionaIniciacaoAcademica(
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica,
			BindingResult result, @ModelAttribute("id") Integer id, @PathVariable("idselecao") Integer idSelecao,
			RedirectAttributes redirect, Model modelo) {

		if (result.hasErrors()) {

			modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			modelo.addAttribute("turno", Turno.toMap());
			modelo.addAttribute("diasUteis", DiaUtil.toMap());
			modelo.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			modelo.addAttribute("totalEstado", Estado.toMap());
			modelo.addAttribute("grauParentesco", GrauParentesco.toMap());
			modelo.addAttribute("selecaoBolsa", id);
			
			return "inscricao/iniciacaoAcademica";

		} else {
			Aluno aluno = alunoService.getAlunoById(id);
			questionarioIniciacaoAcademica.setAluno(aluno);
			
			SelecaoBolsa selecao = selecaoBolsaService.getSelecaoBolsaById(idSelecao);
			questionarioIniciacaoAcademica.setSelecaoBolsa(selecao);
			
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
