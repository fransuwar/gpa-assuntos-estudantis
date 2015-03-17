package br.com.ufc.quixada.npi.gpa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica.Estado;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica.HorarioDisponivel;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica.NivelInstrucao;
import br.com.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica.SituacaoResidencia;
import br.com.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;


@Controller
@RequestMapping("iniciacaoAcademica")
public class IniciacaoAcademicaController {
	
	
	
	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;

	
	@RequestMapping(value="/inscricao", method = RequestMethod.GET)
	 public String cadastro(Model modelo){
		
		modelo.addAttribute("questionarioIniciacaoAcademica", new QuestionarioIniciacaoAcademica());
		
		List<NivelInstrucao> nivelInstrucao = new ArrayList<NivelInstrucao>(Arrays.asList(NivelInstrucao.values()));
		List<HorarioDisponivel> horarioDisponivel = new ArrayList<HorarioDisponivel>(Arrays.asList(HorarioDisponivel.values()));
		List<SituacaoResidencia> situacaoResidencia = new ArrayList<SituacaoResidencia>(Arrays.asList(SituacaoResidencia.values()));
		List<Estado> estado = new ArrayList<Estado>(Arrays.asList(Estado.values()));
		
		modelo.addAttribute("NivelInstrucao", nivelInstrucao);
		modelo.addAttribute("HorarioDisponivel", horarioDisponivel);
		modelo.addAttribute("SituacaoResidencia", situacaoResidencia);
		modelo.addAttribute("TotalEstado", estado);
		
		return "inscricao/iniciacaoAcademica";
	}
	
	
	@RequestMapping(value="/inscricao", method = RequestMethod.POST)
     public String adicionaIniciacaoAcademica(@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica, 
    		 BindingResult result, 
    		 RedirectAttributes redirect ){
		
		if(result.hasErrors()){
			questionarioIniciacaoAcademica = new QuestionarioIniciacaoAcademica();
			return ("inscricao/iniciacaoAcademica");
		
		} else {
		
			this.iniciacaoAcademicaService.save(questionarioIniciacaoAcademica);
			redirect.addFlashAttribute("info", "Projeto cadastrado com sucesso.");
		}
		
		return "redirect:/selecao/listar";
	}
}	 
	
	

