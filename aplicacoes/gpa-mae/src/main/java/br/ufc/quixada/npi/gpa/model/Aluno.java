package br.ufc.quixada.npi.gpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

@NamedQueries({ @NamedQuery(name = "Aluno.findAlunoByMatricula", 
								query = "SELECT a FROM Aluno a WHERE a.matricula = :matricula"),
				@NamedQuery(name = "Aluno.findAlunoById",
								query = "SELECT a FROM Aluno a WHERE a.pessoa.id = :idPessoa"),
				@NamedQuery(name = "Aluno.findAlunoComSelecoes",
								query = "SELECT DISTINCT a FROM Aluno a LEFT JOIN FETCH a.editais WHERE a.pessoa.id = :idPessoa")})
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "matricula"}))
public class Aluno {
	
	public Aluno() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Campo obrigatório")
	private String matricula;

	@ManyToMany(mappedBy = "alunosSelecao")
	private List<SelecaoBolsa> editais;

	@OneToMany(mappedBy = "aluno")
	private List<QuestionarioAuxilioMoradia> auxilioMoradia;
	
	@OneToMany(mappedBy= "aluno")
	private List<RelatorioVisitaDomiciliar>relatorioVisitaDomiciliar;

	@OneToMany(mappedBy = "aluno")
	private List<QuestionarioIniciacaoAcademica> iniciacaoAcademica;

	@NotNull(message="Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	private String nome;

	@NotEmpty(message = "Campo obrigatório")
	private String anoIngresso;

	@NotNull(message = "Campo obrigatório")
	@Min(value = 1, message = "IRA deve ser maior que 0")
	@Max(value = 10, message = "IRA deve ter valor máximo 10")
	private double ira;

	@NotNull(message="Campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Banco banco;

	@NotEmpty(message = "Campo obrigatório")
	@Size(max = 10, message = "Agencia de possuir no máximo 10 dígitos")
	private String agencia;

	@NotEmpty(message = "Campo obrigatório")
	@Size(max = 20, message = "Conta deve possuir no máximo 20 dígitos")
	private String conta;

	@ManyToOne
	private Pessoa pessoa;
	
	private byte[] foto;

	public List<QuestionarioAuxilioMoradia> getAuxilioMoradia() {
		return auxilioMoradia;
	}

	public void setAuxilioMoradia(
			List<QuestionarioAuxilioMoradia> auxilioMoradia) {
		this.auxilioMoradia = auxilioMoradia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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

	public List<QuestionarioIniciacaoAcademica> getIniciacaoAcademica() {
		return iniciacaoAcademica;
	}

	public void setIniciacaoAcademica(
			List<QuestionarioIniciacaoAcademica> iniciacaoAcademica) {
		this.iniciacaoAcademica = iniciacaoAcademica;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<SelecaoBolsa> getEditais() {
		return editais;
	}

	public void setEditais(List<SelecaoBolsa> editais) {
		this.editais = editais;
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
	
	public double getIra() {
		return ira;
	}

	public void setIra(Double ira) {
		this.ira = ira;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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

	@Override
	public String toString() {
		return "Aluno [id=" + id + "]";
	}


}