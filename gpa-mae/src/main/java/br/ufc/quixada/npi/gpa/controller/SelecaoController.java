package br.ufc.quixada.npi.gpa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.PageConstants;

@Controller
@RequestMapping("/selecao")
public class SelecaoController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private SelecaoService selecaoService;
	
	@GetMapping("/listar")
	public String listarSelecoes(Model model, Authentication auth) {
		Pessoa pessoa = pessoaService.getByCpf(auth.getName());
		if (pessoa.isCoordenador() || pessoa.isAluno()) {
			model.addAttribute("selecoes", selecaoService.getAll());
		} else if (pessoa.isServidor()) {
			model.addAttribute("selecoes", selecaoService.getByMembroComissao(pessoa.getCpf()));
		}
		return PageConstants.LISTAR_SELECAO;
	}
	
	/*@RequestMapping(value = { "detalhesPublico/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoesPublico(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		Selecao selecao = selecaoRepository.getOne(idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}

		model.addAttribute("selecao", selecao);
		return PAGINA_DETALHE_SELECAO_COMISSAO;
	}

	@RequestMapping(value = { "detalhes/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoes(@PathVariable("idSelecao") Selecao selecao, Model model, RedirectAttributes redirect,Authentication auth) {
		//Detalhe da seleção, apenas para aluno

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}

		model.addAttribute("selecao", selecao);

		Aluno aluno = alunoRepository.findByCpf(auth.getName());
		List<Inscricao> inscricoes = inscricaoRepository.findInscricoesBySelecaoAndByAluno(selecao.getId(),aluno.getId());
		boolean controle = false;
		
		model.addAttribute("aluno", aluno);
		if(!inscricoes.isEmpty()){
			controle=true;
			model.addAttribute("inscricao", inscricoes.get(0));
		}

		model.addAttribute("controle", controle);

		return PAGINA_DETALHE_SELECAO_COMISSAO;
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
		return PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "/listarPorServidor/{id}")
	public String listarSelecaoPorServidor(@PathVariable("id") Integer id, ModelMap model) {

		// TODO: alterar estratégia para buscar as seleções de que um servidor participa
		//List<Selecao> selecoes = this.servidorRepository.findById(id).getParticipaComissao();

		//model.addAttribute(SELECOES, selecoes);
		return PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "parecer/{idSelecao}", method = RequestMethod.POST)
	public String emitirParecer(@Valid @ModelAttribute("pareceres") ParecerForm parecerForm,
			@PathVariable("idSelecao") Integer id, BindingResult result, HttpServletRequest request,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return PAGINA_LISTAR_INSCRITOS_SELECAO;
		}

		// TODO - Implementar o método que dará o parecer do aluno.

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_PARECER_EMITIDO);

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "inscricao/detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricao() {
		// TODO - Método p/ implementar que retorna página de detalhes de uma seleção.
		return "";
	}
	
	@RequestMapping(value = {"ranking/{idSelecao}"}, method = RequestMethod.GET)
	public String visualizarRanking(ModelMap model, @PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect){

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		
		List<Inscricao> classificados = inscricaoRepository.findClassificadosBySelecao(selecao.getId());

		model.addAttribute(CLASSIFICADOS, classificados);
		
		return PAGINA_RANKING_CLASSIFICADOS;
		
	}
	
	@RequestMapping(value = {"selecionarClassificados/{idSelecao}"}, method = RequestMethod.GET)
	public String visualizarPaginaSelecionarClassificados(ModelMap model, @PathVariable("idSelecao") Selecao selecao,
		 RedirectAttributes redirect){		

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
		model.addAttribute("selecao",selecao);
		
		model.addAttribute(Constants.CARD_SELECIONADO, Constants.CARD_RANK);
		
		return PAGINA_SELECIONAR_CLASSIFICADOS;
		
	}
	
	@RequestMapping(value = {"/selecionarClassificados/{idSelecao}"}, method = RequestMethod.POST)
	public String selecionarClassificados(ModelMap model, @RequestParam("checkClassificados[]") List<Integer> idsClassificados,
			@PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect, Authentication auth){
		
		if(idsClassificados.isEmpty()){
			model.addAttribute(ERRO, MENSAGEM_ERRO_SELECIONE_UM_CLASSIFICADO);
		}
		
		 if (selecao == null) {
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
				return REDIRECT_PAGINA_LISTAR_SELECAO;
			}
		 
		 List<Inscricao> classificados = inscricaoRepository.findClassificadosBySelecao(selecao.getId());
		 Integer vagasRestantes = selecao.getQuantidadeVagas() - classificados.size();		     	
		 
		 if(idsClassificados.size() > vagasRestantes){
			 redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_QTD_VAGAS); 
			return REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS + selecao.getId();
		 } else{		      
		     for(Integer id: idsClassificados){
		         inscricaoRepository.atualizarClassificacao(id, true);
		     }
		 }
		
		return REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS + selecao.getId();
		
	}
	
	@RequestMapping(value = {"/removerClassificados/{idSelecao}"}, method = RequestMethod.POST)
	public String removerClassificados(ModelMap model, @RequestParam("checkClassificaveis[]") List<Integer> idsClassificaveis,
			@PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect, Authentication auth){

		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}		     		 
       
		for(Integer id: idsClassificaveis){
			inscricaoRepository.atualizarClassificacao(id,false);
		}
		
		return REDIRECT_PAGINA_SELECIONAR_CLASSIFICADOS + selecao.getId();
		
	}*/
	
}
