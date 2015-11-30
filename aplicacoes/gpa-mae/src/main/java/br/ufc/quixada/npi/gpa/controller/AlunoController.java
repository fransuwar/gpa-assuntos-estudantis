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
import br.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoImovelRural;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;
import br.ufc.quixada.npi.gpa.enums.MoraCom;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.HorarioDisponivelService;
import br.ufc.quixada.npi.gpa.service.PessoaFamiliaService;
import br.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;
import br.ufc.quixada.npi.gpa.service.QuestionarioIniciacaoAcademicaService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("aluno")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO })
public class AlunoController {
	
	@Inject
	private QuestionarioIniciacaoAcademicaService iniciacaoAcademicaService;
	
	@Inject
	private QuestionarioAuxMoradiaService auxilioMoradiaService;

	@Inject
	private HorarioDisponivelService horarioDisponivelService;

	@Inject
	private PessoaFamiliaService pessoaFamiliaService;
	
	@Inject
	private AlunoService alunoService;
	
	@RequestMapping(value = { "inscricao/listar" }, method = RequestMethod.GET)
	public String listarInscricoes(Model model, Authentication auth) {
		
		Aluno aluno = this.alunoService.getAlunoByCPF(auth.getName());
		
		model.addAttribute("inscricoes", aluno.getInscricoes());
		
		return "";
	}

	@RequestMapping(value = { "inscricao/{idSelecao}/iniciacao-academica" }, method = RequestMethod.GET)
	public String realizarInscricaoBIA(@PathVariable("idSelecao") Integer idSelecao, Model model) {
		
		model.addAttribute("action", "inscricao");
		
		model.addAttribute("questionarioIniciacaoAcademica", new QuestionarioIniciacaoAcademica());
		model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		model.addAttribute("turno", Turno.toMap());
		model.addAttribute("diasUteis", DiaUtil.toMap());
		model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		model.addAttribute("totalEstado", Estado.toMap());
		model.addAttribute("grauParentesco", GrauParentesco.toMap());
		
		return "aluno/inscricaoIniciacaoAcademica";
	}
	
	@RequestMapping(value = { "inscricao/iniciacao-academica" }, method = RequestMethod.POST)
	public String realizarInscricaoBIA(@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica, 
			BindingResult result, Model model, RedirectAttributes redirect, Authentication auth) {
		
		model.addAttribute("action", "inscricao");
		
		if (result.hasErrors()) {
			
			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute("diasUteis", DiaUtil.toMap());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			
			return "aluno/inscricaoIniciacaoAcademica";
		}
		
//		Aluno aluno = this.alunoService.getAlunoByCPF(auth.getName());
//		iniciacaoAcademica.setAluno(aluno);
//		Selecao selecao = this.selecaoBolsaService.getSelecaoBolsaComAlunos(idSelecao);
//		selecao.getAlunosSelecao().add(aluno);
//		
//		this.selecaoBolsaService.update(selecao);
//		
//		iniciacaoAcademica.setSelecaoBolsa(selecao);
		
		this.iniciacaoAcademicaService.save(iniciacaoAcademica);
		redirect.addFlashAttribute("info", "Cadastro realizado com sucesso.");
		
		return "redirect:/selecao/listar";

	}

//	@RequestMapping(value = { "inscricao/editar/iniciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
//	public String editarInscricaoBIA(@PathVariable("idInscricao") Integer idInscricao, Model model, RedirectAttributes redirect) {
//		
//		QuestionarioIniciacaoAcademica iniciacaoAcademica = this.iniciacaoAcademicaService.getQuestIniAcadById(idInscricao);
//		Selecao selecao = iniciacaoAcademica.getSelecaoBolsa();
//		
//		if (selecao.getStatus() != null && selecao.getStatus().equals(Status.INSC_ABERTA)) {
//			
//			model.addAttribute("action", "editar");
//			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
//			model.addAttribute("selecaoBolsa", selecao.getId());
//			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
//			model.addAttribute("turno", Turno.values());
//			model.addAttribute("diasUteis", DiaUtil.values());
//			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
//			model.addAttribute("totalEstado", Estado.toMap());
//			model.addAttribute("grauParentesco", GrauParentesco.values());
//			
//			List<HorarioDisponivel> horariosDisponiveis = this.horarioDisponivelService.getHorariosDisponiveisByQuest(iniciacaoAcademica.getId());
//			if (horariosDisponiveis != null) {
//				model.addAttribute("horariosDisponiveis", horariosDisponiveis);
//			}
//			
//			List<PessoaFamilia> pessoasDaFamilia = this.pessoaFamiliaService.getPessoaFamiliaByIdQuestBIA(iniciacaoAcademica.getId());
//			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
//				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
//			}
//			
//		} else {
//			redirect.addFlashAttribute("erro", "Só pode editar sua inscrição enquanto a seleção estiver aberta.");
//			return "redirect:/selecao/listar";
//		}
//		
//		return "aluno/InscricaoIniciacaoAcademica";
//	}
//	
	@RequestMapping(value = { "inscricao/editar/iniciacao-academica" }, method = RequestMethod.POST)
	public String editarInscricaoBIA(@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica,
			BindingResult result,Model model, RedirectAttributes redirect) {
		
		model.addAttribute("action", "editar");
		
		if (result.hasErrors()) {
			
			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.values());
			model.addAttribute("diasUteis", DiaUtil.values());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			
			List<HorarioDisponivel> horariosDisponiveis = this.horarioDisponivelService.getHorariosDisponiveisByQuest(iniciacaoAcademica.getId());
			if (horariosDisponiveis != null) {
				model.addAttribute("horariosDisponiveis", horariosDisponiveis);
			}
			
			List<PessoaFamilia> pessoasDaFamilia = this.pessoaFamiliaService.getPessoaFamiliaByIdQuestBIA(iniciacaoAcademica.getId());
			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
			}
			
