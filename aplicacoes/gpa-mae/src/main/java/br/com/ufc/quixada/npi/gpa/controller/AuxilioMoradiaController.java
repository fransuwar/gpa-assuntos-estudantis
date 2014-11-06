package br.com.ufc.quixada.npi.gpa.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ufc.quixada.npi.gpa.model.MoraCom;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.Estado;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.FinalidadeVeiculo;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.GrauParentescoImovelRural;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.GrauParentescoVeiculos;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.SituacaoImovel;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.TipoEnsinoFundamental;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.TipoEnsinoMedio;
import br.com.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;

@Controller
@RequestMapping("inscricao/auxilio")
public class AuxilioMoradiaController {

	@Inject
	private QuestionarioAuxMoradiaService questionarioAuxMoradiaService;
	
	
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cadastro(Model model) {
	model.addAttribute("questionarioAuxilioMoradia", new QuestionarioAuxilioMoradia());
	
	
	
	model.addAttribute("ufs", Estado.values());
	
	model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.values());
	
	model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.values());
	
	model.addAttribute("situacaoImovel", SituacaoImovel.values());
		
	model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.values());
	
	model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.values());
	
	model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.values());
	
	model.addAttribute("auxilio", new QuestionarioAuxilioMoradia());	
	
	inicialMoraCom(model);
	
	return "inscricao/auxilio";
	
	
	}
	
	private void inicialMoraCom(Model model){
		
		Map<MoraCom, String> moraCom = new TreeMap<MoraCom, String>();
		
		moraCom.put(MoraCom.Pais, " Pais ");
		moraCom.put(MoraCom.Pai, " Pai ");
		moraCom.put(MoraCom.Mae, " Mãe ");
		moraCom.put(MoraCom.Irmaos, " Irmãos ");
		moraCom.put(MoraCom.Parentes, " Parentes ");
		moraCom.put(MoraCom.Conjuge_Companheiro, " Conjuge ou Companheiro ");
		moraCom.put(MoraCom.Filhos, " Filhos ");
		moraCom.put(MoraCom.Outra_moradia, " Outra Moradia ");

		model.addAttribute("moraCom", moraCom);		
		
		
		
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String selecaoAluno(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia questionarioAuxilioMoradia,
			BindingResult result, Model model) {

		System.out.println();

		if (result.hasErrors()) {
			return ("inscricao/auxilio");
		} else {
			this.questionarioAuxMoradiaService.save(questionarioAuxilioMoradia);
		}
		return "redirect:/";

	}
		
}
