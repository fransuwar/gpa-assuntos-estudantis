package br.ufc.quixada.npi.repository.jpa;
import javax.inject.Named;

import br.ufc.quixada.npi.model.Selecao;
import br.ufc.quixada.npi.repository.SelecaoRepository;
@Named
public class JpaSelecaoRepositoryImpl extends JpaGenericRepositoryImpl<Selecao> implements SelecaoRepository{

}
