package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Entrevista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String observacao;
<<<<<<< HEAD

	@Column(nullable = false)
	private boolean deferimento;
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

	public boolean isDeferimento() {
		return deferimento;
	}

	public void setDeferimento(boolean deferimento) {
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
=======
	
	@Column(nullable = false)
	private boolean deferimento;
	@OneToOne
	private Inscricao inscricao;
	
	@ManyToOne
	private Servidor servidor;
	
	public Entrevista(){
		
	}

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

	public boolean isDeferimento() {
		return deferimento;
	}

	public void setDeferimento(boolean deferimento) {
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
		result = prime * result + (deferimento ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inscricao == null) ? 0 : inscricao.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((servidor == null) ? 0 : servidor.hashCode());
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
		if (deferimento != other.deferimento)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inscricao == null) {
			if (other.inscricao != null)
				return false;
		} else if (!inscricao.equals(other.inscricao))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrevista [id=" + id + ", observacao=" + observacao + ", deferimento=" + deferimento + ", inscricao="
				+ inscricao + ", servidor=" + servidor + "]";
	}
	
	

>>>>>>> branch '2572-AdaptarModelo' of https://github.com/npi-ufc-qxd/gpa-assuntos-estudantis.git
}
