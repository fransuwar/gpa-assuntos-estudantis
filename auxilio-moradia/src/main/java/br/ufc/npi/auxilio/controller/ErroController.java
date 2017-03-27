package br.ufc.npi.auxilio.controller;

import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.TAMANHO_LIMITE_ARQUIVO_EXCEPTION;

@Controller
@RequestMapping("/error")
public class ErroController {
	@PostMapping("/selecao/limite-excedido/{idSelecao}")
	public String errorArquivoLimiteExcedido( @PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect ) {
		redirect.addFlashAttribute(ERRO, TAMANHO_LIMITE_ARQUIVO_EXCEPTION);
		return RedirectConstants.REDIRECT_DETALHES_SELECAO + selecao.getId();
	}
	
	@PostMapping("/inscricao/limite-excedido/{idInscricao}")
	public String errorInscricaoLimiteExcedido(@PathVariable("idInscricao") Inscricao inscricao, 
			RedirectAttributes redirect) {
		redirect.addFlashAttribute(ERRO, TAMANHO_LIMITE_ARQUIVO_EXCEPTION);
		return RedirectConstants.REDIRECT_DETALHES_SELECAO + inscricao.getId();
	}
}
