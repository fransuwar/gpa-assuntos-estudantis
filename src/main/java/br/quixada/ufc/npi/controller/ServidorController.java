package br.quixada.ufc.npi.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.quixada.ufc.npi.model.Servidor;
import br.quixada.ufc.npi.service.ServidorService;

@Named
@RequestMapping("/servidores")
public class ServidorController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private ServidorService ss;

	// Metodo listar servidores
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listaServidores(Servidor servidor, BindingResult result,
			Map<String, Object> model) {

		try {
			List<Servidor> results = ss.findAll();

			model.put("selections", results);
			return "servidor/servidoresList";
		} catch (Exception e) {
			// Mensagem com erro, falta corrigir
			return "servidor/servidoresList";
		}

	}

	// Metodo Deletar um servidor
	@RequestMapping(value = "/{servidorId}", method = RequestMethod.DELETE)
	public @ResponseBody
	String deletarservidor(@PathVariable("servidorId") int servidorId) {
		Servidor servidor = this.ss.findById(servidorId);

		if (servidor == null) {
			/* incluir erros */
			return "erro";
		} else {
			this.ss.delete(servidor);
			return "ok";
		}
	}

}
