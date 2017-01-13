package br.ufc.npi.auxilio.model;

import br.ufc.npi.auxilio.model.questionario.Identificacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String matricula;

	private String curso;

	private String anoIngresso;
	
	private Double ira;
	
	@ManyToOne
	private Pessoa pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Double getIra() {
		return ira;
	}

	public void setIra(Double ira) {
		this.ira = ira;
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

	public void setIdentificacao(Identificacao identificacao) {
		this.pessoa.setRg(identificacao.getIdentidade());
		this.pessoa.setOrgaoEmissorRg(identificacao.getOrgaoEmissor());
		this.pessoa.setUfRg(identificacao.getUfIdentidade());
		this.pessoa.setNaturalidade(identificacao.getNaturalidade());
		this.pessoa.setUfNaturalidade(identificacao.getUfNaturalidade());
		this.pessoa.setEstadoCivil(identificacao.getEstadoCivil());
		this.pessoa.setTelefone(identificacao.getContato());
		this.ira = identificacao.getIra();
	}

	public Identificacao getIdentificacao() {
		Identificacao identificacao = new Identificacao(pessoa.getRg(), pessoa.getOrgaoEmissorRg(), pessoa.getUfRg(),
				pessoa.getNaturalidade(), pessoa.getUfNaturalidade(), pessoa.getEstadoCivil(), pessoa.getTelefone(), ira);
		return identificacao;
	}

}