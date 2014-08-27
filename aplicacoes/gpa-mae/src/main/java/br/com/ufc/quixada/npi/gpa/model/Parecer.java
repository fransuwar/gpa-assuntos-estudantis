package br.com.ufc.quixada.npi.gpa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Parecer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private StatusParecer status;
	
	@Lob
	private String comentario;
	
	private String parecer;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dataAtribuicao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dataRealizacao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date prazo;
	
	@ManyToOne
	@JoinColumn(name="documento_id")
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "selecaoBolsa_id")
	private SelecaoBolsa selecaoBolsa;

	public Parecer(){
		super();
	}
	
	public Parecer(Long id, StatusParecer status,
			String comentario, String parecer, Date dataAtribuicao,
			Date dataRealizacao, Date prazo, Pessoa pessoa, SelecaoBolsa selecaoBolsa) {
		super();
		this.id = id;
		this.status = status;
		this.comentario = comentario;
		this.parecer = parecer;
		this.dataAtribuicao = dataAtribuicao;
		this.dataRealizacao = dataRealizacao;
		this.prazo = prazo;
		this.pessoa = pessoa;
		this.selecaoBolsa = selecaoBolsa;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusParecer getStatus() {
		return status;
	}

	public void setStatus(StatusParecer status) {
		this.status = status;
	}

	public String getComentario() {
		return comentario;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Date getDataAtribuicao() {
		return dataAtribuicao;
	}

	public void setDataAtribuicao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public SelecaoBolsa getSelecaoBolsa() {
		return selecaoBolsa;
	}

	public void setSelecaoBolsa(SelecaoBolsa selecaoBolsa) {
		this.selecaoBolsa = selecaoBolsa;
	}


	@Override
	public String toString() {
		return "Parecer [id=" + id + ", statusParecer=" + status
				+ ", comentarioDiretor=" + comentario + ", parecer="
				+ parecer + ", dataAtribuicao=" + dataAtribuicao
				+ ", dataRealizacao=" + dataRealizacao + ", prazo=" + prazo
				+ ", pessoa=" + pessoa + ", selecaoBolsa=" + selecaoBolsa + "]";
	}

	public enum StatusParecer {
		FAVORAVEL , NAO_FAVORAVEL;
	}
}
