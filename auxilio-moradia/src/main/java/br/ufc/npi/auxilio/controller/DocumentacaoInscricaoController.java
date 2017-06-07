package br.ufc.npi.auxilio.controller;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.repository.AnaliseDocumentacaoRepository;
import br.ufc.npi.auxilio.repository.DocumentacaoRepository;
import br.ufc.npi.auxilio.repository.DocumentoRepository;
import br.ufc.npi.auxilio.repository.InscricaoRepository;
import br.ufc.npi.auxilio.service.DocumentacaoService;
import br.ufc.npi.auxilio.service.PessoaService;
import br.ufc.npi.auxilio.utils.ErrorMessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import br.ufc.npi.auxilio.utils.SuccessMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

@Controller
@RequestMapping("/documentacao")
public class DocumentacaoInscricaoController {
	
	@Autowired
	private DocumentacaoService documentacaoService;
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
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
			RedirectAttributes redirect) {
		
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			Documentacao documentacao = documentacaoRepository.findByTipoDocumento(tipoDocumento, inscricao.getAnaliseDocumentacao());
			if( documentacao == null )
				documentacao = new Documentacao();
			
			for (MultipartFile mfiles : files) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {

						Documento documento = new Documento();
						documento.setNome(mfiles.getOriginalFilename());
						documento.setCaminho(mfiles.getContentType());

						if(inscricao.getAnaliseDocumentacao() == null)
							inscricao.setAnaliseDocumentacao(new AnaliseDocumentacao());
						documentoRepository.save(documento);
						documentacao.getDocumentos().add(documento);
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
				analiseDocumentacaoRepository.save(analiseDocumento);
			}
			
			analiseDocumento.setInscricao(inscricao);
			documentacao.setAnaliseDocumentacao(analiseDocumento);
			documentacaoRepository.save(documentacao);
			
			inscricao.setAnaliseDocumentacao(analiseDocumento);
			inscricaoRepository.save(inscricao);
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
		documentacaoRepository.save(documentacao);
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
		model.addAttribute("resultado", Resultado.values());
		model.addAttribute("inscricao", inscricao);
		if (inscricao.getAnaliseDocumentacao() == null) {
			AnaliseDocumentacao aD = new AnaliseDocumentacao();
			inscricao.setAnaliseDocumentacao(aD);
			inscricaoRepository.save(inscricao);
		}
		model.addAttribute("analiseDocumentacao", inscricao.getAnaliseDocumentacao());
		return "inscricao/analisar-documento";
	}
	
	@Secured(COORDENADOR)
	@PostMapping("/inscricao/{idInscricao}/salvar")
	public String salvarAnaliseDocumentacao(@PathVariable("idInscricao") Inscricao inscricao, 
			AnaliseDocumentacao analiseDocumentacao){
//		System.out.println(analiseDocumentacao.getCidadeOrigem());
//		System.out.println(analiseDocumentacao.getCidade());
//		System.out.println(analiseDocumentacao.getRendaPai());
//		System.out.println(analiseDocumentacao.getRendaMae());
//		System.out.println(analiseDocumentacao.getRendaOutros());
//		System.out.println(analiseDocumentacao.getRendaPerCapita());
//		System.out.println(analiseDocumentacao.getGrupoFamiliar());
//		System.out.println(analiseDocumentacao.getBeneficio());
//		System.out.println(analiseDocumentacao.getEnergia());
//		System.out.println(analiseDocumentacao.getParecer());
//		System.out.println(analiseDocumentacao.getObservacao());
		analiseDocumentacao.setRendaPerCapita(((analiseDocumentacao.getRendaPai()==null?0:analiseDocumentacao.getRendaPai())+
				(analiseDocumentacao.getRendaMae() == null? 0:analiseDocumentacao.getRendaMae())+
				(analiseDocumentacao.getRendaOutros()==null? 0:analiseDocumentacao.getRendaOutros()))/
				(analiseDocumentacao.getGrupoFamiliar()==null? 1:analiseDocumentacao.getGrupoFamiliar()));
		
		inscricao.setAnaliseDocumentacao(analiseDocumentacao);
		inscricaoRepository.save(inscricao);
		return "redirect:/documentacao/inscricao/"+inscricao.getId();
	}
	
}
