package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum GrauParentesco {
	FILHO("Filho(a)"), NETO("Neto(a)"), SOBRINHO("Sobrinho(a)"), IRMAO("Irmão"), CONJ_COMP(
			"Cônjuge ou Companheiro(a)"), OUTROS("Outros");

	private String nome;
	private static Map<GrauParentesco, String> map;

	private GrauParentesco(String nome) {
		this.nome = nome;
	}

	public static Map<GrauParentesco, String> toMap() {
		if (map == null) {
			map = new HashMap<GrauParentesco, String>();
			for (GrauParentesco gp : GrauParentesco.values()) {
				map.put(gp, gp.nome);
			}
		}
		return map;
	}

}
