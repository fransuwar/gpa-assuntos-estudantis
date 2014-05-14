package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.npi.model.Bolsa;
import br.ufc.quixada.npi.repository.BolsaRepository;

@Named
public class BolsaServiceImpl implements BolsaService {

	@Inject
	private BolsaRepository bolsaRepository;

	public BolsaServiceImpl() {
	}

	@Transactional
	public void save(Bolsa bolsa) {
		bolsaRepository.save(bolsa);

	}

	@Transactional
	public void update(Bolsa bolsa) {
		bolsaRepository.update(bolsa);

	}

	@Transactional
	public Bolsa findById(int id) {
		return bolsaRepository.find(id);

	}

	@Transactional
	public List<Bolsa> findAll() {
		List<Bolsa> l = bolsaRepository.find();
		return l;
	}

	@Transactional
	public void delete(Bolsa bolsa) {
		bolsaRepository.delete(bolsa);

	}

}
