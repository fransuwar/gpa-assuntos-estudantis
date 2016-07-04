package br.ufc.quixada.npi.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufc.quixada.npi.gpa.model.Aluno;

public interface AlunoRepository extends JpaRepository <Aluno, Integer>{
	
	@Query("SELECT a FROM Aluno a WHERE a.pessoa.cpf = :cpf")
	public abstract Aluno findByCpf(@Param("cpf") String cpf);
	
	public abstract Aluno findById(Integer idAluno);
	
	public abstract List<Aluno> findAll();
	
	@Query("SELECT a FROM Aluno a LEFT JOIN FETCH a.inscricoes WHERE a.pessoa.cpf = :cpf")
	public abstract Aluno findAlunoComInscricoesPorCpf(@Param("cpf") String cpf);

}
