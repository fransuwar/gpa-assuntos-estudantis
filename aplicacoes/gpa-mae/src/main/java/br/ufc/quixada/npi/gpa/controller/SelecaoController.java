package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

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
import br.ufc.quixada.npi.gpa.model.ParecerForm;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
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

	@RequestMapping(value = { "detalhes/{idSelecao}" }, method = RequestMethod.GET)
	public String getInformacoes(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		Selecao selecao = selecaoService.getSelecaoBolsaComDocumentos(idSelecao);


		if (selecao == null) {
			redirect.addFlashAttribute("erro", "seleção Inexistente");
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		model.addAttribute("selecao", selecao);

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

	@RequestMapping(value = "inscritos/{id}", method = RequestMethod.GET)
	public String listarInscritos(@PathVariable("id") Integer id, ModelMap model) {
		
		// TODO - Implementar método que pode ser visualizar os inscritos em uma determinada seleção.

		return PAGINA_LISTAR_INSCRITOS_SELECAO;
	}

	@RequestMapping(value = "parecer/{idSelecao}", method = RequestMethod.POST)
	public String emitirParecer(@Valid @ModelAttribute("pareceres") ParecerForm parecerForm,
			@PathVariable("idSelecao") Integer id, BindingResult result, HttpServletRequest request,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return PAGINA_LISTAR_INSCRITOS_SELECAO;
		}
		
		// TODO - 

		redirect.addFlashAttribute("info", "Parecer emitido com sucesso.");
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	
	@RequestMapping(value = { "inscricao/detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricao() {
		//TODO - Método p/ implementar que retorna página de detalhes de uma seleção.
		return "";
	}
}
