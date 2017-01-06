package br.ufc.npi.auxilio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufc.npi.auxilio.enums.Resultado;

@Entity
public class AnaliseDocumentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	private String parecer;
	
	@OneToOne
	private Inscricao inscricao;

	@ManyToOne
	private Servidor responsavel;
	
	@ManyToMany(mappedBy = "analiseDocumentacao", cascade = CascadeType.REMOVE)
	private List<Documentacao> documentacao;
	
	public AnaliseDocumentacao() {
		this.documentacao = new ArrayList<Documentacao>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public Servidor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
	}

	public List<Documentacao> getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(List<Documentacao> documentacao) {
		this.documentacao = documentacao;
	}

	

}
