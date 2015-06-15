package br.ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.RelatorioVisitaDomiciliar;
import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.RelatorioVisitaDomiciliarService;
import br.ufc.quixada.npi.gpa.service.SelecaoBolsaService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping("relatorioVisita")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO})
public class RelatorioVisitaDomiciliarController {
	
	@Inject
	private RelatorioVisitaDomiciliarService relatorioVisitaService;
	@Inject
	private AlunoService alunoService;
	@Inject
	private SelecaoBolsaService selecaoBolsaService;
	

	@RequestMapping(value="cadastrar/{idAluno}/{idSelecaoBolsa}", method = RequestMethod.GET)
	public String cadastrar(@PathVariable("idAluno") Integer id,
							@PathVariable("idSelecaoBolsa") Integer idSelecaoBolsa, Model modelo){
		Aluno aluno = alunoService.find(Aluno.class, id);
		modelo.addAttribute("relatorioVisitaDomiciliar", new RelatorioVisitaDomiciliar());
		modelo.addAttribute("curso", Curso.values());
		modelo.addAttribute("moradiaEstado", EstadoMoradia.values());
		modelo.addAttribute("aluno", aluno);
		modelo.addAttribute("idSelecaoBolsa", idSelecaoBolsa);
		return "/selecao/relatorioVisita";
	}
	
	@RequestMapping(value="/cadastrar/{idAluno}/{idSelecaoBolsa}", method= RequestMethod.POST)
	public String adicionarRelatorio(
			@PathVariable("idAluno") Integer idAluno,
			@PathVariable("idSelecaoBolsa") Integer idSelecaoBolsa, 
			@Valid @ModelAttribute("relatorioVisitaDomiciliar") RelatorioVisitaDomiciliar relatorioVisitaDomiciliar,
			BindingResult result, RedirectAttributes redirect, Model modelo){
		
		
		if(result.hasErrors()){
			Aluno aluno = alunoService.find(Aluno.class, idAluno);
			modelo.addAttribute("relatorioVisitaDomiciliar", relatorioVisitaDomiciliar);
			modelo.addAttribute("curso", Curso.values());
			modelo.addAttribute("moradiaEstado", EstadoMoradia.values());
			modelo.addAttribute("aluno", aluno);
			modelo.addAttribute("idSelecaoBolsa", idSelecaoBolsa);
			if(relatorioVisitaDomiciliar.getId() != null) 
				modelo.addAttribute("action", "editar");
			return "/selecao/relatorioVisita";
		}
		
		if(relatorioVisitaDomiciliar.getId() != null){
			
			this.relatorioVisitaService.update(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute("info", "Relatório Atualizado com sucesso.");
			return "redirect:/selecao/inscritos/"+idSelecaoBolsa;
			
		} else {
			relatorioVisitaDomiciliar.setAluno(alunoService.getAlunoById(idAluno));
			relatorioVisitaDomiciliar.setSelecaoBolsa(selecaoBolsaService.find(SelecaoBolsa.class, idSelecaoBolsa));
			
			this.relatorioVisitaService.save(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute("info", "Relatorio cadastrado com sucesso.");
			
			return "redirect:/selecao/inscritos/"+idSelecaoBolsa;
		}
	}
	
	@RequestMapping(value="/informacoesRelatorio/{id}", method= RequestMethod.GET)
	public String visualizarInformacoes(@PathVariable("id") Integer id, Model modelo, RedirectAttributes redirect){
		
		RelatorioVisitaDomiciliar relatorio= relatorioVisitaService.find(RelatorioVisitaDomiciliar.class, id);
		
		/*if(relatorio == null){
			redirect.addFlashAttribute("erro", "Relatório não existe");
			return "redirect:/selecao/inscritos/{id}";
		}
		
		modelo.addAttribute(relatorio);
		*/
		return "/selecao/informacoesRelatorio";
	}//Rever método
}
