package br.com.ufc.quixada.npi.enums;

import java.util.HashMap;
import java.util.Map;

public enum Cargo {
    PROF("Professor"), TADM("Técnico Administrativo");

    private String nome;
    private static Map<Cargo, String> map;

    Cargo(String nome) {
        this.nome = nome;
    }

    public static Map<Cargo, String> toMap() {
        if (map == null) {
            map = new HashMap<Cargo, String>();
            for (Cargo c : Cargo.values()) {
                map.put(c, c.nome);
            }
        }
        return map;
    }
}