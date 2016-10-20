package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.INFO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RELATORIO_VISITA_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_SELECAO_INSCRITOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.RELATORIO_VISITA_DOMICILIAR;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.repository.AlunoRepository;
import br.ufc.quixada.npi.gpa.repository.VisitaDomiciliarRepository;

@Controller
@RequestMapping("relatorioVisita")
public class VisitaDomiciliarController {
	
	@Inject
	private AlunoRepository alunoRepository;
	
	@Inject
	private VisitaDomiciliarRepository visitaRepository;

	@RequestMapping(value="cadastrar/{idAluno}/{idSelecao}", method = RequestMethod.GET)
	public String cadastrar(@PathVariable("idAluno") Integer id,
							@PathVariable("idSelecao") Integer idSelecao, Model modelo){
		
		Aluno aluno = alunoRepository.findById(id);
		modelo.addAttribute(RELATORIO_VISITA_DOMICILIAR, new VisitaDomiciliar());
		modelo.addAttribute(ALUNO, aluno);
		modelo.addAttribute("idSelecao", idSelecao);
		return PAGINA_RELATORIO_VISITA_SELECAO;
	}
	
	@RequestMapping(value="/cadastrar/{idAluno}/{idSelecao}", method= RequestMethod.POST)
	public String adicionarRelatorio(
			@PathVariable("idAluno") Integer idAluno,
			@PathVariable("idSelecao") Integer idSelecao, 
			@Valid @ModelAttribute("relatorioVisitaDomiciliar") VisitaDomiciliar relatorioVisitaDomiciliar,
			BindingResult result, RedirectAttributes redirect, Model modelo){
		
		
		if(result.hasErrors()){
			Aluno aluno = alunoRepository.findById(idAluno);
			modelo.addAttribute(RELATORIO_VISITA_DOMICILIAR, relatorioVisitaDomiciliar);
			modelo.addAttribute(ALUNO, aluno);
			modelo.addAttribute("idSelecao", idSelecao);
			if(relatorioVisitaDomiciliar.getId() != null) 
				modelo.addAttribute("action", "editar");
			return PAGINA_RELATORIO_VISITA_SELECAO;
		}
		
		if(relatorioVisitaDomiciliar.getId() != null){
			
			this.visitaRepository.save(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute(INFO, "Relatório Atualizado com sucesso.");
			return REDIRECT_PAGINA_SELECAO_INSCRITOS+idSelecao;
			
		} else {
			
			this.visitaRepository.save(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute(INFO, "Relatorio cadastrado com sucesso.");
			
			return REDIRECT_PAGINA_SELECAO_INSCRITOS+idSelecao;
		}
	}
	
	@RequestMapping(value="informacoesRelatorio/{id}", method= RequestMethod.GET)
	public String visualizarInformacoes(@PathVariable("id") Integer idRelatorio, Model modelo, RedirectAttributes redirect){
		
		VisitaDomiciliar relatorio= visitaRepository.findById(idRelatorio);
		
		if(relatorio == null){
			redirect.addFlashAttribute("erro", "Relatório não existe");
			return REDIRECT_PAGINA_SELECAO_INSCRITOS+"{id}";
		}
		
		modelo.addAttribute("relatorio",relatorio);
		
		return "selecao/informacoesRelatorio";
	}
}
