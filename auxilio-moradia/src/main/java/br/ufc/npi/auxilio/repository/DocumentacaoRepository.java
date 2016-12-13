package br.ufc.npi.auxilio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.AnaliseDocumentacao;
import br.ufc.npi.auxilio.model.Documentacao;
import br.ufc.npi.auxilio.model.TipoDocumento;

@Repository
public interface DocumentacaoRepository extends JpaRepository <Documentacao, Integer>{
	@Query("SELECT d FROM Documentacao d WHERE d.tipoDocumento = :tipoDocumento and d.analiseDocumentacao = :analiseDocumentacao")
	public abstract Documentacao findByTipoDocumento(@Param("tipoDocumento") TipoDocumento tipoDocumento, @Param("analiseDocumentacao") AnaliseDocumentacao analiseDocumentacao);
}
