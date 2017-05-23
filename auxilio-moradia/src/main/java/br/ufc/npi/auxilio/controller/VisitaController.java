package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.ALUNO;
import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_COORDENADOR;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_DOCUMENTO_ADICIONADO;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Documento;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.model.VisitaDomiciliar;
import br.ufc.npi.auxilio.repository.VisitaDomiciliarRepository;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.ServidorService;
import br.ufc.npi.auxilio.service.VisitaService;
import br.ufc.npi.auxilio.utils.ErrorMessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;

@Controller
@RequestMapping("visita")
public class VisitaController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ServidorService servidorService;

	@Autowired
	private VisitaDomiciliarRepository visitaDomiciliarRepository;

	@Autowired
	private VisitaService visitaService;

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/cadastrar/{inscricao}")
	public String cadastrar(@PathVariable Inscricao inscricao, Authentication auth, Model model) {
		Aluno aluno = alunoService.buscarPorCpf(inscricao.getAluno().getPessoa().getCpf());
		Servidor servidor = servidorService.getByCpf(auth.getName());

		model.addAttribute("servidor", servidor);
		model.addAttribute("inscrição", inscricao != null ? inscricao.getId() : null);
		model.addAttribute(ALUNO, aluno);
		model.addAttribute("selecao", inscricao.getSelecao());
		model.addAttribute("visita", new VisitaDomiciliar());

		return PageConstants.PAGINA_VISITA;
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/editar/{inscricao}")
	public String editar(@PathVariable Inscricao inscricao, Authentication auth, Model model) {
		
		VisitaDomiciliar visitaDomiciliar = inscricao.getVisitaDomiciliar();
		Servidor servidor = servidorService.getByCpf(auth.getName());

		model.addAttribute("servidor", servidor);
		model.addAttribute("visita", visitaDomiciliar);
		return PageConstants.PAGINA_VISITA;
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/cadastrar/{inscricao}")
	public String cadastrar(@RequestParam("imagensVisita") List<MultipartFile> imagens,
			@RequestParam("formularioVisita") List<MultipartFile> formulario, VisitaDomiciliar visitaDomiciliar,@PathVariable("inscricao") Inscricao inscricao,
			Model model, Authentication auth, RedirectAttributes redirect) {

		// Salvar Imagens
		if (imagens != null && !imagens.isEmpty() && imagens.get(0).getSize() > 0) {
			for (MultipartFile mfiles : imagens) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						visitaService.adicionarImagens(visitaDomiciliar, mfiles);
						redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_ADICIONADO);
					}
				} catch (IOException e)	{
					redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
				} catch (AuxilioMoradiaException e) {
					redirect.addFlashAttribute(ERRO, e.getMessage());
				}
			}
		}
		// Salvar Formulário
		if (formulario != null && !imagens.isEmpty() && imagens.get(0).getSize() > 0) {
			for (MultipartFile mfiles : formulario) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						visitaService.adicionarFormulario(visitaDomiciliar, mfiles);
						redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_ADICIONADO);
					}
				} catch (IOException e)	{
					redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
				} catch (AuxilioMoradiaException e) {
					redirect.addFlashAttribute(ERRO, e.getMessage());
				}
			}
		}
		inscricao.setVisitaDomiciliar(visitaDomiciliar);
		visitaDomiciliar.setResponsavel(servidorService.getByCpf(auth.getName()));
		//salvar visita
		this.visitaDomiciliarRepository.save(visitaDomiciliar);
		return RedirectConstants.REDIRECT_LISTAR_SELECAO;
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/documento/{inscricao}/download/{documento}")
	public HttpEntity<?> downloadDocumento(@PathVariable Inscricao inscricao, @PathVariable Documento documento) {
		try {
			if(inscricao != null && documento != null) {
				documento = visitaService.buscarDocumento(documento);
				return visitaService.downloadDocumento(documento, "attachment");
			}
		} catch (AuxilioMoradiaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@ModelAttribute("resultados")
	public Resultado[] getResultados() {
		return Resultado.values();
	}
}