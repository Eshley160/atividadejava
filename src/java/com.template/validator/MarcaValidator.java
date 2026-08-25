package com.template.validator;

import java.util.ArrayList;
import java.util.List;

public class MarcaValidator {

    public static boolean camposInvalidos(String nome, String paisOrigem, String ano) {

        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("País de origem", paisOrigem));
        validadores.add(new CampoObrigatorioValidador("Ano de fundação", ano));

        validadores.add(new AnoFundacaoValidador(ano));

        for (Validador<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                return true;
            }
        }

        return false;
    }
}