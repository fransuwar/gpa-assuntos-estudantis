package br.ufc.quixada.npi.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import br.ufc.quixada.npi.model.Servidor;
import br.ufc.quixada.npi.service.GenericService;

@Named
@RequestMapping("/servidores")
public class ServidorController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private GenericService<Servidor> genericService;

	@RequestMapping(value = "{servidorId}", method = RequestMethod.GET)
	public @ResponseBody
	Servidor getServidorJson(@PathVariable("servidorId") Long servidorId) {

		return this.genericService.find(Servidor.class, servidorId);

	}

	// Metodo listar servidores
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listarServidores(Servidor servidor, BindingResult result,
			Map<String, Object> model) {

		try {
			List<Servidor> results = genericService.find(Servidor.class);

			model.put("selections", results);
			return "servidor/servidoresList";
		} catch (Exception e) {
			// Mensagem com erro, falta corrigir
			return "servidor/servidoresList";
		}

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody
	Servidor adicionarServidor(@RequestBody Servidor servidor,
			BindingResult result, SessionStatus status) {

		if (result.hasErrors()) {
			/* incluir erros */
			return servidor;
		} else {
			this.genericService.save(servidor);

			status.setComplete();
			return servidor;
		}
	}

	// Metodo atualizar um servidor
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public @ResponseBody
	Servidor atualizarServidor(@RequestBody Servidor servidor,
			BindingResult result, SessionStatus status) {

		if (result.hasErrors()) {
			return servidor;
		} else {
			this.genericService.update(servidor);
			return servidor;
		}

	}

	// Metodo Deletar um servidor
	@RequestMapping(value = "/{servidorId}", method = RequestMethod.DELETE)
	public @ResponseBody
	String deletarservidor(@PathVariable("servidorId") Long servidorId) {
		Servidor servidor = this.genericService
				.find(Servidor.class, servidorId);

		if (servidor == null) {
			/* incluir erros */
			return "erro";
		} else {
			this.genericService.delete(servidor);
			return "ok";
		}

	}

}
