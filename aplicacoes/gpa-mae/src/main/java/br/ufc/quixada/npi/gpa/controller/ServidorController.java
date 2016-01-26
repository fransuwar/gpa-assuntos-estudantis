package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;
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
		Servidor servidor = servidorService.getServidorByCpf(auth.getName());
		model.addAttribute("selecoes", servidor.getParticipaComissao());
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO_SERVIDOR;
	}

	@RequestMapping(value= {"entrevista/{idInscricao}"}, method = RequestMethod.GET)
	public String realizarEntrevista(@PathVariable("idInscricao") Integer idInscricao,Authentication auth, RedirectAttributes redirect, Model model ){
		Inscricao inscricao = this.inscricaoService.find(Inscricao.class, idInscricao);		

		if(inscricao == null){
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}else{

			Selecao selecao = inscricao.getSelecao();

			Servidor servidor = servidorService.getServidorByCpf(auth.getName());

			List<Servidor> comissao = selecao.getMembrosComissao();

			if(comissao.contains(servidor)){

				model.addAttribute("entrevista", new Entrevista());
				model.addAttribute("idInscricao", idInscricao);

				return PAGINA_REALIZAR_ENTREVISTA;

			}else{
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_ENTREVISTA);
				return REDIRECT_PAGINA_LISTAR_SELECAO;
			}
		}
	}

	@RequestMapping(value= {"entrevista"}, method = RequestMethod.POST)
	public String realizarEntrevista(@Valid @ModelAttribute("entrevista") Entrevista entrevista, @RequestParam("idInscricao") Integer idInscricao, @RequestParam("idServidor") Integer idPessoa, 
			BindingResult result, RedirectAttributes redirect, Model model , Authentication auth){

		Servidor servidor = this.servidorService.getServidorComComissao(auth.getName());
		entrevista.setServidor(servidor);
		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);
		inscricao.setEntrevista(entrevista);
		entrevista.setInscricao(inscricaoService.find(Inscricao.class, idInscricao));			
		inscricaoService.update(inscricao);

		redirect.addFlashAttribute("info", MENSAGEM_DE_SUCESSO_ENTREVISTA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "visita/{idInscricao}" }, method = RequestMethod.GET)
	public String realizarVisita(@PathVariable("idInscricao")Integer idInscricao, Authentication auth, @PathVariable("idServidor") Integer idServidor, RedirectAttributes redirect, Model model) {

		Inscricao inscricao = this.inscricaoService.find(Inscricao.class, idInscricao);	

		if(inscricao == null){
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}else{

			Selecao selecao = inscricao.getSelecao();

			Servidor servidor = servidorService.getServidorByCpf(auth.getName());

			List<Servidor> comissao = selecao.getMembrosComissao();

			if(comissao.contains(servidor) && selecao.getTipoSelecao().equals(TipoSelecao.AUX_MOR)){

				VisitaDomiciliar relatorioVisitaDomiciliar = new VisitaDomiciliar();

				relatorioVisitaDomiciliar.setInscricao(inscricao);

				model.addAttribute("relatorioVisitaDomiciliar", relatorioVisitaDomiciliar);
				model.addAttribute("curso", Curso.values());
				model.addAttribute("moradiaEstado", EstadoMoradia.values());
				model.addAttribute("idAluno", inscricao.getAluno().getId());
				model.addAttribute("idSelecao", inscricao.getSelecao().getId());
				model.addAttribute("despesa", relatorioVisitaDomiciliar.getDespesa());

				return PAGINA_RELATORIO_VISITA;
			} else{
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_VISITA);
				return REDIRECT_PAGINA_LISTAR_SELECAO;
			}
		}
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
		relatorioVisitaDomiciliar.setSelecao(selecaoService.find(Selecao.class, idSelecao));

		inscricaoService.salvarVisitaDocimiciliar(relatorioVisitaDomiciliar);;
		redirect.addFlashAttribute("info", MENSAGEM_VISITA_CADASTRADA);

		return REDIRECT_PAGINA_INSCRITOS_SELECAO  + idSelecao;
	}

	@RequestMapping(value = { "informacoes/visita-domiciliar/{idVisita}" }, method = RequestMethod.GET)
	public String visulizarInformacoes(@PathVariable("idVisita") Integer idVisita, Model model, RedirectAttributes redirect) {

		VisitaDomiciliar visitaDomiciliar = inscricaoService.getVisitaDocimiciliar(idVisita);

		if (visitaDomiciliar == null ) {

			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_VISITA_INEXISTENTE);
			return REDIRECT_PAGINA_INSCRITOS_SELECAO;
		}

		model.addAttribute("relatorio", visitaDomiciliar);

		return PAGINA_INFORMACOES_RELATORIO;
	}
	@RequestMapping(value = "inscritos/{idSelecao}", method = RequestMethod.GET)
	public String listarInscritos(@PathVariable("idSelecao") Integer idSelecao, ModelMap model) {

		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
		model.addAttribute("selecao", selecao);

		return PAGINA_LISTAR_INSCRITOS_SELECAO;
	}

	@RequestMapping(value = { "detalhes/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoes(@PathVariable("idSelecao") Integer idSelecao,Authentication auth, Model model, RedirectAttributes redirect){

		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
		if (selecao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}else{

			Servidor servidor = servidorService.getServidorByCpf(auth.getName());

			List<Servidor> comissao = selecao.getMembrosComissao();

			if(comissao.contains(servidor)){

				List<Inscricao> inscricoes = inscricaoService.getInscricoesBySelecao(idSelecao);
				model.addAttribute("selecao", selecao);
				model.addAttribute("inscricoes", inscricoes);

				return PAGINA_INFORMACOES_SELECAO_SERVIDOR;
			}else{
				redirect.addFlashAttribute("erro",  MENSAGEM_PERMISSAO_NEGADA);
				return REDIRECT_PAGINA_LISTAR_SELECAO;
			}
		}
	}

	@RequestMapping(value = { "detalhes/iniciacao-academica/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricaoIniciacaoAcademica(@PathVariable("idInscricao") Integer idInscricao, Model modelo,
			RedirectAttributes redirect) {

		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);

		if (inscricao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE);

			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		modelo.addAttribute("inscricao", inscricao);

		return PAGINA_DETALHES_INSCRICAO;
	}

	@RequestMapping(value ={ "detalhes/inscricao/{idInscricao}"}, method = RequestMethod.GET)
	public String detalhesInscricao(@PathVariable("idInscricao") Integer idInscricao, Model modelo,
			RedirectAttributes redirect) {
		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);
		if (inscricao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_SELECAO;

		}else if(inscricao.getSelecao().getTipoSelecao().equals(TipoSelecao.AUX_MOR)){
			modelo.addAttribute("inscricao", inscricao);
			modelo.addAttribute("questAuxMor", inscricao.getQuestionarioAuxilioMoradia());
			return PAGINA_DETALHES_AUXILIO_MORADIA;
		}else {
			modelo.addAttribute("inscricao", inscricao);
			modelo.addAttribute("questInic", inscricao.getQuestionarioIniciacaoAcademica());
			return PAGINA_DETALHES_INICIACAO_ACADEMICA;
		}
	}
	@RequestMapping(value= {"avaliarDocumentacao/{idInscricao}"}, method = RequestMethod.GET)
	public String avaliarDocumentacao(@PathVariable("idInscricao") Integer idInscricao,Authentication auth, RedirectAttributes redirect, Model model){

		Inscricao inscricao = this.inscricaoService.find(Inscricao.class, idInscricao);		

		if(inscricao == null){
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_INSCRICAO_INEXISTENTE);
			return REDIRECT_PAGINA_INFORMACOES_SELECAO_SERVIDOR;
		}else{

			Selecao selecao = inscricao.getSelecao();
			Servidor servidor = servidorService.getServidorByCpf(auth.getName());

			List<Servidor> comissao = selecao.getMembrosComissao();

			if(comissao.contains(servidor)){

				model.addAttribute("avaliarDocumentacao", inscricao.isAvaliacaoDocumentos());
				model.addAttribute("idInscricao", idInscricao);

				return PAGINA_AVALIAR_DOCUMENTACAO;
			}else{
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SERVIDOR_NAO_PERTENCE_A_COMISSAO_ENTREVISTA);
				return REDIRECT_PAGINA_INFORMACOES_SELECAO_SERVIDOR;
			}
		}
	}
	
	@RequestMapping(value= {"avaliarDocumentacao"}, method = RequestMethod.POST)
	public String avaliarDocumentacao(@Valid @ModelAttribute("avaliarDocumentacao") Inscricao avaliarDocumentacao , @RequestParam("idInscricao") Integer idInscricao, 
			BindingResult result, RedirectAttributes redirect, Model model , Authentication auth){

		Inscricao inscricao = inscricaoService.find(Inscricao.class, idInscricao);
		
		if(avaliarDocumentacao.isAvaliacaoDocumentos()== true){
			inscricao.setAvaliacaoDocumentos(true);
		}else{
			inscricao.setAvaliacaoDocumentos(false);
		}

		model.addAttribute("avaliarDocumentacao", avaliarDocumentacao);	
		inscricaoService.update(inscricao);

		redirect.addFlashAttribute("info", MENSAGEM_DE_SUCESSO_AVALIAR_DOCUMENTACAO);
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}


}