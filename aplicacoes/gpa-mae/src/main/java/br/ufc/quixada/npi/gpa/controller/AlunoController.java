package br.ufc.quixada.npi.gpa.controller;

import java.util.List;

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
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.HorarioDisponivelService;
import br.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;
import br.ufc.quixada.npi.gpa.service.QuestionarioIniciacaoAcademicaService;
import br.ufc.quixada.npi.gpa.service.SelecaoBolsaService;
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
	private SelecaoBolsaService selecaoBolsaService;
	
	@Inject
	private AlunoService alunoService;

	@RequestMapping(value = { "inscricao/{idSelecao}/iniciacao-academica" }, method = RequestMethod.GET)
	public String realizarInscricaoBIA(@PathVariable("idSelecao") Integer idSelecao, Model model) {
		
		model.addAttribute("action", "incricao-inciacao-academica");
		
		model.addAttribute("questionarioIniciacaoAcademica", new QuestionarioIniciacaoAcademica());
		model.addAttribute("selecaoBolsa", idSelecao);
		model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
		model.addAttribute("turno", Turno.toMap());
		model.addAttribute("diasUteis", DiaUtil.toMap());
		model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
		model.addAttribute("totalEstado", Estado.toMap());
		model.addAttribute("grauParentesco", GrauParentesco.toMap());
		
		return "inscricao/iniciacaoAcademica";
	}
	
	@RequestMapping(value = { "inscricao/{idSelecao}/iniciacao-academica" }, method = RequestMethod.POST)
	public String realizarInscricaoBIA(@PathVariable("idSelecao") Integer idSelecao, @Valid @ModelAttribute("id") Integer idAluno,
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica,
			Model model, BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "incricao-inciacao-academica");
		
		if (result.hasErrors()) {
			
			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute("selecaoBolsa", idSelecao);
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute("diasUteis", DiaUtil.toMap());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			
			return "inscricao/iniciacaoAcademica";
		}
		
		Aluno aluno = this.alunoService.find(Aluno.class, idAluno);
		SelecaoBolsa selecao = this.selecaoBolsaService.getSelecaoBolsaComAlunos(idSelecao);
		
		selecao.getAlunosSelecao().add(aluno);
		this.selecaoBolsaService.update(selecao);
		
		iniciacaoAcademica.setAluno(aluno);
		iniciacaoAcademica.setSelecaoBolsa(selecao);
		
		this.iniciacaoAcademicaService.save(iniciacaoAcademica);
		
		return "redirect:/selecao/listar";
	}

	@RequestMapping(value = { "{idAluno}/inscricao/iniciacao-academica/editar" }, method = RequestMethod.GET)
	public String editarInscricaoBIA(@PathVariable("idAluno") Integer idAluno, Model model, RedirectAttributes redirect) {
		
		QuestionarioIniciacaoAcademica iniciacaoAcademica = this.iniciacaoAcademicaService.getQuestIniAcadById(idAluno);
		SelecaoBolsa selecao = iniciacaoAcademica.getSelecaoBolsa();
		
		if (selecao.getStatus() != null && selecao.getStatus().equals(Status.INSC_ABERTA)) {
			
			model.addAttribute("action", "editar-inciacao-academica");
			
			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute("selecaoBolsa", selecao.getId());
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute("diasUteis", DiaUtil.toMap());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			
			List<HorarioDisponivel> horariosDisponiveis = this.horarioDisponivelService
					.getHorariosDisponiveisByQuest(idAluno);
			if (horariosDisponiveis != null) {
				model.addAttribute("horariosDisponiveis", horariosDisponiveis);
			}
			
		} else {
			redirect.addFlashAttribute("erro", "Só pode editar sua inscrição enquanto a seleção estiver aberta.");
			return "redirect:/selecao/listar";
		}
		
		return "inscricao/iniciacaoAcademica";
	}
	
	@RequestMapping(value = { "{idAluno}/inscricao/iniciacao-academica/editar" }, method = RequestMethod.POST)
	public String editarInscricaoBIA(@PathVariable("idAluno") Integer idAluno,
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica,
			Model model,BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "editar-inciacao-academica");
		
		if (result.hasErrors()) {
			
			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute("selecaoBolsa", iniciacaoAcademica.getSelecaoBolsa().getId());
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute("diasUteis", DiaUtil.toMap());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			List<HorarioDisponivel> horariosDisponiveis = this.horarioDisponivelService
					.getHorariosDisponiveisByQuest(idAluno);
			if (horariosDisponiveis != null) {
				model.addAttribute("horariosDisponiveis", horariosDisponiveis);
			}
			
			return "inscricao/iniciacaoAcademica";
		}
		
		this.iniciacaoAcademicaService.update(iniciacaoAcademica);
		redirect.addFlashAttribute("info", "Seleção editada com sucesso.");
		return "redirect:/selecao/listar";
	}
	
	@RequestMapping(value = { "inscricao/{idSelecao}/auxilio-moradia" }, method = RequestMethod.GET)
	public String realizarInscricaoAMOR(@PathVariable("idSelecao") Integer idSelecao, Model model) {
		
		model.addAttribute("action", "incricao-auxilio-moraida");
		
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
		
		return "inscricao/auxilio";
	}
	
	@RequestMapping(value = { "inscricao/{idSelecao}/auxilio-moradia" }, method = RequestMethod.POST)
	public String realizarInscricaoAmor(@PathVariable("idSelecao") Integer idSelecao,
			@Valid @ModelAttribute("id") Integer idAluno,
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia, 
			Model model, BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "incricao-auxilio-moraida");
		
		if (result.hasErrors()) {
			
			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
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
			
			return "inscricao/auxilio";
		}
		
		Aluno aluno = this.alunoService.find(Aluno.class, idAluno);
		SelecaoBolsa selecao = this.selecaoBolsaService.getSelecaoBolsaComAlunos(idSelecao);
		
		selecao.getAlunosSelecao().add(aluno);
		this.selecaoBolsaService.update(selecao);
		
		auxilioMoradia.setAluno(aluno);
		auxilioMoradia.setSelecaoBolsa(selecao);
		
		this.auxilioMoradiaService.save(auxilioMoradia);
		
		return "redirect:/selecao/listar";
	}
	
	@RequestMapping(value = { "{idAluno}/inscricao/auxilio-moradia/editar" }, method = RequestMethod.GET)
	public String editarInscricaoAMOR(@PathVariable("idAluno") Integer idAluno, Model model, RedirectAttributes redirect) {
		
		QuestionarioAuxilioMoradia auxilioMoradia = this.auxilioMoradiaService.getQuestAuxMorById(idAluno);
		SelecaoBolsa selecao = auxilioMoradia.getSelecaoBolsa();
		
		if (selecao.getStatus() != null && selecao.getStatus().equals(Status.INSC_ABERTA)) { 
			
			model.addAttribute("action", "editar-auxilio-moradia");
			
			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
			model.addAttribute("selecaoBolsa", selecao.getId());
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute("diasUteis", DiaUtil.toMap());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			model.addAttribute("moraCom", MoraCom.toMap());
			
		} else {
			redirect.addFlashAttribute("erro", "Só pode editar sua inscrição enquanto a seleção estiver aberta.");
			return "redirect:/selecao/listar";
		}
		
		return "inscricao/auxilio";
	}
	
	@RequestMapping(value = { "{idAluno}/inscricao/auxilio-moradia/editar" }, method = RequestMethod.POST)
	public String editarInscricaoAMOR(@PathVariable("idAluno") Integer idAluno, @Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia, Model model, BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "editar-auxilio-moradia");
		
		if (result.hasErrors()) {
			model.addAttribute("questionarioAuxilioMoradia", auxilioMoradia);
			model.addAttribute("selecaoBolsa", auxilioMoradia.getSelecaoBolsa().getId());
			model.addAttribute("nivelInstrucao", NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute("diasUteis", DiaUtil.toMap());
			model.addAttribute("situacaoResidencia", SituacaoResidencia.toMap());
			model.addAttribute("totalEstado", Estado.toMap());
			model.addAttribute("grauParentesco", GrauParentesco.toMap());
			model.addAttribute("moraCom", MoraCom.toMap());
			
			return "inscricao/auxilio";
		}
		
		this.auxilioMoradiaService.update(auxilioMoradia);
		redirect.addFlashAttribute("info", "Seleção editada com sucesso.");
		return "redirect:/selecao/listar";
	}
	
