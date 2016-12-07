package br.ufc.npi.auxilio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.Documentacao;

@Repository
public interface DocumentosTipoInscricaoRepository extends JpaRepository <Documentacao, Integer>{

}
