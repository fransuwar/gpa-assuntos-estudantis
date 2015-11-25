package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_FORMULARIO_PREENCHIDO_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INFORMACOES_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_INSCRITOS_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.TipoBolsa;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.ParecerForm;
import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.gpa.service.QuestionarioAuxMoradiaService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Named
@RequestMapping("selecao")
@SessionAttributes({ Constants.USUARIO_ID })
public class SelecaoController {

	@Inject
	private ServidorService servidorService;
	
	@Inject
	private AlunoService alunoService;
	
	@Inject
	private DocumentoService documentoService;

	@Inject
	private SelecaoService selecaoService;
	
	@Inject
	private PessoaService servicePessoa;
	
	@Inject
	private QuestionarioAuxMoradiaService auxService;

	@Inject
	private InscricaoService inscricaoService;
	
	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)

	public String listar(ModelMap model, HttpServletRequest request, Authentication auth) {
		
		List<Selecao> selecoes = selecaoService.find(Selecao.class);

		if (request.isUserInRole("DISCENTE")) {

			
			Aluno aluno = this.alunoService.getAlunoComInscricoesCpf(auth.getName());
			
			model.addAttribute("selecoes", selecoes);
			model.addAttribute("aluno", aluno);
			model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
			model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);

			
		} else if(request.isUserInRole("SERVIDOR")){
			
			//Pessoa pessoa = servicePessoa.getPessoaByCpf(auth.getName());
			//Integer id = pessoa.getId(); 
			Servidor servidor = this.servidorService.getServidorByCpf(auth.getName());
			
			selecoes = servidor.getParticipaBancas();
			model.addAttribute("selecoes", selecoes);
			model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
			model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);

		} else {

			model.addAttribute("selecoes", selecoes);
			model.addAttribute("tipoBolsa", TipoBolsa.values());
			model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
			model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);
		}
		
		return PAGINA_LISTAR_SELECAO;
	}
	

	@RequestMapping(value = { "/detalhes/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoes(@PathVariable("idSelecao") Integer id, Model model, RedirectAttributes redirect) {
		Selecao selecao = selecaoService.getSelecaoBolsaComDocumentos(id);

		if (selecao == null) {
			redirect.addFlashAttribute("erro", "seleção Inexistente");
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		model.addAttribute("selecao", selecao);

		return PAGINA_INFORMACOES_SELECAO;
	}

	//	@RequestMapping(value = "inscritos/relatorioVisita/{idAluno}/{idSelecaoBolsa}")
	//	public String cadastrarRelatorio(@PathVariable("idAluno") Integer idAluno,
	//			@PathVariable("idSelecaoBolsa") Integer idSelecaoBolsa, Model modelo) {
	//		return "redirect:/relatorioVisita/cadastrar/" + idAluno + "/" + idSelecaoBolsa;
	//	}

	//	@RequestMapping(value = "inscritos/informacoesRelatorio/{id}")
	//	public String visualizarRelatorioVisita(@PathVariable("id") Integer id, Model modelo) {
	//		return "redirect:/relatorioVisita/informacoesRelatorio/" + id;
	//	}

	@RequestMapping(value = {"documento/{idDocumento}"}, method = RequestMethod.GET)
	public HttpEntity<byte[]> downloadDocumento(@PathVariable("idDocumento") Long id, 
			RedirectAttributes redirectAttributes){
		Documento documento = documentoService.find(Documento.class, id);
		byte[] arquivo = documento.getArquivo();
		String[] tipo = documento.getTipo().split("/");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(tipo[0], tipo[1]));
		headers.set("Content-Disposition", "attachment; filename=" + documento.getNome().replace(" ", "_"));
		headers.setContentLength(arquivo.length);
		redirectAttributes.addFlashAttribute("success", "Download do Documento realizado com sucesso");
		return new HttpEntity<byte[]>(arquivo, headers);
	}
	@RequestMapping(value = "/listarPorServidor/{id}")
	public String listarSelecaoPorServidor(@PathVariable("id") Integer id, ModelMap model) {

		List<Selecao> selecoes = this.servidorService.getPessoaServidorComBancas(id).getParticipaBancas();

		model.addAttribute("selecoes", selecoes);
		model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
		model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);

		return PAGINA_LISTAR_SELECAO;
	}

//	@RequestMapping(value = "inscritos/{id}", method = RequestMethod.GET)
//	public String listarInscritos(@PathVariable("id") Integer id, ModelMap model) {
//
//		List<Aluno> alunosSelecao = this.selecaoService.getSelecaoBolsaComAlunos(id).getAlunosSelecao();
//		
//		List<Parecer> pareceres = new ArrayList<Parecer>();
//		for (Aluno aluno : alunosSelecao) {
//			Parecer parecer = new Parecer();
//			parecer.setAlunoApto(aluno);
//			pareceres.add(parecer);
//		}
//		
//		ParecerForm parecerForm = new ParecerForm();
//		parecerForm.setPareceres(pareceres);
//		
//		model.addAttribute("pareceres", parecerForm);
//		model.addAttribute("idSelecao", id);
//
//		return PAGINA_LISTAR_INSCRITOS_SELECAO;
//	}
//	
//	@RequestMapping(value = "/visualizarFormulario/{idaluno}")
//	public String visualizarFormularioAluno(@PathVariable("idaluno") Integer id, Model model) {
//		return null;
//	}

	@RequestMapping(value = "/parecer/{idSelecao}", method = RequestMethod.POST)
	public String emitirParecer(@Valid @ModelAttribute("pareceres") ParecerForm parecerForm,
			@PathVariable("idSelecao") Integer id, BindingResult result, HttpServletRequest request,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return PAGINA_LISTAR_INSCRITOS_SELECAO;
		}

		/*
		 * List<Aluno> alunosSelecao = this.selecaoService
		 * .getSelecaoBolsaComAlunos(id).getAlunosSelecao(); Selecao selecao =
		 * this.selecaoService.getSelecaoBolsaComAlunos(id);
		 * 
		 * List<Parecer> pareceres = parecerForm.getPareceres();
		 * 
		 * for (Parecer parecer : pareceres) { for (Aluno aluno : alunosSelecao)
		 * { parecer.setAlunoApto(aluno); parecer.setSelecao(selecao); }
		 * 
		 * this.parecerService.save(parecer); }
		 */

		redirect.addFlashAttribute("info", "Parecer emitido com sucesso.");
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "formularioInscricaoPreenchido/{id}/{idSelecao}", method = RequestMethod.GET)
	public String visualizarDadosInscricao(@PathVariable("id") Integer idAluno,
			@PathVariable("idSelecao") Integer idSelecao, Model modelo, RedirectAttributes redirect) {

		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
		Aluno aluno = alunoService.find(Aluno.class, idAluno);
		QuestionarioAuxilioMoradia questionario = auxService.find(QuestionarioAuxilioMoradia.class, idAluno);

		if (selecao == null) {
			redirect.addFlashAttribute("erro", "Relatório não existe");
			return "redirect:/selecao/inscritos/{id}";
		}
		modelo.addAttribute("aluno", aluno);
		modelo.addAttribute("selecao", selecao);
		modelo.addAttribute("questionario", questionario);

		return PAGINA_FORMULARIO_PREENCHIDO_SELECAO;

	}

}
