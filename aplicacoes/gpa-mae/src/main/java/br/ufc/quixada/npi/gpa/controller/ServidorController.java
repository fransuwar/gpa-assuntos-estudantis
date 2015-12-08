package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Banco;
import br.ufc.quixada.npi.gpa.enums.Cargo;
import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;
import br.ufc.quixada.npi.gpa.enums.TipoBolsa;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;

import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping ("servidor")
@SessionAttributes({ Constants.USUARIO_ID , Constants.USUARIO_LOGADO})
public class ServidorController {

	@Inject
	private InscricaoService inscricaoService;
	
	@Inject
	private ServidorService servidorService;
	
	@Inject
	private SelecaoService selecaoService;

	@Inject
	private AlunoService alunoService;

	

	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(Model model, Authentication auth, RedirectAttributes redirect) {
		Servidor servidor = this.servidorService.getServidorByCPFComBancas(auth.getName());

		if (!servidor.getParticipaBancas().isEmpty()) {

			model.addAttribute("selecoes", servidor.getParticipaBancas());
			model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
			model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);

			return PAGINA_LISTAR_SELECAO;

		}

		model.addAttribute("erro", MENSAGEM_SERVIDOR_NAO_ASSOCIADO);

		return PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value= {"entrevista/{idInscricao}"}, method = RequestMethod.GET)
	public String realizarEntrevista(@PathVariable("idInscricao") Integer idInscricao, RedirectAttributes redirect, Model model ){
		Inscricao inscricao = this.inscricaoService.find(Inscricao.class, idInscricao);
		
		if(inscricao == null){
			redirect.addFlashAttribute("erro", MENSAGEM_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}		
			model.addAttribute("entrevista", new Entrevista());
			model.addAttribute("idInscricao", idInscricao);
		
		return PAGINA_REALIZAR_ENTREVISTA;
	}
	
	@RequestMapping(value= {"entrevista"}, method = RequestMethod.POST)
	public String realizarEntrevista(@Valid @ModelAttribute("entrevista") Entrevista entrevista, @RequestParam("idInscricao") Integer idInscricao, @RequestParam("idServidor") Integer idPessoa, 
			 BindingResult result, RedirectAttributes redirect, Model model , Authentication auth){
			
			Servidor servidor = this.servidorService.getPessoaServidorComBancas(idPessoa);
			entrevista.setServidor(servidor);
			entrevista.setInscricao(inscricaoService.find(Inscricao.class, idInscricao));			
			
			inscricaoService.saveEntrevista(entrevista);
			
			redirect.addFlashAttribute("info", MENSAGEM_DE_SUCESSO_ENTREVISTA);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "visita/{idInscricao}" }, method = RequestMethod.GET)
	public String realizarVisita(@PathVariable("idInscricao")Integer idInscricao, Model model) {
		
		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);
		VisitaDomiciliar relatorioVisitaDomiciliar = new VisitaDomiciliar();
		
		relatorioVisitaDomiciliar.setInscricao(inscricao);
		
		model.addAttribute("relatorioVisitaDomiciliar", relatorioVisitaDomiciliar);
		model.addAttribute("curso", Curso.values());
		model.addAttribute("moradiaEstado", EstadoMoradia.values());
		model.addAttribute("idAluno", inscricao.getAluno().getId());
		model.addAttribute("idSelecao", inscricao.getSelecao().getId());
		
		return PAGINA_RELATORIO_VISITA;
	}

	@RequestMapping(value = { "visita" }, method = RequestMethod.POST)
	public String realizarVisita(@RequestParam("idAluno") Integer idAluno, @RequestParam("idSelecao") Integer idSelecao,
			@Valid @ModelAttribute("relatorioVisitaDomiciliar") VisitaDomiciliar relatorioVisitaDomiciliar, Model model,
			BindingResult result, RedirectAttributes redirect) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("relatorioVisitaDomiciliar", relatorioVisitaDomiciliar);
			model.addAttribute("curso", Curso.values());
			model.addAttribute("moradiaEstado", EstadoMoradia.values());
			model.addAttribute("idAluno", idAluno);
			model.addAttribute("idSelecao", idSelecao);
			
			return PAGINA_RELATORIO_VISITA;
			
		}
		
		relatorioVisitaDomiciliar.setAluno(alunoService.find(Aluno.class, idAluno));
		relatorioVisitaDomiciliar.setSelecaoBolsa(selecaoService.find(Selecao.class, idSelecao));
		
		inscricaoService.salvarVisitaDocimiciliar(relatorioVisitaDomiciliar);;
		redirect.addFlashAttribute("info", MENSAGEM_VISITA_CADASTRADA);
		
		return REDIRECT_PAGINA_INSCRITOS_SELECAO  + idSelecao;
	}
	
	@RequestMapping(value = { "informacoes/visita-domiciliar/{idVisita}" }, method = RequestMethod.GET)
	public String visulizarInformacoes(@PathVariable("idVisita") Integer idVisita, Model model, RedirectAttributes redirect) {
		
		VisitaDomiciliar visitaDomiciliar = inscricaoService.getVisitaDocimiciliarByIdVisitaDomiciliar(idVisita);
		
		if (visitaDomiciliar == null ) {
			
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_VISITA_INEXISTENTE);
			return REDIRECT_PAGINA_INSCRITOS_SELECAO;
		}
		
		model.addAttribute("relatorio", visitaDomiciliar);
		
		return PAGINA_INFORMACOES_RELATORIO;
	}
}
