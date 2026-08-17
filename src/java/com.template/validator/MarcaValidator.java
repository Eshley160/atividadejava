package com.template.validator;

import java.time.Year;

public class MarcaValidator {

    public static boolean camposInvalidos(String nome, String paisOrigem, String ano) {
        if (nome == null || nome.trim().isEmpty() ||
                paisOrigem == null || paisOrigem.trim().isEmpty() ||
                ano == null || ano.trim().isEmpty()) {
            return true;
        }

        try {
            int anoInt = Integer.parseInt(ano.trim());
            int anoAtual = 2026;

            if (anoInt < 1500 || anoInt > anoAtual) {
                return true;
            }
        } catch (NumberFormatException e) {
            return true;
        }

        return false;
    }
}