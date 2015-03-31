package br.com.ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.enums.Estado;
import br.com.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.com.ufc.quixada.npi.gpa.enums.GrauParentescoImovelRural;
import br.com.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;
import br.com.ufc.quixada.npi.gpa.enums.MoraCom;
import br.com.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.com.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.com.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.com.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;

@Controller
@RequestMapping("auxilio")
public class AuxilioMoradiaController {

	@Inject
	private QuestionarioAuxMoradiaService questionarioAuxMoradiaService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
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

		model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.toMap());

		model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.toMap());

		model.addAttribute("grauParentescoImovelRural",
				GrauParentescoImovelRural.toMap());

		model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.toMap());

		model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.toMap());
		
		model.addAttribute("moraCom", MoraCom.toMap());
		
		return "inscricao/auxilio";

	}

	@RequestMapping(value = "/inscricao", method = RequestMethod.POST)
	public String selecaoAluno(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia questionarioAuxilioMoradia,
			BindingResult result, RedirectAttributes redirect) {

		System.out.println();

		if (result.hasErrors()) {
			return ("inscricao/auxilio");
		} else {
			this.questionarioAuxMoradiaService.save(questionarioAuxilioMoradia);
			redirect.addFlashAttribute("info", "Inscrição realizada com sucesso.");
		}
		return "redirect:/selecao/listar";
		

	}

}
