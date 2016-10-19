package br.ufc.quixada.npi.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.Servidor;

public interface ServidorRepository extends JpaRepository <Servidor, Integer>{
	
	public abstract Servidor findByPessoaCpf(String cpf);
	
	
	// old
	public abstract Servidor findById(Integer idServidor);
	
	public abstract List<Servidor> findAll();
	
	

}