//	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
//	public String salvarAluno(@Valid @ModelAttribute(value = "aluno") Aluno aluno,
//			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {
//
//		if(aluno.getId()!=null){
//			return atualizarAluno(aluno.getId(), aluno, result, model, redirect);
//		}else{
//			return adicionarAluno(aluno, result, redirect, model);
//		}
//
//	}
//
//	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
//	public String cadastro(Model model) {
//		model.addAttribute("action", "cadastrar");
//		model.addAttribute("banco", Banco.values());
//		model.addAttribute("curso", Curso.values());
//		model.addAttribute("aluno", new Aluno());
//		return "/aluno/cadastrar";
//	}
//
//	public String adicionarAluno(
//			@Valid @ModelAttribute("aluno") Aluno aluno,
//			BindingResult result,RedirectAttributes redirect, Model model) {
//
//
//		if(aluno.getAnoIngresso() != null && !aluno.getAnoIngresso().equals("")){
//			if(aluno.getAnoIngresso().length() < 4){
//				result.rejectValue("anoIngresso", "aluno.anoIngresso", "O ano deve possuir pelo menos quatro dígitos");
//			} else {
//				DateTime anoIngresso = DateTime.parse(aluno.getAnoIngresso());
//				if(anoIngresso.isAfterNow()){
//					result.rejectValue("anoIngresso", "aluno.anoIngresso", "Informe um ano menor ou igual ao atual");
//				}
//			}
//		}
//		
//		if(aluno.getMatricula() != null && !aluno.getMatricula().equals("") && aluno.getMatricula().length() < 6){
//			result.rejectValue("matricula", "aluno.matricula", "A matrícula deve possuir pelo menos seis dígitos");
//		}
//		
//		if(aluno.getAgencia() != null && !aluno.getAgencia().equals("") && aluno.getAgencia().length() < 6){
//			result.rejectValue("agencia", "aluno.agencia", "O número da agência deve possuir pelo menos seis dígitos");
//		}
//		
//		if(aluno.getConta() != null && !aluno.getConta().equals("") && aluno.getConta().length() < 4){
//			result.rejectValue("conta", "aluno.conta", "O número da conta deve possuir pelo menos quatro dígitos");
//		}
//
//		if (result.hasErrors()) {
//			model.addAttribute("banco", Banco.values());
//			model.addAttribute("curso", Curso.values());
//			return ("aluno/cadastrar");
//		}
//
//
//		try{
//			this.alunoService.save(aluno);
//		} catch (PersistenceException e){
//			if(e.getCause() instanceof ConstraintViolationException){
//				redirect.addFlashAttribute("erro", "Não é possível cadastrar uma matrícula já existente.");
//				return "redirect:/aluno/listar";
//			}
//		} 
//		redirect.addFlashAttribute("info", "Aluno cadastrado com sucesso.");
//		return "redirect:/aluno/listar";
//	}
//
//	@RequestMapping(value = "/listar", method = RequestMethod.GET)
//	public String listaAluno(Aluno aluno, Model model) {	
//		List<Aluno> results = alunoService.find(Aluno.class);
//		model.addAttribute("alunos", results);
//		return "aluno/listar";
//	}
//
//	@RequestMapping(value = "/listar", method = RequestMethod.POST)
//	public String listarAluno(@RequestParam("matricula") String matricula, Model model, RedirectAttributes redirect) {
//		List<Aluno> results = new ArrayList<Aluno>();
//		Aluno aluno = alunoService.getAlunoByMatricula(matricula);
//		results.add(aluno);
//		model.addAttribute("alunos", results);
//
//		if(aluno == null){
//			redirect.addFlashAttribute("erro", "Aluno não encontrado");
//			redirect.addFlashAttribute("alunoEncontrado", false);	
//			return "redirect:/aluno/listar";
//		}
//		return "/aluno/listar";
//	}
//
//	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
//	public String editar(@PathVariable("id") Integer id, Model model) {
//		Aluno aluno = alunoService.find(Aluno.class, id);
//
//		model.addAttribute("aluno", aluno);
//		model.addAttribute("banco", Banco.values());
//		model.addAttribute("curso", Curso.values());
//		model.addAttribute("action", "editar");
//		
//		return "aluno/cadastrar";		
//	}
//
//	public String atualizarAluno(Integer id,Aluno alunoAtualizado,
//			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {
//		
//		if(alunoAtualizado.getAnoIngresso() != null && !alunoAtualizado.getAnoIngresso().equals("")){
//			if(alunoAtualizado.getAnoIngresso().length() < 4){
//				result.rejectValue("anoIngresso", "aluno.anoIngresso", "O ano deve possuir pelo menos quatro dígitos");
//			} else {
//				DateTime anoIngresso = DateTime.parse(alunoAtualizado.getAnoIngresso());
//				if(anoIngresso.isAfterNow()){
//					result.rejectValue("anoIngresso", "aluno.anoIngresso", "Informe um ano menor ou igual ao atual");
//				}
//			}
//		}
//		
//		if(alunoAtualizado.getMatricula() != null && !alunoAtualizado.getMatricula().equals("") && alunoAtualizado.getMatricula().length() < 6){
//			result.rejectValue("matricula", "aluno.matricula", "A matrícula deve possuir pelo menos seis dígitos");
//		}
//		
//		if(alunoAtualizado.getAgencia() != null && !alunoAtualizado.getAgencia().equals("") && alunoAtualizado.getAgencia().length() < 6){
//			result.rejectValue("agencia", "aluno.agencia", "O número da agência deve possuir pelo menos seis dígitos");
//		}
//		
//		if(alunoAtualizado.getConta() != null && !alunoAtualizado.getConta().equals("") && alunoAtualizado.getConta().length() < 4){
//			result.rejectValue("conta", "aluno.conta", "O número da conta deve possuir pelo menos quatro dígitos");
//		}
//
//		if (result.hasErrors()) {
//			model.addAttribute("action", "editar"); 
//			return "aluno/cadastrar";
//		}
//
//
//		Aluno aluno = alunoService.find(Aluno.class, id);	
//
//		alunoAtualizado.setAuxilioMoradia(aluno.getAuxilioMoradia());
//		alunoAtualizado.setIniciacaoAcademica(aluno.getIniciacaoAcademica());
//		alunoAtualizado.setPessoa(aluno.getPessoa());
//
//		this.alunoService.update(alunoAtualizado);
//		redirect.addFlashAttribute("info", "Aluno atualizado com sucesso.");
//		return "redirect:/aluno/listar";
//	}
//
//	@RequestMapping(value = "/excluir/{id}")
//	public String excluirAluno(Aluno p, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes
//			) {
//		Aluno aluno = alunoService.find(Aluno.class, id);
//		if (aluno == null) {
//			redirectAttributes.addFlashAttribute("erro", "Aluno inexistente.");
//		}else{
//
//			this.alunoService.delete(aluno);
//			redirectAttributes.addFlashAttribute("info", "Aluno excluído com sucesso.");
//		}
//		return "redirect:/aluno/listar";
//	}
}
