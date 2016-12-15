package br.ufc.npi.auxilio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Integer>{
	
	@Query("SELECT a FROM Aluno a WHERE a.pessoa.cpf = :cpf")
	public abstract Aluno findByCpf(@Param("cpf") String cpf);
	
	public abstract Aluno findById(Integer idAluno);
	
	public abstract List<Aluno> findAll();
	
 }
