package br.ufc.quixada.npi.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufc.quixada.npi.gpa.model.Servidor;

public interface ServidorRepository extends JpaRepository <Servidor, Integer>{
	
	public abstract Servidor findById(Integer idServidor);
	
	public abstract List<Servidor> findAll();
	
	@Query("SELECT s FROM Servidor s WHERE s.pessoa.cpf = :cpf")
	public abstract Servidor findByCpf(@Param("cpf") String cpf);
	
	@Query("SELECT s FROM Servidor s LEFT JOIN FETCH s.participaComissao WHERE s.pessoa.cpf = :cpf")
	public abstract Servidor findServidorComComissaoByCpf(@Param("cpf") String CPF);
	
	@Query("SELECT sr FROM Servidor sr LEFT JOIN sr.participaComissao s WHERE s.id = :idSelecao and :idServidor member of s.membrosComissao")
	public abstract List<Servidor> findServidorPertenceBanca(@Param("idServidor") Integer idServidor, @Param("idSelecao") Integer idSelecao);

}
