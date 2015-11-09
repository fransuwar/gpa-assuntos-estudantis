
package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomeOriginal;
	
	private String nome;
	
	private String tipo;
	
	@Type(type="org.hibernate.type.BinaryType") 
	private byte[] arquivo;

	@ManyToOne
	@JoinColumn(name = "selecaoBolsa_id")
	private SelecaoBolsa selecaoBolsa;
	
	

	public Documento(){
		super();
	}	
	
	public Documento(String nomeOriginal, String nome, String tipo, byte[] arquivo, SelecaoBolsa selecaoBolsa){
		super();
		this.nomeOriginal = nomeOriginal;
		this.nome = nome;
		this.tipo = tipo;
		this.arquivo = arquivo;
		this.selecaoBolsa = selecaoBolsa;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	
	public SelecaoBolsa getSelecaoBolsa() {
		return selecaoBolsa;
	}

	public void setSelecaoBolsa(SelecaoBolsa selecaoBolsa) {
		this.selecaoBolsa = selecaoBolsa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	

	
}