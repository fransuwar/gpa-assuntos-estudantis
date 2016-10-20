package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.gpa.model.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends JpaRepository <TipoDocumento, Integer>{

}
