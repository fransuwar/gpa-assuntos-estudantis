package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_SELECOES_ABERTAS;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping("inscricao")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO})
public class InscricaoController {
	
	@Inject
	private InscricaoService inscricaoService;
	
	@Inject
	private SelecaoService selecaoService;
	
	private void enviarImagemPadraoEmCasoDeErro(HttpServletResponse response){
		try {
			response.setContentType("text/html");
			response.sendRedirect(Constants.CAMINHO_IMAGEM_ALUNO_SEM_FOTO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("detalhes/fotoAluno/{idInscricao}")
	public void pegarFotoAluno(@PathVariable("idInscricao") Integer idInscricao, HttpServletResponse response){
		Inscricao inscricao = this.inscricaoService.getInscricaoPorId(idInscricao);
		
		try {
			response.setContentType("image/jpg");
			java.io.OutputStream out = response.getOutputStream();
			out.write(inscricao.getQuestionarioAuxilioMoradia().getFoto());
			out.flush();
		} catch (IOException e) {
			enviarImagemPadraoEmCasoDeErro(response);
		} catch (NullPointerException e) {
			enviarImagemPadraoEmCasoDeErro(response);
		}
	}
	
	@RequestMapping(value = {"consolidar/{idInscricao}"}, method = RequestMethod.GET)
	public String consolidarInscricao(@PathVariable("idInscricao") Integer idInscricao,Model model){
		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);
		List<Selecao> selecoes = selecaoService.getSelecoes();
		
		inscricao.setConsolidacao(true);
		inscricaoService.update(inscricao);	
		
		model.addAttribute("selecoes", selecoes);
		
		return PAGINA_SELECOES_ABERTAS;
	}
	
	@RequestMapping(value = "consolidar", method = RequestMethod.GET,  produces=  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Model consolidarViaAjax(@RequestParam("idInscricao") Integer idInscricao, @RequestParam("consolidacao") boolean consolidacao,Model model){
		inscricaoService.consolidar(idInscricao, consolidacao);
		model.addAttribute("resultado","sucesso");
		return model;
		
	}
	
	@RequestMapping(value = "consolidarTodos", method = RequestMethod.GET,  produces=  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Model consolidarTodos(@RequestParam("idSelecao") Integer idSelecao,@RequestParam("consolidacao") boolean consolidacao,Model model){
		inscricaoService.consolidacaoDeTodos(idSelecao, consolidacao);
		model.addAttribute("resultado","sucesso");
		return model;
	}
}
