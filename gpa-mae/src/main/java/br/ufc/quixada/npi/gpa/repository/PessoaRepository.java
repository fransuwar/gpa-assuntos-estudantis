package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	public abstract Pessoa findByCpf(String cpf);

}
