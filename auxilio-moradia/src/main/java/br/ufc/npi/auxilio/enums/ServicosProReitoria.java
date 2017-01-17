package br.ufc.npi.auxilio.enums;


public enum ServicosProReitoria {

    ISENCAO_RU("Isenção do RU"), RESIDENCIA_UNIVERSITARIA("Residência universitária"), AUXILIO_MORADIA("Auxílio moradia"),
    INICIACAO_ACADEMICA("Bolsa de iniciação acadêmica"), BOLSA_DESPORTO("Bolsa de desporto"),
    BOLSA_INICIART("Bolsa de iniciart"), OUTROS("Outros");

    private String nome;

    ServicosProReitoria (String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

}
