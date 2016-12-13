package br.ufc.npi.auxilio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Documentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private TipoDocumento tipoDocumento;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Documento> documentos;
	
	@ManyToOne
	private AnaliseDocumentacao analiseDocumentacao;
	
	public Documentacao() {
		this.documentos = new ArrayList<Documento>();
	}
	
	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public AnaliseDocumentacao getAnaliseDocumentacao() {
		return analiseDocumentacao;
	}

	public void setAnaliseDocumentacao(AnaliseDocumentacao analiseDocumentacao) {
		this.analiseDocumentacao = analiseDocumentacao;
	}
	
}
