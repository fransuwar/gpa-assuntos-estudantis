package br.ufc.npi.auxilio.enums;


public enum MoradoresOrigem {

    PAI("Pai"), MAE("Mãe"), IRMAOS("Irmãos"), AVOS("Avós"), CONJUUGE("Cônjuge ou companheiro(a)"),
    FILHOS("Filhos"), OUTROS("Outros");

    private String nome;

    MoradoresOrigem (String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
