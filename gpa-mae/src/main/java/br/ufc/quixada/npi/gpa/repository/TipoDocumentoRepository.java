package br.ufc.quixada.npi.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository <TipoDocumento, Integer>{
	
	public abstract TipoDocumento findById(Integer idTipoDocumento);
	
	public abstract List<TipoDocumento> findAll();

}
