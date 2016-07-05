package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.ABA_SELECIONADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.DOCUMENTOS_TAB;
import static br.ufc.quixada.npi.gpa.utils.Constants.ERRO;
import static br.ufc.quixada.npi.gpa.utils.Constants.INSCRICAO_TAB;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ADICIONAR_DOCUMENTOS_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_DADOS_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_DOCUMENTO_FORMATO_INVALIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EDITAR_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_INSCRICAO_EXISTENTE_NA_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_INSCRICAO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_REALIZAR_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_UPLOAD_FOTO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_INSCRICAO_EDITADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_INSCRICAO_REALIZADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_AUXILIO_MORADIA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCREVER_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INSCRICOES_ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_SELECOES_ABERTAS;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_DETALHES_INSCRICAO_ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_INSCRICOES_ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_MINHAS_INSCRICOES;
import static br.ufc.quixada.npi.gpa.utils.Constants.ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.NIVEL_INSTRUCAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.SITUACAO_RESIDENCIA;
import static br.ufc.quixada.npi.gpa.utils.Constants.SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.ID_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.INFO;
import static br.ufc.quixada.npi.gpa.utils.Constants.DIAS_UTEIS;
import static br.ufc.quixada.npi.gpa.utils.Constants.TOTAL_ESTADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.GRAU_PARENTESCO;
import static br.ufc.quixada.npi.gpa.utils.Constants.ERROR;
import static br.ufc.quixada.npi.gpa.utils.Constants.QUESTIONARIO_AUXILIO_MORADIA;
import static br.ufc.quixada.npi.gpa.utils.Constants.INSCRICAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
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
import br.ufc.quixada.npi.gpa.enums.Escolaridade;
import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.FinalidadeVeiculo;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.Resultado;
import br.ufc.quixada.npi.gpa.enums.SituacaoImovel;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;
import br.ufc.quixada.npi.gpa.enums.TipoEnsino;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.enums.Turno;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.AnaliseDocumentacao;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.DocumentosTipoInscricao;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.repository.AlunoRepository;
import br.ufc.quixada.npi.gpa.repository.AnaliseDocumentacaoRepository;
import br.ufc.quixada.npi.gpa.repository.DocumentoRepository;
import br.ufc.quixada.npi.gpa.repository.DocumentosTipoInscricaoRepository;
import br.ufc.quixada.npi.gpa.repository.InscricaoRepository;
import br.ufc.quixada.npi.gpa.repository.SelecaoRepository;
import br.ufc.quixada.npi.gpa.repository.TipoDocumentoRepository;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;
import br.ufc.quixada.npi.ldap.service.UsuarioService;



@Controller
@RequestMapping(ALUNO)
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO })
public class AlunoController {

	@Inject
	private InscricaoService inscricaoService;

	@Inject
	private UsuarioService usuarioService;
	
	
	@Inject
	private AlunoRepository alunoRepository;
	
	@Inject
	private AnaliseDocumentacaoRepository documentacaoRepository;

	@Inject
	private DocumentoRepository documentoRepository;

	@Inject
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Inject
	private DocumentosTipoInscricaoRepository documentosTipoInscricaoRepository;
	
	@Inject
	private InscricaoRepository inscricaoRepository;
	
	@Inject
	private SelecaoRepository selecaoRepository;


	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(Model model, HttpServletRequest request, Authentication auth) {

		List<Selecao> selecoes = selecaoRepository.findAll();

		Aluno aluno = alunoRepository.findAlunoComInscricoesPorCpf(auth.getName());

		model.addAttribute("selecoes", selecoes);
		model.addAttribute(ALUNO, aluno);
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_SELECOES_ABERTAS;

	}

	@RequestMapping(value = { "inscricao/{idSelecao}/iniciacao-academica" }, method = RequestMethod.GET)
	public String realizarInscricaoIniciacaoAcademica(@PathVariable(ID_SELECAO) Integer idSelecao, Model model) {

		model.addAttribute("action", "inscricao");

		Selecao selecao = selecaoRepository.findById(idSelecao);

		model.addAttribute("questionarioIniciacaoAcademica", new QuestionarioIniciacaoAcademica());
		model.addAttribute(NIVEL_INSTRUCAO, NivelInstrucao.toMap());
		model.addAttribute("turno", Turno.toMap());
		model.addAttribute(DIAS_UTEIS, DiaUtil.toMap());
		model.addAttribute(SITUACAO_RESIDENCIA, SituacaoResidencia.toMap());
		model.addAttribute(TOTAL_ESTADO, Estado.toMap());
		model.addAttribute(GRAU_PARENTESCO, GrauParentesco.values());
		model.addAttribute(SELECAO, selecao);	

		return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
	}

