package br.com.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Cargo {
    PROF("Professor"), TADM("Técnico Administrativo");

    private String nome;
    private static Map<Cargo, String> map;

    Cargo(String nome) {
        this.nome = nome;
    }

    public static Map<Cargo, String> toMap() {
        if (map == null) {
            map = new TreeMap<Cargo, String>();
            for (Cargo c : Cargo.values()) {
                map.put(c, c.nome);
            }
        }
        return map;
    }
}