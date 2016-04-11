package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

import java.util.ArrayList;

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
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

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
	
	@Inject
	private UsuarioService usuarioService;

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
		model.addAttribute("grauParentesco", GrauParentesco.values());
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
			model.addAttribute("grauParentesco", GrauParentesco.values());
			model.addAttribute("idSelecao", idSelecao);
			model.addAttribute("selecao", selecaoService.find(Selecao.class, idSelecao));

			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
		Aluno aluno = alunoService.getAlunoByCPF(auth.getName());

		if (inscricaoService.getInscricao(selecao, aluno) == null) {
			Inscricao inscricao = new Inscricao();

			inscricao.setData(new Date());

			inscricao.setAluno(aluno);
			inscricao.setSelecao(selecao);
			inscricao.setQuestionarioIniciacaoAcademica(iniciacaoAcademica);

			inscricaoService.save(inscricao);
		} else {
			redirect.addFlashAttribute("error", MENSAGEM_ERRO_INSCRICAO_EXISTENTE_NA_SELECAO);
			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_REALIZADA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "inscricao/editar/iniciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricaoIniciacaoAcademica(@PathVariable("idInscricao") Integer idInscricao, Model model,
			RedirectAttributes redirect) {

		model.addAttribute("action", "editar");

		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);

		model.addAttribute("inscricao", inscricao);
		model.addAttribute("questionarioIniciacaoAcademica", inscricao.getQuestionarioIniciacaoAcademica());
		model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		model.addAttribute("turno", Turno.toMap());
		model.addAttribute("diasUteis", DiaUtil.toMap());
		model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		model.addAttribute("totalEstado", Estado.toMap());
		model.addAttribute("grauParentesco", GrauParentesco.values());
		model.addAttribute("selecao", inscricao.getSelecao());

		return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
	}

	@RequestMapping(value = { "inscricao/editar/iniciacao-academica" }, method = RequestMethod.POST)
	public String editarInscricaoIniciacaoAcademica(
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica,
			BindingResult result, Model model, RedirectAttributes redirect) {

		model.addAttribute("action", "editar");

		if (result.hasErrors()) {

			model.addAttribute("action", "editar");

			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.values());
			model.addAttribute("diasUteis", DiaUtil.values());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.values());

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
	public String realizarInscricaoAuxilioMoradia(@PathVariable("idSelecao") Integer idSelecao, Model model, Authentication auth) {

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
		model.addAttribute("usuarioAtivo", usuarioService.getByCpf(auth.getName()));

		return PAGINA_INSCREVER_AUXILIO_MORADIA;
	}

	@RequestMapping(value = { "inscricao/auxilio-moradia" }, method = RequestMethod.POST)
	public String realizarInscricaoAuxilioMoradia(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia,
			BindingResult result, @RequestParam("mora") List<String> comQuemMora,
			@RequestParam("idSelecao") Integer idSelecao, Authentication auth, RedirectAttributes redirect,
			Model model) {

		List<ComQuemMora> comQuemMoraList = new ArrayList<ComQuemMora>();
		for (String m : comQuemMora) {
			ComQuemMora mora = inscricaoService.getComQuemMora(MoraCom.valueOf(m));
			comQuemMoraList.add(mora);
		}

		auxilioMoradia.setComQuemMora(comQuemMoraList);

		if (result.hasErrors()) {

			model.addAttribute("action", "inscricao");

			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
			model.addAttribute("estado", Estado.values());
			model.addAttribute("situacaoImovel", SituacaoImovel.values());
			model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.values());
			model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.values());
			model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.values());
			model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.values());
			model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.values());
			model.addAttribute("moraCom", MoraCom.values());
			model.addAttribute("grauParentesco", GrauParentesco.values());
			model.addAttribute("idSelecao", idSelecao);
			model.addAttribute("selecao", selecaoService.find(Selecao.class, idSelecao));

			return PAGINA_INSCREVER_AUXILIO_MORADIA;

		} else {

			Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
			Aluno aluno = alunoService.getAlunoByCPF(auth.getName());

			if (inscricaoService.getInscricao(selecao, aluno) == null) {

				Inscricao inscricao = new Inscricao();

				inscricao.setData(new Date());

				inscricao.setAluno(aluno);
				inscricao.setSelecao(selecao);
				inscricao.setQuestionarioAuxilioMoradia(auxilioMoradia);

				inscricaoService.save(inscricao);
			} else {
				redirect.addFlashAttribute("error", MENSAGEM_ERRO_INSCRICAO_EXISTENTE_NA_SELECAO);
				return PAGINA_INSCREVER_AUXILIO_MORADIA;
			}

			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_REALIZADA);
		}

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_REALIZADA);

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "inscricao/editar/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricao(@PathVariable("idInscricao") Integer idInscricao, Model model,
			RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);

		if (inscricao != null) {

			if (inscricao.getSelecao().getTipoSelecao().equals(TipoSelecao.AUX_MOR)) {

				model.addAttribute("inscricao", inscricao);
				model.addAttribute("questionarioAuxilioMoradia", inscricao.getQuestionarioAuxilioMoradia());
				model.addAttribute("estado", Estado.values());
				model.addAttribute("situacaoImovel", SituacaoImovel.values());
				model.addAttribute("tipoEnsinoFundamental", TipoEnsinoFundamental.values());
				model.addAttribute("tipoEnsinoMedio", TipoEnsinoMedio.values());
				model.addAttribute("grauParentescoImovelRural", GrauParentescoImovelRural.values());
				model.addAttribute("grauParentescoVeiculos", GrauParentescoVeiculos.values());
				model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.values());
				model.addAttribute("moraCom", MoraCom.values());
				model.addAttribute("grauParentesco", GrauParentesco.values());
				model.addAttribute("selecao", inscricao.getSelecao());

				return PAGINA_INSCREVER_AUXILIO_MORADIA;

			} else {

				model.addAttribute("inscricao", inscricao);
				model.addAttribute("selecao", inscricao.getSelecao());
				model.addAttribute("questionarioIniciacaoAcademica", inscricao.getQuestionarioIniciacaoAcademica());
				model.addAttribute("nivelInstrucao", NivelInstrucao.values());
				model.addAttribute("turno", Turno.values());
				model.addAttribute("diasUteis", DiaUtil.values());
				model.addAttribute("situacaoResidencia", SituacaoResidencia.values());
				model.addAttribute("totalEstado", Estado.values());
				model.addAttribute("grauParentesco", GrauParentesco.values());
				
				
				List<HorarioDisponivel> horariosDisponiveis = inscricaoService
						.getHorariosDisponiveisIniciacaoAcademica(inscricao.getQuestionarioIniciacaoAcademica().getId());
				if (horariosDisponiveis != null && !horariosDisponiveis.isEmpty()) {
					model.addAttribute("horariosDisponiveis", horariosDisponiveis);
				}

				List<PessoaFamilia> pessoasDaFamilia = inscricaoService
						.getPessoaFamiliaByIdIniciacaoAcademica(inscricao.getQuestionarioIniciacaoAcademica().getId());

				if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
					model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
				}

				
				return PAGINA_INSCREVER_INICIACAO_ACADEMICA;

			}
		}

		redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
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
			@PathVariable("idInscricao") Integer idInscricao, RedirectAttributes redirect) {

		Inscricao inscricao = this.inscricaoService.find(Inscricao.class, idInscricao);

		if (inscricao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ALUNO_NAO_ENCONTRADO);
		} else {
			inscricaoService.delete(inscricao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA);
		}

		return PAGINA_INSCRICOES_ALUNO;

	}

	@RequestMapping(value = { "inscricao/detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricaoIniciacaoAcademica(@PathVariable("idInscricao") Integer idInscricao, Model model,
			RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);
		model.addAttribute("inscricao", inscricao);

		if (inscricao == null) {

			redirect.addAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_INSCRICOES_ALUNO;

		} else if (inscricao.getQuestionarioAuxilioMoradia() != null) {

			return PAGINA_DETALHES_AUXILIO_MORADIA;

		} else {

			return PAGINA_DETALHES_INICIACAO_ACADEMICA;
		}

	}


}
