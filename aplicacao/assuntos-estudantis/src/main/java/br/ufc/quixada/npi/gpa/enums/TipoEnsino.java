package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TipoEnsino{
	PUBLICO("Público"), PARTICULAR("Particular"), PART_COM_BOLSA("Particular com Bolsa");
	
	private String nome;
	private static Map<TipoEnsino, String> map;

	TipoEnsino(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public static Map<TipoEnsino, String> toMap() {
		if (map == null) {
			map = new TreeMap<TipoEnsino, String>();
			for (TipoEnsino tef : TipoEnsino.values()) {
				map.put(tef, tef.nome);
			}
		}
		return map;
	}
} 
