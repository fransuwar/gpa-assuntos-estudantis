package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.ufc.quixada.npi.gpa.enums.DiaUtil;
import br.ufc.quixada.npi.gpa.enums.Turno;

@Entity
public class HorarioDisponivel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica;
	
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	@Enumerated(EnumType.STRING)
	private DiaUtil dia;
	
	public HorarioDisponivel(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuestionarioIniciacaoAcademica getQuestionarioIniciacaoAcademica() {
		return questionarioIniciacaoAcademica;
	}

	public void setQuestionarioIniciacaoAcademica(
			QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica) {
		this.questionarioIniciacaoAcademica = questionarioIniciacaoAcademica;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public DiaUtil getDia() {
		return dia;
	}

	public void setDia(DiaUtil dia) {
		this.dia = dia;
	}


	
	

}
