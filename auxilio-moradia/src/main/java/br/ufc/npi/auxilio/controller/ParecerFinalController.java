package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.COORDENADOR;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_ANALISE_DOCUMENTACAO;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.repository.AnaliseDocumentacaoRepository;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import br.ufc.npi.auxilio.utils.SuccessMessageConstants;

@Controller
@RequestMapping("/parecerFinal")
public class ParecerFinalController {


	@Autowired
	private InscricaoService inscricaoService;

	@Autowired
	private AnaliseDocumentacaoRepository analiseDocumentacaoRepository;
	
	
	@Secured(COORDENADOR)
	@GetMapping("/{idInscricao}")
	public String analisarDocumentacaoInscricao(
			@PathVariable("idInscricao") Inscricao inscricao, Model model){

		model.addAttribute("resultado", Resultado.values());
		model.addAttribute("inscricao", inscricao);
		
		return PageConstants.PAGINA_EDITAR_PARECER;
	}
	
	@Secured(COORDENADOR)
	@PostMapping("/{inscricao}")
	public String analisarDocumentacaoInscricao(@PathVariable Inscricao inscricao, 
			Inscricao insc, Authentication auth, 
			RedirectAttributes redirectAttributes) throws AuxilioMoradiaException{
		
		inscricaoService.editar(insc);
		
		redirectAttributes.addFlashAttribute(INFO, SuccessMessageConstants.MSG_SUCESSO_EDITAR_PARECER_FINAL);
		return RedirectConstants.REDIRECT_SELECAO_INSCRICOES+insc.getSelecao().getId();
	}
	
	
	
	
	@ModelAttribute("resultados")
	public Resultado[] getResultados() {
		return Resultado.values();
	}
	
}
