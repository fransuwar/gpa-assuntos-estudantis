package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.service.ServidorService;


@Controller
@RequestMapping("coordenador")
public class CoordenadorController {
	
	@Inject
	private SelecaoService selecaoService;

	@Inject
	private DocumentoService documentoService;

	@Inject
	private ServidorService servidorService;
	
	
	@RequestMapping(value = "excluir-tipo-documento/{id}", method = RequestMethod.GET)
	public String excluirTipoDocumento(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirect) {
		
		TipoDocumento tipoDocumento = documentoService.findById(id);

		if (tipoDocumento != null){
			documentoService.deletarTipoDocumento(tipoDocumento);
		}
		else{ 
			model.addAttribute("Error", MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO);
		}
		model.addAttribute(DOCUMENTOS,documentoService.find());
		return  REDIRECT_PAGINA_GERENCIAR_DOCUMENTOS;
		
		
	}
	
	@RequestMapping(value = "adicionar-tipo-arquivo", method = RequestMethod.GET)
	public String addTipoArquivo(TipoDocumento tipoDocumento){
		if(!tipoDocumento.getNome().isEmpty()){
			documentoService.salvarTipoDocumento(tipoDocumento);
		}
		return REDIRECT_PAGINA_GERENCIAR_DOCUMENTOS;
	}
	
	
	@RequestMapping(value = "gerenciarDocumentos", method = RequestMethod.GET)
	public String gerenciarDocumentos(ModelMap model){
		model.addAttribute(DOCUMENTOS,documentoService.find() );
		return PAGINA_GERENCIAR_DOCUMENTOS;
	}
	
	
	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(ModelMap model, HttpServletRequest request, Authentication auth){

		List<Selecao> selecoes = this.selecaoService.getSelecoes();

		model.addAttribute("selecoes", selecoes);
		model.addAttribute("tipoSelecao", TipoSelecao.values());
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO_SERVIDOR;
	}

	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.GET)
	public String cadastroSelecao(Model model) {

		model.addAttribute("action", "cadastrar");
		model.addAttribute("selecao", new Selecao());

		return PAGINA_CADASTRAR_SELECAO;

	}

	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.POST)
	public String cadastroSelecao(Model model,	@Valid @ModelAttribute("selecao") Selecao selecao, 
			BindingResult result, Authentication auth, RedirectAttributes redirect) {
		
		Integer proxSequencial = selecaoService.getUltimoSequencialPorAno(selecao);
		
		selecao.setSequencial(proxSequencial);

		model.addAttribute("action", "cadastrar");

		if (selecao != null && selecao.getAno() != null) {
			if (selecao.getAno() < DateTime.now().getYear()) {
				result.rejectValue("ano", "selecao.ano", MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR);
			}
		}

		if (selecao != null && selecao.getDataInicio() != null && selecao.getDataTermino() != null) {
			if ((new DateTime(selecao.getDataTermino())).isBefore(new DateTime(selecao.getDataInicio()))) {
				result.rejectValue("dataTermino", "selecao.dataTermino", MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR);
			}
		}

		if (selecao != null)  {
			if (selecaoService.SelecaoEstaCadastrada(selecao)) {
				result.rejectValue("sequencial", "selecao.sequencial", MENSAGEM_ERRO_SEQUENCIAL_SELECAO_CADASTRAR);
			}			
			if(selecao.getTipoSelecao()==null){
				result.rejectValue("tipoSelecao", "selecao.tipoSelecao", MENSAGEM_ERRO_TIPO_BOLSA);
			}			
		}

		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);

			return PAGINA_CADASTRAR_SELECAO;
		}

		Servidor coordenador = servidorService.getServidorPorCpf(auth.getName());

		if(selecao.getResponsavel() == null){
			selecao.addCoordenador(coordenador);
			selecao.setResponsavel(coordenador);
		}

		this.selecaoService.save(selecao);
		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_CADASTRADA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;

	}

	@RequestMapping(value = { "selecao/editar/{idSelecao}" }, method = RequestMethod.GET)
	public String editarSelecao(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);

		if (selecao != null) {
			model.addAttribute("action", "editar");
			model.addAttribute("tipoSelecao", TipoSelecao.values());
			model.addAttribute("selecao", selecao);
			model.addAttribute("membrosComissao", selecao.getMembrosComissao());
			return PAGINA_CADASTRAR_SELECAO;
		}


		return PAGINA_CADASTRAR_SELECAO;

	}

	@RequestMapping(value = { "selecao/editar" }, method = RequestMethod.POST)
	public String editarSelecao(@Valid @ModelAttribute("selecao") Selecao selecao, BindingResult result,
			Model model, RedirectAttributes redirect) {


		model.addAttribute("action", "editar");

		if (selecao != null && selecao.getDataInicio() != null && selecao.getDataTermino() != null) {
			if ((new DateTime(selecao.getDataTermino())).isBefore(new DateTime(selecao.getDataInicio()))) {
				result.rejectValue("dataTermino", "selecao.dataTermino", MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR);
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tipoSelecao", TipoSelecao.values());

			return PAGINA_CADASTRAR_SELECAO;
		}

		this.selecaoService.update(selecao);

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_ATUALIZADA);

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value =  "selecao/excluir/{idSelecao}" , method = RequestMethod.GET)
	public String excluirSelecao(@PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect) {

		Selecao selecao = this.selecaoService.getSelecaoPorId(idSelecao);

		if (selecao != null && selecao.getInscritos().isEmpty()) {

			this.selecaoService.delete(selecao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_REMOVIDA);

		} else {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_EXCLUIR_SELECAO_COM_INSCRITOS);

		}

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "/comissao/atribuir/{idSelecao}", method = RequestMethod.GET)
	public String atribuirParecerista(@PathVariable("idSelecao") Integer idSelecao,
			Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("idSelecao", idSelecao);
		model.addAttribute("servidores", servidorService.listarServidores());
		model.addAttribute("selecao", selecaoService.getSelecaoPorId(idSelecao));

		return PAGINA_ATRIBUIR_COMISSAO;
	}

	@RequestMapping(value = "/comissao/atribuir", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(@RequestParam("idSelecao") Integer idSelecao,
			@RequestParam("idServidor") Integer idServidor, Model model, RedirectAttributes redirect) {

		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		List<Servidor> comissao = selecao.getMembrosComissao();
		Servidor servidor = this.servidorService.getServidorPorId(idServidor);

		if (comissao.contains(servidor)) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_MEMBRO_COMISSAO_REPETICAO);
			return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;

		} else {

			selecao.getMembrosComissao().add(servidor);
			selecaoService.update(selecao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_COMISSAO_FORMADA);

			return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;	
		}
	}
	
	@RequestMapping(value = "/selecao/adicionar-documento/{idSelecao}", method = RequestMethod.GET)
	public String adicionarDocumento(@PathVariable("idSelecao") Integer idSelecao,
			Model model, RedirectAttributes redirectAttributes) {
		
		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		if (selecao != null) {
			model.addAttribute("selecao", selecao);
		}

		return PAGINA_ADICIONAR_ARQUIVO;
	}
	

	@RequestMapping(value = "/selecao/adicionar-documento", method = RequestMethod.POST)
	public String adicionarDocumento(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {

		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			for (MultipartFile mfiles : files) {
				try {

					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {

						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						documento.setSelecao(selecaoService.getSelecaoPorId(idSelecao));
						documentoService.salvarDocumento(documento);
					}


				} catch (IOException ioe) {
					redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SALVAR_DOCUMENTOS);

					return REDIRECT_PAGINA_ADICIONAR_ARQUIVO +idSelecao;
				}
			} 

		} 

		return REDIRECT_PAGINA_ADICIONAR_ARQUIVO + idSelecao;
	}

	@RequestMapping(value = "/selecao/excluir-documento/{idDocumento}", method = RequestMethod.GET)
	public String excluirDocumento(@PathVariable("idDocumento") Integer idDocumento, @ModelAttribute("selecao") Selecao selecao, 
			Model model, RedirectAttributes redirect) {

		Documento documento = documentoService.getDocumentoPorId(idDocumento);

		if (documento != null) {
			Integer idSelecao = documento.getSelecao().getId();

			documentoService.deletarDocumento(documento);

			model.addAttribute("tipoBolsa", TipoSelecao.values());
			model.addAttribute("selecao", selecao);

			return  REDIRECT_PAGINA_ADICIONAR_ARQUIVO + idSelecao;

		} else {

			model.addAttribute("tipoBolsa", TipoSelecao.values());
			model.addAttribute("selecao", selecao);
			model.addAttribute("anexoError", MENSAGEM_ERRO_ANEXO);

			return REDIRECT_PAGINA_ADICIONAR_ARQUIVO;
		}
	}

	@RequestMapping(value = "/comissao/excluir/{idSelecao}/{idServidor}", method = RequestMethod.GET)
	public String excluirMembroComissao(@PathVariable("idSelecao") Integer idSelecao,@PathVariable("idServidor") Integer idServidor, 
			Model model, Authentication auth, RedirectAttributes redirect) {	

		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		Servidor coordenador = servidorService.getServidorPorCpf(auth.getName());			
		Servidor servidor = this.servidorService.getServidorPorId(idServidor);

		if(coordenador.getId() != servidor.getId()){

			selecao.getMembrosComissao().remove(servidor);
			selecaoService.update(selecao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO);
		}else

			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR);

		return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;

	}

}
