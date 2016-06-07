
package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nomeOriginal;
	
	private String nome;
	
	private String extensao;
	
	@OneToOne
	private TipoDocumento tipo;
	
	@Type(type="org.hibernate.type.BinaryType") 
	private byte[] arquivo;

	public Documento(){
		super();
	}	
	
	public Documento(String nomeOriginal, String nome, String extensao, byte[] arquivo, Selecao selecao, TipoDocumento tipo){
		super();
		this.nomeOriginal = nomeOriginal;
		this.nome = nome;
		this.extensao = extensao;
		this.arquivo = arquivo;
		this.tipo = tipo;
//		this.selecao = selecao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	
//	public Selecao getSelecao() {
//		return selecao;
//	}
//
//	public void setSelecao(Selecao selecao) {
//		this.selecao = selecao;
//	}

	public TipoDocumento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
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