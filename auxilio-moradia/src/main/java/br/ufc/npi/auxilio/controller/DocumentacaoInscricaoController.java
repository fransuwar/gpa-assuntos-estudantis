package br.ufc.npi.auxilio.controller;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.repository.AnaliseDocumentacaoRepository;
import br.ufc.npi.auxilio.repository.DocumentacaoRepository;
import br.ufc.npi.auxilio.repository.DocumentoRepository;
import br.ufc.npi.auxilio.service.DocumentacaoService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.PessoaService;
import br.ufc.npi.auxilio.service.ServidorService;
import br.ufc.npi.auxilio.utils.ErrorMessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import br.ufc.npi.auxilio.utils.SuccessMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

import static br.ufc.npi.auxilio.utils.Constants.ALUNO;
import static br.ufc.npi.auxilio.utils.Constants.COORDENADOR;
import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_COORDENADOR;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_DOCUMENTO_ADICIONADO;

@Controller
@RequestMapping("/documentacao")
public class DocumentacaoInscricaoController {
	
	@Autowired
	private DocumentacaoService documentacaoService;
	
	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private ServidorService servidorService;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private DocumentacaoRepository documentacaoRepository;
	
	@Autowired
	private AnaliseDocumentacaoRepository analiseDocumentacaoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Secured(ALUNO)
	@GetMapping("/{idInscricao}")
	public String formDocumentacao( @PathVariable("idInscricao") Inscricao inscricao, Model model , Authentication auth) {
		if(!pessoaService.getByCpf(auth.getName()).equals(inscricao.getAluno().getPessoa())){
			return RedirectConstants.REDIRECT_LISTAR_SELECAO;
		}
		model.addAttribute("inscricao", inscricao);
		return PageConstants.ENVIAR_DOCUMENTACAO;
	}
	
	@Secured(ALUNO)
	@PostMapping("/{idInscricao}")
	public String adicionarDocumentacao( @RequestParam("files") List<MultipartFile> files,
			@PathVariable("idInscricao") Inscricao inscricao, 
			@RequestParam("tipoDocumento") TipoDocumento tipoDocumento,
			RedirectAttributes redirect) throws AuxilioMoradiaException {
		
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			Documentacao documentacao = documentacaoRepository.findByTipoDocumento(tipoDocumento, inscricao.getAnaliseDocumentacao());
			if( documentacao == null )
				documentacao = new Documentacao();
			
			for (MultipartFile mfiles : files) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						documentacaoService.adicionarDocumentos(inscricao, documentacao, mfiles);
						redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_ADICIONADO);
					}
				} catch (IOException e)	{
					redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
					return RedirectConstants.REDIRECT_INSCRICAO_DOCUMENTACAO + inscricao.getId();
				}
			}
			documentacao.setTipoDocumento(tipoDocumento);
			
			AnaliseDocumentacao analiseDocumento = inscricao.getAnaliseDocumentacao();
			if( analiseDocumento == null || analiseDocumento.getId() == null ) {
				analiseDocumento = new AnaliseDocumentacao();
				analiseDocumento.setResultado(Resultado.NAO_AVALIADO);
				analiseDocumentacaoRepository.save(analiseDocumento);
				
			}
			analiseDocumento.setInscricao(inscricao);
			analiseDocumento.setResultado(Resultado.NAO_AVALIADO);
			analiseDocumentacaoRepository.save(analiseDocumento);
			
			documentacao.setAnaliseDocumentacao(analiseDocumento);
			documentacaoService.salvar(documentacao);
			
			inscricao.setAnaliseDocumentacao(analiseDocumento);
			inscricaoService.salvar(inscricao);
		}
		
		else {
			redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_NENHUM_ARQUIVO);
			return RedirectConstants.REDIRECT_INSCRICAO_DOCUMENTACAO + inscricao.getId();
		}
		
		redirect.addFlashAttribute(INFO, SuccessMessageConstants.MSG_SUCESSO_DOCUMENTO_ADICIONADO);
		return RedirectConstants.REDIRECT_INSCRICAO_DOCUMENTACAO + inscricao.getId();
	}
	
	@Secured(ALUNO)
	@GetMapping("/inscricao/{idInscricao}/documentacao/{idDocumentacao}/excluir-documento/{idDocumento}")
	public String excluirDocumentoDaInscricao(@PathVariable("idInscricao") Inscricao inscricao,
			@PathVariable("idDocumentacao") Documentacao documentacao,
			@PathVariable("idDocumento") Documento documento,
			RedirectAttributes redirect) {
		documentacao.getDocumentos().remove(documento);
		documentacaoService.salvar(documentacao);
		documentoRepository.delete(documento);
		
		redirect.addFlashAttribute(INFO, SuccessMessageConstants.MSG_SUCESSO_DOCUMENTO_REMOVIDO);
		return RedirectConstants.REDIRECT_INSCRICAO_DOCUMENTACAO + inscricao.getId();
	}
	
	@ModelAttribute("tiposDeDocumento")
	public List<TipoDocumento> getTiposDeDocumento() {
		return documentacaoService.getAllTipoDocumento();
	}
	
	@Secured(COORDENADOR)
	@GetMapping("/inscricao/{idInscricao}")
	public String analisarDocumentacaoInscricao(@PathVariable("idInscricao") Inscricao inscricao, Model model){
		
		AnaliseDocumentacao analiseDocumentacao = inscricao.getAnaliseDocumentacao();
		if(analiseDocumentacao == null){
			model.addAttribute("analiseDocumentacao", new AnaliseDocumentacao());
		}else {
			model.addAttribute("analiseDocumentacao", analiseDocumentacao);
		}
		model.addAttribute("resultado", Resultado.values());
		model.addAttribute("inscricao", inscricao);
		return "inscricao/analisar-documento";
	}
	
	@Secured(COORDENADOR)
	@PostMapping("/analiseDocumentacao/{inscricao}")
	public String analisarDocumentacaoInscricao(@PathVariable Inscricao inscricao, AnaliseDocumentacao analiseDocumentacao, Authentication auth, RedirectAttributes redirectAttributes){
		
		Servidor servidor = servidorService.getByCpf(auth.getName());
		AnaliseDocumentacao analise = analiseDocumentacaoRepository.findById(analiseDocumentacao.getId());
		
		if(analise == null){
			analise = analiseDocumentacao;
			inscricao.setAnaliseDocumentacao(analise);
		}else{
			analise.setObservacoes(analiseDocumentacao.getObservacoes());
			analise.setResultado(analiseDocumentacao.getResultado());
			
		}
		analise.setResponsavel(servidor);
		analiseDocumentacaoRepository.save(analise);
		return RedirectConstants.REDIRECT_SELECAO_INSCRICOES+inscricao.getSelecao().getId();
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/documento/{inscricao}/download/{documento}")
	public HttpEntity<?> downloadDocumento(@PathVariable Inscricao inscricao, @PathVariable Documento documento, RedirectAttributes redirect) {
		try {
			if(inscricao != null && documento != null) {
				documento = documentacaoService.buscarDocumento(documento);
				return documentacaoService.downloadDocumento(documento, "attachment");
			}
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return null;
	}
	
	
}
