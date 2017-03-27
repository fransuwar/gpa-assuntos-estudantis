package br.ufc.npi.auxilio.model.questionario;

import br.ufc.npi.auxilio.enums.Estado;
import br.ufc.npi.auxilio.enums.Moradores;
import br.ufc.npi.auxilio.enums.MoradoresOrigem;
import br.ufc.npi.auxilio.enums.SituacaoImovel;

import java.util.List;

public class Moradia {

    // Núcleo familiar
    private String mae;

    private String pai;

    // Moradia de origem
    private List<MoradoresOrigem> moradoresOrigem;

    private String outroMoradorOrigem;

    private String enderecoOrigem;

    private String numeroOrigem;

    private String bairroOrigem;

    private String cidadeOrigem;

    private Estado estadoOrigem;

    private String cepOrigem;

    private String referenciaOrigem;

    // Moradia de origem - outras informações
    private SituacaoImovel situacaoImovel;

    private Double financiamento;

    private Integer quantidadeBemMovel;

    private String descricaoBemMovel;

    // Moradia atual
    private List<Moradores> moradores;

    private String outroMorador;

    private String endereco;

    private String numero;

    private String bairro;

    private String cidade;

    private Estado estado;

    private String cep;

    private String referencia;

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public List<Moradores> getMoradores() {
        return moradores;
    }

    public void setMoradores(List<Moradores> moradores) {
        this.moradores = moradores;
    }

    public String getOutroMorador() {
        return outroMorador;
    }

    public void setOutroMorador(String outroMorador) {
        this.outroMorador = outroMorador;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public SituacaoImovel getSituacaoImovel() {
        return situacaoImovel;
    }

    public void setSituacaoImovel(SituacaoImovel situacaoImovel) {
        this.situacaoImovel = situacaoImovel;
    }

    public Double getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(Double financiamento) {
        this.financiamento = financiamento;
    }

    public Integer getQuantidadeBemMovel() {
        return quantidadeBemMovel;
    }

    public void setQuantidadeBemMovel(Integer quantidadeBemMovel) {
        this.quantidadeBemMovel = quantidadeBemMovel;
    }

    public List<MoradoresOrigem> getMoradoresOrigem() {
        return moradoresOrigem;
    }

    public void setMoradoresOrigem(List<MoradoresOrigem> moradoresOrigem) {
        this.moradoresOrigem = moradoresOrigem;
    }

    public String getOutroMoradorOrigem() {
        return outroMoradorOrigem;
    }

    public void setOutroMoradorOrigem(String outroMoradorOrigem) {
        this.outroMoradorOrigem = outroMoradorOrigem;
    }

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public void setEnderecoOrigem(String enderecoOrigem) {
        this.enderecoOrigem = enderecoOrigem;
    }

    public String getNumeroOrigem() {
        return numeroOrigem;
    }

    public void setNumeroOrigem(String numeroOrigem) {
        this.numeroOrigem = numeroOrigem;
    }

    public String getBairroOrigem() {
        return bairroOrigem;
    }

    public void setBairroOrigem(String bairroOrigem) {
        this.bairroOrigem = bairroOrigem;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public Estado getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(Estado estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }

    public String getReferenciaOrigem() {
        return referenciaOrigem;
    }

    public void setReferenciaOrigem(String referenciaOrigem) {
        this.referenciaOrigem = referenciaOrigem;
    }

    public String getDescricaoBemMovel() {
        return descricaoBemMovel;
    }

    public void setDescricaoBemMovel(String descricaoBemMovel) {
        this.descricaoBemMovel = descricaoBemMovel;
    }
}
