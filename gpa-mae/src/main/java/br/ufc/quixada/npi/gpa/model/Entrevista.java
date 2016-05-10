package br.ufc.quixada.npi.gpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.ufc.quixada.npi.gpa.enums.Resultado;

import com.itextpdf.text.Image;

@Entity
public class Entrevista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private Resultado deferimento;
	
	@OneToOne
	private Inscricao inscricao;

	@ManyToOne
	private Servidor servidor;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Resultado getDeferimento() {
		return deferimento;
	}

	public void setDeferimento(Resultado deferimento) {
		this.deferimento = deferimento;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Entrevista other = (Entrevista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrevista [id=" + id + ", observacao=" + observacao + ", deferimento=" + deferimento + ", inscricao="
				+ inscricao + ", servidor=" + servidor + "]";
	}

}
