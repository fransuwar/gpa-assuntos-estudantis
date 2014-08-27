package br.com.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Pessoa;
import br.com.ufc.quixada.npi.gpa.repository.PessoaRepository;

@Named
public class JpaPessoaRepositoryImpl extends JpaGenericRepositoryImpl<Pessoa> implements PessoaRepository{

}
