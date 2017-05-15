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

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.model.VisitaDomiciliar;
import br.ufc.npi.auxilio.repository.VisitaDomiciliarRepository;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.ServidorService;

@Controller
@RequestMapping("visita")
public class VisitaController {
	
	@Autowired
	private InscricaoService inscricaoService;

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ServidorService servidorService;
	
	@Autowired
	private VisitaDomiciliarRepository visitaDomiciliarRepository;
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/cadastrar/{inscricao}")
	public String cadastrarVisitaForm(@PathVariable Inscricao inscricao, Authentication auth, Model model){
		Aluno aluno = alunoService.buscarPorCpf(inscricao.getAluno().getPessoa().getCpf());
		Servidor servidor =  servidorService.getByCpf(auth.getName());
		
		Inscricao inscricaoBanco = inscricaoService.get(aluno, inscricao.getSelecao());
		model.addAttribute("servidor", servidor);
		model.addAttribute("inscrição", inscricaoBanco != null ? inscricaoBanco.getId() : null);
		model.addAttribute("selecao", inscricao.getSelecao());
		model.addAttribute("visita", new VisitaDomiciliar());
		model.addAttribute("tiposDeDocumento", inscricao.getSelecao().getDocumentos());
		return "selecao/visita-domiciliar";
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/cadastrar")
	public String cadastrarVisita(VisitaDomiciliar visitaDomiciliar, Model model, Authentication auth) {
		model.addAttribute("acao", "cadastrar");
		visitaDomiciliar.setResponsavel(servidorService.getByCpf(auth.getName()));
		this.visitaDomiciliarRepository.save(visitaDomiciliar);
		return "redirect:/selecao/";
	}
	
	@ModelAttribute("resultados")
	public Resultado[] getResultados() {
		return Resultado.values();
	}
}