	@RequestMapping(value = { "inscricao/iniciacao-academica" }, method = RequestMethod.POST)
	public String realizarInscricaoIniciacaoAcademica(@RequestParam(ID_SELECAO) Integer idSelecao,
			@Valid @ModelAttribute("questionarioIniciacaoAcademica") QuestionarioIniciacaoAcademica iniciacaoAcademica,
			BindingResult result, Model model, RedirectAttributes redirect, Authentication auth) {

		model.addAttribute("action", "inscricao");

		if (result.hasErrors()) {

			model.addAttribute("questionarioIniciacaoAcademica", iniciacaoAcademica);
			model.addAttribute(NIVEL_INSTRUCAO, NivelInstrucao.toMap());
			model.addAttribute("turno", Turno.toMap());
			model.addAttribute(DIAS_UTEIS, DiaUtil.toMap());
			model.addAttribute(SITUACAO_RESIDENCIA, SituacaoResidencia.toMap());
			model.addAttribute(TOTAL_ESTADO, Estado.toMap());
			model.addAttribute(GRAU_PARENTESCO, GrauParentesco.values());
			model.addAttribute(ID_SELECAO, idSelecao);
			model.addAttribute(SELECAO, selecaoRepository.findById(idSelecao));


			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		Aluno aluno = alunoRepository.findByCpf(auth.getName());
		Selecao selecao = selecaoRepository.findById(idSelecao);

		if (inscricaoRepository.findInscricaoBySelecaoAndByAluno(selecao.getId(), aluno.getId()) == null) {
			Inscricao inscricao = new Inscricao();

			inscricao.setData(new Date());

			inscricao.setAluno(aluno);
			inscricao.setSelecao(selecao);
			inscricao.setQuestionarioIniciacaoAcademica(iniciacaoAcademica);

			inscricaoRepository.save(inscricao);
		} else {
			redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_INSCRICAO_EXISTENTE_NA_SELECAO);
			return PAGINA_INSCREVER_INICIACAO_ACADEMICA;
		}

		redirect.addFlashAttribute(INFO, MENSAGEM_SUCESSO_INSCRICAO_REALIZADA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "inscricao/editar/iniciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricaoIniciacaoAcademica(@PathVariable("idInscricao") Integer idInscricao, Model model,
			RedirectAttributes redirect) {

		model.addAttribute("action", "editar");

		Inscricao inscricao = inscricaoRepository.findById(idInscricao);

		model.addAttribute(INSCRICAO, inscricao);
		model.addAttribute("questionarioIniciacaoAcademica", inscricao.getQuestionarioIniciacaoAcademica());
		model.addAttribute(NIVEL_INSTRUCAO, NivelInstrucao.toMap());
		model.addAttribute("turno", Turno.toMap());
		model.addAttribute(DIAS_UTEIS, DiaUtil.toMap());
		model.addAttribute(SITUACAO_RESIDENCIA, SituacaoResidencia.toMap());
		model.addAttribute(TOTAL_ESTADO, Estado.toMap());
		model.addAttribute(GRAU_PARENTESCO, GrauParentesco.values());
		model.addAttribute(SELECAO, inscricao.getSelecao());

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
	public String realizarInscricaoAuxilioMoradia(@PathVariable("idSelecao") Integer idSelecao, Model model, Authentication auth, RedirectAttributes redirect){

		model.addAttribute("action", "inscricao");

		Aluno aluno = alunoRepository.findByCpf(auth.getName());
		Selecao selecao = selecaoRepository.findById(idSelecao);

		model.addAttribute(ALUNO, aluno);
		model.addAttribute(QUESTIONARIO_AUXILIO_MORADIA, new QuestionarioAuxilioMoradia());

		Date date = new Date();
		
		if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){		
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_REALIZAR_INSCRICAO);
			return REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO;
		}else{

		
			model.addAttribute("action", "inscricao");
			
			//Aluno aluno = alunoService.getAlunoPorCPF(auth.getName());
	
			model.addAttribute(ALUNO, aluno);
			model.addAttribute(QUESTIONARIO_AUXILIO_MORADIA, new QuestionarioAuxilioMoradia());
			
			Model modelFormAuxilio = this.carregarFormularioAuxilioMoradia(model);
			model.mergeAttributes(modelFormAuxilio.asMap());
			
			model.addAttribute(SELECAO, selecao);
			model.addAttribute("usuarioAtivo", usuarioService.getByCpf(auth.getName()));
	
			return PAGINA_INSCREVER_AUXILIO_MORADIA;
			}
	}

