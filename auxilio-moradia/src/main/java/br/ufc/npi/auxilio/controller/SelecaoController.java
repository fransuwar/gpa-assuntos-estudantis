package br.ufc.npi.auxilio.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.TipoDocumento;
import br.ufc.npi.auxilio.service.DocumentacaoService;
import br.ufc.npi.auxilio.service.SelecaoService;
import br.ufc.npi.auxilio.service.ServidorService;
import br.ufc.npi.auxilio.utils.Constants;
import br.ufc.npi.auxilio.utils.MessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;

@Controller
@RequestMapping("/selecao")
public class SelecaoController {
	
	@Autowired
	private SelecaoService selecaoService;
	
	@Autowired
	private ServidorService servidorService;
	
	@Autowired
	private DocumentacaoService documentacaoService;
	
	@GetMapping({"", "/", "/listar"})
	public String listarSelecoes(Model model) {
		model.addAttribute("selecoes", selecaoService.getAll());
		return PageConstants.LISTAR_SELECAO;
	}
	
	@Secured("COORDENADOR")
	@GetMapping("/cadastrar")
	public String cadastrarSelecaoForm(Model model) {
		model.addAttribute("acao", "Cadastrar");
		model.addAttribute("selecao", new Selecao());
		return PageConstants.CADASTRAR_SELECAO;
	}
	
	@Secured("COORDENADOR")
	@PostMapping("/cadastrar")
	public String cadastrarSelecao(@Valid Selecao selecao, Authentication auth, BindingResult result,
			Model model) {
		
		if (selecao.getAno() != null && selecao.getAno() < LocalDate.now().getYear()) {
			result.rejectValue("ano", "selecao.ano", MessageConstants.MSG_ERRO_ANO_SELECAO);
		}

		if ((selecao.getDataInicio() != null && selecao.getDataTermino() != null) && 
				selecao.getDataTermino().before(selecao.getDataInicio())) {
			result.rejectValue("dataTermino", "selecao.dataTermino", MessageConstants.MSG_ERRO_DATATERMINO_SELECAO);
		}
		
		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tiposDeDocumento", documentacaoService.getAllTipoDocumento());
			return PageConstants.CADASTRAR_SELECAO;
		}
		selecao.setResponsavel(servidorService.getByCpf(auth.getName()));
		selecaoService.cadastrar(selecao);
		return RedirectConstants.REDIRECT_LISTAR_SELECAO;
	}
	
	@Secured("COORDENADOR")
	@GetMapping("/excluir/{idSelecao}")
	public String excluirSelecao(@PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect) {
		if (selecao != null && (selecao.getInscricoes() == null || selecao.getInscricoes().isEmpty())) {
			this.selecaoService.excluir(selecao);
			redirect.addFlashAttribute(Constants.INFO, MessageConstants.MSG_SUCESSO_SELECAO_REMOVIDA);
		}

		return RedirectConstants.REDIRECT_LISTAR_SELECAO;
	}
	
	@Secured("COORDENADOR")
	@GetMapping("/editar/{idSelecao}")
	public String editarSelecao(@PathVariable("idSelecao") Selecao selecao, Model model, 
			RedirectAttributes redirect) {
		
		if (selecao != null) {
			model.addAttribute("acao", "Editar");
			model.addAttribute("selecao", selecao);
			model.addAttribute("tiposDeDocumento", documentacaoService.getAllTipoDocumento());
			return PageConstants.CADASTRAR_SELECAO;
		}
		return RedirectConstants.REDIRECT_LISTAR_SELECAO;
	}
	
	@GetMapping("/documento")
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
				redirect.addFlashAttribute(Constants.INFO, MessageConstants.MSG_TIPO_DOCUMENTO_EXCUIDO_COM_SUCESSO);
			} catch(Exception e){
				redirect.addFlashAttribute(Constants.ERRO, MessageConstants.MSG_ERRO_TIPO_DOCUMENTO_EM_USO);
			}
		}
		return  RedirectConstants.REDIRECT_GERENCIAR_DOCUMENTOS;
	}

}
