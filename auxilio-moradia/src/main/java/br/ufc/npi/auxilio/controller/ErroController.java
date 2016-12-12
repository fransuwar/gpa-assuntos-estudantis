package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.ERRO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.utils.ExceptionConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;

@Controller
@RequestMapping("/error")
public class ErroController {
	@PostMapping("/arquivoLimiteExcedido/{idSelecao}")
	public String errorArquivoLimiteExcedido( @PathVariable("idSelecao") Selecao selecao, RedirectAttributes redirect ) {
		redirect.addFlashAttribute( ERRO, ExceptionConstants.TAMANHO_LIMITE_ARQUIVO_EXCEPTION );
		return RedirectConstants.REDIRECT_PAGINA_ADICIONAR_ARQUIVO + selecao.getId();
	}
}
