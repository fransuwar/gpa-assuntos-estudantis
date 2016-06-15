package br.ufc.quixada.npi.gpa.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping("inscricao")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO})
public class InscricaoController {
	
	@Inject
	private InscricaoService inscricaoService;
	
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
}
