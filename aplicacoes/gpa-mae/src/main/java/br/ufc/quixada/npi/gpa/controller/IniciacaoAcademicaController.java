package br.ufc.quixada.npi.gpa.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.DiaUtil;
import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.HorarioDisponivelService;
import br.ufc.quixada.npi.gpa.service.IniciacaoAcademicaService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.QuestionarioIniciacaoAcademicaService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("iniciacaoAcademica")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO })
public class IniciacaoAcademicaController {

	@Inject
	private IniciacaoAcademicaService iniciacaoAcademicaService;

	@Inject
	private AlunoService alunoService;

	@Inject
	private SelecaoService selecaoBolsaService;

	@Inject
	private QuestionarioIniciacaoAcademicaService questionarioIniciacaoAcademicaService;

	@Inject
	private HorarioDisponivelService horarioDisponivelService;
	
	@Inject
	private InscricaoService inscricaoService;
	
	@RequestMapping(value = "/inscricao/{idselecao}", method = RequestMethod.GET)
	public String cadastro(@PathVariable("idselecao") Integer id, Model modelo) {

		QuestionarioIniciacaoAcademica q = new QuestionarioIniciacaoAcademica();
		modelo.addAttribute("questionarioIniciacaoAcademica", q);
		modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		modelo.addAttribute("turno", Turno.toMap());
		modelo.addAttribute("diasUteis", DiaUtil.toMap());
		modelo.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		modelo.addAttribute("totalEstado", Estado.toMap());
		modelo.addAttribute("grauParentesco", GrauParentesco.toMap());
		modelo.addAttribute("idSelecao", id);
		
		return "aluno/InscricaoIniciacaoAcademica";
	}

	@RequestMapping(value = "/inscricao/{idselecao}", method = RequestMethod.POST)
	public String adicionaIniciacaoAcademica(
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica,
			BindingResult result,Authentication authentication, @PathVariable("idselecao") Integer idSelecao,
			RedirectAttributes redirect, Model modelo) {
		if (result.hasErrors()) {
			modelo.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			modelo.addAttribute("turno", Turno.toMap());
			modelo.addAttribute("diasUteis", DiaUtil.toMap());
			modelo.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			modelo.addAttribute("totalEstado", Estado.toMap());
			modelo.addAttribute("grauParentesco", GrauParentesco.toMap());
			modelo.addAttribute("selecaoBolsa", idSelecao);
			return "aluno/InscricaoIniciacaoAcademica";

		} else {
			this.questionarioIniciacaoAcademicaService.save(questionarioIniciacaoAcademica);
			Inscricao inscricao = new Inscricao();
			inscricao.setQuestionarioIniciacaoAcademica(questionarioIniciacaoAcademica);
			inscricao.setAluno(alunoService.getAlunoByCpf(authentication.getName()));
			inscricao.setSelecao(selecaoBolsaService.find(Selecao.class, idSelecao));
			
			this.inscricaoService.save(inscricao);
			
			redirect.addFlashAttribute("info", "Cadastro realizado com sucesso.");
		}

		return "redirect:/selecao/listar";
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, RedirectAttributes redirect, Model model) {

		QuestionarioIniciacaoAcademica q = iniciacaoAcademicaService.getQuestIniAcadById(id);

		/*Selecao selecao = q.getSelecaoBolsa();

		if (q.getSelecaoBolsa().getStatus() != null && q.getSelecaoBolsa().getStatus().equals(Status.INSC_ABERTA)) {

			model.addAttribute("questionarioIniciacaoAcademica", q);
			model.addAttribute("selecaoBolsa", selecao.getId());
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.values());
			model.addAttribute("diasUteis", DiaUtil.values());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());

			List<HorarioDisponivel> horariosDisponiveis = this.horarioDisponivelService
					.getHorariosDisponiveisByQuest(id);
			if (horariosDisponiveis != null)
				model.addAttribute("horariosDisponiveis", horariosDisponiveis);

		} else {
			redirect.addFlashAttribute("erro", "Só pode editar sua inscrição enquanto a seleção estiver aberta.");
			return "redirect:/selecao/listar";
		}

*/


		return "aluno/InscricaoIniciacaoAcademica";

	}

}
