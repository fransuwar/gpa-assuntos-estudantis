package br.ufc.quixada.npi.gpa.model;

import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Imagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] img;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImg() {
		return Base64.getEncoder().encodeToString(img);
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Imagem){
			return ((Imagem) obj).id == this.id;
		}
		return super.equals(obj);
	}
	

}
