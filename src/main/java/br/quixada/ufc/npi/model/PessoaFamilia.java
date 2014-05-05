package br.quixada.ufc.npi.model;

import javax.persistence.ManyToOne;

public class PessoaFamilia {

	@ManyToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;
}
