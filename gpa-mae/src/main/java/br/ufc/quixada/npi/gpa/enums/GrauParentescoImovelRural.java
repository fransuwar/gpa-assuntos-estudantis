package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum GrauParentescoImovelRural {
	SEMPROPRUR("Sem propriedade rural"), PAI("Pai"), AVO("Avô"), TIO("Tio"), IRMAO(
			"Irmão"), CONJUGUE("Cônjuge ou Companheiro(a)"), OUTROS("Outros");

	private String nome;
	private static Map<GrauParentescoImovelRural, String> map;

	GrauParentescoImovelRural(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<GrauParentescoImovelRural, String> toMap() {
		if (map == null) {
			map = new TreeMap<GrauParentescoImovelRural, String>();
			for (GrauParentescoImovelRural gpir : GrauParentescoImovelRural
					.values()) {
				map.put(gpir, gpir.nome);
			}
		}

		return map;
	}

}
