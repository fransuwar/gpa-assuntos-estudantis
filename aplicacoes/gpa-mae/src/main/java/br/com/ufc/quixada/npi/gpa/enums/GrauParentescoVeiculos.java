package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum GrauParentescoVeiculos {
	SEMVEIC("Sem veículo"), PAI("Pai"), AVO("Avô"), TIO("Tio"), IRMAO("Irmão"), CONJ(
			"Cônjuge ou Companheiro(a)"), OUTROS("Outros");

	private String nome;
	private static Map<GrauParentescoVeiculos, String> map;

	GrauParentescoVeiculos(String nome) {
		this.nome = nome;
	}

	public static Map<GrauParentescoVeiculos, String> toMap() {
		if (map == null) {
			map = new HashMap<GrauParentescoVeiculos, String>();
			for (GrauParentescoVeiculos gpveic : GrauParentescoVeiculos
					.values()) {
				map.put(gpveic, gpveic.nome);
			}
		}
		return map;
	}

}
