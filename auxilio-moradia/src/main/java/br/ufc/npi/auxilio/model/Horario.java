package br.ufc.npi.auxilio.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ufc.npi.auxilio.enums.HorarioEntrevista;

public class Horario {
	
	
	@Enumerated(EnumType.STRING)
	private HorarioEntrevista horario;
	
	private String turno;
	
	private List<Aluno> alunos;
	
	private Date data;

	public HorarioEntrevista getHorario() {
		return horario;
	}

	public void setHorario(HorarioEntrevista horario) {
		this.horario = horario;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
