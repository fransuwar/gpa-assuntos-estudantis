package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

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
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping("relatorioVisita")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO})
public class VisitaDomiciliarController {
	
	@Inject
	private AlunoService alunoService;
	
	@Inject
	private InscricaoService inscricaoService;
	
	@Inject
	private SelecaoService selecaoBolsaService;

	@RequestMapping(value="cadastrar/{idAluno}/{idSelecaoBolsa}", method = RequestMethod.GET)
	public String cadastrar(@PathVariable("idAluno") Integer idAluno,
							@PathVariable("idSelecaoBolsa") Integer idSelecaoBolsa, Model model){
		
		Aluno aluno = alunoService.find(Aluno.class, idAluno);
		
		model.addAttribute("relatorioVisitaDomiciliar", new VisitaDomiciliar());
		model.addAttribute("curso", Curso.values());
		model.addAttribute("moradiaEstado", EstadoMoradia.values());
		model.addAttribute("aluno", aluno);
		model.addAttribute("idSelecaoBolsa", idSelecaoBolsa);
		
		return PAGINA_RELATORIO_VISITA;
	}
	
	@RequestMapping(value="/cadastrar/{idAluno}/{idSelecaoBolsa}", method= RequestMethod.POST)
	public String adicionarRelatorio(@PathVariable("idAluno") Integer idAluno,
			@PathVariable("idSelecaoBolsa") Integer idSelecaoBolsa,
			@Valid @ModelAttribute("relatorioVisitaDomiciliar") VisitaDomiciliar relatorioVisitaDomiciliar,
			BindingResult result, RedirectAttributes redirect, Model model) {
		
		
		if(result.hasErrors()){
			
			Aluno aluno = alunoService.find(Aluno.class, idAluno);
			
			model.addAttribute("relatorioVisitaDomiciliar", relatorioVisitaDomiciliar);
			model.addAttribute("curso", Curso.values());
			model.addAttribute("moradiaEstado", EstadoMoradia.values());
			model.addAttribute("aluno", aluno);
			model.addAttribute("idSelecaoBolsa", idSelecaoBolsa);
			
			if(relatorioVisitaDomiciliar.getId() != null) 
				model.addAttribute("action", "editar");
			return PAGINA_RELATORIO_VISITA;
		}
		
		if(relatorioVisitaDomiciliar.getId() != null){
			
			inscricaoService.atualizarVisitaDomiciliar(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute("info", "Relatório Atualizado com sucesso.");
			return REDIRECT_PAGINA_INSCRITOS_SELECAO + idSelecaoBolsa;
			
		} else {
			relatorioVisitaDomiciliar.setAluno(alunoService.getAlunoByIdPessoa(idAluno));
			relatorioVisitaDomiciliar.setSelecaoBolsa(selecaoBolsaService.find(Selecao.class, idSelecaoBolsa));
			
			inscricaoService.salvarVisitaDocimiciliar(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute("info", "Relatorio cadastrado com sucesso.");
			
			return REDIRECT_PAGINA_INSCRITOS_SELECAO  +idSelecaoBolsa;
		}
	}
	
	@RequestMapping(value="informacoesRelatorio/{idRelatorio}", method= RequestMethod.GET)
	public String visualizarInformacoes(@PathVariable("idRelatorio") Integer idRelatorio, Model model, RedirectAttributes redirect){
		
		VisitaDomiciliar relatorio= inscricaoService.getVisitaDocimiciliarByIdVisitaDomiciliar(idRelatorio);
		
		if(relatorio == null){
			redirect.addFlashAttribute("erro", "Relatório não existe");
			return REDIRECT_PAGINA_INSCRITOS_SELECAO;
		}
		
		model.addAttribute("relatorio",relatorio);
		
		return PAGINA_INFORMACOES_RELATORIO;
	}
}
