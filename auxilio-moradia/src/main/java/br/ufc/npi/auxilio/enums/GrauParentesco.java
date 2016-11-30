package br.ufc.npi.auxilio.enums;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public enum GrauParentesco {
	EU("Eu"), PAI("Pai"), MAE("Mãe"), IRMAO("Irmão(ã)"), FILHO("Filho(a)"), CONJ_COMP("Cônjuge ou Companheiro(a)"), OUTRO("Outro");

	private String nome;

	private GrauParentesco(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static GrauParentesco[] getTodos(){
		return GrauParentesco.values();
	}

	public static List<GrauParentesco> getTodosExcetoEu(){
		
		List<GrauParentesco> listaParentescos = new LinkedList<GrauParentesco>(Arrays.asList(GrauParentesco.values()));
		listaParentescos.remove(GrauParentesco.EU);
		
		return listaParentescos;
	}
}
