package com.template.validator;

public class AnoFundacaoValidador implements Validador<String> {

    private final String ano;

    public AnoFundacaoValidador(String ano) {
        this.ano = ano;
    }

    @Override
    public boolean validar(String valorAtual) {
        if (this.ano == null || this.ano.trim().length() != 4) {
            return false;
        }

        try {
            int anoInt = Integer.parseInt(this.ano);
            return anoInt >= 1500 && anoInt <= 2026;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "Digite um ano válido com 4 dígitos (ex: 2020)!";
    }

    @Override
    public String getValor() {
        return ano;
    }
}