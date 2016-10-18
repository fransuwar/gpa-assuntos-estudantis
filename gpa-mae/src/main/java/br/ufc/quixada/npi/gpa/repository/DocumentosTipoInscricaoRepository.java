package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.Documentacao;

public interface DocumentosTipoInscricaoRepository extends JpaRepository <Documentacao, Integer>{

}
