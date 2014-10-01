package br.com.ufc.quixada.npi.gpa.model;

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
	private List<SelecaoBolsa> participaBancas;
	
	@OneToMany(mappedBy="responsavel")
	private List<SelecaoBolsa> responsavelBancas;
	
	@ManyToOne
	private Pessoa usuario;
	
	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
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

	public List<SelecaoBolsa> getParticipaBancas() {
		return participaBancas;
	}

	public void setParticipaBancas(List<SelecaoBolsa> participaBancas) {
		this.participaBancas = participaBancas;
	}

	public List<SelecaoBolsa> getResponsavelBancas() {
		return responsavelBancas;
	}

	public void setResponsavelBancas(List<SelecaoBolsa> responsavelBancas) {
		this.responsavelBancas = responsavelBancas;
	}
	
	@Override
	public String toString() {
		return "Servidor [id=" + id + ", siape=" + siape + ", participaBancas="
				+ participaBancas + ", responsavelBancas=" + responsavelBancas + ", usuario="
				+ usuario + "]";
	}
		
}
