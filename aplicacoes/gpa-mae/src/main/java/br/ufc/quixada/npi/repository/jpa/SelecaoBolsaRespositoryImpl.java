package br.ufc.quixada.npi.repository.jpa;

import javax.inject.Named;

import br.ufc.quixada.npi.model.SelecaoBolsa;
import br.ufc.quixada.npi.repository.SelecaoBolsaRepository;

@Named
public class SelecaoBolsaRespositoryImpl extends JpaGenericRepositoryImpl<SelecaoBolsa> implements SelecaoBolsaRepository {

}
