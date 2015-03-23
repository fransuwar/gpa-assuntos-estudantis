package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum MoraCom{
	PAI("Pai"), MAE("Mãe"), IRMAOS("Irmãos"), PARENTES("Parentes"),
	CONJ_COMP("Cônjuge ou Companheiro(a)"), FILHOS("Filhos(as)"), OUTRO("Outros");
	
	private String nome;
	private static Map<MoraCom, String> map;

	MoraCom(String nome) {
		this.nome = nome;
	}

	public static Map<MoraCom, String> toMap() {
		if (map == null) {
			map = new HashMap<MoraCom, String>();
			for (MoraCom mc : MoraCom.values()) {
				map.put(mc, mc.nome);
			}
		}
		return map;
	}
}
