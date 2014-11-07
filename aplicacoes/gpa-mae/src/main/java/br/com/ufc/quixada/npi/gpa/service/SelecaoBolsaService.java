package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoBolsaService extends GenericService<SelecaoBolsa> {

	public abstract List<SelecaoBolsa> getSelecaoBolsasSubmetidos();
	
	public List<SelecaoBolsa> getSelecaoBolsasAtribuidos();

	public abstract List<SelecaoBolsa> getSelecaoBolsasByUsuario(Long id);
	
	public abstract List<SelecaoBolsa> getSelecaoBolsasAguardandoParecer();

}
