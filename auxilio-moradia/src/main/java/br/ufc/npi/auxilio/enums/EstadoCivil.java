package br.ufc.npi.auxilio.enums;


public enum EstadoCivil {
    SOLTEIRO("Solteiro(a)"), CASADO("Casado(a)"), SEPARADO("Separado(a)"), DIVORCIADO("Divorciado(a)"),
    VIUVO("Viúvo(a)"), OUTRO("Outro");
    private String nome;

    EstadoCivil (String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
