package br.ufc.npi.auxilio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.npi.auxilio.enums.Resultado;

@Entity
public class VisitaDomiciliar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date data;

	@ManyToOne
	private Servidor responsavel;

	private String parecer;

	@OneToOne
	private Documento formulario;

	@Enumerated(EnumType.STRING)
	private Resultado resultado;

	@OneToMany
	private List<Documento> imagens;

	public Documento getFormulario() {
		return formulario;
	}

	public void setFormulario(Documento formulario) {
		this.formulario = formulario;
	}

	public List<Documento> getImagens() {
		if(imagens == null){
			imagens = new ArrayList<>();
		}
		return imagens;
	}

	public void setImagens(List<Documento> imagens) {
		this.imagens = imagens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Servidor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setDeferimento(Resultado resultado) {
		this.resultado = resultado;
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
		VisitaDomiciliar other = (VisitaDomiciliar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}





}