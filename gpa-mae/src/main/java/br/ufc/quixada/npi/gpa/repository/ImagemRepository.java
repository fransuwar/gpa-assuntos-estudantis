package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.Imagem;

public interface ImagemRepository extends JpaRepository <Imagem, Integer>{
	
	public abstract Imagem findById(Integer idImagem);

}
