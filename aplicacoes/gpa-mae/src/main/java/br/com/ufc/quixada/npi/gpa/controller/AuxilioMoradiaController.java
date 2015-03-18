package br.com.ufc.quixada.npi.gpa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

import br.com.ufc.quixada.npi.gpa.model.MoraCom;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
//import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.Estado;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.Finalidade_Veiculo;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.GrauParentescoImovelRural;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.GrauParentescoVeiculos;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.SituacaoImovel;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.TipoEnsinoFundamental;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.TipoEnsinoMedio;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia.Uf;
import br.com.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;

@Controller
@RequestMapping("auxilio")
public class AuxilioMoradiaController {

	@Inject
	private QuestionarioAuxMoradiaService questionarioAuxMoradiaService;


	private Logger log = LoggerFactory.getLogger(this.getClass());

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

		List<Uf> ufs = new ArrayList<Uf>(Arrays.asList(Uf.values()));
		model.addAttribute("ufs", ufs);
		System.out.println(ufs.toString());

		List<TipoEnsinoFundamental> tipoEnsinoFundamental = new ArrayList<TipoEnsinoFundamental>(
				Arrays.asList(TipoEnsinoFundamental.values()));
		model.addAttribute("tipoEnsinoFundamental", tipoEnsinoFundamental);

		List<TipoEnsinoMedio> tipoEnsinoMedio = new ArrayList<TipoEnsinoMedio>(
				Arrays.asList(TipoEnsinoMedio.values()));
		model.addAttribute("tipoEnsinoMedio", tipoEnsinoMedio);

		List<SituacaoImovel> situacaoImovel = new ArrayList<SituacaoImovel>(
				Arrays.asList(SituacaoImovel.values()));
		model.addAttribute("situacaoImovel", situacaoImovel);

		List<GrauParentescoImovelRural> grauParentescoImovelRural = new ArrayList<GrauParentescoImovelRural>(
				Arrays.asList(GrauParentescoImovelRural.values()));
		model.addAttribute("grauParentescoImovelRural",
				grauParentescoImovelRural);

		List<GrauParentescoVeiculos> grauParentescoVeiculos = new ArrayList<GrauParentescoVeiculos>(
				Arrays.asList(GrauParentescoVeiculos.values()));
		model.addAttribute("grauParentescoVeiculos", grauParentescoVeiculos);

		List<Finalidade_Veiculo> finalidadeVeiculo = new ArrayList<Finalidade_Veiculo>(
				Arrays.asList(Finalidade_Veiculo.values()));
		model.addAttribute("finalidadeVeiculo", finalidadeVeiculo);

		model.addAttribute("auxilio", new QuestionarioAuxilioMoradia());

		inicialMoraCom(model);

		

		model.addAttribute("questionarioAuxilioMoradia", new QuestionarioAuxilioMoradia());
			

		model.addAttribute("ufs", Uf.values());
		
		model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.values());
		
		model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.values());
		
		model.addAttribute("situacaoImovel", SituacaoImovel.values());
			
		model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.values());
		
		model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.values());
		
		model.addAttribute("finalidadeVeiculo", Finalidade_Veiculo.values());
		
		model.addAttribute("auxilio", new QuestionarioAuxilioMoradia());	
		
		inicialMoraCom(model);
		
		return "inscricao/auxilio";

	}

	private void inicialMoraCom(Model model) {

		Map<MoraCom, String> moraCom = new TreeMap<MoraCom, String>();
		

		moraCom.put(MoraCom.Pais, "Pais");
		moraCom.put(MoraCom.Pai, "Pai");
		moraCom.put(MoraCom.Mae, "Mãe");
		moraCom.put(MoraCom.Irmaos, "Irmãos");
		moraCom.put(MoraCom.Parentes, "Parentes");
		moraCom.put(MoraCom.Conjuge_Companheiro, "Cônjuge ou Companheiro(a)");
		moraCom.put(MoraCom.Filhos, "Filhos(as)");
		moraCom.put(MoraCom.Outra_moradia, "Outros");

		model.addAttribute("moraCom", moraCom);

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
