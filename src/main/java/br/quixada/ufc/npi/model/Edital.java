package br.quixada.ufc.npi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Edital {

	public Edital(){
		
	}
	
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
}
