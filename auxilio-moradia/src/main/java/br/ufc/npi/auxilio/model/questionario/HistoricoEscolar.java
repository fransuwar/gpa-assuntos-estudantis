package br.ufc.npi.auxilio.model.questionario;

import br.ufc.npi.auxilio.enums.ServicosProReitoria;
import br.ufc.npi.auxilio.enums.TipoEnsino;
import br.ufc.npi.auxilio.enums.Trajeto;

import java.util.List;

public class HistoricoEscolar {

    private TipoEnsino ensinoMedio;

    private boolean bolsistaEnsinoMedio;

    private Integer percentualEnsinoMedio;

    private Integer quantidadeParticipacaoAuxilio;

    private String bolsaAtual;

    private String outraGraduacao;

    private List<ServicosProReitoria> servicos;

    private String outroServico;

    // Trajeto até a universidade
    private List<Trajeto> trajetos;

    private String outroTrajeto;

    private Double valorMensalTransporte;

    // Em KM
    private Integer distancia;

    // Em horas
    private Integer tempoGasto;

    public TipoEnsino getEnsinoMedio() {
        return ensinoMedio;
    }

    public void setEnsinoMedio(TipoEnsino ensinoMedio) {
        this.ensinoMedio = ensinoMedio;
    }

    public boolean isBolsistaEnsinoMedio() {
        return bolsistaEnsinoMedio;
    }

    public void setBolsistaEnsinoMedio(boolean bolsistaEnsinoMedio) {
        this.bolsistaEnsinoMedio = bolsistaEnsinoMedio;
    }

    public Integer getPercentualEnsinoMedio() {
        return percentualEnsinoMedio;
    }

    public void setPercentualEnsinoMedio(Integer percentualEnsinoMedio) {
        this.percentualEnsinoMedio = percentualEnsinoMedio;
    }

    public Integer getQuantidadeParticipacaoAuxilio() {
        return quantidadeParticipacaoAuxilio;
    }

    public void setQuantidadeParticipacaoAuxilio(Integer quantidadeParticipacaoAuxilio) {
        this.quantidadeParticipacaoAuxilio = quantidadeParticipacaoAuxilio;
    }

    public String getBolsaAtual() {
        return bolsaAtual;
    }

    public void setBolsaAtual(String bolsaAtual) {
        this.bolsaAtual = bolsaAtual;
    }

    public String getOutraGraduacao() {
        return outraGraduacao;
    }

    public void setOutraGraduacao(String outraGraduacao) {
        this.outraGraduacao = outraGraduacao;
    }

    public List<ServicosProReitoria> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicosProReitoria> servicos) {
        this.servicos = servicos;
    }

    public String getOutroServico() {
        return outroServico;
    }

    public void setOutroServico(String outroServico) {
        this.outroServico = outroServico;
    }

    public List<Trajeto> getTrajetos() {
        return trajetos;
    }

    public void setTrajetos(List<Trajeto> trajetos) {
        this.trajetos = trajetos;
    }

    public String getOutroTrajeto() {
        return outroTrajeto;
    }

    public void setOutroTrajeto(String outroTrajeto) {
        this.outroTrajeto = outroTrajeto;
    }

    public Double getValorMensalTransporte() {
        return valorMensalTransporte;
    }

    public void setValorMensalTransporte(Double valorMensalTransporte) {
        this.valorMensalTransporte = valorMensalTransporte;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(Integer tempoGasto) {
        this.tempoGasto = tempoGasto;
    }
}
