package br.ufc.npi.auxilio.repository;

import br.ufc.npi.auxilio.model.PessoaFamilia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFamiliaRepository extends JpaRepository<PessoaFamilia, Integer> {
}
