package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum EstadoMoradia {
	CONCLUIDO("Concluído"), EM_CONST("Em contução"), DETERIORADO("Deteriorado");
	
	private String nome;
	private static Map<EstadoMoradia, String> map;
	
	private EstadoMoradia(String nome) {
		this.nome=nome;
	}
	
	public static Map<EstadoMoradia, String> toMap(){
		if(map == null) {
			map = new TreeMap<EstadoMoradia, String>();
			for (EstadoMoradia e : EstadoMoradia.values()) {
				map.put(e, e.nome);
			}
		}
		return map;
	}
	
	public String getNome() {
		return nome;
	}
}
