package br.ufc.npi.auxilio.model.questionario;

import br.ufc.npi.auxilio.enums.Estado;

public class Identificacao {

    private String identidade;

    private String orgaoEmissor;

    private Estado ufIdentidade;

    private String naturalidade;

    private Estado ufNaturalidade;

    private String estadoCivil;

    private String contato;

    private Double ira;

    // Dados bancários
    private String banco;

    private String agencia;

    private String contaCorrente;

    public Identificacao() {

    }

    public Identificacao(String identidade, String orgaoEmissor, Estado ufIdentidade, String naturalidade,
             Estado ufNaturalidade, String estadoCivil, String contato, Double ira) {
        this.identidade = identidade;
        this.orgaoEmissor = orgaoEmissor;
        this.ufIdentidade = ufIdentidade;
        this.naturalidade = naturalidade;
        this.ufNaturalidade = ufNaturalidade;
        this.estadoCivil = estadoCivil;
        this.contato = contato;
        this.ira = ira;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public Estado getUfIdentidade() {
        return ufIdentidade;
    }

    public void setUfIdentidade(Estado ufIdentidade) {
        this.ufIdentidade = ufIdentidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Estado getUfNaturalidade() {
        return ufNaturalidade;
    }

    public void setUfNaturalidade(Estado ufNaturalidade) {
        this.ufNaturalidade = ufNaturalidade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Double getIra() {
        return ira;
    }

    public void setIra(Double ira) {
        this.ira = ira;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
}
