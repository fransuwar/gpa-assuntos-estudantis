package br.quixada.ufc.npi.service;

import java.util.List;

import br.quixada.ufc.npi.model.Usuario;

public interface UserService {

public abstract void save(Usuario usuario);
	
	public abstract void update(Usuario usuario);

	public abstract Usuario findById(int id);

	public abstract List<Usuario> findAll();

	public abstract void delete(Usuario usuario);
}
