package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ALUNO_ATUALIZADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ALUNO_CADASTRADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ALUNO_ENCONTRADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ALUNO_EXCLUIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_DE_SUCESSO_ENTREVISTA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_AGENCIA_DIGITOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ALUNO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ANO_INGRESSO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ANO_INGRESSO_DIGITOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_CONTA_DIGITOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_MATRICULA_DIGITOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_MATRICULA_EXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SERVIDOR_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SIAPE_EXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_VISITA_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_INSCRICAO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SERVIDOR_ATUALIZADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SERVIDOR_CADASTRADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SERVIDOR_EXCLUIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SERVIDOR_NAO_ASSOCIADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SERVIDOR_NAO_ENCONTRADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_VISITA_CADASTRADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_CADASTRAR_ALUNO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_CADASTRAR_SERVIDOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INFORMACOES_RELATORIO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_ALUNOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_SERVIDOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_REALIZAR_ENTREVISTA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RELATORIO_VISITA;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_INSCRITOS_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_ALUNOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SERVIDOR;

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

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarServidor(@Valid @ModelAttribute(value = "servidor") Servidor servidor,
			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {

		if(servidor.getId() != null){
			return atualizarServidor(servidor.getId(), servidor, result, model, redirect);
		} else {
			return adicionarServidor(servidor, result, redirect, model);
		}

	}

	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(Model model, Authentication auth, RedirectAttributes redirect) {
		Servidor servidor = this.servidorService.getServidorByCPFComComissao(auth.getName());

		if (!servidor.getParticipaComissao().isEmpty()) {

			model.addAttribute("selecoes", servidor.getParticipaComissao());
			model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
			model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

			return PAGINA_LISTAR_SELECAO;

		}

		model.addAttribute("erro", MENSAGEM_SERVIDOR_NAO_ASSOCIADO);

		return PAGINA_LISTAR_SELECAO;
	}


	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {

		model.addAttribute("action", "cadastrar");
		model.addAttribute("cargos", Cargo.toMap());
		model.addAttribute("servidor", new Servidor());

		return PAGINA_CADASTRAR_SERVIDOR;
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adicionarServidor(
			@Valid @ModelAttribute("servidor") Servidor servidor,
			BindingResult result,RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("cargos", Cargo.toMap());
			return PAGINA_CADASTRAR_SERVIDOR;
		}

		try{
			this.servidorService.save(servidor);
		} catch (PersistenceException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SIAPE_EXISTENTE);

				return REDIRECT_PAGINA_LISTAR_SERVIDOR;
			}
		}
		
		redirect.addFlashAttribute("info", MENSAGEM_SERVIDOR_CADASTRADO);

		return REDIRECT_PAGINA_LISTAR_SERVIDOR;

	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaServidor(Servidor servidor, BindingResult result, Model model) {
		List<Servidor> results = servidorService.find(Servidor.class);	
		model.addAttribute("servidores", results);
		return PAGINA_LISTAR_SERVIDOR;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public String listarServidor(@RequestParam("siape") String siape, Model model, RedirectAttributes redirect) {
		List<Servidor> results = new ArrayList<Servidor>();
		Servidor servidor = servidorService.getServidorBySiape(siape);
		results.add(servidor);
		model.addAttribute("servidores", results);

		if(servidor == null){
			redirect.addFlashAttribute("erro", MENSAGEM_SERVIDOR_NAO_ENCONTRADO);
			redirect.addFlashAttribute("servidorEncontrado", false);

			return REDIRECT_PAGINA_LISTAR_SERVIDOR;
		}


		return PAGINA_LISTAR_SERVIDOR;
	}

	@RequestMapping(value = "{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, Model model) {

		Servidor servidor = servidorService.find(Servidor.class, id);

		model.addAttribute("cargos", Cargo.toMap());
		model.addAttribute("servidor", servidor);
		model.addAttribute("action", "editar");

		return PAGINA_CADASTRAR_SERVIDOR;
	}

	@RequestMapping(value = "{id}/editar", method = RequestMethod.POST)
	public String atualizarServidor(@PathVariable("id") Integer id,
			@Valid @ModelAttribute(value = "servidor") Servidor servidorAtualizado,
			BindingResult result, Model model,RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar");
			return PAGINA_CADASTRAR_SERVIDOR;
		}

		Servidor servidor = servidorService.find(Servidor.class, id);

		servidor.setSiape(servidorAtualizado.getSiape());
		servidor.setCargo(servidorAtualizado.getCargo());

		this.servidorService.update(servidor);
		redirect.addFlashAttribute("info", MENSAGEM_SERVIDOR_ATUALIZADO);

		return REDIRECT_PAGINA_LISTAR_SERVIDOR;
	}

	@RequestMapping(value = "{id}/excluir")
	public String excluirServidor(Servidor p, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Servidor servidor = servidorService.find(Servidor.class, id);

		if (servidor == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_ERRO_SERVIDOR_INEXISTENTE);
		}else{

			this.servidorService.delete(servidor);
			redirectAttributes.addFlashAttribute("info", MENSAGEM_SERVIDOR_EXCLUIDO);
		}

		return REDIRECT_PAGINA_LISTAR_SERVIDOR;

	}

	@RequestMapping(value = { "listar/alunos" }, method = RequestMethod.GET)
	public String listarAlunos(Model model){

		List<Aluno> alunos = this.alunoService.find(Aluno.class);
		model.addAttribute("alunos", alunos);

		return PAGINA_LISTAR_ALUNOS;
	}

	@RequestMapping(value = "listar/alunos", method = RequestMethod.POST)
	public String listarAluno(@RequestParam("matricula") String matricula, Model model, RedirectAttributes redirect) {

		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = alunoService.getAlunoByMatricula(matricula);
		alunos.add(aluno);
		model.addAttribute("alunos", alunos);

		if (aluno == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ALUNO_ENCONTRADO);
			redirect.addFlashAttribute("alunoEncontrado", false);

			return REDIRECT_PAGINA_LISTAR_ALUNOS;
		}

		return PAGINA_LISTAR_ALUNOS;
	}

	@RequestMapping(value = { "cadastrar/aluno" }, method = RequestMethod.GET)
	public String cadastrarAluno(Model model) {

		model.addAttribute("action", "cadastrar");
		model.addAttribute("banco", Banco.values());
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("aluno", new Aluno());

		return PAGINA_CADASTRAR_ALUNO;
	}

	@RequestMapping(value = { "cadastrar/aluno" }, method = RequestMethod.POST)
	public String cadastrarAluno(@Valid @ModelAttribute("aluno") Aluno aluno, Model model, BindingResult result,
			RedirectAttributes redirect) {

		model.addAttribute("action", "cadastrar");


		if (aluno.getAnoIngresso() != null && !aluno.getAnoIngresso().equals("")) {
			if (aluno.getAnoIngresso().length() < 4) {
				result.rejectValue("anoIngresso", "aluno.anoIngresso", MENSAGEM_ERRO_ANO_INGRESSO_DIGITOS);
			} else {
				DateTime anoIngresso = DateTime.parse(aluno.getAnoIngresso());
				if (anoIngresso.isAfterNow()) {
					result.rejectValue("anoIngresso", "aluno.anoIngresso", MENSAGEM_ERRO_ANO_INGRESSO);
				}
			}
		}

		if (aluno.getMatricula() != null && !aluno.getMatricula().equals("") && aluno.getMatricula().length() < 6) {
			result.rejectValue("matricula", "aluno.matricula", MENSAGEM_ERRO_MATRICULA_DIGITOS);
		}

		if (aluno.getAgencia() != null && !aluno.getAgencia().equals("") && aluno.getAgencia().length() < 6) {
			result.rejectValue("agencia", "aluno.agencia", MENSAGEM_ERRO_AGENCIA_DIGITOS);
		}

		if (aluno.getConta() != null && !aluno.getConta().equals("") && aluno.getConta().length() < 4) {
			result.rejectValue("conta", "aluno.conta", MENSAGEM_ERRO_CONTA_DIGITOS);
		}

		if (aluno != null) {
			if (this.alunoService.existsAlunoEquals(aluno)) {
				result.rejectValue("matricula", "aluno.matricula", MENSAGEM_ERRO_MATRICULA_EXISTENTE);
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("aluno", aluno);
			model.addAttribute("banco", Banco.values());
			model.addAttribute("curso", Curso.values());

			return PAGINA_CADASTRAR_ALUNO;
		}

		this.alunoService.save(aluno);
		redirect.addFlashAttribute("info", MENSAGEM_ALUNO_CADASTRADO);
		return REDIRECT_PAGINA_LISTAR_ALUNOS;
	}

	@RequestMapping(value = { "editar/aluno/{idAluno}" }, method = RequestMethod.GET)
	public String editarAluno(@PathVariable("idAluno") Integer idAluno, Model model) {

		Aluno aluno = this.alunoService.find(Aluno.class, idAluno);

		model.addAttribute("action", "editar");
		model.addAttribute("banco", Banco.values());
		model.addAttribute("curso", Curso.values());
		model.addAttribute("aluno", aluno);

		return PAGINA_CADASTRAR_ALUNO;
	}

	@RequestMapping(value = { "editar/aluno" }, method = RequestMethod.POST)
	public String editarAluno(@Valid @ModelAttribute("aluno") Aluno aluno, Model model, BindingResult result, RedirectAttributes redirect) {

		model.addAttribute("action", "editar");

		if (aluno.getAnoIngresso() != null && !aluno.getAnoIngresso().equals("")) {
			if (aluno.getAnoIngresso().length() < 4) {
				result.rejectValue("anoIngresso", "aluno.anoIngresso", MENSAGEM_ERRO_ANO_INGRESSO_DIGITOS);
			} else {
				DateTime anoIngresso = DateTime.parse(aluno.getAnoIngresso());
				if (anoIngresso.isAfterNow()) {
					result.rejectValue("anoIngresso", "aluno.anoIngresso", MENSAGEM_ERRO_ANO_INGRESSO);
				}
			}
		}

		if (aluno.getMatricula() != null && !aluno.getMatricula().equals("") && aluno.getMatricula().length() < 6) {
			result.rejectValue("matricula", "aluno.matricula", MENSAGEM_ERRO_MATRICULA_DIGITOS);
		}

		if (aluno.getAgencia() != null && !aluno.getAgencia().equals("") && aluno.getAgencia().length() < 6) {
			result.rejectValue("agencia", "aluno.agencia", MENSAGEM_ERRO_AGENCIA_DIGITOS);
		}

		if (aluno.getConta() != null && !aluno.getConta().equals("") && aluno.getConta().length() < 4) {
			result.rejectValue("conta", "aluno.conta", MENSAGEM_ERRO_CONTA_DIGITOS);
		}

		if (result.hasErrors()) {
			model.addAttribute("aluno", aluno);
			model.addAttribute("banco", Banco.values());
			model.addAttribute("curso", Curso.values());

			return PAGINA_CADASTRAR_ALUNO;
		}

		this.alunoService.update(aluno);
		redirect.addFlashAttribute("info", MENSAGEM_ALUNO_ATUALIZADO);
		return REDIRECT_PAGINA_LISTAR_ALUNOS;
	}

	@RequestMapping(value = { "excluir/aluno/{idAluno}" }, method = RequestMethod.GET)
	public String excluirAluno(@PathVariable("idAluno") Integer idAluno, RedirectAttributes redirect) {

		Aluno aluno = this.alunoService.find(Aluno.class, idAluno);

		if (aluno != null) {
			this.alunoService.delete(aluno);
			redirect.addFlashAttribute("info", MENSAGEM_ALUNO_EXCLUIDO);
		} else {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_ALUNO_INEXISTENTE);
		}

		return REDIRECT_PAGINA_LISTAR_ALUNOS;
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
			
			Servidor servidor = this.servidorService.getPessoaServidorComComissao(idPessoa);
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
		relatorioVisitaDomiciliar.setSelecao(selecaoService.find(Selecao.class, idSelecao));
		
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
