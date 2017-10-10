package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_COORDENADOR;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_SERVIDOR;
import static br.ufc.npi.auxilio.utils.Constants.NUM_CARACTERES;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MSG_ERRO_ANALISE_DOCUMENTACAO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_ENTREVISTA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.enums.Modalidades;
import br.ufc.npi.auxilio.enums.ParticipouSelecaoResultado;
import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.model.Entrevista;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.repository.EntrevistaRepository;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.ServidorService;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;

@Controller
@RequestMapping("/entrevista")
public class EntrevistaController {
	@Autowired
	private ServidorService servidorService;
	
	@Autowired
	private EntrevistaRepository entrevistaRepository;
	
	@Autowired
	private InscricaoService inscricaoService;
	
	//@PreAuthorize(PERMISSAO_COORDENADOR)
	@PreAuthorize(PERMISSAO_COORDENADOR + " or " + PERMISSAO_SERVIDOR)
	@GetMapping("/{idInscricao}")
	public String formEntrevista( @PathVariable("idInscricao") Inscricao inscricao, Model model ) {
		if( inscricao == null ) {
			return RedirectConstants.REDIRECT_LISTAR_SELECAO;
		}
		model.addAttribute("modalidade", Modalidades.values());
		model.addAttribute("participouSelecao", ParticipouSelecaoResultado.values());
		model.addAttribute("resultado", Resultado.values());
		model.addAttribute("inscricao", inscricao);
		model.addAttribute("entrevista", inscricao.getEntrevista() == null ? new Entrevista() : inscricao.getEntrevista());
		
		return PageConstants.PAGINA_ENTREVISTA;
	}
	
	//@PreAuthorize(PERMISSAO_COORDENADOR)
	@PreAuthorize(PERMISSAO_COORDENADOR + " or " + PERMISSAO_SERVIDOR)
	@PostMapping("/{idInscricao}")
	public String cadastrarEntrevista( @PathVariable("idInscricao") Inscricao inscricao, Entrevista entrevista,
			Authentication auth,  RedirectAttributes redirect) {
		if( inscricao == null || entrevista == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			return RedirectConstants.REDIRECT_LISTAR_SELECAO;
		}
		if( entrevista.getObservacao().length() > NUM_CARACTERES){
			redirect.addFlashAttribute(ERRO, MSG_ERRO_ANALISE_DOCUMENTACAO);
			return RedirectConstants.REDIRECT_PAGINA_ENTREVISTA + inscricao.getId();
		}
		entrevista.setResponsavel(servidorService.getByCpf(auth.getName()));
		entrevistaRepository.save(entrevista);
		
		inscricao.setEntrevista(entrevista);
		inscricaoService.salvar(inscricao);
		redirect.addFlashAttribute(INFO, MSG_SUCESSO_ENTREVISTA);
		return RedirectConstants.REDIRECT_PAGINA_ENTREVISTA + inscricao.getId();
	}
	
	@ModelAttribute("resultados")
	public Resultado[] getResultados() {
		return Resultado.values();
	}
}
