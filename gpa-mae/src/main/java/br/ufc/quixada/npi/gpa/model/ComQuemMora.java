package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.ufc.quixada.npi.gpa.enums.GrauParentesco;

@NamedQueries({
	@NamedQuery(name = "ComQuemMora.findComQuemMoraByDescricao", query = "SELECT cqm FROM ComQuemMora cqm WHERE cqm.descricao = :descricao")
	})

@Entity
public class ComQuemMora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private GrauParentesco descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GrauParentesco getDescricao() {
		return descricao;
	}

	public void setDescricao(GrauParentesco descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "ComQuemMora [id=" + id + ", descricao=" + descricao + "]";
	}

	
	
}
