package com.example;

public class AnimalSilvestre {
    private String nome;
    private double pesoMedio;
    private String bioma;
    private boolean emRiscoExtincao;

    public AnimalSilvestre() {
    }

    public AnimalSilvestre(String nome, double pesoMedio, String bioma, boolean emRiscoExtincao) {
        this.nome = nome;
        this.pesoMedio = pesoMedio;
        this.bioma = bioma;
        this.emRiscoExtincao = emRiscoExtincao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPesoMedio() {
        return pesoMedio;
    }

    public void setPesoMedio(double pesoMedio) {
        this.pesoMedio = pesoMedio;
    }

    public String getBioma() {
        return bioma;
    }

    public void setBioma(String bioma) {
        this.bioma = bioma;
    }

    public boolean isEmRiscoExtincao() {
        return emRiscoExtincao;
    }

    public void setEmRiscoExtincao(boolean emRiscoExtincao) {
        this.emRiscoExtincao = emRiscoExtincao;
    }
}
