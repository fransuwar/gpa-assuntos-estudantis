package br.ufc.quixada.npi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.ufc.quixada.npi.model.MoraCom;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia.Estado;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia.FinalidadeVeiculo;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia.GrauParentescoImovelRural;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia.GrauParentescoVeiculos;
import br.ufc.quixada.npi.model.MoraCom;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia.SituacaoImovel;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia.TipoEnsinoFundamental;
import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia.TipoEnsinoMedio;
import br.ufc.quixada.npi.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.model.QuestionarioIniciacaoAcademica.NivelInstrucao;
import br.ufc.quixada.npi.model.Usuario.Uf;
import br.ufc.quixada.npi.service.QuestionarioAuxMoradiaService;


@Controller
@RequestMapping("inscricao")
public class AuxilioMoradiaController {

	@Inject
	private QuestionarioAuxMoradiaService questionarioAuxMoradiaService;
	
	
	
	//private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	throws ServletException {
	binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
	//log.info("controller: questionarioAuxilioMoradia - action: index");
	return "inscricao/auxilio";
	}
	
	@RequestMapping(value = "/auxilio", method = RequestMethod.GET)
	public String cadastro(Model model) {
	model.addAttribute("questionarioAuxilioMoradia", new QuestionarioAuxilioMoradia());
	//model.addAttribute("Uf", Uf.values());
	
	List<MoraCom> moracom = new ArrayList<MoraCom>(Arrays.asList(MoraCom.values()));	
	//MoraCom[] m = MoraCom.values();
	model.addAttribute("moraCom", moracom);
	
	List<Estado> ufs = new ArrayList<Estado>(Arrays.asList(Estado.values()));
	model.addAttribute("ufs", ufs);
	System.out.println(ufs.toString());
	
	List<TipoEnsinoFundamental> tipoEnsinoFundamental = new ArrayList<TipoEnsinoFundamental>(Arrays.asList(TipoEnsinoFundamental.values()));
	model.addAttribute("tipoEnsinoFundamental", tipoEnsinoFundamental);
	
	List<TipoEnsinoMedio> tipoEnsinoMedio = new ArrayList<TipoEnsinoMedio>(Arrays.asList(TipoEnsinoMedio.values()));
	model.addAttribute("tipoEnsinoMedio", tipoEnsinoMedio);
	
	List<SituacaoImovel> situacaoImovel = new ArrayList<SituacaoImovel>(Arrays.asList(SituacaoImovel.values()));
	model.addAttribute("situacaoImovel", situacaoImovel);
		
	List<GrauParentescoImovelRural> grauParentescoImovelRural = new ArrayList<GrauParentescoImovelRural>(Arrays.asList(GrauParentescoImovelRural.values()));
	model.addAttribute("grauParentescoImovelRural", grauParentescoImovelRural);
	
	List<GrauParentescoVeiculos> grauParentescoVeiculos = new ArrayList<GrauParentescoVeiculos>(Arrays.asList(GrauParentescoVeiculos.values()));
	model.addAttribute("grauParentescoVeiculos", grauParentescoVeiculos);
	
	List<FinalidadeVeiculo> finalidadeVeiculo = new ArrayList<FinalidadeVeiculo>(Arrays.asList(FinalidadeVeiculo.values()));
	model.addAttribute("finalidadeVeiculo", finalidadeVeiculo);
	
	model.addAttribute("auxilio", new QuestionarioAuxilioMoradia());
	
	return "inscricao/auxilio";

	
	}
	
	@RequestMapping(value = "/auxilio", method = RequestMethod.POST)
	public String selecaoAluno(
	@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia questionarioAuxilioMoradia,
	BindingResult result, HttpSession session,	RedirectAttributes redirect) {
		
	if (result.hasErrors()) {
	return ("inscricao/auxilio");
	
	}
	this.questionarioAuxMoradiaService.save(questionarioAuxilioMoradia);
	
	return "redirect:/inscricao/index";

}
}
