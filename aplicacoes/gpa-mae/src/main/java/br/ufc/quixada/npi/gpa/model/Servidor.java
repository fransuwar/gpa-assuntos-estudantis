package br.ufc.quixada.npi.gpa.model;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.ufc.quixada.npi.gpa.enums.Cargo;

@NamedQueries({ @NamedQuery(name = "Servidor.findServidorBySiape", query = "SELECT s FROM Servidor s WHERE s.siape = :siape"), 
@NamedQuery(name = "Servidor.findServidorComBancas", query = "SELECT s FROM Servidor s LEFT JOIN FETCH s.participaBancas WHERE s.id = :servidorId"), 
@NamedQuery(name = "Servidor.findPessoaServidorComBancas", query = "SELECT s FROM Servidor s LEFT JOIN FETCH s.participaBancas WHERE s.pessoa.id = :pessoaId")})
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "siape" }))
public class Servidor {


	public Servidor() {
		super();
	}

	public Servidor(Integer id){
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Campo obrigatório")
	private String siape;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	@OneToMany(mappedBy = "servidor")
	private List<VisitaDomiciliar> visitas; 
	
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
		return "Servidor [id=" + id +"]";
	}


}
