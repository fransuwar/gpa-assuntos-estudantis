package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Cursos {
	SI("Sistemas de Informação"), ES("Engenharia de Software"),
	RC("Redes de Computadores"), CC("Ciência da Computação"),
	EC("Engenharia da Computação"), DD("Design Digital");
	
	private String nome;
	private static Map<Cursos, String> map;
	
	Cursos(String nome){
		this.nome = nome;
	}
	
	public static Map<Cursos, String> toMap(){
		if(map == null) {
			map = new TreeMap<Cursos, String>();
			for (Cursos c : Cursos.values()) {
				map.put(c, c.nome);
			}
		}
		return map;
	}

	public String getNome() {
		return nome;
	}

}
