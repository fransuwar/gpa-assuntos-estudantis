package br.ufc.npi.auxilio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento,Integer>{
	
	public abstract Documento findById(Integer idDocumento);

}
