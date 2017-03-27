package br.ufc.npi.auxilio.controller;

import br.ufc.npi.auxilio.enums.TipoSelecao;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.service.*;
import br.ufc.npi.auxilio.utils.ErrorMessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

import static br.ufc.npi.auxilio.utils.Constants.*;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_SELECAO_INEXISTENTE;
import static br.ufc.npi.auxilio.utils.PageConstants.*;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_DETALHES_SELECAO;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_LISTAR_SELECAO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.*;

@Controller
@RequestMapping("/selecao")
public class SelecaoController {
	
	@Autowired
	private SelecaoService selecaoService;
	
	@Autowired
	private ServidorService servidorService;

	@Autowired
	private InscricaoService inscricaoService;

	@Autowired
	private AlunoService alunoService;
	
	@GetMapping({"", "/", "/listar"})
	public String listarSelecoes(Model model) {
		model.addAttribute("selecoes", selecaoService.getAll());
		return PageConstants.LISTAR_SELECAO;
	}

	@GetMapping("detalhes/{selecao}")
	public String detalhes(@PathVariable Selecao selecao, Model model, Authentication auth, RedirectAttributes redirect){
		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE);
			return REDIRECT_LISTAR_SELECAO;
		}
		Aluno aluno = alunoService.buscarPorCpf(auth.getName());
		Inscricao inscricao = inscricaoService.get(aluno, selecao);
		model.addAttribute("selecao", selecao)
				.addAttribute("membroComissao", selecao.isMembroComissao(servidorService.getByCpf(auth.getName())))
				.addAttribute("inscricaoAberta", selecao.isInscricaoAberta())
				.addAttribute("inscricaoRealizada", inscricao != null)
				.addAttribute("inscricaoConsolidada", inscricao != null && inscricao.isConsolidada())
				.addAttribute("inscricao", inscricao != null ? inscricao.getId() : null);
		return DETALHES_SELECAO;

	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/cadastrar")
	public String cadastrarSelecaoForm(Model model) {
		model.addAttribute("acao", "cadastrar");
		model.addAttribute("opcoesTipoSelecao", TipoSelecao.values());
		model.addAttribute("selecao", new Selecao());
		
		return CADASTRAR_SELECAO;
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/cadastrar")
	public String cadastrarSelecao(Selecao selecao, Authentication auth, Model model, RedirectAttributes redirect) {
		selecao.setResponsavel(servidorService.getByCpf(auth.getName()));
		try {
			selecaoService.cadastrar(selecao);
			redirect.addFlashAttribute(INFO, MSG_SELECAO_CADASTRADA);
			return REDIRECT_LISTAR_SELECAO;
		} catch (AuxilioMoradiaException e) {
			model.addAttribute(ERRO, e.getMessage());
			return CADASTRAR_SELECAO;
		}
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/excluir/{selecao}")
	public String excluirSelecao(@PathVariable Selecao selecao, RedirectAttributes redirect) {
		// Se a seleção não existe
		if (selecao == null) {
			// Avisa ao usuário...
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE);
		} else {
			try {
				// Tenta excluir a seleção
				selecaoService.excluir(selecao);
				// Avisa ao usuário do sucesso da remoção
				redirect.addFlashAttribute(INFO, MSG_SUCESSO_SELECAO_REMOVIDA);
			} catch (AuxilioMoradiaException e) {
				// Avisa ao usuário do erro na remoção
				redirect.addFlashAttribute(ERRO, e.getMessage());
			}
		}
		return REDIRECT_LISTAR_SELECAO;
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/editar/{selecao}")
	public String editarSelecao(@PathVariable Selecao selecao, Model model, RedirectAttributes redirect) {
		if (selecao == null) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SELECAO_INEXISTENTE);
		} else {
			model.addAttribute("acao", "editar");
			model.addAttribute("opcoesTipoSelecao", TipoSelecao.values());
			model.addAttribute("selecao", selecao);
			return CADASTRAR_SELECAO;
		}
		return REDIRECT_LISTAR_SELECAO;
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/documento/{selecao}/adicionar")
	public String adicionarDocumento( @RequestParam List<MultipartFile> files,
			@PathVariable Selecao selecao, RedirectAttributes redirect ) {
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) {
			for (MultipartFile mfiles : files) {
				try {
					selecaoService.adicionarDocumento(selecao, mfiles);
					redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_ADICIONADO);
				} catch (IOException e)	{
					redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
				} catch (AuxilioMoradiaException e) {
					redirect.addFlashAttribute(ERRO, e.getMessage());
				}
			} 
		}
		return REDIRECT_DETALHES_SELECAO + selecao.getId();
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/documento/{selecao}/excluir/{documento}")
	public String excluirDocumento(@PathVariable Documento documento,
			@PathVariable Selecao selecao, Model model, RedirectAttributes redirect) {
		try {
			selecaoService.removerDocumento(selecao, documento);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_REMOVIDO);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_LISTAR_SELECAO;
		}
		return  REDIRECT_DETALHES_SELECAO + selecao.getId();
	}

	@GetMapping("/documento/{selecao}/download/{documento}")
	public HttpEntity<?> downloadDocumento(@PathVariable Selecao selecao, @PathVariable Documento documento) {
		try {
			if(selecao != null && documento != null && selecao.getDocumentos().contains(documento)) {
				documento = selecaoService.buscarDocumento(documento);
				return selecaoService.downloadDocumento(documento, "attachment");
			}
		} catch (AuxilioMoradiaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping(value = "/comissao/adicionar")
	public String adicionarMembroComissao(@RequestParam Selecao selecao,
			@RequestParam Servidor servidor, RedirectAttributes redirect) {
		try {
			selecaoService.adicionarMembroComissao(servidor, selecao);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_MEMBRO_ADICIONADO);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_DETALHES_SELECAO + selecao.getId();
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/comissao/{selecao}/excluir/{servidor}")
	public String excluirMembroComissao(@PathVariable Selecao selecao,
			@PathVariable Servidor servidor, RedirectAttributes redirect) {
		try {
			selecaoService.removerMembroComissao(servidor, selecao);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_MEMBRO_REMOVIDO);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_DETALHES_SELECAO + selecao.getId();
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping(value = "/documentacao/adicionar")
	public String adicionarTipoDocumento(@RequestParam Selecao selecao, @RequestParam String nome, @RequestParam String descricao,
		 RedirectAttributes redirect) {
		try {
			TipoDocumento tipoDocumento = new TipoDocumento(nome, descricao);
			selecaoService.adicionarTipoDocumento(selecao, tipoDocumento);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_TIPO_DOCUMENTO_ADICIONADO);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_DETALHES_SELECAO + selecao.getId();
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/documentacao/excluir/{tipoDocumento}")
	public String excluirTipoDocumento(@PathVariable TipoDocumento tipoDocumento, RedirectAttributes redirect) {
		Selecao selecao = tipoDocumento.getSelecao();
		try {
			selecaoService.removerTipoDocumento(tipoDocumento);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_TIPO_DOCUMENTO_REMOVIDO);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_DETALHES_SELECAO + selecao.getId();
	}

	@PreAuthorize(PERMISSAO_SERVIDOR)
	@GetMapping("/inscricoes/{selecao}")
	public String listarInscricoes(@PathVariable Selecao selecao, Authentication auth, Model model) {
		if (selecao == null || !selecao.isMembroComissao(servidorService.getByCpf(auth.getName()))) {
			return REDIRECT_LISTAR_SELECAO;
		}
		model.addAttribute("selecao", selecao);
		return LISTAR_INSCRICOES;
	}

	@ModelAttribute("servidores")
	public List<Servidor> getAllServidores() {
		return servidorService.getAll();
	}
}
