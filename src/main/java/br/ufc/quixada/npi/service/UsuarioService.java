package br.ufc.quixada.npi.service;

import java.util.List;

import br.ufc.quixada.npi.model.Usuario;

public interface UsuarioService {

public abstract void save(Usuario usuario);
	
	public abstract void update(Usuario usuario);

	public abstract Usuario findById(int id);

	public abstract List<Usuario> findAll();

	public abstract void delete(Usuario usuario);
}