			return "aluno/inscricaoIniciacaoAcademica";
		}
		
		this.iniciacaoAcademicaService.update(iniciacaoAcademica);
		redirect.addFlashAttribute("info", "Seleção editada com sucesso.");
		return "redirect:/selecao/listar";
	}
	
	@RequestMapping(value = { "inscricao/{idSelecao}/auxilio-moradia" }, method = RequestMethod.GET)
	public String realizarInscricaoAMOR(@PathVariable("idSelecao") Integer idSelecao, Model model) {
		
		model.addAttribute("action", "inscricao");
		
		model.addAttribute("questionarioAuxilioMoradia", new QuestionarioAuxilioMoradia());
		model.addAttribute("selecaoBolsa", idSelecao);
		model.addAttribute("estado", Estado.toMap());
		model.addAttribute("situacaoImovel", SituacaoImovel.toMap());
		model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.toMap());
		model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.toMap());
		model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.toMap());
		model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.toMap());
		model.addAttribute("grauParentesco", GrauParentesco.toMap());
		model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.toMap());
		model.addAttribute("moraCom", MoraCom.toMap());
		
		return "aluno/inscricaoAuxilio";
	}
	
	@RequestMapping(value = { "inscricao/auxilio-moradia" }, method = RequestMethod.POST)
	public String realizarInscricaoAMOR(@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia, 
			Model model, BindingResult result, RedirectAttributes redirect, Authentication auth) {
		
		model.addAttribute("action", "inscricao");
		
		if (result.hasErrors()) {
			
			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
			model.addAttribute("estado", Estado.toMap());
			model.addAttribute("situacaoImovel", SituacaoImovel.toMap());
			model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.toMap());
			model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.toMap());
			model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.toMap());
			model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.toMap());
			model.addAttribute("moraCom", MoraCom.toMap());
			
			return "aluno/inscricaoAuxilio";
		}
		
//		Aluno aluno = this.alunoService.getAlunoByCPF(auth.getName());
//		auxilioMoradia.setAluno(aluno);
//		SelecaoBolsa selecao = this.selecaoBolsaService.getSelecaoBolsaComAlunos(idSelecao);
//		selecao.getAlunosSelecao().add(aluno);
//		
//		this.selecaoBolsaService.update(selecao);
//		
//		auxilioMoradia.setSelecaoBolsa(selecao);
//		
//		this.auxilioMoradiaService.save(auxilioMoradia);
		redirect.addFlashAttribute("info", "Cadastro realizado com sucesso.");
		
		return "redirect:/selecao/listar";
	}
	
	@RequestMapping(value = { "inscricao/editar/auxilio-moradia/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricaoAMOR(@PathVariable("idInscricao") Integer idInscricao, Model model, RedirectAttributes redirect) {
		
		QuestionarioAuxilioMoradia auxilioMoradia = this.auxilioMoradiaService.getQuestAuxMorById(idInscricao);
		Selecao selecao = auxilioMoradia.getInscricao().getSelecaoBolsa();
		
		if (selecao.getStatus() != null && selecao.getStatus().equals(Status.INSC_ABERTA)) { 
			
			model.addAttribute("action", "editar");
			
			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
			model.addAttribute("selecaoBolsa", selecao.getId());
			model.addAttribute("estado", Estado.toMap());
			model.addAttribute("situacaoImovel", SituacaoImovel.toMap());
			model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.toMap());
			model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.toMap());
			model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.toMap());
			model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.toMap());
			model.addAttribute("moraCom", MoraCom.toMap());
			
			List<PessoaFamilia> pessoasDaFamilia = this.pessoaFamiliaService.getPessoaFamiliaByIdQuestBIA(auxilioMoradia.getId());
			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
			}
			
		} else {
			redirect.addFlashAttribute("erro", "Só pode editar sua inscrição enquanto a seleção estiver aberta.");
			return "redirect:/selecao/listar";
		}
		
		return "aluno/inscricaoAuxilio";
	}
	
	@RequestMapping(value = { "inscricao/editar/auxilio-moradia" }, method = RequestMethod.POST)
	public String editarInscricaoAMOR(@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia, Model model,
			BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "editar");
		
		if (result.hasErrors()) {
			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
			model.addAttribute("selecaoBolsa", auxilioMoradia.getInscricao().getSelecaoBolsa().getId());
			model.addAttribute("estado", Estado.toMap());
			model.addAttribute("situacaoImovel", SituacaoImovel.toMap());
			model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.toMap());
			model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.toMap());
			model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.toMap());
			model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.toMap());
			model.addAttribute("moraCom", MoraCom.toMap());
			
			List<PessoaFamilia> pessoasDaFamilia = this.pessoaFamiliaService.getPessoaFamiliaByIdQuestBIA(auxilioMoradia.getId());
			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
			}
			
			return "aluno/inscricaoAuxilio";
		}
		
		this.auxilioMoradiaService.update(auxilioMoradia);
		redirect.addFlashAttribute("info", "Seleção editada com sucesso.");
		return "redirect:/selecao/listar";

	}
	
}
