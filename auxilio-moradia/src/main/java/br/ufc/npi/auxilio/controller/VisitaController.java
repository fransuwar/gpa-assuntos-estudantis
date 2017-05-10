package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_COORDENADOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.enums.TipoSelecao;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.model.VisitaDomiciliar;
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
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/cadastrar/{inscricao}")
	public String cadastrarVisitaForm(@PathVariable Inscricao inscricao, Authentication auth, Model model){
		Aluno aluno = alunoService.buscarPorCpf(inscricao.getAluno().getPessoa().getCpf());
		Servidor servidor =  servidorService.getByCpf(auth.getName());
		
		Inscricao inscricaoBanco = inscricaoService.get(aluno, inscricao.getSelecao());
		model.addAttribute("inscrição", inscricaoBanco != null ? inscricaoBanco.getId() : null);
		model.addAttribute("selecao", inscricao.getSelecao());
		model.addAttribute("resultados", Resultado.values());
		model.addAttribute("visita", new VisitaDomiciliar());
		model.addAttribute("tiposDeDocumento", inscricao.getSelecao().getDocumentos());
		return "selecao/visita-domiciliar";
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/cadastrar/{inscricao}")
	public String cadastrarVisita(Model model) {
		model.addAttribute("acao", "cadastrar");
		model.addAttribute("opcoesTipoSelecao", TipoSelecao.values());
		model.addAttribute("selecao", new Selecao());
		
		return "selecao/cadastrar-visita";
	}
}