package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"id"}))
public class Papel implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	public Papel() {
		super();
	}
	
	public Papel(int id, String descricao) {
		super();
		this.id = id;
		this.nome = descricao;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getAuthority() {
		return this.nome;
	}
}