	@RequestMapping(value = { "inscricao/auxilio-moradia" }, method = RequestMethod.POST)
	public String realizarInscricaoAuxilioMoradia(
			@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia,
			BindingResult result, @RequestParam(value="mora", required=false) List<String> comQuemMora,
			@RequestParam(ID_SELECAO) Integer idSelecao, Authentication auth, RedirectAttributes redirect,
			Model model, @RequestParam("fileFoto") MultipartFile foto) {

		if(!this.verificarExtensaoFoto(foto)){
			redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO);
			//Adicionando o erro no result.
			result.addError(new ObjectError(ERROR, MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO));
		}

		try{
			auxilioMoradia.setFoto(this.verificarConteudoFoto(foto));
		}catch(IOException exception){
			result.addError(new ObjectError(ERROR, MENSAGEM_ERRO_UPLOAD_FOTO));
			redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_UPLOAD_FOTO);
		}

		if (result.hasErrors()) {

			model.addAttribute("action", "inscricao");
			model.addAttribute(QUESTIONARIO_AUXILIO_MORADIA, auxilioMoradia);

			Model modelFormAuxilio = this.carregarFormularioAuxilioMoradia(model);
			model.mergeAttributes(modelFormAuxilio.asMap());
			model.addAttribute(ID_SELECAO, idSelecao);
			model.addAttribute(SELECAO, selecaoRepository.findById(idSelecao));
			model.addAttribute("usuarioAtivo", usuarioService.getByCpf(auth.getName()));

			redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_DADOS_INSCRICAO);



			return PAGINA_INSCREVER_AUXILIO_MORADIA;

		} else {

			Aluno aluno = alunoRepository.findByCpf(auth.getName());
			Selecao selecao = selecaoRepository.findById(idSelecao);

			List<PessoaFamilia> pessoasEntrevista = new ArrayList<>();
			if(auxilioMoradia.getPessoas() != null){
				for(PessoaFamilia pessoa : auxilioMoradia.getPessoas()){
					pessoasEntrevista.add(pessoa.clone());
				}
			}
			auxilioMoradia.setPessoasEntrevista(pessoasEntrevista);

			if (inscricaoRepository.findInscricaoBySelecaoAndByAluno(selecao.getId(), aluno.getId()) == null) {

				Inscricao inscricao = new Inscricao();

				inscricao.setData(new Date());

				inscricao.setAluno(aluno);
				inscricao.setSelecao(selecao);
				inscricao.setQuestionarioAuxilioMoradia(auxilioMoradia);
				inscricao.setResultado(Resultado.NAO_AVALIADO);
				auxilioMoradia.setComQuemMora(this.adicionarComQuemMora(comQuemMora));

                inscricaoRepository.save(inscricao);

				redirect.addFlashAttribute(INFO, MENSAGEM_ADICIONAR_DOCUMENTOS_INSCRICAO);		
				redirect.addFlashAttribute(ABA_SELECIONADA, DOCUMENTOS_TAB);
				return REDIRECT_PAGINA_DETALHES_INSCRICAO_ALUNO + inscricao.getId();

			} else {
				redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_INSCRICAO_EXISTENTE_NA_SELECAO);
				return PAGINA_INSCREVER_AUXILIO_MORADIA;
			}

		}


	}

	@RequestMapping(value = { "inscricao/editar/{idInscricao}" }, method = RequestMethod.GET)
	public String editarInscricao(@PathVariable("idInscricao") Integer idInscricao, Model model,
			RedirectAttributes redirect, Authentication auth) {

		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		if(inscricao.isConsolidacao())
			return REDIRECT_PAGINA_MINHAS_INSCRICOES;



		Selecao selecao = inscricao.getSelecao();
		Date date = new Date();

		if (inscricao != null) {

			if (inscricao.getSelecao().getTipoSelecao().equals(TipoSelecao.AUX_MOR)) {

				if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){		
					redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_EDITAR_INSCRICAO);
					return REDIRECT_PAGINA_MINHAS_INSCRICOES;
				}else{

					model.addAttribute(INSCRICAO, inscricao);
					model.addAttribute(QUESTIONARIO_AUXILIO_MORADIA, inscricao.getQuestionarioAuxilioMoradia());
					model.addAttribute("usuarioAtivo", usuarioService.getByCpf(auth.getName()));

					Model modelFormAuxilio = this.carregarFormularioAuxilioMoradia(model);
					model.mergeAttributes(modelFormAuxilio.asMap());

					model.addAttribute(SELECAO, inscricao.getSelecao());
					model.addAttribute("action", "editar");

					return PAGINA_INSCREVER_AUXILIO_MORADIA;
				}

			} else {

				model.addAttribute(INSCRICAO, inscricao);
				model.addAttribute(SELECAO, inscricao.getSelecao());
				model.addAttribute("questionarioIniciacaoAcademica", inscricao.getQuestionarioIniciacaoAcademica());
				model.addAttribute(NIVEL_INSTRUCAO, NivelInstrucao.values());
				model.addAttribute("turno", Turno.values());
				model.addAttribute(DIAS_UTEIS, DiaUtil.values());
				model.addAttribute(SITUACAO_RESIDENCIA, SituacaoResidencia.values());
				model.addAttribute(TOTAL_ESTADO, Estado.values());
				model.addAttribute(GRAU_PARENTESCO, GrauParentesco.getTodos());
				model.addAttribute("escolaridade",Escolaridade.values());


				List<HorarioDisponivel> horariosDisponiveis = inscricaoService
						.getHorariosDisponiveisIniciacaoAcademica(inscricao.getQuestionarioIniciacaoAcademica().getId());
				if (horariosDisponiveis != null && !horariosDisponiveis.isEmpty()) {
					model.addAttribute("horariosDisponiveis", horariosDisponiveis);
				}

				model.addAttribute("pessoasDaFamilia", inscricao.getQuestionarioAuxilioMoradia().getPessoas());


				return PAGINA_INSCREVER_INICIACAO_ACADEMICA;

			}
		}

		redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}



	@RequestMapping(value = { "inscricao/editar/{idInscricao}" }, method = RequestMethod.POST)
	public String editarInscricaoPost(@Valid @ModelAttribute("questionarioAuxilioMoradia") QuestionarioAuxilioMoradia auxilioMoradia,
			BindingResult result, @RequestParam(value="mora", required=false) List<String> comQuemMora, 
			Authentication auth, RedirectAttributes redirect, @RequestParam(ID_SELECAO) Integer idSelecao,
			Model model, @RequestParam("fileFoto") MultipartFile foto,	@PathVariable("idInscricao") Integer idInscricao) {

		if (result.hasErrors()) {

			model.addAttribute("action", "inscricao");
			model.addAttribute(QUESTIONARIO_AUXILIO_MORADIA, auxilioMoradia);

			Model modelFormAuxilio = this.carregarFormularioAuxilioMoradia(model);
			model.mergeAttributes(modelFormAuxilio.asMap());
			model.addAttribute(ID_SELECAO, idSelecao);
			model.addAttribute(SELECAO, selecaoRepository.findById(idSelecao));

			redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_DADOS_INSCRICAO);

			return PAGINA_INSCREVER_AUXILIO_MORADIA;

		}else{

			Inscricao inscricao = this.inscricaoRepository.findById(idInscricao);
			
			if(!this.verificarExtensaoFoto(foto)){
				redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO);
				//Adicionando o erro no result.
				result.addError(new ObjectError(ERROR, MENSAGEM_ERRO_FOTO_FORMATO_INVALIDO));
			}
			
			try{
				if(foto != null && foto.getSize() > 0){
					auxilioMoradia.setFoto(this.verificarConteudoFoto(foto));
				}else{
					// Se não foi adicionado foto na edição, deixar a foto anterior
					auxilioMoradia.setFoto(inscricao.getQuestionarioAuxilioMoradia().getFoto());
				}
			}catch(IOException exception){
				result.addError(new ObjectError(ERROR, MENSAGEM_ERRO_UPLOAD_FOTO));
				redirect.addFlashAttribute(ERROR, MENSAGEM_ERRO_UPLOAD_FOTO);
			}

			auxilioMoradia.setComQuemMora(this.adicionarComQuemMora(comQuemMora));

			inscricao.setQuestionarioAuxilioMoradia(auxilioMoradia);

			this.inscricaoRepository.save(inscricao);

		}

		redirect.addFlashAttribute(INFO, MENSAGEM_SUCESSO_INSCRICAO_EDITADA);

		return REDIRECT_PAGINA_LISTAR_SELECAO;


	}


	@RequestMapping(value = { "inscricao/listar" }, method = RequestMethod.GET)
	public String listarInscricoes(Model model, Authentication auth) {

		Aluno aluno = alunoRepository.findAlunoComInscricoesPorCpf(auth.getName());

		model.addAttribute(ALUNO, aluno);
		model.addAttribute("inscricoes", aluno.getInscricoes());


		return PAGINA_INSCRICOES_ALUNO;

	}

	@RequestMapping(value = "/inscricao/excluir/{idInscricao}", method = RequestMethod.GET)
	public String excluirInscricao(@PathVariable("idInscricao") Integer idInscricao, RedirectAttributes redirect) {

		Inscricao inscricao = this.inscricaoRepository.findById(idInscricao);
		if(inscricao.isConsolidacao())
			return REDIRECT_PAGINA_MINHAS_INSCRICOES;


		Selecao selecao = inscricao.getSelecao();
		Date date = new Date();

		if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){		
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_EXCLUIR_INSCRICAO);
			return REDIRECT_PAGINA_MINHAS_INSCRICOES;

		} else{

            inscricaoRepository.delete(inscricao);
			redirect.addFlashAttribute(INFO, MENSAGEM_SUCESSO_INSCRICAO_EXCLUIDA);

		}

		return REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "inscricao/detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricao(@PathVariable("idInscricao") Integer idInscricao, Authentication auth, Model model,
			RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		Selecao selecao = inscricao.getSelecao();
		Date date = new Date();
		model.addAttribute(INSCRICAO, inscricao);
		model.addAttribute("usuarioAtivo", inscricao.getAluno().getPessoa());

		if (inscricao == null) {

			redirect.addAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_INSCRICOES_ALUNO;

		}else if (inscricao.getQuestionarioAuxilioMoradia() != null) {

			if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){
				model.addAttribute("esconderBotoes",true);
			} else{
				model.addAttribute("esconderBotoes",false);			
			}


			//Recebendo a mensagem recebida do redirect
			String msgAddDocumentos = (String) model.asMap().getOrDefault(INFO, null);

			if(msgAddDocumentos != null){
				model.addAttribute(INFO,msgAddDocumentos);
			}

			//Verificando se alguma aba específica foi setada no redirect
			String nomeAba = (String) model.asMap().getOrDefault(ABA_SELECIONADA, null);

			if(nomeAba == null){
				//Se nenhuma aba foi setada então a aba padrão é selecionada 
				nomeAba = INSCRICAO_TAB; 
			}

			model.addAttribute(ABA_SELECIONADA, nomeAba);


			return PAGINA_DETALHES_INSCRICAO;

		} else{

			return PAGINA_DETALHES_INICIACAO_ACADEMICA;
		}

	}

	public List<ComQuemMora> adicionarComQuemMora(List<String> listaComQuemMora){

		List<ComQuemMora> comQuemMoraList = new ArrayList<ComQuemMora>();

		if(listaComQuemMora != null){
			for (String m : listaComQuemMora) {
				ComQuemMora comQuemMora = new ComQuemMora();
				comQuemMora.setDescricao(GrauParentesco.valueOf(m));
				comQuemMoraList.add(comQuemMora);
			}
		}

		return comQuemMoraList;
	}

	public boolean verificarExtensaoFoto(MultipartFile foto){

		CommonsMultipartFile multipartFile = (CommonsMultipartFile) foto;

		List<String> formatos = Arrays.asList("image/jpg", "image/jpeg", "image/png");

		if(foto.getSize() == 0) return true;
		return (foto.getSize() > 0 && formatos.contains(multipartFile.getContentType()));

	}

	public byte[] verificarConteudoFoto(MultipartFile foto) throws IOException{

		/*
		 * Conferindo se o aluno enviou uma foto
		 * A inscrição pode ser efetuada mesmo se o aluno não enviar a foto.
		 */

		if( foto.getSize() == 0 ){
			return null;
		}else{
			return foto.getBytes();
		}

	}

	public Model carregarFormularioAuxilioMoradia(Model model){

		model.addAttribute("estado", Estado.values());
		model.addAttribute("situacaoImovel", SituacaoImovel.values());
		model.addAttribute("tipoEnsino", TipoEnsino.values());
		model.addAttribute("finalidadeVeiculo", FinalidadeVeiculo.values());
		model.addAttribute("moraCom", GrauParentesco.getTodosExcetoEu());
		model.addAttribute(GRAU_PARENTESCO, GrauParentesco.getTodos());
		model.addAttribute("escolaridade", Escolaridade.values());

		return model;

	}

	@RequestMapping(value = "/inscricao/adicionarDocumento/{idInscricao}", method = RequestMethod.POST)
	public String enviarDocumento(MultipartFile formulario,	@PathVariable("idInscricao") Integer idInscricao, @RequestParam("idTipo") Integer idTipo, Model model, RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoRepository.findById(idInscricao);

		try {
			List<String> formatos = Arrays.asList("application/pdf");

			if(formatos.contains(formulario.getContentType())){
				Documento documento = new Documento();
				documento.setArquivo(formulario.getBytes());
				documento.setNome(formulario.getOriginalFilename());
				documento.setTipo(formulario.getContentType());

				documentoRepository.save(documento);

				AnaliseDocumentacao documentacao = null;
				DocumentosTipoInscricao dti;

				if(inscricao.getDocumentacao() == null){
					documentacao = new AnaliseDocumentacao();
					documentacao.setInscricao(inscricao);

					TipoDocumento tipo = tipoDocumentoRepository.findById(idTipo);

					dti = new DocumentosTipoInscricao();					
					dti.setTipo(tipo);
					dti.getDocumentos().add(documento);

					documentacao.getDocumentosTipoInscricao().put(idTipo, dti);
					
					documentosTipoInscricaoRepository.save(dti);

					documentacaoRepository.save(documentacao);				
					inscricao.setDocumentacao(documentacao);				
				} else{
					dti = inscricao.getDocumentacao().getDocumentosTipoInscricao().get(idTipo);
					if(dti == null){
						TipoDocumento tipo = tipoDocumentoRepository.findById(idTipo);

						dti = new DocumentosTipoInscricao();											
						dti.setTipo(tipo);
						dti.getDocumentos().add(documento);
						
						documentosTipoInscricaoRepository.save(dti);

						inscricao.getDocumentacao().getDocumentosTipoInscricao().put(idTipo, dti);
					} else{
						dti.getDocumentos().add(documento);
						documentosTipoInscricaoRepository.save(dti);
						inscricao.getDocumentacao().getDocumentosTipoInscricao().put(idTipo, dti);

					}

				}

				inscricaoRepository.save(inscricao);

			}else{
				model.addAttribute(ERROR, MENSAGEM_ERRO_DOCUMENTO_FORMATO_INVALIDO);
			}

		} catch (IOException e) {

		}

		model.addAttribute(INSCRICAO, inscricao);
		model.addAttribute(ABA_SELECIONADA, DOCUMENTOS_TAB);

		return PAGINA_DETALHES_INSCRICAO;
	}

	@RequestMapping(value= {"inscricao/removerDocumento/{idInscricao}/{idTipo}/{idDocumento}"}, method = RequestMethod.GET)
	public String removerDocumento(@PathVariable("idInscricao") Integer idInscricao, @PathVariable("idTipo") Integer idTipo, @PathVariable("idDocumento") Integer idDocumento, Model modelo){

		Inscricao inscricao = inscricaoRepository.findById(idInscricao);

		Documento documento = documentoRepository.findById(idDocumento);

		inscricao.getDocumentacao().getDocumentosTipoInscricao().get(idTipo).getDocumentos().remove(documento);

		inscricaoRepository.save(inscricao);

		documentoRepository.delete(documento);

		modelo.addAttribute(ABA_SELECIONADA, DOCUMENTOS_TAB);
		modelo.addAttribute(INSCRICAO, inscricao);

		return PAGINA_DETALHES_INSCRICAO;
	}
}
