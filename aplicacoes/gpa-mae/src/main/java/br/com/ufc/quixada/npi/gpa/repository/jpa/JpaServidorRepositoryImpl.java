package br.com.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Servidor;
import br.com.ufc.quixada.npi.gpa.repository.ServidorRepository;

@Named
public class JpaServidorRepositoryImpl extends JpaGenericRepositoryImpl<Servidor> implements ServidorRepository{

}
