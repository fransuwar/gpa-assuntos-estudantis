package br.ufc.quixada.npi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Servidor {

	
	public Servidor() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String siape;
	
	@ManyToMany
	private List<Selecao> participaBancas;
	
	@OneToMany(mappedBy="responsavel")
	private List<Selecao> responsavelBancas;
	
	@ManyToOne
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public List<Selecao> getParticipaBancas() {
		return participaBancas;
	}

	public void setParticipaBancas(List<Selecao> participaBancas) {
		this.participaBancas = participaBancas;
	}

	public List<Selecao> getResponsavelBancas() {
		return responsavelBancas;
	}

	public void setResponsavelBancas(List<Selecao> responsavelBancas) {
		this.responsavelBancas = responsavelBancas;
	}
	
	@Override
	public String toString() {
		return "Servidor [id=" + id + ", siape=" + siape + ", participaBancas="
				+ participaBancas + ", responsavelBancas=" + responsavelBancas + ", usuario="
				+ usuario + "]";
	}
		
}
