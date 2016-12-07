package br.ufc.npi.auxilio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends JpaRepository <TipoDocumento, Integer>{

}
