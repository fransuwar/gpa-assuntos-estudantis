package br.ufc.quixada.npi.gpa.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufc.quixada.npi.gpa.enums.Resultado;

@Entity
public class AnaliseDocumentacao {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Resultado deferimento;
	
	private String observacao;
	
	@OneToOne
	private Inscricao inscricao;

	@ManyToOne
	private Servidor servidor;
	
	@ManyToMany
	private Map<Integer, DocumentosTipoInscricao> documentosTipoInscricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Resultado getDeferimento() {
		return deferimento;
	}

	public void setDeferimento(Resultado deferimento) {
		this.deferimento = deferimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

	public Map<Integer, DocumentosTipoInscricao> getDocumentosTipoInscricao() {
		if(documentosTipoInscricao == null){
			documentosTipoInscricao = new HashMap<>();
		}
		return documentosTipoInscricao;
	}

	public void setDocumentosTipoInscricao(Map<Integer, DocumentosTipoInscricao> documentosTipoInscricao) {
		this.documentosTipoInscricao = documentosTipoInscricao;
	}

}
