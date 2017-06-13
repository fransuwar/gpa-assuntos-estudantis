package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_COORDENADOR;

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

import br.ufc.npi.auxilio.enums.Modalidades;
import br.ufc.npi.auxilio.enums.ParticipouSelecaoResultado;
import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.model.Entrevista;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.repository.EntrevistaRepository;
import br.ufc.npi.auxilio.repository.InscricaoRepository;
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
	private InscricaoRepository InscricaoRepository;
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
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
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/{idInscricao}")
	public String cadastrarEntrevista( @PathVariable("idInscricao") Inscricao inscricao, Entrevista entrevista,
			Authentication auth) {
		if( inscricao == null ) {
			return RedirectConstants.REDIRECT_LISTAR_SELECAO;
		}
		
		entrevista.setResponsavel(servidorService.getByCpf(auth.getName()));
		entrevistaRepository.save(entrevista);
		
		inscricao.setEntrevista(entrevista);
		InscricaoRepository.save(inscricao);
		
		return RedirectConstants.REDIRECT_PAGINA_ENTREVISTA + inscricao.getId();
	}
	
	@ModelAttribute("resultados")
	public Resultado[] getResultados() {
		return Resultado.values();
	}
}
