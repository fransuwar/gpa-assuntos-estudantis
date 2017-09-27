package br.ufc.npi.auxilio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends JpaRepository <TipoDocumento, Integer>{
	
	@Query(
            "SELECT td FROM TipoDocumento td WHERE LOWER(td.nome) = LOWER(:nomeTipoDocumento)" 
    )
	List<TipoDocumento> search(@Param("nomeTipoDocumento") String nomeTipoDocumento);

}
