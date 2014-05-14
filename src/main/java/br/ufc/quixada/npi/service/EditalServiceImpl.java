package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.npi.model.Edital;
import br.ufc.quixada.npi.repository.EditalRepository;

@Named
public class EditalServiceImpl implements EditalService {

	@Inject
	private EditalRepository editalRepository;

	public EditalServiceImpl() {
	}

	@Transactional
	public void save(Edital edital) {
		editalRepository.save(edital);

	}

	@Transactional
	public void update(Edital edital) {
		editalRepository.update(edital);

	}

	@Transactional
	public Edital findById(int id) {
		return editalRepository.find(id);

	}

	@Transactional
	public List<Edital> findAll() {
		List<Edital> l = editalRepository.find();
		return l;
	}

	@Transactional
	public void delete(Edital edital) {
		editalRepository.delete(edital);

	}

}
