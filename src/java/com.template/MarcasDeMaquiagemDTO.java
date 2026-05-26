package com.template;

/* declarando a classe DTO, responsável por representar os dados da entidade */
//contém getters e setters relacionados aos campos da tabela marcas_maquiagem no banco de dados.

public class MarcasDeMaquiagemDTO {

    private int id;
    private String nome;
    private String paisOrigem;
    private int anoFundacao;
    private boolean crueltyFree;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public boolean isCrueltyFree() {
        return crueltyFree;
    }

    public void setCrueltyFree(boolean crueltyFree) {
        this.crueltyFree = crueltyFree;
    }
}