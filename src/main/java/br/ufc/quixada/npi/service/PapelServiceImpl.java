package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.npi.model.Papel;
import br.ufc.quixada.npi.repository.PapelRepository;

@Named
public class PapelServiceImpl implements PapelService {

	@Inject
	private PapelRepository papelRepository;

	public PapelServiceImpl() {
	}

	@Transactional
	public void save(Papel papel) {
		papelRepository.save(papel);

	}

	@Transactional
	public void update(Papel papel) {
		papelRepository.update(papel);

	}

	@Transactional
	public Papel findById(int id) {
		return papelRepository.find(id);

	}

	@Transactional
	public List<Papel> findAll() {
		List<Papel> l = papelRepository.find();
		return l;
	}

	@Transactional
	public void delete(Papel papel) {
		papelRepository.delete(papel);

	}

}
