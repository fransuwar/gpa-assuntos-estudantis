package br.com.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SelecaoBolsa {

	public enum Status {
		NOVA, SUBMETIDO, INSCRICAO_ABERTA, PROCESSO_SELETIVO, FINALIZADA, CANCELADA
	}


	public enum TipoBolsa {
		INICIACAO_ACADEMICA("Iniciação Acadêmica"), AUXILIO_MORADIA(
				"Auxilio Moradia");
		private String tipo;

		TipoBolsa(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(mappedBy="selecaoBolsa")
	private List<QuestionarioIniciacaoAcademica> questionariosIniciacaoAcademica;
	
	@OneToMany(mappedBy="selecaoBolsa")
	private List<QuestionarioAuxilioMoradia> questionariosAuxilioMoradia;
		
	@Min(value = 1, message = "Número de bolsas deve ser maior que 0")
	private int quantidadeVagas;
		
	@Future
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	
	@ManyToOne
	private  Pessoa autor;
	
	@OneToMany(mappedBy = "selecaoBolsa", cascade = CascadeType.REMOVE)
	private List<Documento> documentos;
	
	@NotNull
	private Integer sequencial;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Future
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTermino;
	
	@NotNull
	private String duracao;

	private String local;

	@NotNull
	private Integer ano;
	
	@Lob
	@Size(min = 2, message = "Mínimo 2 caracteres")
	private String comentarios;
	
	private byte[] edital;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Servidor> membrosBanca;

	@ManyToOne
	private Servidor responsavel;

	@ManyToMany
	private List<Aluno> alunosSelecao;

	@Enumerated(EnumType.STRING)
	private TipoBolsa tipoBolsa;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelecaoBolsa other = (SelecaoBolsa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Aluno> getAlunosSelecao() {
		return alunosSelecao;
	}

	public Integer getAno() {
		return ano;
	}

	public Pessoa getAutor() {
		return autor;
	}

	public String getComentarios() {
		return comentarios;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	
	public Date getDataTermino() {
		return dataTermino;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public String getDuracao() {
		return duracao;
	}

	public Integer getId() {
		return id;
	}

	public String getLocal() {
		return local;
	}

	public List<Servidor> getMembrosBanca() {
		return membrosBanca;
	}

	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public Servidor getResponsavel() {
		return responsavel;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public Status getStatus() {
		return status;
	}

	public TipoBolsa getTipoBolsa() {
		return tipoBolsa;
	}

	public byte[] getEdital() {
		return edital;
	}

	public void setEdital(byte[] edital) {
		this.edital = edital;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setAlunosSelecao(List<Aluno> alunosSelecao) {
		this.alunosSelecao = alunosSelecao;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setAutor(Pessoa autor) {
		this.autor = autor;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public void setDataInicio(Date datadeInicio) {
		this.dataInicio = datadeInicio;
	}

	public void setDataTermino(Date datadeTermino) {
		this.dataTermino = datadeTermino;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public void setMembrosBanca(List<Servidor> membrosBanca) {
		this.membrosBanca = membrosBanca;
	}

	public void setQuantidadeVagas(int QuantidadeVagas) {
		this.quantidadeVagas = QuantidadeVagas;
	}

	public void setResponsavel(Servidor responsavel) {
		this.responsavel = responsavel;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTipoBolsa(TipoBolsa tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}
		
	/*public int verificaData(Date dataIn, Date dataFim){
		Calendar data1= Calendar.getInstance();
		data1.setTime(dataIn);
		Calendar data2 = Calendar.getInstance();
		data2.setTime(dataFim);
		
		if(data1.get(Calendar.YEAR) > data2.get(Calendar.YEAR)){
			return 1;
		}else if(data1.get(Calendar.YEAR) < data2.get(Calendar.YEAR)){
			return -1;
		}if(data1.get(Calendar.MONTH) > data2.get(Calendar.MONTH)){
			return 1;
		}else if(data1.get(Calendar.MONTH) < data2.get(Calendar.MONTH)){
			return -1;
		}
		if(data1.get(Calendar.DAY_OF_MONTH) > data2.get(Calendar.DAY_OF_MONTH)){
			return 1;
		}else if(data1.get(Calendar.DAY_OF_MONTH) < data2.get(Calendar.DAY_OF_MONTH)){
			return -1;
		}
		
		return 0;
	}//
	
	public boolean validarData(){
		if(verificaData(getDataInicio(), getDataTermino()) == -1){
			return false;
		}
		return true;
	}*/

	
	
	

}
