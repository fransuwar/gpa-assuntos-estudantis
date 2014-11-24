package br.com.ufc.quixada.npi.gpa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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
import br.com.ufc.quixada.npi.gpa.service.PessoaService;


@Controller
@RequestMapping("iniciacaoAcademica")
public class IniciacaoAcademicaController {
	
	
	
	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;
	
	@Inject
	private PessoaService servicePessoa;

	
	@RequestMapping(value="/inscricao", method = RequestMethod.GET)
	 public String cadastro(Model modelo){
		modelo.addAttribute("questionarioIniciacaoAcademica", new QuestionarioIniciacaoAcademica());
		//modelo.addAttribute("NivelInstrução", NivelInstrucao.values());
		
		
		List<NivelInstrucao> nivelInstrucao = new ArrayList<NivelInstrucao>(Arrays.asList(NivelInstrucao.values()));
		List<HorarioDisponivel> horarioDisponivel = new ArrayList<HorarioDisponivel>(Arrays.asList(HorarioDisponivel.values()));
		List<SituacaoResidencia> situacaoResidencia = new ArrayList<SituacaoResidencia>(Arrays.asList(SituacaoResidencia.values()));
		List<Estado> estado = new ArrayList<Estado>(Arrays.asList(Estado.values()));
		modelo.addAttribute("NivelInstrucao", nivelInstrucao);
		modelo.addAttribute("HorarioDisponivel", horarioDisponivel);
		modelo.addAttribute("SituacaoResidencia", situacaoResidencia);
		modelo.addAttribute("TotalEstado", estado);
		
		System.out.println(nivelInstrucao.toString());
		System.out.println(estado.toString());
		
		return "inscricao/iniciacaoAcademica";
	}
	
	
	@RequestMapping(value="/inscricao", method = RequestMethod.POST)
     public String adicionaAuxilio(@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica, BindingResult result, HttpSession session, RedirectAttributes redirect ){
		if(result.hasErrors()){
			return ("inscricao/iniciacaoAcademica");
		}
		
		this.iniciacaoAcademicaService.save(questionarioIniciacaoAcademica);
		redirect.addFlashAttribute("info", "Projeto cadastrado com sucesso.");
		
		return "redirect:/selecao/listar";
	}
}	 
	
	

