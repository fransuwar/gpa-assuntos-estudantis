package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.DATA_TERMINO;
import static br.ufc.quixada.npi.gpa.utils.Constants.EDITAR;
import static br.ufc.quixada.npi.gpa.utils.Constants.ERRO;
import static br.ufc.quixada.npi.gpa.utils.Constants.INFO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ANEXO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_SELECAO_COM_INSCRITOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO_EM_USO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_MEMBRO_COMISSAO_REPETICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_FALTA_DE_PERMISSAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_COMISSAO_FORMADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_SELECAO_ATUALIZADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_SELECAO_REMOVIDA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_TIPO_DOCUMENTO_EXCUIDO_COM_SUCESSO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_ADICIONAR_ARQUIVO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_ATRIBUIR_COMISSAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_CADASTRAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_GERENCIAR_DOCUMENTOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RELATORIO_FINAL;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_ADICIONAR_ARQUIVO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_ATRIBUIR_COMISSAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_DETALHES_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_GERENCIAR_DOCUMENTOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.repository.DocumentoRepository;
import br.ufc.quixada.npi.gpa.repository.InscricaoRepository;
import br.ufc.quixada.npi.gpa.repository.SelecaoRepository;
import br.ufc.quixada.npi.gpa.service.DocumentacaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.gpa.utils.Constants;
import br.ufc.quixada.npi.gpa.utils.MessageConstants;
import br.ufc.quixada.npi.gpa.utils.PageConstants;
import br.ufc.quixada.npi.gpa.utils.RedirectConstants;

@Controller
@RequestMapping("coordenador")
public class CoordenadorController {

	@Autowired
	private SelecaoService selecaoService;
	
	@Autowired
	private DocumentacaoService documentacaoService;
	
	@Autowired
	private ServidorService servidorService;

	/*@Autowired
	private InscricaoRepository inscricaoRepository;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private SelecaoRepository selecaoRepository;*/

	
	// Gerenciamento de seleções
	
	/*@GetMapping("selecao/cadastrar")
	public String cadastrarSelecao(Model model) {
		model.addAttribute("action", "cadastrar");
		model.addAttribute("selecao", new Selecao());
		model.addAttribute("tiposDeDocumento", documentacaoService.getAllTipoDocumento());

		return PAGINA_CADASTRAR_SELECAO;
	}

	@PostMapping("selecao/cadastrar")
	public String cadastroSelecao(@Valid @ModelAttribute("selecao") Selecao selecao, BindingResult result, 
			@RequestParam(value="checkDocumentos[]", required=false) List<Integer> idsTiposDocumentos,
			 Authentication auth, Model model) {

		if (selecao.getAno() != null && selecao.getAno() < LocalDate.now().getYear()) {
			result.rejectValue("ano", "selecao.ano", MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR);
		}

		if ((selecao.getDataInicio() != null && selecao.getDataTermino() != null) && 
				selecao.getDataTermino().before(selecao.getDataInicio())) {
			result.rejectValue(DATA_TERMINO, "selecao.dataTermino", MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR);
		}
		
		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("action", "cadastrar");
			model.addAttribute("tiposDeDocumento", documentacaoService.getAllTipoDocumento());
			return PAGINA_CADASTRAR_SELECAO;
		}
		
		selecao.setResponsavel(servidorService.getByCpf(auth.getName()));
		
		if(idsTiposDocumentos != null) {
			for(Integer id: idsTiposDocumentos) {
				selecao.addTipoDocumento(documentacaoService.getTipoDocumentoById(id));
			}
		}

		this.selecaoService.cadastrar(selecao);
		return REDIRECT_PAGINA_DETALHES_SELECAO + selecao.getId();

	}*/
	
	
	// Gerenciamento de tipos de documentos
	
	@GetMapping("tipo-documento")
	public String listarTipoDocumento(Model model){
		model.addAttribute("documentos", documentacaoService.getAllTipoDocumento());
		return PageConstants.GERENCIAR_DOCUMENTOS;
	}
	
	@PostMapping("tipo-documento/cadastrar")
	public String cadastrarTipoDocumento(TipoDocumento tipoDocumento){
		if(!tipoDocumento.getNome().isEmpty()){
			documentacaoService.salvar(tipoDocumento);
		}
		return RedirectConstants.REDIRECT_GERENCIAR_DOCUMENTOS;
	}
	
