package br.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.TipoBolsa;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.SelecaoBolsaService;
import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Named
@RequestMapping("selecao")
@SessionAttributes({ Constants.USUARIO_ID })
public class SelecaoBolsaController {

	@Inject
	private ServidorService servidorService;

	@Inject
	private SelecaoBolsaService selecaoService;

	@Inject
	private AlunoService alunoService;

	@Inject
	private DocumentoService documentoService;

	@RequestMapping(value = "{id}/informacoes")
	public String getInformacoes(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		SelecaoBolsa selecao = selecaoService.getSelecaoBolsaComDocumentos(id);
		if (selecao == null) {
			redirectAttributes.addFlashAttribute("erro", "seleção Inexistente");
			return "redirect:/selecao/listar";
		}
		model.addAttribute("selecao", selecao);

		return "selecao/informacoes";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarSelecaoBolsa(
			@Valid @ModelAttribute(value = "selecao") SelecaoBolsa selecaoBolsa,
			BindingResult result,
			@RequestParam("files") List<MultipartFile> files, Model model,
			RedirectAttributes redirect, HttpServletRequest request)
			throws IOException {

		if (selecaoBolsa.getId() != null) {

			model.addAttribute("action", "editar");

			if (result.hasErrors()) {
				model.addAttribute("action", "editar");
				return "selecao/cadastrar";
			}

			if (selecaoBolsa.getAno() < DateTime.now().getYear()) {
				model.addAttribute("dataError",
						"Digite um ano maior ou igual ao atual");
				return ("selecao/cadastrar");
			}

			String doc[] = request.getParameterValues("doc");

			if (doc != null) {
				for (int k = 0; k < doc.length; k++) {
					Documento d = new Documento();
					d.setId(Long.parseLong(doc[k]));
					documentoService.delete(d);
				}
			}

			this.selecaoService.update(selecaoBolsa);
			redirect.addFlashAttribute("info",
					"Seleção atualizada com sucesso.");
			return "redirect:/selecao/listar";

		} else {

			model.addAttribute("action", "cadastrar");

			return adicionarSelecao(selecaoBolsa, result, files, redirect,
					model);
		}
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("tipoBolsa", TipoBolsa.toMap());
		model.addAttribute("action", "cadastrar");
		model.addAttribute("selecao", new SelecaoBolsa());
		return "/selecao/cadastrar";
	}

	public String adicionarSelecao(
			@Valid @ModelAttribute("selecao") SelecaoBolsa selecao,
			BindingResult result,
			@RequestParam("files") List<MultipartFile> files,
			RedirectAttributes redirect, Model model) {

		if (selecao == null || selecao.getAno() == null
				|| selecao.getAno() < DateTime.now().getYear()) {
			result.rejectValue("ano", "selecao.ano",
					"Digite um ano maior ou igual ao atual");
		}

		if (result.hasErrors()) {
			model.addAttribute("tipoBolsa", TipoBolsa.toMap());
			return ("selecao/cadastrar");
		}

		List<Documento> documentos = new ArrayList<Documento>();
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) {
			for (MultipartFile mfiles : files) {
				try {
					if (mfiles.getBytes() != null
							&& mfiles.getBytes().length != 0) {
						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						documento.setSelecaoBolsa(selecao);
						documentos.add(documento);
					}
				} catch (IOException ioe) {
					model.addAttribute("erro",
							"Não foi possivel salvar os documentos.");
					return "selecao/cadastrar";
				}
			}
			if (!documentos.isEmpty()) {
				selecao.setDocumentos(documentos);
			}
		} else {
			model.addAttribute("tipoBolsa", TipoBolsa.toMap());
			model.addAttribute("anexoError", "Adicione anexo a seleção.");
			return "selecao/cadastrar";
		}

		if (selecaoService.existsSelecaoEquals(selecao)) {
			redirect.addFlashAttribute("erro",
					"Número do edital ou tipo de bolsa já existente");
			return "redirect:/selecao/listar";
		}

		this.selecaoService.save(selecao);
		redirect.addFlashAttribute("info", "Seleção realizada com Sucesso.");
		return "redirect:/selecao/listar";
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id,
			RedirectAttributes redirect, Model model) {
		SelecaoBolsa selecao = selecaoService.getSelecaoBolsaComDocumentos(id);

		if (selecao.getStatus() != null
				&& selecao.getStatus().equals(Status.NOVA)) {

			model.addAttribute("tipoBolsa", TipoBolsa.toMap());
			model.addAttribute("selecao", selecao);
			model.addAttribute("action", "editar");

		} else {
			redirect.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/selecao/listar";
		}
		return "selecao/cadastrar";
	}

