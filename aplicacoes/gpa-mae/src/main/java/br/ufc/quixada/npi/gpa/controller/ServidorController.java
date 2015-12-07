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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Banco;
import br.ufc.quixada.npi.gpa.enums.Cargo;
import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.ServidorService;


@Controller
@RequestMapping ("servidor")
public class ServidorController {

	@Inject
	private ServidorService servidorService;

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

			return "servidor/listaSelecoes";

		}

		model.addAttribute("erro", "Você não está associado a nenhuma seleção.");

		return "servidor/listaSelecoes";
		//return PAGINA_LISTAR_SELECAO;
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
				redirect.addFlashAttribute("erro", "Não é possível cadastrar um SIAPE já existente.");

				return REDIRECT_PAGINA_LISTAR_SERVIDOR;
			}
		}
		redirect.addFlashAttribute("info", "Servidor cadastrado com sucesso.");

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
			redirect.addFlashAttribute("erro", "Servidor não encontrado");
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
		redirect.addFlashAttribute("info", "Servidor atualizado com sucesso.");

		return REDIRECT_PAGINA_LISTAR_SERVIDOR;
	}

	@RequestMapping(value = "{id}/excluir")
	public String excluirServidor(Servidor p, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Servidor servidor = servidorService.find(Servidor.class, id);

		if (servidor == null) {
			redirectAttributes.addFlashAttribute("erro", "Servidor inexistente.");
		}else{

			this.servidorService.delete(servidor);
			redirectAttributes.addFlashAttribute("info", "Servidor excluído com sucesso.");
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
			redirect.addFlashAttribute("erro", "Aluno não encontrado");
			redirect.addFlashAttribute("alunoEncontrado", false);

			return REDIRECT_PAGINA_LISTAR_ALUNOS;
		}

		return PAGINA_LISTAR_ALUNOS;
	}

	@RequestMapping(value = { "cadastrar/aluno" }, method = RequestMethod.GET)
	public String cadastrarAluno(Model model) {

		model.addAttribute("action", "cadastrar");
		model.addAttribute("banco", Banco.values());
		model.addAttribute("curso", Curso.values());
		model.addAttribute("aluno", new Aluno());

		return PAGINA_CADASTRAR_ALUNO;
	}

	@RequestMapping(value = { "cadastrar/aluno" }, method = RequestMethod.POST)
	public String cadastrarAluno(@Valid @ModelAttribute("aluno") Aluno aluno, Model model, BindingResult result,
			RedirectAttributes redirect) {

		model.addAttribute("action", "cadastrar");


		if (aluno.getAnoIngresso() != null && !aluno.getAnoIngresso().equals("")) {
			if (aluno.getAnoIngresso().length() < 4) {
				result.rejectValue("anoIngresso", "aluno.anoIngresso", "O ano deve possuir pelo menos quatro dígitos");
			} else {
				DateTime anoIngresso = DateTime.parse(aluno.getAnoIngresso());
				if (anoIngresso.isAfterNow()) {
					result.rejectValue("anoIngresso", "aluno.anoIngresso", "Informe um ano menor ou igual ao atual");
				}
			}
		}

		if (aluno.getMatricula() != null && !aluno.getMatricula().equals("") && aluno.getMatricula().length() < 6) {
			result.rejectValue("matricula", "aluno.matricula", "A matrícula deve possuir pelo menos seis dígitos");
		}

		if (aluno.getAgencia() != null && !aluno.getAgencia().equals("") && aluno.getAgencia().length() < 6) {
			result.rejectValue("agencia", "aluno.agencia", "O número da agência deve possuir pelo menos seis dígitos");
		}

		if (aluno.getConta() != null && !aluno.getConta().equals("") && aluno.getConta().length() < 4) {
			result.rejectValue("conta", "aluno.conta", "O número da conta deve possuir pelo menos quatro dígitos");
		}

		if (aluno != null) {
			if (this.alunoService.existsAlunoEquals(aluno)) {
				result.rejectValue("matricula", "aluno.matricula", "Matrícula já existente.");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("aluno", aluno);
			model.addAttribute("banco", Banco.values());
			model.addAttribute("curso", Curso.values());

			return PAGINA_CADASTRAR_ALUNO;
		}

		this.alunoService.save(aluno);
		redirect.addFlashAttribute("info", "Aluno cadastrado com sucesso.");
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
				result.rejectValue("anoIngresso", "aluno.anoIngresso", "O ano deve possuir pelo menos quatro dígitos");
			} else {
				DateTime anoIngresso = DateTime.parse(aluno.getAnoIngresso());
				if (anoIngresso.isAfterNow()) {
					result.rejectValue("anoIngresso", "aluno.anoIngresso", "Informe um ano menor ou igual ao atual");
				}
			}
		}

		if (aluno.getMatricula() != null && !aluno.getMatricula().equals("") && aluno.getMatricula().length() < 6) {
			result.rejectValue("matricula", "aluno.matricula", "A matrícula deve possuir pelo menos seis dígitos");
		}

		if (aluno.getAgencia() != null && !aluno.getAgencia().equals("") && aluno.getAgencia().length() < 6) {
			result.rejectValue("agencia", "aluno.agencia", "O número da agência deve possuir pelo menos seis dígitos");
		}

		if (aluno.getConta() != null && !aluno.getConta().equals("") && aluno.getConta().length() < 4) {
			result.rejectValue("conta", "aluno.conta", "O número da conta deve possuir pelo menos quatro dígitos");
		}

		if (result.hasErrors()) {
			model.addAttribute("aluno", aluno);
			model.addAttribute("banco", Banco.values());
			model.addAttribute("curso", Curso.values());

			return PAGINA_CADASTRAR_ALUNO;
		}

		this.alunoService.update(aluno);
		redirect.addFlashAttribute("info", "Aluno atualizado com sucesso.");
		return REDIRECT_PAGINA_LISTAR_ALUNOS;
	}

	@RequestMapping(value = { "excluir/aluno/{idAluno}" }, method = RequestMethod.GET)
	public String excluirAluno(@PathVariable("idAluno") Integer idAluno, RedirectAttributes redirect) {

		Aluno aluno = this.alunoService.find(Aluno.class, idAluno);

		if (aluno != null) {
			this.alunoService.delete(aluno);
			redirect.addFlashAttribute("info", "Aluno removido com sucesso.");
		} else {
			redirect.addFlashAttribute("erro", "Aluno inexistente.");
		}

		return REDIRECT_PAGINA_LISTAR_ALUNOS;
	}

	@RequestMapping(value = { "entrevista/{idInscricao}" }, method = RequestMethod.GET)
	public String realizarEntrevista(Model model) {
		//TODO - Método p/ ser implementado que retorna página de realização de entrevista.
		return "";
	}

	@RequestMapping(value = { "entrevista" }, method = RequestMethod.POST)
	public String realizarEntrevista() {
		//TODO - Método p/ ser implementado que realiza uma entrevista de uma determinada inscrição.
		return "";
	}

	@RequestMapping(value = { "visita/{idInscricao}" }, method = RequestMethod.GET)
	public String realizarVisita(Model model) {
		//TODO - Método p/ ser implementado que retorna a página de realização uma visita.
		return "";
	}

	@RequestMapping(value = { "visita" }, method = RequestMethod.POST)
	public String realizarVisita() {
		//TODO - Método p/ ser implementado que realiza uma visita de uma determinada inscrição.
		return "";
	}
}
