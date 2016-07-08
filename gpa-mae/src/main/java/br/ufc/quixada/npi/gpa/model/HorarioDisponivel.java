package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.ufc.quixada.npi.gpa.enums.DiaUtil;
import br.ufc.quixada.npi.gpa.enums.Turno;

@NamedQueries({
		@NamedQuery(name = "HorarioDisponivel.findHorarioDisponivelByIdIniciacaoAcademica", query = "Select hd from HorarioDisponivel hd where hd.questionarioIniciacaoAcademica.id = :idIniciacaoAcademica ") })

@Entity
public class HorarioDisponivel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Turno turno;
	@Enumerated(EnumType.STRING)
	private DiaUtil dia;
	@ManyToOne
	private QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica;

	public QuestionarioIniciacaoAcademica getQuestionarioIniciacaoAcademica() {
		return questionarioIniciacaoAcademica;
	}

	public void setQuestionarioIniciacaoAcademica(QuestionarioIniciacaoAcademica questionarioIniciacaoAcademica) {
		this.questionarioIniciacaoAcademica = questionarioIniciacaoAcademica;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorarioDisponivel other = (HorarioDisponivel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HorarioDisponivel [id=" + id + "]";
	}
	
}
