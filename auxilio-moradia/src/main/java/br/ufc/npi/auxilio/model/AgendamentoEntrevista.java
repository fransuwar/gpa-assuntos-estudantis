package br.ufc.npi.auxilio.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.npi.auxilio.enums.HorarioEntrevista;
import br.ufc.npi.auxilio.enums.Turno;

@Entity
public class AgendamentoEntrevista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	@Enumerated(EnumType.STRING)
	private HorarioEntrevista horario;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@OneToMany
	private List<Inscricao> inscricoes;
	
	@ManyToOne
	private Selecao selecao;
	
	public Selecao getSelecao() {
		return selecao;
	}

	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
	}

	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public HorarioEntrevista getHorario() {
		return horario;
	}

	public void setHorario(HorarioEntrevista horario) {
		this.horario = horario;
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

	public Boolean alocaAgendamentoEntrevista(Inscricao inscricao) {
		if(this.inscricoes == null){
			inscricoes = new ArrayList<Inscricao>();
		}
		if (inscricao != null && !this.inscricoes.contains(inscricao)) {
			this.inscricoes.add(inscricao);
			
			return true;
		}
		return false;
	}

	public void removeInscricaoAgendamento(Inscricao inscricao) {
		if(this.inscricoes == null){
			inscricoes = new ArrayList<Inscricao>();
		}
		this.inscricoes.remove(inscricao);
	}

	@Override
	public String toString() {
		return "AgendamentoEntrevista [id=" + id + ", turno=" + turno + ", horario=" + horario + ", data=" + data
				+ ", inscricoes=" + inscricoes + ", selecao=" + selecao + "]";
	}
	
	


	
	
	
	
}
