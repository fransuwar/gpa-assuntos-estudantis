package br.com.ufc.quixada.npi.gpa.model;

import java.util.List;

import javax.persistence.Entity;
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
	
	private String  cargo;
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public enum Cargo{
		PROFESSOR ("Professor"), TECADMINISTRATIVO("Tec Administrativo");
		
		private String cargo;

		Cargo(String cargo){
			this.cargo = cargo;
		}
		
		public String getCargo(){
			return cargo;
		}
		
		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
	}
	
	
	
	@ManyToMany
	private List<SelecaoBolsa> servidoresBanca;
	
	@OneToMany(mappedBy="servidor")
	private List<SelecaoBolsa> selecoes;
	
	

	@ManyToOne
	private Pessoa pessoa;
	
	public Pessoa getUsuario() {
		return pessoa;
	}

	public void setUsuario(Pessoa pessoa) {
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
	
	
	public List<SelecaoBolsa> getSelecoes() {
		return selecoes;
	}

	public void setSelecoes(List<SelecaoBolsa> selecoes) {
		this.selecoes = selecoes;
	}
	public List<SelecaoBolsa> getServidoresBanca() {
		return servidoresBanca;
	}

	public void setServidoresBanca(List<SelecaoBolsa> servidoresBanca) {
		this.servidoresBanca = servidoresBanca;
	}

	@Override
	public String toString() {
		return "Servidor [id=" + id + ", siape=" + siape + ", "
				+ " servidoresBanca=" + servidoresBanca + ", selecoes="
				+ selecoes + ", pessoa=" + pessoa + "]";
	}
		
}
