package br.ufc.quixada.npi.model;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
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
	private Long id;
	
	
	@Min(value = 1, message = "Número de bolsas deve ser maior que 1")
	private int quantidadeVagas;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String datadeInicio;
	
	private String identificador;
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	private Integer sequencial;
	
	
		public enum TipodeBolsa {
		iniciacao_academica("Iniciação Acadêmica"), auxilio_moradia("Auxilio Moradia");
		private String tipo;
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		TipodeBolsa(String tipo){
			this.tipo = tipo;
		}
	}
		@Enumerated(EnumType.STRING)
		private TipodeBolsa tipodeBolsa;
		
		
		
		
		public TipodeBolsa getTipodeBolsa() {
			return tipodeBolsa;
		}
		public void setTipodeBolsa(TipodeBolsa tipodeBolsa) {
			this.tipodeBolsa = tipodeBolsa;
		}
		public enum Status {
		NOVO, SUBMETIDO
	}
	@Enumerated(EnumType.STRING)
	private Status status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String datadeTermino;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer duracao;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ano;
	
	@Lob	
	@Size(min = 100, message = "Mínimo 100 caracteres")
	private String comentarios;
	
	private String edital;	
		
	@ManyToMany(mappedBy="servidoresBanca")
	private List<Servidor> servidores;
	
	@ManyToOne
	private Servidor servidor;

	
	@ManyToMany
	private List<Aluno> alunosSelecao;
	
	@ManyToOne
	private Bolsa bolsa;
	
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Bolsa getBolsa() {
		return bolsa;
	}
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	
	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}
	public void setQuantidadeVagas(int QuantidadeVagas) {
		this.quantidadeVagas = QuantidadeVagas;
	}
	public String getDatadeInicio() {
		return datadeInicio;
	}
	public void setDatadeInicio(String datadeInicio) {
		this.datadeInicio = datadeInicio;
	}
	public String getEdital() {
		return edital;
	}
	public void setEdital(String Edital) {
		this.edital = Edital;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getDatadeTermino() {
		return datadeTermino;
	}
	public void setDatadeTermino(String datadeTermino) {
		this.datadeTermino = datadeTermino;
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
		
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
