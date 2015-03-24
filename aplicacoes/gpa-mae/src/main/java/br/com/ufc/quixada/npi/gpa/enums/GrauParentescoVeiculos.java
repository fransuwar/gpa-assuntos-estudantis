package br.com.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum GrauParentescoVeiculos {
	SEMVEIC("Sem veículo"), PAI("Pai"), AVO("Avô"), TIO("Tio"), IRMAO("Irmão"), CONJ(
			"Cônjuge ou Companheiro(a)"), OUTROS("Outros");

	private String nome;
	private static Map<GrauParentescoVeiculos, String> map;

	GrauParentescoVeiculos(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<GrauParentescoVeiculos, String> toMap() {
		if (map == null) {
			map = new TreeMap<GrauParentescoVeiculos, String>();
			for (GrauParentescoVeiculos gpveic : GrauParentescoVeiculos
					.values()) {
				map.put(gpveic, gpveic.nome);
			}
		}
		return map;
	}

}
