package br.ufc.npi.auxilio.controller;

import br.ufc.npi.auxilio.enums.HorarioEntrevista;
import br.ufc.npi.auxilio.enums.TipoSelecao;
import br.ufc.npi.auxilio.enums.Turno;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static br.ufc.npi.auxilio.utils.Constants.*;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_SELECAO_INEXISTENTE;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_DOCUMENTACAO_JA_ADICIONADA;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_MEMBRO_JA_ADICIONADO;
import static br.ufc.npi.auxilio.utils.PageConstants.*;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_DETALHES_SELECAO;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_LISTAR_SELECAO;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_CADASTRAR_SELECAO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.*;

@Controller
@RequestMapping("/selecao")
public class SelecaoController {
	
	
	@Autowired
	private SelecaoService selecaoService;
	
	@Autowired
	private ServidorService servidorService;
	
	@Autowired
	private AgendamentoEntrevistaService agendamentoEntrevistaService;

	@Autowired
	private InscricaoService inscricaoService;

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@GetMapping({"", "/", "/listar"})
	public String listarSelecoes(Model model, Authentication auth) {
		List<Selecao> selecoes = selecaoService.getAll();
		Pessoa pessoa = pessoaService.getByCpf(auth.getName());
		if(pessoa.isAluno()){
			HashMap<Integer, Inscricao> inscricaoSelecao = new HashMap<>();
			Aluno aluno = alunoService.buscarPorCpf(auth.getName());
			for(Selecao selecao: selecoes){
				Inscricao inscricao = inscricaoService.get(aluno, selecao);
				inscricaoSelecao.put(selecao.getId(), inscricao);
			}
			model.addAttribute("inscricaoSelecao", inscricaoSelecao);
		}
		model.addAttribute("opcoesTipoSelecao", TipoSelecao.values());	
		model.addAttribute("selecoes", selecoes);
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
				.addAttribute("opcoesTipoSelecao", TipoSelecao.values())
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
	@GetMapping("/editar/{id}")
	public String editarSelecaoForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("acao", "editar");
		model.addAttribute("opcoesTipoSelecao", TipoSelecao.values());
		model.addAttribute("selecao", selecaoService.getById(id));
		
		return DETALHES_SELECAO;
	}

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/cadastrar")
	public String cadastrarSelecao(Selecao selecao, Authentication auth, Model model, RedirectAttributes redirect) {
		selecao.setResponsavel(servidorService.getByCpf(auth.getName()));
		try {
			selecaoService.cadastrar(selecao);
			redirect.addFlashAttribute(INFO, MSG_SELECAO_CADASTRADA);
			return REDIRECT_DETALHES_SELECAO+selecao.getId();
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
			return REDIRECT_CADASTRAR_SELECAO;
		}
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/editar")
	public String editarSelecao(Selecao selecao, Authentication auth, Model model, RedirectAttributes redirect) {
		selecaoService.editar(selecao);
		redirect.addFlashAttribute(INFO, MSG_SELECAO_EDITADA);
		return REDIRECT_LISTAR_SELECAO;
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
	@GetMapping("/agendamentoEntrevista/editar/{selecao}/{agendamento}")
	public String editarAgendamentoEntrevistaForm(@PathVariable Selecao selecao, Model model,RedirectAttributes redirect, @PathVariable AgendamentoEntrevista agendamento) {
		model.addAttribute("agendamento", agendamento);
		model.addAttribute("selecao", selecao);
		model.addAttribute("horario", HorarioEntrevista.values());
		model.addAttribute("turno", Turno.values());
		model.addAttribute("acao", "editar");
		return AGENDAR_ENTREVISTA;
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/agendamentoEntrevista/editar/{selecao}")
	public String editarAgendamentoEntrevista(@PathVariable Selecao selecao, AgendamentoEntrevista agendamento, Authentication auth, Model model, RedirectAttributes redirect) {
		agendamentoEntrevistaService.editar(agendamento);
		redirect.addFlashAttribute(INFO, MSG_SUCESSO_AGENDAMENTO_ENTRVISTA_EDICAO);
		return RedirectConstants.REDIRECT_AGENDAMENTO_ENTREVISTA + selecao.getId();
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping(value="/agendamentoEntrevista/excluir/{selecao}/{agendamento}")
	public String excluirAgendamentoEntrevista(@PathVariable Selecao selecao, @PathVariable AgendamentoEntrevista agendamento, Authentication auth, 
			RedirectAttributes redirect) throws AuxilioMoradiaException{
		agendamentoEntrevistaService.excluirHorarioAgendamentoEntrevista(agendamento);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_HORARIO_REMOVIDO);
		return RedirectConstants.REDIRECT_AGENDAMENTO_ENTREVISTA + selecao.getId();
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
		else {
			redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_NENHUM_ARQUIVO);
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
			if (selecaoService.adicionarMembroComissao(servidor, selecao))
				redirect.addFlashAttribute(INFO, MSG_SUCESSO_MEMBRO_ADICIONADO);
			else
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_MEMBRO_JA_ADICIONADO);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_DETALHES_SELECAO + selecao.getId();
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping(value="/alocacaoAgendamentoEntrevista/adicionar")
	public String alocacaoAgendamentoEntrevista(@RequestParam AgendamentoEntrevista agendamento, @RequestParam Inscricao inscricao,
			 RedirectAttributes redirect) throws AuxilioMoradiaException{
		if (agendamentoEntrevistaService.alocarAgendamentoEntrevista(agendamento, inscricao))
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_ALOCACAO_INSCRICAO_AGENDAMENTO_ADICIONADA);
		return RedirectConstants.REDIRECT_AGENDAMENTO_ENTREVISTA + agendamento.getInscricoes().get(0).getSelecao().getId();
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping(value="/agendamentoEntrevista/adicionar/{selecao}")
	public String adicionarAgendamentoEntrevista(@PathVariable Selecao selecao, AgendamentoEntrevista agendamento, Authentication auth, 
			RedirectAttributes redirect){
		try {
			
			if(agendamentoEntrevistaService.adicionarHorarioAgendamentoEntrevista(agendamento))
				redirect.addFlashAttribute(INFO, MSG_SUCESSO_AGENDAMENTO_ENTREVISTA);
			else
				redirect.addFlashAttribute(ERRO, "Erro ao inserir agendamento de entrevista");
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_AGENDAMENTO_ENTREVISTA + selecao.getId();
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
	@GetMapping("/agendamentoEntrevista/{agendamento}/excluir/{inscricao}")
	public String excluirInscricaoAgendamento(@PathVariable AgendamentoEntrevista agendamento,
			@PathVariable Inscricao inscricao, RedirectAttributes redirect) throws AuxilioMoradiaException {
		int selecaoId = agendamento.getInscricoes().get(0).getSelecao().getId();
		agendamentoEntrevistaService.removerInscricaoAgendamento(inscricao, agendamento);
		redirect.addFlashAttribute(INFO, MSG_SUCESSO_ALOCACAO_INSCRICAO_AGENDAMENTO_REMOVIDA);
		return RedirectConstants.REDIRECT_AGENDAMENTO_ENTREVISTA + selecaoId;
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping(value = "/documentacao/adicionar")
	public String adicionarTipoDocumento(@RequestParam Selecao selecao, @RequestParam String nome, @RequestParam String descricao,
		 RedirectAttributes redirect) {
		try {
			TipoDocumento tipoDocumento = new TipoDocumento(nome, descricao);
			if (selecaoService.adicionarTipoDocumento(selecao, tipoDocumento))
				redirect.addFlashAttribute(INFO, MSG_SUCESSO_TIPO_DOCUMENTO_ADICIONADO);
			else
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_DOCUMENTACAO_JA_ADICIONADA);
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

		List<Inscricao> inscricoes = inscricaoService.getAllOrdenado(selecao);
		Collections.sort(inscricoes);

		model.addAttribute("inscricoes", inscricoes);
		model.addAttribute("selecao", selecao);
		return VISUALIZAR_INSCRICOES;
	}
	
	@PreAuthorize(PERMISSAO_SERVIDOR)
	@GetMapping("/inscricoes/relatorio/{selecao}")
	public String listarInscricoesGeral(@PathVariable Selecao selecao, Authentication auth, Model model) {
		model.addAttribute("selecao", selecao);
		return LISTAR_INSCRICOES;
	}

	@ModelAttribute("servidores")
	public List<Servidor> getAllServidores() {
		return servidorService.getAll();
	}
	
	@GetMapping("/inscricoes/agendarEntrevista/{selecao}")
	public String agendarEntrevista(@PathVariable Selecao selecao, Authentication auth, Model model){
		List<Inscricao> inscricoes = inscricaoService.inscricoesParaEntrevista(selecao);
		AgendamentoEntrevista ae = new AgendamentoEntrevista();
		List<AgendamentoEntrevista> agendamentos = agendamentoEntrevistaService.findAll(selecao);
		List<AgendamentoEntrevista> datas = agendamentoEntrevistaService.findAllDatas(selecao);
		
		model.addAttribute("inscricoes", inscricoes);
		model.addAttribute("agendamentos", agendamentos);
		model.addAttribute("selecao", selecao);
		model.addAttribute("agendamento", ae);
		model.addAttribute("horario", HorarioEntrevista.values());
		model.addAttribute("turno", Turno.values());
		model.addAttribute("datas", datas);
		return AGENDAR_ENTREVISTA;
	}
}
