package br.ufc.quixada.npi.gpa.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.DiasUteis;
import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;

@Controller
@RequestMapping("iniciacaoAcademica")
public class IniciacaoAcademicaController {

	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;

	@RequestMapping(value = "/inscricao", method = RequestMethod.GET)
	public String cadastro(Model modelo) {

		QuestionarioIniciacaoAcademica q = new QuestionarioIniciacaoAcademica();
		modelo.addAttribute("questionarioIniciacaoAcademica",
				q);

		modelo.addAttribute("NivelInstrucao", NivelInstrucao.toMap());
		modelo.addAttribute("Turno", Turno.toMap());
		modelo.addAttribute("DiasUteis", DiasUteis.toMap());
		modelo.addAttribute("SituacaoResidencia", SituacaoResidencia.toMap());
		modelo.addAttribute("TotalEstado", Estado.toMap());
		modelo.addAttribute("grauParentesco", GrauParentesco.toMap());

		return "inscricao/iniciacaoAcademica";
	}

	@RequestMapping(value = "/inscricao", method = RequestMethod.POST)
	public String adicionaIniciacaoAcademica(
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica,
			BindingResult result, RedirectAttributes redirect, Model modelo) {

		if (result.hasErrors()) {
			modelo.addAttribute("NivelInstrucao", NivelInstrucao.toMap());
			modelo.addAttribute("Turno", Turno.toMap());
			modelo.addAttribute("DiasUteis", DiasUteis.toMap());
			modelo.addAttribute("SituacaoResidencia", SituacaoResidencia.toMap());
			modelo.addAttribute("TotalEstado", Estado.toMap());
			modelo.addAttribute("grauParentesco", GrauParentesco.toMap());
			return "inscricao/iniciacaoAcademica";
		} else {
			
//			questionarioIniciacaoAcademica.setHorariodisponivelBolsa(new ArrayList<HorarioDisponivel>());
//			HorarioDisponivel h = new HorarioDisponivel();
//			h.setDia(SEG);
//			questionarioIniciacaoAcademica.getHorariodisponivelBolsa().add(h);

			this.iniciacaoAcademicaService.save(questionarioIniciacaoAcademica);
			redirect.addFlashAttribute("info",
					"Incrição realizada com sucesso.");
		}

		return "redirect:/selecao/listar";
	}
}
