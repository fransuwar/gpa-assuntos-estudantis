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
public class SelecaoBolsa {

	public SelecaoBolsa(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int ano;
	private int numero;
	private int vagas;
	private String periodoInscricao;
	private String caminhoArquivo;
	private String comentarios;
	private String periodoAlocacao;
	private boolean editalAberto;
	
	@ManyToMany(mappedBy="servidoresBanca")
	private List<Servidor> servidores;
	
	@ManyToOne
	private Servidor servidor;

	
	

	@ManyToMany
	private List<Aluno> alunosSelecao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	public String getPeriodoInscricao() {
		return periodoInscricao;
	}
	public void setPeriodoInscricao(String periodoInscricao) {
		this.periodoInscricao = periodoInscricao;
	}
	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}
	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}



	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getPeriodoAlocacao() {
		return periodoAlocacao;
	}
	public void setPeriodoAlocacao(String periodoAlocacao) {
		this.periodoAlocacao = periodoAlocacao;
	}
	public boolean isEditalAberto() {
		return editalAberto;
	}
	public void setEditalAberto(boolean editalAberto) {
		this.editalAberto = editalAberto;
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
	@Override
	public String toString() {
		return "SelecaoBolsa [id=" + id + ", ano=" + ano + ", numero=" + numero
				+ ", vagas=" + vagas + ", periodoInscricao=" + periodoInscricao
				+ ", caminhoArquivo=" + caminhoArquivo + ", comentarios="
				+ comentarios + ", periodoAlocacao=" + periodoAlocacao
				+ ", editalAberto=" + editalAberto + ", servidores="
				+ servidores + ", servidor=" + servidor + ", alunosSelecao=" + alunosSelecao + ", bolsa="
				+ "]";
	}
	
}
