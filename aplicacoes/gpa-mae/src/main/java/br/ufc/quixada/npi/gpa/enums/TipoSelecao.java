package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TipoSelecao {
	INIC_ACAD("Iniciação Acadêmica"), AUX_MOR("Auxilio Moradia");

	private String nome;
	private static Map<TipoSelecao, String> map;

	TipoSelecao(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<TipoSelecao, String> toMap() {
		if (map == null) {
			map = new TreeMap<TipoSelecao, String>();
			for (TipoSelecao ts : TipoSelecao.values()) {
				map.put(ts, ts.nome);
			}
		}
		return map;
	}
}
