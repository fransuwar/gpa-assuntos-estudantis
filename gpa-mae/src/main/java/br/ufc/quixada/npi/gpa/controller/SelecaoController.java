package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SELECAO_INEXISTENTE;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_DOWNLOAD_DOCUMENTO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_PARECER_EMITIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_INFORMACOES_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_INSCRITOS_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RANKING_DEFERIDOS;
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

import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.ParecerForm;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
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
	private DocumentoService documentoService;

	@Inject
	private SelecaoService selecaoService;
	
	@Inject
	private AlunoService alunoService;
	@Inject
	private InscricaoService inscricaoService;
	
	@RequestMapping(value = { "detalhesPublico/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoesPublico(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}

		model.addAttribute("selecao", selecao);
		return PAGINA_INFORMACOES_SELECAO;
	}
	
	@RequestMapping(value = { "detalhes/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoes(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect,Authentication auth) {
		//Detalhe da seleção, apenas para aluno
		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}

		model.addAttribute("selecao", selecao);
		
		Aluno aluno = alunoService.getAlunoComInscricoes(auth.getName());
		List<Inscricao> inscricoes = inscricaoService.getInscricoesBySelecaoByAluno(selecao.getId(),aluno.getId());
		boolean controle = false;
		if(!inscricoes.isEmpty())
			controle=true;
		model.addAttribute("controle", controle);
	
		return PAGINA_INFORMACOES_SELECAO;
	}
	

	@RequestMapping(value = {"documento/{idDocumento}"}, method = RequestMethod.GET)
	public HttpEntity<byte[]> downloadDocumento(@PathVariable("idDocumento") Integer id, 
			RedirectAttributes redirectAttributes){

		Documento documento = documentoService.find(Documento.class, id);
		byte[] arquivo = documento.getArquivo();
		String[] tipo = documento.getTipo().split("/");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(tipo[0], tipo[1]));
		headers.set("Content-Disposition", "attachment; filename=" + documento.getNome().replace(" ", "_"));
		headers.setContentLength(arquivo.length);
		redirectAttributes.addFlashAttribute("success", MENSAGEM_SUCESSO_DOWNLOAD_DOCUMENTO);

		return new HttpEntity<byte[]>(arquivo, headers);

	}

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listar(ModelMap model, HttpServletRequest request) {

		List<Selecao> selecoes = this.selecaoService.find(Selecao.class);

		model.addAttribute("selecoes", selecoes);
		model.addAttribute("tipoBolsa", TipoSelecao.values());
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "/listarPorServidor/{id}")
	public String listarSelecaoPorServidor(@PathVariable("id") Integer id, ModelMap model) {

		List<Selecao> selecoes = this.servidorService.find(Servidor.class, id).getParticipaComissao();

		model.addAttribute("selecoes", selecoes);
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "parecer/{idSelecao}", method = RequestMethod.POST)
	public String emitirParecer(@Valid @ModelAttribute("pareceres") ParecerForm parecerForm,
			@PathVariable("idSelecao") Integer id, BindingResult result, HttpServletRequest request,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return PAGINA_LISTAR_INSCRITOS_SELECAO;
		}
		
		// TODO - Implementar o método que dará o parecer do aluno.

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_PARECER_EMITIDO);

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = { "inscricao/detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricao() {
		// TODO - Método p/ implementar que retorna página de detalhes de uma seleção.
		return "";
	}
	
	@RequestMapping(value = {"ranking/{idSelecao}"}, method = RequestMethod.GET)
	public String visualizarRanking(ModelMap model, @PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect){
		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);

		if (selecao == null) {
			redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE); 
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		
		List<Inscricao> deferidos = inscricaoService.getDeferidosBySelecao(selecao);

		model.addAttribute("deferidos", deferidos);
		
		return PAGINA_RANKING_DEFERIDOS;
		
	}

}
