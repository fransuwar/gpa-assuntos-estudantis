package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.ALERTA;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.utils.ExceptionConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import br.ufc.npi.auxilio.utils.alert.AlertSet;

@Controller
@RequestMapping("/error")
public class ErroController {
	@PostMapping("/selecao/limite-excedido/{idSelecao}")
	public String errorArquivoLimiteExcedido( @PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect ) {
		redirect.addFlashAttribute( ALERTA, AlertSet.createError(ExceptionConstants.TAMANHO_LIMITE_ARQUIVO_EXCEPTION) );
		return RedirectConstants.REDIRECT_PAGINA_ADICIONAR_ARQUIVO + selecao.getId();
	}
	
	@PostMapping("/inscricao/limite-excedido/{idInscricao}")
	public String errorInscricaoLimiteExcedido(@PathVariable("idInscricao") Inscricao inscricao, 
			RedirectAttributes redirect) {
		redirect.addFlashAttribute( ALERTA, AlertSet.createError(ExceptionConstants.TAMANHO_LIMITE_ARQUIVO_EXCEPTION) );
		return RedirectConstants.REDIRECT_INSCRICAO_DOCUMENTACAO + inscricao.getId();
	}
}
