package br.com.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.com.ufc.quixada.npi.gpa.repository.SelecaoBolsaRepository;

@Named
public class JpaSelecaoBolsaRepositoryImpl extends JpaGenericRepositoryImpl<SelecaoBolsa> implements SelecaoBolsaRepository {
	
}