	@RequestMapping(value = "/{id}/excluir")
	public String excluirSelecao(SelecaoBolsa p,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {
		SelecaoBolsa selecao = selecaoService.find(SelecaoBolsa.class, id);
		if (selecao == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Seleção inexistente.");
			return "redirect:/selecao/listar";
		}
		if (selecao.getStatus().equals(Status.NOVA)) {
			this.selecaoService.delete(selecao);
			redirectAttributes.addFlashAttribute("info",
					"Seleção excluída com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		}
		return "redirect:/selecao/listar";
	}

	@RequestMapping(value = "/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		List<SelecaoBolsa> selecoes = this.selecaoService.getSelecaoBolsaComMembros();

		if (request.isUserInRole("ROLE_ALUNO")) {

			Aluno aluno = this.alunoService.getAlunoComSelecoes(1);
			model.addAttribute("selecoes", selecoes);
			model.addAttribute("aluno", aluno);
			model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
			model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);

		} else {

			model.addAttribute("selecoes", selecoes);
			model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
			model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);
		}

		return "selecao/listar";
	}

	@RequestMapping(value = "/{id}/inscritos", method = RequestMethod.GET)
	public String listarInscritos(@PathVariable("id") Integer id, ModelMap model) {

		List<Aluno> alunosSelecao = this.selecaoService
				.getSelecaoBolsaComAlunos(id).getAlunosSelecao();
		model.addAttribute("alunos", alunosSelecao);

		return "selecao/listarInscritos";
	}

	@RequestMapping(value = "/{id}/atribuir", method = RequestMethod.GET)
	public String atribuirParecerista(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		List<Servidor> servidoresBanca = selecaoService
				.getSelecaoBolsaComMembros(id).getMembrosBanca();

		if (!servidoresBanca.isEmpty()) {
			model.addAttribute("m1", servidoresBanca.get(0).getId());
			model.addAttribute("m2", servidoresBanca.get(1).getId());
			model.addAttribute("m3", servidoresBanca.get(2).getId());
		}
		model.addAttribute("selecao", id);
		model.addAttribute("servidores", servidorService.find(Servidor.class));
		return "selecao/atribuir";
	}

	@RequestMapping(value = "/atribuir", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(@RequestParam("id") Integer id,
			@RequestParam("id1") Integer id1, @RequestParam("id2") Integer id2,
			@RequestParam("id3") Integer id3, Model model,
			RedirectAttributes redirect) {

		if (id1 == null || id2 == null || id3 == null) {
			model.addAttribute("selecao", id);
			model.addAttribute("servidores",
					servidorService.find(Servidor.class));
			model.addAttribute("erroMembros", "Informe os três membros.");
			return "selecao/atribuir";

		} else if (id1.equals(id2) || id1.equals(id3) || id2.equals(id3)) {
			model.addAttribute("selecao", id);
			model.addAttribute("servidores",
					servidorService.find(Servidor.class));
			model.addAttribute("erroMembros",
					"Não é permitida repetição de membros na banca.");
			return "selecao/atribuir";
		} else {
			SelecaoBolsa selecao = selecaoService.find(SelecaoBolsa.class, id);

			List<Servidor> list = new ArrayList<Servidor>();
			list.add(new Servidor(id1));
			list.add(new Servidor(id2));
			list.add(new Servidor(id3));

			selecao.setMembrosBanca(list);

			selecaoService.update(selecao);
			redirect.addFlashAttribute("info", "Banca formada com sucesso.");

			return "redirect:/selecao/listar";
		}
	}

}
