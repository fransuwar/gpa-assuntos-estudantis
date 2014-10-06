package br.com.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"id", "login"}))
public class Pessoa {

	public Pessoa() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private boolean habilitado;

	
	@Column(nullable = false)
	private String email;
	
	private String nome;
	
	@Column(name = "datanascimento", columnDefinition="DATE") 
	@Temporal(TemporalType.DATE) 
	private Date dataNascimento;
	
	private String nacionalidade;
	
	private String naturalidade;
	
	public enum Uf{
		AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MG, MS, MT, PA, PB, PE, PI, PR, RJ, RN, RO, RR, RS, SC, SE, SP, TO;
	}
	
	private Uf uf;
	
	private String sexo;
	
	private String cpf;
	
	private String rg;
	
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	private String telefone;
	
	private String estadoCivil;
	
		
	public String getPassword() {
		return senha;
	}

	public void setPassword(String password) {
		this.senha = password;
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

	
	@OneToMany(mappedBy="usuario")
	private List<Servidor> servidores;
	

	@ManyToMany
	@JoinTable(name = "papel_pessoa", joinColumns = @JoinColumn(name = "pessoa_id"), inverseJoinColumns = @JoinColumn(name = "papel_id"))
	private List<Papel> papeis;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", login=" + login + ", senha="
				+ senha + ", habilitado=" + habilitado + ", email=" + email
				+ ", nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", nacionalidade=" + nacionalidade + ", naturalidade="
				+ naturalidade + ", uf=" + uf + ", sexo=" + sexo + ", cpf="
				+ cpf + ", rg=" + rg + ", telefone=" + telefone
				+ ", estadoCivil=" + estadoCivil + ", alunos=" + alunos
				+ ", servidores=" + servidores + ", papeis=" + papeis + "]";
	}
	
}
