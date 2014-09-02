package br.ufc.quixada.npi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.npi.enumerator.StatusSelecaoBolsa;

@Entity
public class SelecaoBolsa {

	public SelecaoBolsa(){}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/*
	private enum tipoBolsa{
		InicicaoAcademica("IniciaçãoAcadêmica"),AuxilioMoradia("AuxílioMoradia");
		 tipoBolsa(String tipoBolsa) {
			
		}
	};
	*/
	private String tipoBolsa;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataTermino;
	private int quantidadeVagas;
	private String comentarios;
	/*
	@Enumerated(EnumType.STRING)
	private StatusSelecaoBolsa statusSelecao;
	*/
	
	private int ano;
	private int sequencial;
	private String duracaoBolsa;
	
	
	
	
	
	
	/*
	@ManyToMany(mappedBy="servidoresBanca")
	private List<Servidor> servidores;
	*/
	
	/*
	@ManyToOne
	private Servidor servidor;
	 */
	
	/*
	@ManyToMany(mappedBy="editais")
	private List<Aluno> alunosBanca;
  
	@ManyToMany
	private List<Aluno> alunosSelecao;
	
	@ManyToOne
	private Bolsa bolsa;
	*/
	
	/*
	public tipoBolsa getTipoBolsa() {
		return tipo;
	}



	public void setTipoBolsa(tipoBolsa tipoDeBolsa) {
		this.tipo = tipo;
	}

	*/

	public String getTipoBolsa() {
		return tipoBolsa;
	}



	public void setTipoBolsa(String tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}



	public Date getDataInicio() {
		return dataInicio;
	}



	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}



	public Date getDataTermino() {
		return dataTermino;
	}



	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}



	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}



	public void setQuantidadeVagas(int quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}



	public String getComentarios() {
		return comentarios;
	}



	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


	/*
	public StatusSelecaoBolsa getStatusSelecao() {
		return statusSelecao;
	}



	public void setStatusSelecao(StatusSelecaoBolsa statusSelecao) {
		this.statusSelecao = statusSelecao;
	}

	*/

	public int getAno() {
		return ano;
	}



	public void setAno(int ano) {
		this.ano = ano;
	}



	public int getSequencial() {
		return sequencial;
	}



	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}



	public String getDuracaoBolsa() {
		return duracaoBolsa;
	}



	public void setDuracaoBolsa(String duracaoBolsa) {
		this.duracaoBolsa = duracaoBolsa;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	/*
	public List<Servidor> getServidores() {
		return servidores;
	}



	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}



	public Servidor getServidor() {
		return servidor;
	}



	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}



	public List<Aluno> getAlunosBanca() {
		return alunosBanca;
	}



	public void setAlunosBanca(List<Aluno> alunosBanca) {
		this.alunosBanca = alunosBanca;
	}



	public List<Aluno> getAlunosSelecao() {
		return alunosSelecao;
	}



	public void setAlunosSelecao(List<Aluno> alunosSelecao) {
		this.alunosSelecao = alunosSelecao;
	}



	public Bolsa getBolsa() {
		return bolsa;
	}



	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}

*/
   
	@Override
	public String toString() {
		return "SelecaoBolsa [id=" + id + "  tipoDeBolsa=" + tipoBolsa + ", dataInicio="
				+ dataInicio + ", dataTérmino=" + dataTermino
				+ ", quantidadeVagas=" + quantidadeVagas + ", comentários="
				+ comentarios + ", statusSelecao="  + ", ano="
				+ ano + ", sequencial=" + sequencial + ", duraçãoBolsa="
				+ duracaoBolsa + ", identificador="
			/*	+ ", servidores=" + servidores + ", servidor=" + servidor
				+ ", alunosBanca=" + alunosBanca + ", alunosSelecao="
				+ alunosSelecao + ", bolsa=" + bolsa */+ "]";
				
	}
	
	
	
	
	
	
	
	
}
