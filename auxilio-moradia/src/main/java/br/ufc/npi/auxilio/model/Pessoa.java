package br.ufc.npi.auxilio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufc.npi.auxilio.enums.Estado;

@Entity
@EntityListeners(PessoaEntityListener.class)
public class Pessoa implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	
	private String nome;

	private String senha;

	private boolean habilitado;
	
	private LocalDate dataNascimento;
	
	private String naturalidade;

	@Enumerated(EnumType.STRING)
	private Estado ufNaturalidade;
	
	private String sexo;
	
	private String cpf;
	
	private String rg;

	private String orgaoEmissorRg;

	@Enumerated(EnumType.STRING)
	private Estado ufRg;

	private String telefone;
	
	private String estadoCivil;

	@Column(name = "papel")
	@CollectionTable(name = "papel_usuario")
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Papel.class, fetch = FetchType.EAGER)
	private List<Papel> papeis;

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public Estado getUfNaturalidade() {
		return ufNaturalidade;
	}

	public void setUfNaturalidade(Estado ufNaturalidade) {
		this.ufNaturalidade = ufNaturalidade;
	}

	public String getOrgaoEmissorRg() {
		return orgaoEmissorRg;
	}

	public void setOrgaoEmissorRg(String orgaoEmissorRg) {
		this.orgaoEmissorRg = orgaoEmissorRg;
	}

	public Estado getUfRg() {
		return ufRg;
	}

	public void setUfRg(Estado ufRg) {
		this.ufRg = ufRg;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void addPapel(Papel papel) {
		if (this.papeis == null) {
			this.papeis = new ArrayList<Papel>();
		}
		if (papel != null) {
			this.papeis.add(papel);
		}
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.papeis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.habilitado;
	}
	
	public boolean isCoordenador() {
		return this.papeis.stream().filter(p -> p.equals(Papel.COORDENADOR))
				.findFirst().orElse(null) != null;
	}
	
	public boolean isServidor() {
		return this.papeis.stream().filter(p -> p.equals(Papel.SERVIDOR))
				.findFirst().orElse(null) != null;
	}
	
	public boolean isAluno() {
		return this.papeis.stream().filter(p -> p.equals(Papel.ALUNO))
				.findFirst().orElse(null) != null;
	}
	
	public boolean isAssistenteSocial() {
		return this.papeis.stream().filter(p -> p.equals(Papel.ASSISTENTE_SOCIAL))
				.findFirst().orElse(null) != null;
	}
}
