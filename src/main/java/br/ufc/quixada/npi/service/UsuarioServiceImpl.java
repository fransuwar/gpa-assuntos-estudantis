package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.model.Usuario;
import br.ufc.quixada.npi.repository.UsuarioRepository;

@Named
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl() {
	}
	
	@Transactional
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);

	}

	@Transactional
	public void update(Usuario usuario) {
		usuarioRepository.update(usuario);

	}

	@Transactional
	public Usuario findById(int id) {
		return usuarioRepository.find(id);

	}

	@Transactional
	public List<Usuario> findAll() {
		List<Usuario> l = usuarioRepository.find();
		return l;
	}

	@Transactional
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);

	}

}
