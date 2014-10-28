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
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

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

	
	public enum Cargo {
		PROFESSOR("Professor"), TECADMINISTRATIVO("Tec Administrativo");

		private String cargo;

		Cargo(String cargo) {
			this.cargo = cargo;
		}

		public String getCargo() {
			return cargo;
		}

		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
	}
	
	@Override
	public String toString() {
		return "Servidor [id=" + id + ", siape=" + siape + ", usuario="
				 + "]";
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
		
}