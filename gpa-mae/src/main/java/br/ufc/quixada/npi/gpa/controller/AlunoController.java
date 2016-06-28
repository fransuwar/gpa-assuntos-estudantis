package br.ufc.quixada.npi.gpa.controller;
import static br.ufc.quixada.npi.gpa.utils.Constants.ABA_SELECIONADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.DOCUMENTOS_TAB;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_DOCUMENTO_FORMATO_INVALIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EDITAR_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_INSCRICAO_EXISTENTE_NA_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_INSCRICAO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_UPLOAD_FOTO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_INSCRICAO_REALIZADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_AUXILIO_MORADIA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCRICOES_ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_SELECOES_ABERTAS;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_INSCRICOES_ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_MINHAS_INSCRICOES;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.DiaUtil;
import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoImovelRural;
import br.ufc.quixada.npi.gpa.enums.GrauParentescoVeiculos;
import br.ufc.quixada.npi.gpa.enums.MoraCom;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.Resultado;
import br.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoFundamental;
import br.ufc.quixada.npi.gpa.enums.TipoEnsinoMedio;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.DocumentosTipoInscricao;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.DocumentosTipoInscricaoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;
import br.ufc.quixada.npi.ldap.service.UsuarioService;
import br.ufc.quixada.npi.model.Email;
import br.ufc.quixada.npi.service.EmailService;


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

	@Inject
	private DocumentoService documentoService;

	@Inject
	private DocumentosTipoInscricaoService dtiService;
	
	@Inject
	private EmailService emailService;
	
	

	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(Model model, HttpServletRequest request, Authentication auth) {

		List<Selecao> selecoes = selecaoService.getSelecoes();

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

		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);

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
			model.addAttribute("selecao", selecaoService.getSelecaoPorId(idSelecao));

			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}


		Aluno aluno = alunoService.getAlunoPorCPF(auth.getName());
		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);

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

		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);

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

	/*@RequestMapping(value = { "inscricao/editar/iniciacao-academica" }, method = RequestMethod.POST)
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
					.getPessoaFamiliaPorIdIniciacaoAcademica(iniciacaoAcademica.getId());

			if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
				model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
			}

			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		// TODO - Realizar a atualização de uma iniciação acadêmica.
		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_EDITADA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}*/

	@RequestMapping(value = { "inscricao/{idSelecao}/auxilio-moradia" }, method = RequestMethod.GET)
	public String realizarInscricaoAuxilioMoradia(@PathVariable("idSelecao") Integer idSelecao, Model model, Authentication auth) {

		model.addAttribute("action", "inscricao");

		Aluno aluno = alunoService.getAlunoPorCPF(auth.getName());
		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);

		model.addAttribute("aluno", aluno);
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
			BindingResult result, @RequestParam(value="mora", required=false) List<String> comQuemMora,
			@RequestParam("idSelecao") Integer idSelecao, Authentication auth, RedirectAttributes redirect,
			Model model, @RequestParam("fileFoto") MultipartFile foto) {
		
		try {
			CommonsMultipartFile multipartFile = (CommonsMultipartFile) foto;

			List<String> formatos = Arrays.asList("image/jpg", "image/jpeg", "image/png");
			/*
			 * Conferindo se o aluno enviou uma foto e o formato do arquivo
			 * passado é um dos formatos do array acima.
			 * A inscrição pode ser efetuada mesmo se o aluno não enviar a foto.
			 */
			if( foto.getSize() == 0 ){
				auxilioMoradia.setFoto(null);
			}else if(foto.getSize() > 0 && formatos.contains(multipartFile.getContentType())){
				auxilioMoradia.setFoto(foto.getBytes());
			}else{
				redirect.addFlashAttribute("error", MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO);
				//Adicionando o erro no result.
				result.addError(new ObjectError("error", MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO));
			}
		} catch (IOException e) {
			result.addError(new ObjectError("error", MENSAGEM_ERRO_UPLOAD_FOTO));
			redirect.addFlashAttribute("error", MENSAGEM_ERRO_UPLOAD_FOTO);
		}

		List<ComQuemMora> comQuemMoraList = new ArrayList<ComQuemMora>();
		if(comQuemMora != null){
			for (String m : comQuemMora) {
				ComQuemMora mora = inscricaoService.getComQuemMora(MoraCom.valueOf(m));
				comQuemMoraList.add(mora);
			}
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
			model.addAttribute("selecao", selecaoService.getSelecaoPorId(idSelecao));


			return PAGINA_INSCREVER_AUXILIO_MORADIA;

		} else {


			Aluno aluno = alunoService.getAlunoPorCPF(auth.getName());
			Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
			auxilioMoradia.setPessoasEntrevista(auxilioMoradia.getPessoas());

			if (inscricaoService.getInscricao(selecao, aluno) == null) {

				Inscricao inscricao = new Inscricao();

				inscricao.setData(new Date());

				inscricao.setAluno(aluno);
				inscricao.setSelecao(selecao);
				inscricao.setQuestionarioAuxilioMoradia(auxilioMoradia);
				inscricao.setDeferimentoDocumentacao(Resultado.NAO_AVALIADO);
				inscricao.setResultado(Resultado.NAO_AVALIADO);

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

		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);
		Selecao selecao = inscricao.getSelecao();
		Date date = new Date();

		if (inscricao != null) {

			if (inscricao.getSelecao().getTipoSelecao().equals(TipoSelecao.AUX_MOR)) {

				if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){		
					redirect.addFlashAttribute("erro", MENSAGEM_ERRO_EDITAR_INSCRICAO);
					return REDIRECT_PAGINA_MINHAS_INSCRICOES;
				}else{

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
				}

			} else {

				/*model.addAttribute("inscricao", inscricao);
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
						.getPessoaFamiliaPorIdIniciacaoAcademica(inscricao.getQuestionarioIniciacaoAcademica().getId());

				if (pessoasDaFamilia != null && !pessoasDaFamilia.isEmpty()) {
					model.addAttribute("pessoasDaFamilia", pessoasDaFamilia);
				}


				return PAGINA_INSCREVER_INICIACAO_ACADEMICA;*/

			}
		}

		redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}
	
	@RequestMapping(value = {"/inscricao/consolidar/{idInscricao}"}, method = RequestMethod.GET)
	public String consolidarInscricao(@PathVariable("idInscricao") Integer idInscricao,Model model){
		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);
		List<Selecao> selecoes = selecaoService.getSelecoes();
		inscricao.setConsolidacao(true);
		inscricaoService.update(inscricao);	
		model.addAttribute("selecoes", selecoes);
					
		Runnable enviarEmail = new Runnable() {
			@Override
			public void run() {		
				Email email = new Email();
				String from = "naoresponda@gpaassuntosestudantis.com";
				String to = inscricao.getAluno().getPessoa().getEmail();
				System.out.println(inscricao.getAluno().getPessoa().getEmail());
				String body = "Caros amigos, a complexidade dos estudos efetuados desafia a capacidade de equalização de alternativas às soluções ortodoxas.";								
				email.setFrom(from);
				email.setSubject("Assunto");
				email.setText(body);
				email.setTo(to);
				
				try {
					emailService.sendEmail(email);
					
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			};
		
			Thread threadEnviarEmail = new Thread(enviarEmail);
			threadEnviarEmail.start();
		
			
			    
		return PAGINA_SELECOES_ABERTAS;
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

		Inscricao inscricao = this.inscricaoService.getInscricaoPorId(idInscricao);
		Selecao selecao = inscricao.getSelecao();
		Date date = new Date();

		if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){		
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_EXCLUIR_INSCRICAO);
			return REDIRECT_PAGINA_MINHAS_INSCRICOES;

		} else{
			inscricaoService.delete(inscricao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA);
		}

		return PAGINA_INSCRICOES_ALUNO;

	}

	@RequestMapping(value = { "inscricao/detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricao(@PathVariable("idInscricao") Integer idInscricao, Authentication auth, Model model,
			RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);
		Selecao selecao = inscricao.getSelecao();
		Date date = new Date();
		model.addAttribute("inscricao", inscricao);
		model.addAttribute("usuarioAtivo", inscricao.getAluno().getPessoa());

		if (inscricao == null) {

			redirect.addAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_INSCRICOES_ALUNO;

		} else if (inscricao.getQuestionarioAuxilioMoradia() != null) {

			if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){
				model.addAttribute("esconderBotoes",true);
			} else{
				model.addAttribute("esconderBotoes",false);			
			}

			model.addAttribute("aba", "inscricao-tab");


			return PAGINA_DETALHES_INSCRICAO;

		} else {

			return PAGINA_DETALHES_INICIACAO_ACADEMICA;
		}

	}

	@RequestMapping(value = "/inscricao/adicionarDocumento/{idInscricao}", method = RequestMethod.POST)
	public String enviarDocumento(MultipartFile formulario,	@PathVariable("idInscricao") Integer idInscricao, @RequestParam("idTipo") Integer idTipo, Model model, RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);

		try {
			List<String> formatos = Arrays.asList("application/pdf");

			if(formatos.contains(formulario.getContentType())){
				Documento documento = new Documento();
				documento.setArquivo(formulario.getBytes());
				documento.setNome(formulario.getOriginalFilename());
				documento.setTipo(formulario.getContentType());

				documentoService.salvarDocumento(documento);




				DocumentosTipoInscricao dti = inscricao.getDocumentosTipoInscricao().get(idTipo);
				if(dti == null){
					dti = new DocumentosTipoInscricao();
					inscricao.getDocumentosTipoInscricao().put(idTipo, dti);
				}

				dti.getDocumentos().add(documento);

				dtiService.salvarDocumentosTipoInscricao(dti);

				inscricaoService.save(inscricao);

			}else{
				model.addAttribute("error", MENSAGEM_ERRO_DOCUMENTO_FORMATO_INVALIDO);
			}

		} catch (IOException e) {
			
		}

		model.addAttribute("inscricao", inscricao);
		model.addAttribute(ABA_SELECIONADA, DOCUMENTOS_TAB);

		return PAGINA_DETALHES_INSCRICAO;
	}

	@RequestMapping(value= {"inscricao/removerDocumento/{idInscricao}/{idTipo}/{idDocumento}"}, method = RequestMethod.GET)
	public String removerDocumento(@PathVariable("idInscricao") Integer idInscricao, @PathVariable("idTipo") Integer idTipo, @PathVariable("idDocumento") Integer idDocumento, Model modelo){

		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);

		Documento documento = documentoService.getDocumentoPorId(idDocumento);

		inscricao.getDocumentosTipoInscricao().get(idTipo).getDocumentos().remove(documento);

		inscricaoService.save(inscricao);

		documentoService.deletarDocumento(documento);

		modelo.addAttribute(ABA_SELECIONADA, DOCUMENTOS_TAB);
		modelo.addAttribute("inscricao", inscricao);


		return PAGINA_DETALHES_INSCRICAO;
	}
}
