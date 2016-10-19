package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.gpa.model.Documentacao;

@Repository
public interface DocumentosTipoInscricaoRepository extends JpaRepository <Documentacao, Integer>{

}
