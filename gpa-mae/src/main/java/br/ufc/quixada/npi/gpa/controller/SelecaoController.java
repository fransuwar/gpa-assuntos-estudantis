package br.ufc.quixada.npi.gpa.controller;
import static br.ufc.quixada.npi.gpa.utils.Constants.ERRO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_QTD_VAGAS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SELECAO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SELECIONE_UM_CLASSIFICADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_DOWNLOAD_DOCUMENTO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INFORMACOES_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RANKING_CLASSIFICADOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_SELECIONAR_CLASSIFICADOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.TIPO_AUX_MORADIA;
import static br.ufc.quixada.npi.gpa.utils.Constants.CLASSIFICADOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.CONTENT_DISPOSITION;
import static br.ufc.quixada.npi.gpa.utils.Constants.SELECOES;
import static br.ufc.quixada.npi.gpa.utils.Constants.TIPO_INIC_ACAD;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.repository.AlunoRepository;
import br.ufc.quixada.npi.gpa.repository.DocumentoRepository;
import br.ufc.quixada.npi.gpa.repository.InscricaoRepository;
import br.ufc.quixada.npi.gpa.repository.SelecaoRepository;
import br.ufc.quixada.npi.gpa.repository.ServidorRepository;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Named
@RequestMapping("selecao")
@SessionAttributes({ Constants.USUARIO_ID })
public class SelecaoController {
	
	@Inject
	private AlunoRepository alunoRepository;
	
	@Inject
	private DocumentoRepository documentoRepository;
	
	@Inject
	private InscricaoRepository inscricaoRepository;
	
	@Inject
	private SelecaoRepository selecaoRepository;
	
	@Inject
	private ServidorRepository servidorRepository;

