package br.ufc.npi.auxilio.enums;


public enum TipoSelecao {

    AUXILIO_MORADIA("Auxílio Moradia"), INICIACAO_ACADEMICA("Iniciação Acadêmica"), AUXILIO_EMERGENCIAL("Auxílio Emergencial");

    private String nome;

    TipoSelecao (String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

}
