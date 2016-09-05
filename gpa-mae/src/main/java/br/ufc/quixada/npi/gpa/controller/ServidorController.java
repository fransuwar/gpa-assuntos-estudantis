package br.ufc.quixada.npi.gpa.controller;
import static br.ufc.quixada.npi.gpa.utils.Constants.ABA_SELECIONADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.VISITA_TAB;
import static br.ufc.quixada.npi.gpa.utils.Constants.INSCRICAO_TAB;
import static br.ufc.quixada.npi.gpa.utils.Constants.DOCUMENTOS_TAB;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_DE_SUCESSO_AVALIAR_DOCUMENTACAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_DE_SUCESSO_ENTREVISTA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ALUNO_INDEFERIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_INSCRICAO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_REALIZACAO_DE_VISITA_DOMICILIAR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SELECAO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_ENTREVISTA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_VISITA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_VISITA_DOMICILIAR_JA_EXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_VISITA_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_PERMISSAO_NEGADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_VISITA_CADASTRADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INFORMACOES_RELATORIO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INFORMACOES_SELECAO_SERVIDOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_SELECAO_SERVIDOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_REALIZAR_ENTREVISTA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RELATORIO_VISITA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RELATORIO_VISITAS;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_DETALHES_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_DETALHES_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_INFORMACOES_SELECAO_SERVIDOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.ENTREVISTA;
import static br.ufc.quixada.npi.gpa.utils.Constants.INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.DOCUMENTACAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MORADIA_ESTADO;


import java.io.IOException;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.Escolaridade;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.Resultado;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.AnaliseDocumentacao;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.Imagem;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.repository.DocumentoRepository;
import br.ufc.quixada.npi.gpa.repository.EntrevistaRepository;
import br.ufc.quixada.npi.gpa.repository.ImagemRepository;
import br.ufc.quixada.npi.gpa.repository.InscricaoRepository;
import br.ufc.quixada.npi.gpa.repository.SelecaoRepository;
import br.ufc.quixada.npi.gpa.repository.ServidorRepository;
import br.ufc.quixada.npi.gpa.repository.VisitaDomiciliarRepository;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping ("servidor")
@SessionAttributes({ Constants.USUARIO_ID , Constants.USUARIO_LOGADO})
public class ServidorController {
	
	@Inject
	private InscricaoService inscricaoService;
	
	@Inject
	private ImagemRepository imagemRepository;
	
	@Inject
	private DocumentoRepository documentoRepository;
	
	@Inject
	private EntrevistaRepository entrevistaRepository;
	
	@Inject
	private InscricaoRepository inscricaoRepository;
	
	@Inject
	private VisitaDomiciliarRepository visitaRepository;
	
	@Inject
	private SelecaoRepository selecaoRepository;
	
	@Inject
	private ServidorRepository servidorRepository;
	
	
	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(Model model, Authentication auth, RedirectAttributes redirect) {
		Servidor servidor = servidorRepository.findByCpf(auth.getName());
		model.addAttribute("selecoes", servidor.getParticipaComissao());
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO_SERVIDOR;
	}

