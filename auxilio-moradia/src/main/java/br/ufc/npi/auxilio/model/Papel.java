package br.ufc.npi.auxilio.model;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {

    COORDENADOR, SERVIDOR, ALUNO, ASSISTENTE_SOCIAL;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
