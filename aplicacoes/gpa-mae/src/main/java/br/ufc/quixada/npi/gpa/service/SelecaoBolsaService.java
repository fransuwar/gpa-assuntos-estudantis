package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoBolsaService extends GenericService<SelecaoBolsa> {

	public abstract List<SelecaoBolsa> getSelecaoBolsasSubmetidos();
	
	public List<SelecaoBolsa> getSelecaoBolsasAtribuidos();

	public abstract List<SelecaoBolsa> getSelecaoBolsasByUsuario(Integer id);
	
	public abstract List<SelecaoBolsa> getSelecaoBolsasAguardandoParecer();
	
	public abstract boolean existsSelecaoEquals(SelecaoBolsa selecaoBolsa);

	public abstract SelecaoBolsa getSelecaoBolsaComDocumentos(Integer id);

	public abstract List<SelecaoBolsa> getSelecaoBolsaComMembros();

	public abstract SelecaoBolsa getSelecaoBolsaComMembros(Integer id);
	

}
