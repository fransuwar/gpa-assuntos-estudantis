package br.ufc.quixada.npi.gpa.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoImovelRural;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;
import br.ufc.quixada.npi.gpa.enums.MoraCom;
import br.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("auxilio")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO })
public class AuxilioMoradiaController {

	@Inject
	private QuestionarioAuxMoradiaService questionarioAuxMoradiaService;
	@Inject
	private AlunoService alunoService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";

	}

	@RequestMapping(value = "/inscricao", method = RequestMethod.GET)
	public String cadastro(Model model) {

		model.addAttribute("questionarioAuxilioMoradia",
				new QuestionarioAuxilioMoradia());
		model.addAttribute("estado", Estado.toMap());
		model.addAttribute("situacaoImovel", SituacaoImovel.toMap());
		model.addAttribute("tipoEnsinoFundamental",
				TipoEnsinoFundamental.toMap());
		model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.toMap());
		model.addAttribute("grauParentescoImovelRural",
				GrauParentescoImovelRural.toMap());
		model.addAttribute("grauParentescoVeiculos",
				GrauParentescoVeiculos.toMap());
		model.addAttribute("grauParentesco", GrauParentesco.toMap());
		model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.toMap());
		model.addAttribute("moraCom", MoraCom.toMap());

		return "inscricao/auxilio";
	}

	@RequestMapping(value = "/inscricao", method = RequestMethod.POST)
	public String selecaoAluno(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia questionarioAuxilioMoradia,
			BindingResult result, @ModelAttribute("id") Integer id,
			RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("estado", Estado.toMap());
			model.addAttribute("situacaoImovel", SituacaoImovel.toMap());
			model.addAttribute("tipoEnsinoFundamental",
					TipoEnsinoFundamental.toMap());
			model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.toMap());
			model.addAttribute("grauParentescoImovelRural",
					GrauParentescoImovelRural.toMap());
			model.addAttribute("grauParentescoVeiculos",
					GrauParentescoVeiculos.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.toMap());
			model.addAttribute("moraCom", MoraCom.toMap());
			return "inscricao/auxilio";
			
		} else {

			Aluno aluno = alunoService.getAlunoById(id);

			questionarioAuxilioMoradia.setAluno(aluno);
			
			try {
				this.questionarioAuxMoradiaService
						.save(questionarioAuxilioMoradia);
				questionarioAuxilioMoradia.setDataInscricao(new Date());
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
