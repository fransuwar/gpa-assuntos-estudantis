package br.ufc.npi.auxilio.controller;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import br.ufc.npi.auxilio.utils.SuccessMessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;

import static br.ufc.npi.auxilio.utils.Constants.COORDENADOR;
import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.PageConstants.CADASTRAR_SELECAO;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_LISTAR_SELECAO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SELECAO_CADASTRADA;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_SELECAO_REMOVIDA;

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
	
	@Secured(COORDENADOR)
	@GetMapping("/cadastrar")
	public String cadastrarSelecaoForm(Model model) {
		model.addAttribute("acao", "Cadastrar");
		model.addAttribute("selecao", new Selecao());
		return CADASTRAR_SELECAO;
	}
	
	@Secured(COORDENADOR)
	@PostMapping("/cadastrar")
	public String cadastrarSelecao(Selecao selecao, Authentication auth, Model model, RedirectAttributes redirect) {
		selecao.setResponsavel(servidorService.getByCpf(auth.getName()));
		try {
			selecaoService.cadastrar(selecao);
			redirect.addFlashAttribute(INFO, MSG_SELECAO_CADASTRADA);
			return REDIRECT_LISTAR_SELECAO;
		} catch (AuxilioMoradiaException e) {
			model.addAttribute(ERRO, e.getMessage());
			return CADASTRAR_SELECAO;
		}
	}
	
	@Secured(COORDENADOR)
	@GetMapping("/excluir/{selecao}")
	public String excluirSelecao(@PathVariable Selecao selecao, RedirectAttributes redirect) {
		try {
			selecaoService.excluir(selecao);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_SELECAO_REMOVIDA);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return REDIRECT_LISTAR_SELECAO;
	}
	
	@Secured(COORDENADOR)
	@GetMapping("/editar/{selecao}")
	public String editarSelecao(@PathVariable Selecao selecao, Model model,
			RedirectAttributes redirect) {
		
		if (selecao != null) {
			model.addAttribute("acao", "Editar");
			model.addAttribute("selecao", selecao);
			model.addAttribute("tiposDeDocumento", documentacaoService.getAllTipoDocumento());
			return CADASTRAR_SELECAO;
		}
		return REDIRECT_LISTAR_SELECAO;
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
	
	@GetMapping("tipo-documento/excluir/{tipoDocumento}")
	public String excluirTipoDocumento(@PathVariable TipoDocumento tipoDocumento,
			RedirectAttributes redirect) {

		if (tipoDocumento != null){
			try {
				documentacaoService.excluirTipoDocumento(tipoDocumento.getId());
				redirect.addFlashAttribute(Constants.INFO, SuccessMessageConstants.MSG_TIPO_DOCUMENTO_EXCUIDO_COM_SUCESSO);
			} catch(Exception e){
				redirect.addFlashAttribute(Constants.ERRO, SuccessMessageConstants.MSG_ERRO_TIPO_DOCUMENTO_EM_USO);
			}
		}
		return  RedirectConstants.REDIRECT_GERENCIAR_DOCUMENTOS;
	}

}
