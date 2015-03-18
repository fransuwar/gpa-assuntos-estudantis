package br.com.ufc.quixada.npi.gpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.ufc.quixada.npi.enums.Cargo;

@NamedQueries({ @NamedQuery(name = "Servidor.findServidorBySiape", 
								query = "SELECT s FROM Servidor s WHERE s.siape = :siape") })

@Entity
public class Servidor {

	
	public Servidor() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	private String siape;
	
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	@ManyToMany(mappedBy = "membrosBanca")
	private List<SelecaoBolsa> participaBancas;
	
	@OneToMany(mappedBy="responsavel")
	private List<SelecaoBolsa> responsavelBancas;
	
	@ManyToOne
	private Pessoa pessoa;
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		return "Servidor [id=" + id + ", siape=" + siape + ", participaBancas="
				+ participaBancas + ", responsavelBancas=" + responsavelBancas + ", pessoa="
				+ pessoa + "]";
	}

	
		
}
