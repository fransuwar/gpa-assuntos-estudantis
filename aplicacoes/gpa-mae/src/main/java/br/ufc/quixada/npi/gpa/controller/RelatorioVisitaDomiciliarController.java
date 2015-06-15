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
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.RelatorioVisitaDomiciliarService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping("relatorioVisita")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO})
public class RelatorioVisitaDomiciliarController {
	
	@Inject
	private RelatorioVisitaDomiciliarService relatorioVisitaService;
	@Inject
	private AlunoService alunoService;
	
	
	@RequestMapping(value="cadastrar/{id}", method = RequestMethod.GET)
	public String cadastrar(@PathVariable("id") Integer id, Model modelo){
		modelo.addAttribute("relatorioVisitaDomiciliar", new RelatorioVisitaDomiciliar());
		modelo.addAttribute("curso", Curso.values());
		modelo.addAttribute("moradiaEstado", EstadoMoradia.values());
		modelo.addAttribute("idAuxilioMoradia", id);
		modelo.addAttribute("aluno", alunoService.find(Aluno.class, id));
		return "/selecao/relatorioVisita";
	}
	
	@RequestMapping(value="/cadastrar/{idAuxilio}", method= RequestMethod.POST)
	public String adicionarRelatorio(@Valid @ModelAttribute("relatorioVisitaDomiciliar") 
	RelatorioVisitaDomiciliar relatorioVisitaDomiciliar, BindingResult result, 
	@ModelAttribute ("idAuxilio") Integer id, RedirectAttributes redirect, Model modelo){
		
		if(id!=null){
			if(result.hasErrors()){
				modelo.addAttribute("relatorioVisitaDomiciliar", relatorioVisitaDomiciliar);
				modelo.addAttribute("curso", Curso.values());
				modelo.addAttribute("moradiaEstado", EstadoMoradia.values());
				modelo.addAttribute("action", "editar");
				return "redirect:/relatorioVisita/cadastrar/"+id;
			}
			
			this.relatorioVisitaService.update(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute("info", "Relatório Atualizado com sucesso.");
			return "redirect:/selecao/inscritos/{id}";
			
		}else if(result.hasErrors()){
			modelo.addAttribute("relatorioVisitaDomiciliar", new RelatorioVisitaDomiciliar());
			modelo.addAttribute("curso", Curso.values());
			modelo.addAttribute("moradiaEstado", EstadoMoradia.values());
			modelo.addAttribute("auxilioMoradia", id);
			
			return "relatorioVisita/Cadastrar";
		}else{
			Aluno aluno = alunoService.getAlunoById(id);
			relatorioVisitaDomiciliar.setAluno(aluno);
			
			this.relatorioVisitaService.save(relatorioVisitaDomiciliar);
			redirect.addFlashAttribute("info", "Relatorio cadastrado com sucesso.");
			
			return "redirect:/selecao/inscritos/{id}";
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