	@RequestMapping(value= {"entrevista/{idInscricao}"}, method = RequestMethod.GET)
	public String realizarEntrevista(@PathVariable("idInscricao") Integer idInscricao,Authentication auth, RedirectAttributes redirect, Model model ){
		Inscricao inscricao = this.inscricaoRepository.findById(idInscricao);		

		if(inscricao == null){
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}else{

			if(!inscricao.getDocumentacao().getDeferimento().equals(Resultado.NAO_AVALIADO)){

				Selecao selecao = inscricao.getSelecao();

				Servidor servidor = servidorRepository.findByCpf(auth.getName());

				List<Servidor> comissao = selecao.getMembrosComissao();

				if(comissao.contains(servidor)){

					model.addAttribute(ENTREVISTA, new Entrevista());
					model.addAttribute("idInscricao", idInscricao);

					return PAGINA_REALIZAR_ENTREVISTA;

				}else{
					redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_ENTREVISTA);
					return REDIRECT_PAGINA_LISTAR_SELECAO;
				}
			}else{
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_ALUNO_INDEFERIDO);
				return REDIRECT_PAGINA_LISTAR_SELECAO;
			}
		}
	}
	
	@RequestMapping(value="atualizarEntrevista", method = RequestMethod.POST)
	public String atualizarEntrevista(@Valid @ModelAttribute("entrevista") Entrevista entrevista, @RequestParam("idInscricao") Integer idInscricao, @RequestParam("idEntrevista") Integer idEntrevista, 
			RedirectAttributes redirect, boolean realizarVisita ){
		
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		
		Entrevista entrevista2 = entrevistaRepository.findById(idEntrevista);
		entrevista2.setObservacao(entrevista.getObservacao());
		entrevista2.setDeferimento(entrevista.getDeferimento());
		inscricao.setRealizarVisita(realizarVisita);
		entrevistaRepository.save(entrevista2);
		inscricaoRepository.save(inscricao);

		redirect.addFlashAttribute("info",MENSAGEM_DE_SUCESSO_ENTREVISTA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	

	@RequestMapping(value="entrevista", method = RequestMethod.POST)
	public String entrevista(@Valid @ModelAttribute("entrevista") Entrevista entrevista, @RequestParam("idInscricao") Integer idInscricao,
			RedirectAttributes redirect, Authentication auth, @RequestParam(value="realizarVisita", required=false) boolean realizarVisita ){
		
		Servidor servidor = this.servidorRepository.findServidorComComissaoByCpf(auth.getName());
		entrevista.setServidor(servidor);
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		inscricao.setEntrevista(entrevista);
		
		inscricao.setRealizarVisita(realizarVisita);

		inscricaoRepository.save(inscricao);

		redirect.addFlashAttribute("info", MENSAGEM_DE_SUCESSO_ENTREVISTA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	
	
	@RequestMapping(value= {"visita/removerFormulario/{idInscricao}/{idFormulario}"}, method = RequestMethod.GET)
	public String removerFormularioVisita(@PathVariable("idInscricao") Integer idInscricao, @PathVariable("idFormulario") Integer idFormulario, RedirectAttributes redirect){

		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
			
		Documento documento = documentoRepository.findById(idFormulario);
		if(documento != null){
			inscricao.getVisitaDomiciliar().setFormularioVisita(null);
			
			inscricaoRepository.save(inscricao);
			
			documentoRepository.delete(documento);	
		}

		redirect.addFlashAttribute(ABA_SELECIONADA, VISITA_TAB);
		redirect.addFlashAttribute(INSCRICAO, inscricao);
		
		
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + inscricao.getId();
	}
	
	@RequestMapping(value= {"visita/enviarFormulario/{idInscricao}"}, method = RequestMethod.POST)
	public String enviarFormularioDeVisita(@PathVariable("idInscricao") Integer idInscricao, MultipartFile formulario, RedirectAttributes redirect){

		try {
			
			Documento documento = new Documento();
			documento.setArquivo(formulario.getBytes());
			documento.setNome(formulario.getOriginalFilename());
			documento.setTipo(formulario.getContentType());
			
			Inscricao inscricao = inscricaoRepository.findById(idInscricao);
			
			documentoRepository.save(documento);
			
			inscricao.getVisitaDomiciliar().setFormularioVisita(documento);
			
			inscricaoRepository.save(inscricao);
			
			redirect.addFlashAttribute(INSCRICAO, inscricao);
			redirect.addFlashAttribute(ABA_SELECIONADA, VISITA_TAB);
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
	}

	@RequestMapping(value = { "visita/{idInscricao}" }, method = RequestMethod.GET)
	public String realizarVisita(@PathVariable("idInscricao")Integer idInscricao, Model model, RedirectAttributes redirect, Authentication auth) {

		Inscricao inscricao = this.inscricaoRepository.findById(idInscricao);

		if(inscricao == null ){
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}else{

			Servidor servidor = servidorRepository.findByCpf(auth.getName());

			List<Servidor> comissao = inscricao.getSelecao().getMembrosComissao();

			if(comissao.contains(servidor) ){
				if(inscricao.getVisitaDomiciliar() == null){
					if (inscricao.getSelecao().getTipoSelecao().equals(TipoSelecao.AUX_MOR) &&  inscricao.getEntrevista().getDeferimento().equals(Resultado.DEFERIDO)){
						VisitaDomiciliar relatorioVisitaDomiciliar = new VisitaDomiciliar();

						model.addAttribute("relatorioVisitaDomiciliar", relatorioVisitaDomiciliar);
						model.addAttribute("curso", Curso.values());
						model.addAttribute(MORADIA_ESTADO, EstadoMoradia.values());
						model.addAttribute(INSCRICAO, inscricao);
						model.addAttribute("selecao", inscricao.getSelecao());
						model.addAttribute("idInscricao", inscricao.getId());

						return PAGINA_RELATORIO_VISITA;
					}else{
						redirect.addFlashAttribute("erro", MENSAGEM_ERRO_REALIZACAO_DE_VISITA_DOMICILIAR);
						return REDIRECT_PAGINA_DETALHES_SELECAO + inscricao.getSelecao().getId();
					}
				}else{
					redirect.addFlashAttribute("erro", MENSAGEM_ERRO_VISITA_DOMICILIAR_JA_EXISTENTE);
					return REDIRECT_PAGINA_DETALHES_SELECAO + inscricao.getSelecao().getId();
				}

			} else{
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_VISITA);
				return REDIRECT_PAGINA_DETALHES_SELECAO + inscricao.getSelecao().getId();			
			}
		}
	}

	@RequestMapping(value = { "visita" }, method = RequestMethod.POST)
	public String realizarVisita(@Valid @ModelAttribute("relatorioVisitaDomiciliar") VisitaDomiciliar relatorioVisitaDomiciliar, BindingResult result,
			@RequestParam("idInscricao") Integer idInscricao,@RequestParam("idSelecao") Integer idSelecao,
			Model model, RedirectAttributes redirect, Authentication auth) {

		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		Servidor servidor = servidorRepository.findByCpf(auth.getName());
		
		relatorioVisitaDomiciliar.setServidor(servidor);
		inscricao.setVisitaDomiciliar(relatorioVisitaDomiciliar);

		if (result.hasErrors()) {

			model.addAttribute("curso", Curso.values());
			model.addAttribute(MORADIA_ESTADO, EstadoMoradia.values());
			model.addAttribute(INSCRICAO, inscricao);
			model.addAttribute("selecao", inscricao.getSelecao());

			return PAGINA_RELATORIO_VISITA;
		}

		inscricaoRepository.save(inscricao);
		redirect.addFlashAttribute("info", MENSAGEM_VISITA_CADASTRADA);

		return REDIRECT_PAGINA_DETALHES_SELECAO  + inscricao.getSelecao().getId();
	}

	@RequestMapping(value = { "informacoes/visita-domiciliar/{idVisita}" }, method = RequestMethod.GET)
	public String visulizarInformacoes(@PathVariable("idVisita") Integer idVisita, Model model, RedirectAttributes redirect) {

		VisitaDomiciliar visitaDomiciliar = visitaRepository.findById(idVisita);

		if (visitaDomiciliar == null ) {

			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_VISITA_INEXISTENTE);
			return REDIRECT_PAGINA_DETALHES_SELECAO;
		}

		model.addAttribute("relatorio", visitaDomiciliar);

		return PAGINA_INFORMACOES_RELATORIO;
	}
	/*@RequestMapping(value = "inscritos/{idSelecao}", method = RequestMethod.GET)
	public String listarInscritos(@PathVariable("idSelecao") Integer idSelecao, ModelMap model) {
		model.addAttribute("inscricoes", inscricaoService.getInscricoesPorSelecao(idSelecao));
		return PAGINA_LISTAR_INSCRITOS_SELECAO;
	}

	*/@RequestMapping(value = { "detalhes/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoes(@PathVariable("idSelecao") Integer idSelecao,Authentication auth, Model model, RedirectAttributes redirect){

		Selecao selecao = selecaoRepository.findById(idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		} else {
			Servidor servidor = servidorRepository.findByCpf(auth.getName());
			List<Servidor> comissao = selecao.getMembrosComissao();
			if(comissao.contains(servidor)) {
				List<Inscricao> inscricoes = inscricaoRepository.findInscricoesBySelecao(idSelecao);
				model.addAttribute("selecao", selecao);
				model.addAttribute("inscricoes", inscricoes);
				model.addAttribute(Constants.CARD_SELECIONADO, Constants.CARD_INSCRICAO);
				
				return PAGINA_INFORMACOES_SELECAO_SERVIDOR;
			} else {
				redirect.addFlashAttribute("erro",  MENSAGEM_PERMISSAO_NEGADA);
				return REDIRECT_PAGINA_LISTAR_SELECAO;
			}
		}
	}

	@RequestMapping(value ={ "detalhes/inscricao/{idInscricao}"}, method = RequestMethod.GET)
	public String detalhesInscricao(@PathVariable("idInscricao") Integer idInscricao, Model modelo,
			RedirectAttributes redirect, @RequestParam(value="ativar-aba-entrevista",required=false) boolean ativarAbaEntrevista) {
			
		
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		modelo.addAttribute("pessoaDaFamilia",new PessoaFamilia());
		modelo.addAttribute("documentacao", new AnaliseDocumentacao());
		
		if (inscricao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;

		}else if(inscricao.getSelecao().getTipoSelecao().equals(TipoSelecao.AUX_MOR)){
			modelo.addAttribute(INSCRICAO, inscricao);
			modelo.addAttribute("usuarioAtivo", inscricao.getAluno().getPessoa());
			
			//Verificando se alguma aba específica foi setada no redirect
			String nomeAba = (String) modelo.asMap().getOrDefault(ABA_SELECIONADA, null);
			
			if(nomeAba == null){
				//Se nenhuma aba foi setada então a aba padrão é selecionada 
				nomeAba = INSCRICAO_TAB; 
			}

			modelo.addAttribute(ABA_SELECIONADA, nomeAba);
			
			if(inscricao.getEntrevista()!=null){
				modelo.addAttribute(ENTREVISTA, inscricao.getEntrevista());
			}else{
				modelo.addAttribute(ENTREVISTA, new Entrevista());
			    modelo.addAttribute("grauParentesco", GrauParentesco.values());
			    modelo.addAttribute("escolaridade",Escolaridade.values());
			}
			
			if(inscricao.getDocumentacao() != null){
				modelo.addAttribute(DOCUMENTACAO, inscricao.getDocumentacao());
			}else{
				modelo.addAttribute(DOCUMENTACAO, new AnaliseDocumentacao());
			}
			    
			

			return PAGINA_DETALHES_INSCRICAO;
		}else {
			modelo.addAttribute(INSCRICAO, inscricao);
			modelo.addAttribute("questInic", inscricao.getQuestionarioIniciacaoAcademica());
			return PAGINA_DETALHES_INICIACAO_ACADEMICA;
		}
		
		
	}
	
	@RequestMapping(value ={ "detalhes/inscricao/inserirImagem"}, method = RequestMethod.POST)
	public String inserirImagemDaVisitaNaInscricao(@RequestParam("foto") MultipartFile foto, Integer idInscricao, RedirectAttributes redirect){
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		
		try {
			CommonsMultipartFile multipartFile = (CommonsMultipartFile) foto;
			
			List<String> formatos = Arrays.asList("image/jpg", "image/jpeg", "image/png");
			
			if(foto.getSize() > 0 && formatos.contains(multipartFile.getContentType())){
				Imagem imagem = new Imagem();
				imagem.setImg(foto.getBytes());
				
				imagemRepository.save(imagem);
				
				inscricao.getVisitaDomiciliar().getImagens().add(imagem);
				inscricaoRepository.save(inscricao);
			}
		} catch (IOException e) {
			
		}
		
		redirect.addFlashAttribute(INSCRICAO, inscricao);
		redirect.addFlashAttribute(ABA_SELECIONADA, VISITA_TAB);
		
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
	}
	
	@RequestMapping(value ={ "detalhes/inscricao/removerImagem/{idInscricao}/{idImagem}"}, method = RequestMethod.GET)
	public String removerImagemDaVisitaNaInscricao(@PathVariable("idInscricao") Integer idInscricao, @PathVariable("idImagem") Integer idImagem, RedirectAttributes redirect){
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		Imagem imagem = imagemRepository.findById(idImagem);
		
		inscricao.getVisitaDomiciliar().getImagens().remove(imagem);
		
		inscricaoRepository.save(inscricao);
		
		redirect.addFlashAttribute(ABA_SELECIONADA, VISITA_TAB);
		redirect.addFlashAttribute(INSCRICAO, inscricao);
		
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
	}
	
	@RequestMapping(value={"detalhes/inscricao/adicionarObservacaoParecer"}, method = RequestMethod.POST)
	public String adicionarObservacaoParecerVisita(@RequestParam("idInscricao") Integer idInscricao, 
			@RequestParam("parecer") String parecer, @RequestParam("observacao") String observacao, 
			RedirectAttributes redirect, Authentication auth){
		
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		
		VisitaDomiciliar visitaDomiciliar = inscricao.getVisitaDomiciliar();
		
		if(visitaDomiciliar != null){
			Servidor servidor = servidorRepository.findByCpf(auth.getName());
			visitaDomiciliar.setDeferimento(Resultado.valueOf(parecer));
			visitaDomiciliar.setObservacaoParecer(observacao);
			visitaDomiciliar.setServidor(servidor);
			visitaRepository.save(visitaDomiciliar);
		}
		
		redirect.addFlashAttribute(ABA_SELECIONADA, VISITA_TAB);
		
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
	}
	
	@RequestMapping(value={"inscricao/adicionarPessoaFamilia/{idInscricao}"}, method = RequestMethod.POST)
	public String adicionarPessoaFamilaNaEntrevista(@Valid @ModelAttribute("pessoaDaFamilia") PessoaFamilia pessoa, Model model,
			@PathVariable("idInscricao") Integer idInscricao, RedirectAttributes redirect){
		
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		
		if(inscricao == null){
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_INFORMACOES_SELECAO_SERVIDOR;
		}else{
		QuestionarioAuxilioMoradia auxilio = inscricao.getQuestionarioAuxilioMoradia();
		auxilio.getPessoasEntrevista().add(pessoa);
		inscricaoRepository.save(inscricao);
		redirect.addFlashAttribute(ABA_SELECIONADA,"entrevista-tab");
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
		}
			
		
	}
	
	@RequestMapping(value={"inscricao/removerPessoaFamilia/{idPessoa}/{idInscricao}"}, method = RequestMethod.GET)
	public String removerPessoaFamilaNaEntrevista( Model model,
			@PathVariable("idPessoa") Integer idPessoa,@PathVariable("idInscricao") Integer idInscricao, RedirectAttributes redirect){
		
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		
		if(inscricao == null){
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_INFORMACOES_SELECAO_SERVIDOR;
		}else{
		PessoaFamilia pessoa = inscricaoService.buscarPessoaFamiliaPorId(idPessoa);
		inscricao.getQuestionarioAuxilioMoradia().getPessoasEntrevista().remove(pessoa);

		inscricaoRepository.save(inscricao);
        model.addAttribute(INSCRICAO,inscricao);

	    redirect.addFlashAttribute(ABA_SELECIONADA,"entrevista-tab");
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
		}
			
		
	}

	@RequestMapping(value= {"avaliarDocumentacao"}, method = RequestMethod.POST)
	public String avaliarDocumentacao(@Valid @ModelAttribute("documentacao") AnaliseDocumentacao analiseDocumentacao , @RequestParam("idInscricao") Integer idInscricao, 

			BindingResult result, RedirectAttributes redirect, Model model , Authentication auth){

        Inscricao inscricao = inscricaoRepository.findById(idInscricao);
        Servidor servidor = servidorRepository.findByCpf(auth.getName());

        analiseDocumentacao.setServidor(servidor);
		inscricao.setDocumentacao(analiseDocumentacao);
		inscricaoRepository.save(inscricao);
		
		redirect.addFlashAttribute(ABA_SELECIONADA, DOCUMENTOS_TAB);
		redirect.addFlashAttribute("info", MENSAGEM_DE_SUCESSO_AVALIAR_DOCUMENTACAO);
		redirect.addFlashAttribute(ABA_SELECIONADA,DOCUMENTOS_TAB);
		return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
	}
	
	@RequestMapping(value= {"relatorioVisitas/{idSelecao}"}, method = RequestMethod.GET)
	public String relatorioDeVisitas(@PathVariable("idSelecao") Integer idSelecao, Model model){
		
		Selecao selecao = selecaoRepository.findById(idSelecao);
		
		model.addAttribute("inscritosComVisita", selecao.getAlunosSelecionadosVisita());
		model.addAttribute("inscritosSemVisita", selecao.getAlunosNaoSelecionadosVisita());
		model.addAttribute("cidadesVisitadas", selecao.getCidadesVisita());
		model.addAttribute("selecao", selecao);
		
		model.addAttribute(Constants.CARD_SELECIONADO, Constants.CARD_RELATORIO);
		
		return PAGINA_RELATORIO_VISITAS;
	}
}