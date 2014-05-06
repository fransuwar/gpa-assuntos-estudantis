package br.quixada.ufc.npi.model;

<<<<<<< HEAD
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
>>>>>>> branch 'master' of https://github.com/npi-ufc-qxd/gpa-mae.git
public class PessoaFamilia {

<<<<<<< HEAD
=======
	@ManyToOne
	private QuestionarioAuxilioMoradia auxilioMoradia;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
>>>>>>> branch 'master' of https://github.com/npi-ufc-qxd/gpa-mae.git
}
