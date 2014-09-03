package br.ufc.quixada.npi.model;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Selecao {

	public Selecao(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Min(value = 1, message = "Número de bolsas deve ser maior que 1")
	private int QuantidadeVagas;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String DatadeInicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String DatadeTermino;
	
	@Size(min = 100, message = "Mínimo 100 caracteres")
	private String comentarios;
	
	private String Edital;	
		
	@ManyToMany(mappedBy="servidoresBanca")
	private List<Servidor> servidores;
	
	@ManyToOne
	private Servidor servidor;

	
	@ManyToMany
	private List<Aluno> alunosSelecao;
	
	@ManyToOne
	private Bolsa bolsa;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Bolsa getBolsa() {
		return bolsa;
	}
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	
	public int getQuantidadeVagas() {
		return QuantidadeVagas;
	}
	public void setQuantidadeVagas(int QuantidadeVagas) {
		this.QuantidadeVagas = QuantidadeVagas;
	}
	public String getDatadeInicio() {
		return DatadeInicio;
	}
	public void setDatadeInicio(String DatadeInicio) {
		this.DatadeInicio = DatadeInicio;
	}
	public String getEdital() {
		return Edital;
	}
	public void setEdital(String Edital) {
		this.Edital = Edital;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getDatadeTermino() {
		return DatadeTermino;
	}
	public void setDatadeTermino(String DatadeTermino) {
		this.DatadeTermino = DatadeTermino;
	}

	public List<Servidor> getServidores() {
		return servidores;
	}
	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}
	public Servidor getServidor() {
		return servidor;
	}
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public List<Aluno> getAlunosSelecao() {
		return alunosSelecao;
	}
	public void setAlunosSelecao(List<Aluno> alunosSelecao) {
		this.alunosSelecao = alunosSelecao;
	}
	
	
}