	@RequestMapping(value = { "detalhesPublico/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoesPublico(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		Selecao selecao = selecaoRepository.getOne(idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}

		model.addAttribute(SELECAO, selecao);
		return PAGINA_INFORMACOES_SELECAO;
	}

	@RequestMapping(value = { "detalhes/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoes(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect,Authentication auth) {
		//Detalhe da seleção, apenas para aluno
		Selecao selecao = selecaoRepository.findById(idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}

		model.addAttribute(SELECAO, selecao);

		Aluno aluno = alunoRepository.findAlunoComInscricoesPorCpf(auth.getName());
		List<Inscricao> inscricoes = inscricaoRepository.findInscricoesBySelecaoAndByAluno(selecao.getId(),aluno.getId());
		boolean controle = false;
		
		model.addAttribute("aluno", aluno);
		if(!inscricoes.isEmpty()){
			controle=true;
			model.addAttribute("inscricao", inscricoes.get(0));
		}

		model.addAttribute("controle", controle);

		return PAGINA_INFORMACOES_SELECAO;
	}


	@RequestMapping(value = {"documento/{idDocumento}"}, method = RequestMethod.GET)
	public HttpEntity<byte[]> downloadDocumento(@PathVariable("idDocumento") Integer id, 
			RedirectAttributes redirectAttributes){

		Documento documento = documentoRepository.findById(id);
		byte[] arquivo = documento.getArquivo();
		String[] tipo = documento.getCaminho().split("/");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(tipo[0], tipo[1]));
		headers.set(CONTENT_DISPOSITION, "attachment; filename=" + documento.getNome().replace(" ", "_"));
		headers.setContentLength(arquivo.length);
		redirectAttributes.addFlashAttribute("success", MENSAGEM_SUCESSO_DOWNLOAD_DOCUMENTO);

		return new HttpEntity<byte[]>(arquivo, headers);

	}
	
	@RequestMapping(value = {"visualizarDocumento/{idDocumento}"}, method = RequestMethod.GET)
	public ResponseEntity<byte[]> visualizarDocumento(@PathVariable("idDocumento") Integer idDocumento){
		
		Documento documento = documentoRepository.findById(idDocumento);
		byte[] arquivo = documento.getArquivo();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.set(CONTENT_DISPOSITION, "inline; filename=" + documento.getNome().replace(" ", "_"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		
		return new ResponseEntity<byte[]>(arquivo, headers, HttpStatus.OK);
		
	}

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(ModelMap model, HttpServletRequest request) {

		List<Selecao> selecoes = this.selecaoRepository.findAll();

		model.addAttribute(SELECOES, selecoes);
		model.addAttribute("tipoBolsa", TipoSelecao.values());
		model.addAttribute(TIPO_INIC_ACAD, TipoSelecao.INIC_ACAD);
		model.addAttribute(TIPO_AUX_MORADIA, TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "/listarPorServidor/{id}")
	public String listarSelecaoPorServidor(@PathVariable("id") Integer id, ModelMap model) {

		List<Selecao> selecoes = this.servidorRepository.findById(id).getParticipaComissao();

		model.addAttribute(SELECOES, selecoes);
		model.addAttribute(TIPO_INIC_ACAD, TipoSelecao.INIC_ACAD);
		model.addAttribute(TIPO_AUX_MORADIA, TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO;
	}

	/*@RequestMapping(value = "parecer/{idSelecao}", method = RequestMethod.POST)
	public String emitirParecer(@Valid @ModelAttribute("pareceres") ParecerForm parecerForm,
			@PathVariable("idSelecao") Integer id, BindingResult result, HttpServletRequest request,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return PAGINA_LISTAR_INSCRITOS_SELECAO;
		}

		// TODO - Implementar o método que dará o parecer do aluno.

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_PARECER_EMITIDO);

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}*/

	@RequestMapping(value = { "inscricao/detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricao() {
		// TODO - Método p/ implementar que retorna página de detalhes de uma seleção.
		return "";
	}
	
	@RequestMapping(value = {"ranking/{idSelecao}"}, method = RequestMethod.GET)
	public String visualizarRanking(ModelMap model, @PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect){
		Selecao selecao = selecaoRepository.findById(idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		
		List<Inscricao> classificados = inscricaoRepository.findClassificadosBySelecao(selecao.getId());

		model.addAttribute(CLASSIFICADOS, classificados);
		
		return PAGINA_RANKING_CLASSIFICADOS;
		
	}
	
	@RequestMapping(value = {"selecionarClassificados/{idSelecao}"}, method = RequestMethod.GET)
	public String visualizarPaginaSelecionarClassificados(ModelMap model, @PathVariable("idSelecao") Integer idSelecao,
		 RedirectAttributes redirect){		
		Selecao selecao = selecaoRepository.findById(idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		
		List<Inscricao> classificados = inscricaoRepository.findClassificadosBySelecao(selecao.getId());
		
		List<Inscricao> classificaveis = inscricaoRepository.findClassificaveisBySelecao(selecao.getId());
		
		model.addAttribute(CLASSIFICADOS, classificados);
		model.addAttribute("classificaveis",classificaveis);
		model.addAttribute("qtdClassificados",classificados.size());
		model.addAttribute("qtdClassificaveis",classificaveis.size());
		model.addAttribute(SELECAO,selecao);
		
		model.addAttribute(Constants.CARD_SELECIONADO, Constants.CARD_RANK);
		
		return PAGINA_SELECIONAR_CLASSIFICADOS;
		
	}
	
	@RequestMapping(value = {"/selecionarClassificados/{idSelecao}"}, method = RequestMethod.POST)
	public String selecionarClassificados(ModelMap model, @RequestParam("checkClassificados[]") List<Integer> idsClassificados,
			@PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect, Authentication auth){
		
		if(idsClassificados.isEmpty()){
			model.addAttribute(ERRO, MENSAGEM_ERRO_SELECIONE_UM_CLASSIFICADO);
		}
		
		Selecao selecao = selecaoRepository.findById(idSelecao);
		 
		 if (selecao == null) {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
				return REDIRECT_PAGINA_LISTAR_SELECAO;
			}
		 
		 List<Inscricao> classificados = inscricaoRepository.findClassificadosBySelecao(selecao.getId());
		 Integer vagasRestantes = selecao.getQuantidadeVagas() - classificados.size();		     	
		 
		 if(idsClassificados.size() > vagasRestantes){
			 redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_QTD_VAGAS); 
			return REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS + idSelecao;
		 } else{		      
		     for(Integer id: idsClassificados){
		         inscricaoRepository.atualizarClassificacao(id, true);
		     }
		 }
		
		return REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS + idSelecao;
		
	}
	
	@RequestMapping(value = {"/removerClassificados/{idSelecao}"}, method = RequestMethod.POST)
	public String removerClassificados(ModelMap model, @RequestParam("checkClassificaveis[]") List<Integer> idsClassificaveis,
			@PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect, Authentication auth){

		Selecao selecao = selecaoRepository.findById(idSelecao);
		
		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}		     		 
       
		for(Integer id: idsClassificaveis){
			inscricaoRepository.atualizarClassificacao(id,false);
		}
		
		return REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS + idSelecao;
		
	}
	
}
