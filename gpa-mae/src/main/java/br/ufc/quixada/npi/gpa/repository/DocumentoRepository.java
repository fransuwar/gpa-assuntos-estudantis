package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento,Integer>{
	
	public abstract Documento findById(Integer idDocumento);

}
