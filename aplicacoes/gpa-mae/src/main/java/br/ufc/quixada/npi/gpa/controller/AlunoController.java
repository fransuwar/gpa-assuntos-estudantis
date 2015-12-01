package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_AUXILIO_MORADIA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;

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
	public String listarSelecoesAbertas() {
		//TODO - Método p/ implementar que retorna página com seleções em aberto
		return "";
	}
	
	@RequestMapping(value = { "inscricao/listar" }, method = RequestMethod.GET)
	public String listarInscricoes(Model model, Authentication auth) {
		
		Aluno aluno = this.alunoService.getAlunoByCPF(auth.getName());
		
		model.addAttribute("inscricoes", aluno.getInscricoes());
		
		//TODO - Criar página de retorno que mostra as inscrições dos alunos.
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
	public String realizarInscricaoBIA(@RequestParam("idSelecao") Integer idSelecao, @Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica, 
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

		Selecao selecao = selecaoService.find(Selecao.class,idSelecao);
		Aluno aluno = alunoService.getAlunoByCPF(auth.getName()); 
		inscricaoService.realizarInscricaoIniciacaoAcademica(selecao, aluno, iniciacaoAcademica);


		redirect.addFlashAttribute("info", "Cadastro realizado com sucesso.");
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "inscricao/editar/iniciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricaoBIA(@PathVariable("idInscricao") Integer idInscricao, Model model, RedirectAttributes redirect) {
		
		//TODO - Método p/ implementar que retorna página de formulário de inscrição em iniciação acadêmica.
		
		return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
	}
	
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
			
			List<HorarioDisponivel> horariosDisponiveis = this.inscricaoService.getHorariosDisponiveisIniciacaoAcademica(iniciacaoAcademica.getId());
			if (horariosDisponiveis != null) {
				model.addAttribute("horariosDisponiveis", horariosDisponiveis);
			}
			
			List<PessoaFamilia> pessoasDaFamilia = this.inscricaoService.getPessoaFamiliaByIdIniciacaoAcademica(iniciacaoAcademica.getId());
			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
			}
			
			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}
		
		inscricaoService.atualizarInscricaoIniciacaoAcademica(iniciacaoAcademica);
		
		redirect.addFlashAttribute("info", "Seleção editada com sucesso.");
		return REDIRECT_PAGINA_LISTAR_SELECAO;
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
		
		return PAGINA_INSCREVER_AUXILIO_MORADIA;
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
			
			return PAGINA_INSCREVER_AUXILIO_MORADIA;
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
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	
	@RequestMapping(value = { "inscricao/editar/auxilio-moradia/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricaoAMOR(@PathVariable("idInscricao") Integer idInscricao, Model model, RedirectAttributes redirect) {
		
		//TODO - Método p/ implementar que retorna página de edição do formulário de inscrição em auxílio moradia.
		
		return PAGINA_INSCREVER_AUXILIO_MORADIA;
		
	}
	
	@RequestMapping(value = { "inscricao/editar/auxilio-moradia" }, method = RequestMethod.POST)
	public String editarInscricaoAMOR(@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia, Model model,
			BindingResult result, RedirectAttributes redirect) {
		//TODO - Método p/ implementar que salva a edição de um formulário em uma incrição auxílio moradia.
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}
	
	@RequestMapping(value = { "detalhes/inciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhes(@PathVariable("idInscricao") Integer idInscricao, Model modelo, RedirectAttributes redirect){
		
		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);
		
		if (inscricao == null) {
			redirect.addFlashAttribute("erro", "seleção Inexistente");
			
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		modelo.addAttribute("inscricao", inscricao);

		return PAGINA_DETALHES_INSCRICAO;
	}
	
}
