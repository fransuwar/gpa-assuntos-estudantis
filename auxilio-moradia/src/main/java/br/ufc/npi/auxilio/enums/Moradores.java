package br.ufc.npi.auxilio.enums;


public enum Moradores {
    CASA_ESTUDANTE("Casa do estudante"), PENSIONATO("Pensionato"), CASA_AMIGOS("Casa de amigos"),
    IRMAOS("Irmãos"), PARENTES("Parentes"), OUTROS("Outros");

    private String nome;

    Moradores (String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
