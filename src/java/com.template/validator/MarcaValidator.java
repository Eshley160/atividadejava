package com.template.validator;

import java.util.ArrayList;
import java.util.List;

public class MarcaValidator implements IMarcaValidator {

    @Override
    public boolean validarMarca(String nome, String paisOrigem, String ano) {

        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(new CamposObrigatoriosValidador("Nome", nome));
        validadores.add(new CamposObrigatoriosValidador("País de origem", paisOrigem));
        validadores.add(new CamposObrigatoriosValidador("Ano de fundação", ano));

        validadores.add(new AnoFundacaoValidador(ano));

        for (Validador<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                return false;
            }
        }

        return true;
    }
}