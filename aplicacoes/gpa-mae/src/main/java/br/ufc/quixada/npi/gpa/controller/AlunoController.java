package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;

import br.ufc.quixada.npi.gpa.model.Inscricao;

import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("aluno")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO })
public class AlunoController {

	@Inject
	private AlunoService alunoService;

	@Inject
	private SelecaoService selecaoService;

	@Inject
	private InscricaoService inscricaoService;

	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(Model model, HttpServletRequest request, Authentication auth) {

		List<Selecao> selecoes = selecaoService.find(Selecao.class);

		Aluno aluno = alunoService.getAlunoComInscricoes(auth.getName());

		model.addAttribute("selecoes", selecoes);
		model.addAttribute("aluno", aluno);
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_SELECOES_ABERTAS;

	}

	@RequestMapping(value = { "inscricao/{idSelecao}/iniciacao-academica" }, method = RequestMethod.GET)
	public String realizarInscricaoIniciacaoAcademica(@PathVariable("idSelecao") Integer idSelecao, Model model) {

		model.addAttribute("action", "inscricao");

		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);

		model.addAttribute("questionarioIniciacaoAcademica", new QuestionarioIniciacaoAcademica());
		model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		model.addAttribute("turno", Turno.toMap());
		model.addAttribute("diasUteis", DiaUtil.toMap());
		model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		model.addAttribute("totalEstado", Estado.toMap());
		model.addAttribute("grauParentesco", GrauParentesco.toMap());
		model.addAttribute("selecao", selecao);

		return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
	}

	@RequestMapping(value = { "inscricao/iniciacao-academica" }, method = RequestMethod.POST)
	public String realizarInscricaoIniciacaoAcademica(@RequestParam("idSelecao") Integer idSelecao,
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
			model.addAttribute("idSelecao", idSelecao);
			model.addAttribute("selecao", selecaoService.find(Selecao.class, idSelecao));

			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
		Aluno aluno = alunoService.getAlunoByCPF(auth.getName());

		Inscricao inscricao = new Inscricao();

		inscricao.setData(new Date());

		inscricao.setAluno(aluno);
		inscricao.setSelecao(selecao);
		inscricao.setQuestionarioIniciacaoAcademica(iniciacaoAcademica);

		inscricaoService.save(inscricao);

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_REALIZADA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "inscricao/editar/iniciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricaoIniciacaoAcademica(@PathVariable("idInscricao") Integer idInscricao, Model model,
			RedirectAttributes redirect) {

		// TODO - Método p/ implementar que retorna página de formulário de
		// inscrição em iniciação acadêmica.

		return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
	}

	@RequestMapping(value = { "inscricao/editar/iniciacao-academica" }, method = RequestMethod.POST)
	public String editarInscricaoIniciacaoAcademica(
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

			List<HorarioDisponivel> horariosDisponiveis = inscricaoService
					.getHorariosDisponiveisIniciacaoAcademica(iniciacaoAcademica.getId());
			if (horariosDisponiveis != null) {
				model.addAttribute("horariosDisponiveis", horariosDisponiveis);
			}

			List<PessoaFamilia> pessoasDaFamilia = inscricaoService
					.getPessoaFamiliaByIdIniciacaoAcademica(iniciacaoAcademica.getId());

			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
			}

			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		// TODO - Realizar a atualização de uma iniciação acadêmica.
		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_EDITADA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "inscricao/{idSelecao}/auxilio-moradia" }, method = RequestMethod.GET)
	public String realizarInscricaoAuxilioMoradia(@PathVariable("idSelecao") Integer idSelecao, Model model) {

		model.addAttribute("action", "inscricao");

		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);

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
		model.addAttribute("selecao", selecao);

		return PAGINA_INSCREVER_AUXILIO_MORADIA;
	}

	@RequestMapping(value = { "inscricao/auxilio-moradia" }, method = RequestMethod.POST)
	public String realizarInscricaoAuxilioMoradia(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia,
			BindingResult result, @RequestParam("idSelecao") Integer idSelecao, Authentication auth,
			RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {

			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
			model.addAttribute("estado", Estado.values());
			model.addAttribute("situacaoImovel", SituacaoImovel.values());
			model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.values());
			model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.values());
			model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.values());
			model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.values());
			model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.values());
			model.addAttribute("moraCom", MoraCom.values());
			model.addAttribute("idSelecao", idSelecao);
			model.addAttribute("selecao", selecaoService.find(Selecao.class, idSelecao));

			return PAGINA_INSCREVER_AUXILIO_MORADIA;

		} else {

			Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
			Aluno aluno = alunoService.getAlunoByCPF(auth.getName());

			Inscricao inscricao = new Inscricao();

			inscricao.setData(new Date());

			inscricao.setAluno(aluno);
			inscricao.setSelecao(selecao);
			inscricao.setQuestionarioAuxilioMoradia(auxilioMoradia);

			inscricaoService.save(inscricao);

			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_REALIZADA);
		}

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_REALIZADA);

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "inscricao/editar/auxilio-moradia/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricaoAuxilioMoradia(@PathVariable("idInscricao") Integer idInscricao, Model model,
			RedirectAttributes redirect) {

		// TODO - Método p/ implementar que retorna página de edição do
		// formulário de inscrição em auxílio moradia.

		return PAGINA_INSCREVER_AUXILIO_MORADIA;

	}

	@RequestMapping(value = { "inscricao/editar/auxilio-moradia" }, method = RequestMethod.POST)
	public String editarInscricaoAuxilioMoradia(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia, Model model,
			BindingResult result, RedirectAttributes redirect) {

		// TODO - Método p/ implementar que salva a edição de um formulário em
		// uma incrição auxílio moradia.

		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "inscricao/listar" }, method = RequestMethod.GET)
	public String listarInscricoes(Model model, Authentication auth) {

		Aluno aluno = alunoService.getAlunoComInscricoes(auth.getName());

		model.addAttribute("aluno", aluno);
		model.addAttribute("inscricoes", aluno.getInscricoes());

		return PAGINA_INSCRICOES_ALUNO;

	}

	@RequestMapping(value = "/inscricao/excluir/{idAluno}/{idInscricao}", method = RequestMethod.GET)
	public String excluirInscricao(@PathVariable("idAluno") Integer idAluno,
			@PathVariable("idInscricao") Integer idInscricao, RedirectAttributes redirectAttributes) {

		Inscricao inscricao = this.inscricaoService.find(Inscricao.class, idInscricao);

		if (inscricao == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_ALUNO_NAO_ENCONTRADO);
		} else {
			this.inscricaoService.delete(inscricao);
			redirectAttributes.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA);
		}

		return "redirect:/aluno/inscricao/listar/{idAluno}";

	}

	@RequestMapping(value = { "detalhes/inciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricaoIniciacaoAcademica(@PathVariable("idInscricao") Integer idInscricao, Model modelo,
			RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);

		if (inscricao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE);

			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		modelo.addAttribute("inscricao", inscricao);

		return PAGINA_DETALHES_INSCRICAO;
	}

	@RequestMapping(value = "detalhes-inscricaoAuxMor/{id}")
	public String detalhesInscricaoAuxilioMoradia(@PathVariable("id") Integer id, Model modelo,
			RedirectAttributes redirect) {
		Inscricao inscricao = inscricaoService.find(Inscricao.class, id);
		if (inscricao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}

		modelo.addAttribute("inscricao", inscricao);
		modelo.addAttribute("questAuxMor", inscricao.getQuestionarioAuxilioMoradia());
		return PAGINA_VISUALIZAR_INSC_AUX_MOR;
	}

}
