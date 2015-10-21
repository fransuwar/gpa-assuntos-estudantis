package br.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.ufc.quixada.npi.gpa.enums.Estado;

@Entity
@EntityListeners(PessoaEntityListener.class)
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"id", "cpf" }))
public class Pessoa {

	public Pessoa() {
		super();
	}
	
	public Pessoa(Integer id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany
	@JoinTable(name = "papel_pessoa", joinColumns = @JoinColumn(name = "pessoa_id"), inverseJoinColumns = @JoinColumn(name = "papel_id"))
	private List<Papel> papeis;
	
	@Transient
	private String email;
	
	@Transient
	private String nome;
	
	@Column(name = "datanascimento", columnDefinition="DATE") 
	@Temporal(TemporalType.DATE) 
	private Date dataNascimento;
	
	private String nacionalidade;
	
	private String naturalidade;

	@Enumerated(EnumType.STRING)
	private Estado uf;
	
	private String sexo;
	
	private String cpf;
	
	private String rg;

	private String telefone;
	
	private String estadoCivil;

	public Estado getUf() {
		return uf;
	}
	
	public void setUf(Estado uf) {
		this.uf = uf;
	}
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}
	
	@OneToMany(mappedBy = "pessoa")
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	
	@OneToMany(mappedBy="pessoa")
	private List<Aluno> alunos;
	
	public List<Servidor> getServidores() {
		return servidores;
	}

	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}

	@OneToMany(mappedBy="pessoa")
	private List<Servidor> servidores;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + "]";
	}
	
}
