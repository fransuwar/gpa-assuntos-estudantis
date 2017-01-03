package br.ufc.quixada.npi.gpa.model;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {

    COORDENADOR, SERVIDOR, ALUNO;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
