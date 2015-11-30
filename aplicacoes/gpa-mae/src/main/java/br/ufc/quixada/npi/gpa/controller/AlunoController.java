package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_AUXILIO_MORADIA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.HorarioDisponivelService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.PessoaFamiliaService;
import br.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;
import br.ufc.quixada.npi.gpa.service.QuestionarioIniciacaoAcademicaService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
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

	@Inject
	private SelecaoService selecaoService;

	@Inject
	private InscricaoService inscricaoService;
	@Inject
	private SelecaoService selecaoBolsaService;
	@Inject
	private QuestionarioAuxMoradiaService questionarioAuxMoradiaService;

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
		model.addAttribute("idSelecao", idSelecao);

		return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
	}

	@RequestMapping(value = { "inscricao/iniciacao-academica" }, method = RequestMethod.POST)
	public String realizarInscricaoBIA(@RequestParam("idSelecao") Integer idSelecao,
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica,
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

			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		this.iniciacaoAcademicaService.save(iniciacaoAcademica);
		Inscricao inscricao = new Inscricao();
		inscricao.setQuestionarioIniciacaoAcademica(iniciacaoAcademica);
		// inscricao.setAluno(alunoService.getAlunoByCPF(auth.getName()));
		inscricao.setSelecao(selecaoService.find(Selecao.class, idSelecao));
		inscricao.setData(new DateTime().toDate());
		this.inscricaoService.save(inscricao);

		redirect.addFlashAttribute("info", "Cadastro realizado com sucesso.");

		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	// @RequestMapping(value = {
	// "inscricao/editar/iniciacao-academica/{idInscricao}" }, method =
	// RequestMethod.GET)
	// public String editarInscricaoBIA(@PathVariable("idInscricao") Integer
	// idInscricao, Model model, RedirectAttributes redirect) {
	//
	// QuestionarioIniciacaoAcademica iniciacaoAcademica =
	// this.iniciacaoAcademicaService.getQuestIniAcadById(idInscricao);
	// Selecao selecao = iniciacaoAcademica.getSelecaoBolsa();
	//
	// if (selecao.getStatus() != null &&
	// selecao.getStatus().equals(Status.INSC_ABERTA)) {
	//
	// model.addAttribute("action", "editar");
	// model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
	// model.addAttribute("selecaoBolsa", selecao.getId());
	// model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
	// model.addAttribute("turno", Turno.values());
	// model.addAttribute("diasUteis", DiaUtil.values());
	// model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
	// model.addAttribute("totalEstado", Estado.toMap());
	// model.addAttribute("grauParentesco", GrauParentesco.values());
	//
	// List<HorarioDisponivel> horariosDisponiveis =
	// this.horarioDisponivelService.getHorariosDisponiveisByQuest(iniciacaoAcademica.getId());
	// if (horariosDisponiveis != null) {
	// model.addAttribute("horariosDisponiveis", horariosDisponiveis);
	// }
	//
	// List<PessoaFamilia> pessoasDaFamilia =
	// this.pessoaFamiliaService.getPessoaFamiliaByIdQuestBIA(iniciacaoAcademica.getId());
	// if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
	// model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
	// }
	//
	// } else {
	// redirect.addFlashAttribute("erro", "Só pode editar sua inscrição enquanto
	// a seleção estiver aberta.");
	// return REDIRECT_PAGINA_LISTAR_SELECAO;
	// }
	//
	// return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
	// }

	@RequestMapping(value = { "inscricao/editar/iniciacao-academica" }, method = RequestMethod.POST)
	public String editarInscricaoBIA(
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica,
			BindingResult result, Model model, RedirectAttributes redirect) {

		model.addAttribute("action", "editar");

		if (result.hasErrors()) {

			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.values());
			model.addAttribute("diasUteis", DiaUtil.values());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());

			List<HorarioDisponivel> horariosDisponiveis = this.horarioDisponivelService
					.getHorariosDisponiveisByQuest(iniciacaoAcademica.getId());
			if (horariosDisponiveis != null) {
				model.addAttribute("horariosDisponiveis", horariosDisponiveis);
			}

			List<PessoaFamilia> pessoasDaFamilia = this.pessoaFamiliaService
					.getPessoaFamiliaByIdQuestBIA(iniciacaoAcademica.getId());
			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
			}

			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		this.iniciacaoAcademicaService.update(iniciacaoAcademica);
		redirect.addFlashAttribute("info", "Seleção editada com sucesso.");
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "inscricao/{idSelecao}/auxilio-moradia" }, method = RequestMethod.GET)
	public String inscreverAuxMoradia(@ModelAttribute("id") Integer idPessoa,
			@ModelAttribute("idSelecao") Integer idSelecao, Model model) {

		model.addAttribute("action", "inscricao");
		model.addAttribute("questionarioAuxilioMoradia", new QuestionarioAuxilioMoradia());
		model.addAttribute("estado", Estado.values());
		model.addAttribute("situacaoImovel", SituacaoImovel.values());
		model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.values());
		model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.values());
		model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.values());
		model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.values());
		model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.values());
		model.addAttribute("grauParentesco", GrauParentesco.values());
		model.addAttribute("moraCom", MoraCom.values());
		model.addAttribute("idSelecao", idSelecao);

		return PAGINA_INSCREVER_AUXILIO_MORADIA;
	}

	@RequestMapping(value = { "inscricao/auxilio-moradia" }, method = RequestMethod.POST)
	public String realizarInscricaoAuxMoradia(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia questionarioAuxilioMoradia,
			BindingResult result, @ModelAttribute("id") Integer idPessoa,
			@ModelAttribute("idSelecao") Integer idSelecao, Authentication auth, RedirectAttributes redirect,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("estado", Estado.values());
			model.addAttribute("situacaoImovel", SituacaoImovel.values());
			model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.values());
			model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.values());
			model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.values());
			model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.values());
			model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.values());
			model.addAttribute("moraCom", MoraCom.values());
			model.addAttribute("selecaoBolsa", idSelecao);

			return PAGINA_INSCREVER_AUXILIO_MORADIA;

		} else {

			Aluno aluno = this.alunoService.getAlunoComInscricoes(idPessoa);
			Selecao selecao = selecaoBolsaService.find(Selecao.class, idSelecao);
	
			Inscricao inscricao = new Inscricao();
			inscricao.setSelecao(selecao);
			inscricao.setQuestionarioAuxilioMoradia(questionarioAuxilioMoradia);
			inscricao.setDeferimento(false);
			inscricao.setData(new Date());
			aluno.getInscricoes().add(inscricao);

			
			questionarioAuxMoradiaService.save(questionarioAuxilioMoradia);
			//inscricaoService.save(inscricao);
			alunoService.update(aluno);
			
			
			redirect.addFlashAttribute("info", "Cadastro realizado com sucesso.");
		}

		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "inscricao/editar/auxilio-moradia" }, method = RequestMethod.POST)
	public String editarInscricaoAMOR(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia, Model model,
			BindingResult result, RedirectAttributes redirect) {
		/*
		 * model.addAttribute("action", "editar");
		 * 
		 * if (result.hasErrors()) {
		 * model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
		 * model.addAttribute("selecaoBolsa",
		 * auxilioMoradia.getInscricao().getSelecaoBolsa().getId());
		 * model.addAttribute("estado", Estado.toMap());
		 * model.addAttribute("situacaoImovel", SituacaoImovel.toMap());
		 * model.addAttribute("tipoEnsinoFundamental",
		 * TipoEnsinoFundamental.toMap()); model.addAttribute("tipoEnsinoMedio",
		 * TipoEnsinoMedio.toMap());
		 * model.addAttribute("grauParentescoImovelRural",
		 * GrauParentescoImovelRural.toMap());
		 * model.addAttribute("grauParentescoVeiculos",
		 * GrauParentescoVeiculos.toMap()); model.addAttribute("grauParentesco",
		 * GrauParentesco.toMap()); model.addAttribute("finalidadeVeiculo",
		 * FinalidadeVeiculo.toMap()); model.addAttribute("moraCom",
		 * MoraCom.toMap());
		 * 
		 * List<PessoaFamilia> pessoasDaFamilia =
		 * this.pessoaFamiliaService.getPessoaFamiliaByIdQuestBIA(auxilioMoradia
		 * .getId()); if (pessoasDaFamilia != null &&
		 * !pessoasDaFamilia.isEmpty()) { model.addAttribute("pessoasDaFamilia",
		 * pessoasDaFamilia); }
		 * 
		 * return PAGINA_INSCREVER_AUXILIO_MORADIA;
		 * 
		 * }
		 */

		this.auxilioMoradiaService.update(auxilioMoradia);
		redirect.addFlashAttribute("info", "Seleção editada com sucesso.");
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = "detalhes-inscricaoInic/{id}", method = RequestMethod.GET)
	public String detalhes(@PathVariable("id") Integer id, Model modelo, RedirectAttributes redirect) {
		Inscricao inscricao = inscricaoService.getInscricaoId(id);
		if (inscricao == null) {
			redirect.addFlashAttribute("erro", "seleção Inexistente");
			return "redirect:/selecao/listar";
		}
		modelo.addAttribute("inscricao", inscricao);

		return "aluno/detalhesInscricao";
	}

}
