package br.ufc.quixada.npi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UnknownFormatConversionException;

import javax.inject.Inject;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.pdf.ArabicLigaturizer;

import br.ufc.quixada.npi.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.model.QuestionarioIniciacaoAcademica.NivelInstrucao;
import br.ufc.quixada.npi.model.Usuario.Uf;
import br.ufc.quixada.npi.service.IniciacaoAcademicaService;


@Controller
@RequestMapping("inscricao")
public class IniciacaoAcademicaController {
	
	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return "inscricao/iniciacaoAcademica";
	}
	
	@RequestMapping(value="/iniciacaoAcademica", method = RequestMethod.GET)
	 public String cadastro(Model modelo){
		modelo.addAttribute("QuestionarioIniciacaoAcademica", new QuestionarioIniciacaoAcademica());
		modelo.addAttribute("NivelInstrução", NivelInstrucao.values());
		modelo.addAttribute("Uf", Uf.values());
		
		List<NivelInstrucao> nivelInstrucao = new ArrayList<NivelInstrucao>(Arrays.asList(NivelInstrucao.values()));
		modelo.addAttribute("NivelInstrucao", nivelInstrucao);
		//List<UF> ufs = new ArrayList<UF>(Arrays.asList(Uf.values()));
		System.out.println(nivelInstrucao.toString());
		return "inscricao/iniciacaoAcademica";
	}
	
	@RequestMapping(value="/iniciacaoAcademica", method = RequestMethod.POST)
     public String adicionaAuxilio(@Valid @ModelAttribute("iniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica, BindingResult result, HttpSession session, RedirectAttributes redirect ){
		if(result.hasErrors()){
			return "inscricao/iniciacaoAcademica";
		}
		
		this.iniciacaoAcademicaService.save(iniciacaoAcademica);
		
		return "redirect:/inscricao/index";
	}
}	 
	
	

