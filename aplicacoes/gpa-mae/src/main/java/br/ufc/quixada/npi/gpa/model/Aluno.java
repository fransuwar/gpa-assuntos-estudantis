package br.ufc.quixada.npi.gpa.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.ufc.quixada.npi.gpa.enums.Banco;
import br.ufc.quixada.npi.gpa.enums.Curso;

@NamedQueries({
		@NamedQuery(name = "Aluno.findAlunoByMatricula", query = "SELECT a FROM Aluno a WHERE a.matricula = :matricula"),
		@NamedQuery(name = "Aluno.findAlunoByIdPessoa", query = "SELECT a FROM Aluno a WHERE a.pessoa.id = :idPessoa"),
		@NamedQuery(name = "Aluno.findAlunoByCpf", query = "SELECT a FROM Aluno a WHERE a.pessoa.cpf = :cpf"),
		@NamedQuery(name = "Aluno.findAlunoComInscricoes", query = "SELECT DISTINCT a FROM Aluno a LEFT JOIN FETCH a.inscricoes WHERE a.pessoa.id = :idPessoa"),
		@NamedQuery(name = "Aluno.findAlunoComInscricoesCpf", query = "SELECT a FROM Aluno a LEFT JOIN FETCH a.inscricoes WHERE a.pessoa.cpf = :cpf") })
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "matricula" }) )
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Campo obrigatório")
	private String matricula;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	private String nome;

	@NotEmpty(message = "Campo obrigatório")
	private String anoIngresso;

	@NotNull(message = "Campo obrigatório")
	@Min(value = 1, message = "IRA deve ser maior que 0")
	@Max(value = 10, message = "IRA deve ter valor máximo 10")
	private Double ira;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Banco banco;

	@NotEmpty(message = "Campo obrigatório")
	@Size(max = 10, message = "Agencia de possuir no máximo 10 dígitos")
	private String agencia;

	@NotEmpty(message = "Campo obrigatório")
	@Size(max = 20, message = "Conta deve possuir no máximo 20 dígitos")
	private String conta;

	private byte[] foto;

	@ManyToOne
	private Pessoa pessoa;

	@OneToMany(mappedBy = "aluno", cascade = {CascadeType.PERSIST})
	private List<Inscricao> inscricoes;

	
	@OneToMany(mappedBy = "aluno")
	private List<VisitaDomiciliar> relatorioVisitaDomiciliar;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getAnoIngresso() {
		return anoIngresso;
	}

	public void setAnoIngresso(String anoIngresso) {
		this.anoIngresso = anoIngresso;
	}

	public Double getIra() {
		return ira;
	}

	public void setIra(Double ira) {
		this.ira = ira;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", matricula=" + matricula + ", curso=" + curso + ", nome=" + nome + ", anoIngresso="
				+ anoIngresso + ", ira=" + ira + ", banco=" + banco + ", agencia=" + agencia + ", conta=" + conta
				+ ", foto=" + Arrays.toString(foto) + ", pessoa=" + pessoa + ", inscricoes=" + inscricoes + "]";
	}

}