	@GetMapping("tipo-documento/excluir/{id}")
	public String excluirTipoDocumento(@PathVariable("id") TipoDocumento tipoDocumento,
			RedirectAttributes redirect) {

		if (tipoDocumento != null){
			try {
				documentacaoService.excluirTipoDocumento(tipoDocumento.getId());
				redirect.addFlashAttribute(INFO, MessageConstants.MSG_TIPO_DOCUMENTO_EXCUIDO_COM_SUCESSO);
			} catch(Exception e){
				redirect.addFlashAttribute(ERRO, MessageConstants.MSG_ERRO_TIPO_DOCUMENTO_EM_USO);
			}
		}
		return  RedirectConstants.REDIRECT_GERENCIAR_DOCUMENTOS;
	}
	
	
	// old
	/*@RequestMapping(value = { "selecao/editar/{idSelecao}" }, method = RequestMethod.GET)
	public String editarSelecao(@PathVariable("idSelecao") Selecao selecao, Model model, RedirectAttributes redirect) {
		if (selecao != null) {
			model.addAttribute("action", EDITAR);
			model.addAttribute("selecao", selecao);
			model.addAttribute("membrosComissao", selecao.getComissao());
			model.addAttribute("tiposDeDocumento",documentacaoService.getAllTipoDocumento());
			return PAGINA_CADASTRAR_SELECAO;
		}
		return PAGINA_CADASTRAR_SELECAO;
	}

	@RequestMapping(value = { "selecao/editar" }, method = RequestMethod.POST)
	public String editarSelecao(@Valid @ModelAttribute("selecao") Selecao selecaoAtualizada, BindingResult result,
			Model model, RedirectAttributes redirect, @RequestParam("checkDocumentos[]") List<Integer> idstiposDocumentos) {
		model.addAttribute("action", EDITAR);
		if ((selecaoAtualizada != null && selecaoAtualizada.getDataInicio() != null && selecaoAtualizada.getDataTermino() != null) &&
				selecaoAtualizada.getDataTermino().before(selecaoAtualizada.getDataInicio())) {
			result.rejectValue(DATA_TERMINO, "selecao.dataTermino", MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR);
		}

		if (result.hasErrors()) {
			model.addAttribute("selecao", selecaoAtualizada);
			return PAGINA_CADASTRAR_SELECAO;
		}
		Selecao selecao = selecaoService.getById(selecaoAtualizada.getId());
		if(idstiposDocumentos != null ) {
			for(Integer id: idstiposDocumentos){
				selecao.addTipoDocumento(documentacaoService.getTipoDocumentoById(id));
			}
		}

		selecao.setAno(selecaoAtualizada.getAno());
		selecao.setQuantidadeVagas(selecaoAtualizada.getQuantidadeVagas());
		selecao.setDataInicio(selecaoAtualizada.getDataInicio());
		selecao.setDataTermino(selecaoAtualizada.getDataTermino());

		this.selecaoRepository.save(selecao);

		redirect.addFlashAttribute(INFO, MENSAGEM_SUCESSO_SELECAO_ATUALIZADA);

		return REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;
	}

	@RequestMapping(value =  "selecao/excluir/{idSelecao}" , method = RequestMethod.GET)
	public String excluirSelecao(@PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect) {
		if (selecao != null && inscricaoRepository.countBySelecao_Id(selecao.getId()) == 0) {
			this.selecaoRepository.delete(selecao);
			redirect.addFlashAttribute(INFO, MENSAGEM_SUCESSO_SELECAO_REMOVIDA);
		} else {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_EXCLUIR_SELECAO_COM_INSCRITOS);
		}

		return REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;
	}

	@RequestMapping(value = "/comissao/atribuir/{idSelecao}", method = RequestMethod.GET)
	public String atribuirParecerista(@PathVariable("idSelecao") Selecao selecao,
			Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("idSelecao", selecao.getId());
		model.addAttribute("servidores", servidorService.getAll());
		model.addAttribute("selecao", selecao);	
		model.addAttribute(Constants.CARD_SELECIONADO, Constants.CARD_COMISSAO);

		return PAGINA_ATRIBUIR_COMISSAO;
	}

	@RequestMapping(value = "/comissao/atribuir", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(@RequestParam("idSelecao") Selecao selecao,
			@RequestParam("idServidor") Integer idServidor, Model model, RedirectAttributes redirect) {

		List<Servidor> comissao = selecao.getComissao();
		Servidor servidor = servidorService.getById(idServidor);


		if (comissao.contains(servidor)) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_MEMBRO_COMISSAO_REPETICAO);

			return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + selecao.getId();

		} else {

			selecao.getComissao().add(servidor);
			selecaoRepository.save(selecao);
			redirect.addFlashAttribute(INFO, MENSAGEM_SUCESSO_COMISSAO_FORMADA);


			return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + selecao.getId();	
		}
	}

	@RequestMapping(value = "/selecao/adicionar-documento/{idSelecao}", method = RequestMethod.GET)
	public String adicionarDocumento(@PathVariable("idSelecao") Selecao selecao,
			Model model, RedirectAttributes redirectAttributes) {

		if (selecao != null) {
			model.addAttribute("selecao", selecao);
		}

		model.addAttribute(Constants.CARD_SELECIONADO, Constants.CARD_ARQUIVOS);

		return PAGINA_ADICIONAR_ARQUIVO;
	}


	@RequestMapping(value = "/selecao/adicionar-documento", method = RequestMethod.POST)
	public String adicionarDocumento(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("idSelecao") Selecao selecao, Model model, RedirectAttributes redirect) {

		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			for (MultipartFile mfiles : files) {
				try {

					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {

						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setCaminho(mfiles.getContentType());

						documentoRepository.save(documento);

						selecao.getDocumentos().add(documento);

						selecaoRepository.save(selecao);
					}


				} catch (IOException ioe) {
					redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SALVAR_DOCUMENTOS);

					return REDIRECT_PAGINA_ADICIONAR_ARQUIVO + selecao.getId();
				}
			} 

		} 

		return REDIRECT_PAGINA_ADICIONAR_ARQUIVO + selecao.getId();
	}

	@RequestMapping(value = "/selecao/excluir-documento/{idSelecao}/{idDocumento}", method = RequestMethod.GET)
	public String excluirDocumento(@PathVariable("idDocumento"
			+ "") Integer idDocumento, 
			@PathVariable("idSelecao") Selecao selecao, Model model, RedirectAttributes redirect) {

		Documento documento = documentoRepository.findById(idDocumento);

		if (documento != null) {
			selecao.getDocumentos().remove(documento);
			selecaoRepository.save(selecao);
			model.addAttribute("selecao", selecao);

			return  REDIRECT_PAGINA_ADICIONAR_ARQUIVO + selecao.getId();
		} else {
			model.addAttribute("selecao", selecao);
			model.addAttribute("anexoError", MENSAGEM_ERRO_ANEXO);

			return REDIRECT_PAGINA_ADICIONAR_ARQUIVO;
		}
	}

	@RequestMapping(value = "/comissao/excluir/{idSelecao}/{idServidor}", method = RequestMethod.GET)
	public String excluirMembroComissao(@PathVariable("idSelecao") Selecao selecao,@PathVariable("idServidor") Integer idServidor, 
			Model model, Authentication auth, RedirectAttributes redirect) {	

		Servidor coordenador = servidorService.getByCpf(auth.getName());			
		Servidor servidor = servidorService.getById(idServidor);

		if(coordenador.getId() != servidor.getId()){

			selecao.getComissao().remove(servidor);
			selecaoRepository.save(selecao);
			redirect.addFlashAttribute(INFO, MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO);
		} else {

			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR);
		}

		return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + selecao.getId();

	}

	@RequestMapping(value = "/comissao/relatorioFinal/{idSelecao}")
	public String getInformacoesRelatorioFinal(@PathVariable("idSelecao") Selecao selecao, Model modelo,
			Authentication auth,RedirectAttributes redirect){

        Servidor servidor = servidorService.getByCpf(auth.getName());	
		
		if(selecao.getComissao().contains(servidor)){
			
			//dividi o resultado já em 3 listas a serem exibidas na jsp

			List<Inscricao> classificados = inscricaoRepository.findClassificadosBySelecao(selecao.getId());
			List<Inscricao> reservas = inscricaoRepository.findClassificaveisBySelecao(selecao.getId());
			List<Inscricao> indeferidos = inscricaoRepository.findIndeferidosBySelecao(selecao.getId());

			//ordeno de acordo com o nome dos alunos inscritos
			Collections.sort(classificados);
			Collections.sort(reservas);
			Collections.sort(indeferidos);

			modelo.addAttribute("classificados", classificados);
			modelo.addAttribute("reservas", reservas);
			modelo.addAttribute("indeferidos", indeferidos);
			modelo.addAttribute("selecao", selecao);

			modelo.addAttribute(Constants.ABA_SELECIONADA, "classificados-tab");
			modelo.addAttribute(Constants.CARD_SELECIONADO, Constants.CARD_RELATORIO);

			return PAGINA_RELATORIO_FINAL;
			
		}else{
			 redirect.addFlashAttribute(ERRO,MENSAGEM_FALTA_DE_PERMISSAO);
				
			 return REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;
		}

	}*/

}
