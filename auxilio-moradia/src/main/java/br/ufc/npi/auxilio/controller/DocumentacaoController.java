package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.COORDENADOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.model.TipoDocumento;
import br.ufc.npi.auxilio.service.DocumentacaoService;
import br.ufc.npi.auxilio.utils.Constants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import br.ufc.npi.auxilio.utils.SuccessMessageConstants;
import br.ufc.npi.auxilio.utils.alert.AlertSet;

@Controller
@RequestMapping("/documentacao")
public class DocumentacaoController {
	
	@Autowired
	private DocumentacaoService documentacaoService;
	
	@Secured(COORDENADOR)
	@GetMapping({"/", ""})
	public String listarTipoDocumento(Model model){
		model.addAttribute("documento", new TipoDocumento());
		model.addAttribute("documentos", documentacaoService.getAllTipoDocumento());
		return PageConstants.GERENCIAR_DOCUMENTOS;
	}
	
	@Secured(COORDENADOR)
	@PostMapping("/tipo-documento/cadastrar")
	public String cadastrarTipoDocumento(TipoDocumento tipoDocumento, RedirectAttributes redirect){
		if(!tipoDocumento.getNome().isEmpty()) {
			tipoDocumento.setNome(tipoDocumento.getNome().toUpperCase());
			try {
				documentacaoService.salvar(tipoDocumento);
			} catch (DataIntegrityViolationException e) {
				redirect.addFlashAttribute(Constants.ALERTA, "Documento já existe!");
				return RedirectConstants.REDIRECT_GERENCIAR_DOCUMENTOS;
			}
		}
		redirect.addFlashAttribute(Constants.ALERTA, "Documento cadastrado com sucesso!");
		return RedirectConstants.REDIRECT_GERENCIAR_DOCUMENTOS;
	}
	
	@Secured(COORDENADOR)
	@GetMapping("/tipo-documento/excluir/{id}")
	public String excluirTipoDocumento(@PathVariable("id") TipoDocumento tipoDocumento,
			RedirectAttributes redirect) {

		if (tipoDocumento != null){
			try {
				documentacaoService.excluirTipoDocumento(tipoDocumento.getId());
				redirect.addFlashAttribute(Constants.ALERTA, AlertSet.createSuccess(SuccessMessageConstants.MSG_TIPO_DOCUMENTO_EXCUIDO_COM_SUCESSO));
			} catch(Exception e){
				redirect.addFlashAttribute(Constants.ALERTA, AlertSet.createError(SuccessMessageConstants.MSG_ERRO_TIPO_DOCUMENTO_EM_USO));
			}
		}
		return  RedirectConstants.REDIRECT_GERENCIAR_DOCUMENTOS;
	}
}
