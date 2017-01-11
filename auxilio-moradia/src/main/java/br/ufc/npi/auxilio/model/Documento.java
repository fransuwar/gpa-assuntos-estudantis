package br.ufc.npi.auxilio.model;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String caminho;
	
	@Transient
	private byte[] arquivo;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	public Documento(){
		super();
	}	
	
	public Documento(String nome, String caminho, byte[] arquivo){
		super();
		this.nome = nome;
		this.caminho = caminho;
		this.arquivo = arquivo;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public enum Tipo {
		PDF("pdf"), JPG("jpg"), JPEG("jpeg"), PNG("png");

		private String nome;

		private Tipo(String nome){
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;

		return (id == other.id);
	}
	

	
}