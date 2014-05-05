package br.quixada.ufc.npi.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class PessoaFamilia {

	@ManyToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;
}
