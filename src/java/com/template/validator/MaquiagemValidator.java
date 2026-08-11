package com.template.validator;

import static com.template.util.DialogUtil.showError;

import javafx.scene.control.TextField;

public class MaquiagemValidator {

    public static boolean validarCampos(
            TextField txtAno,
            TextField txtNome,
            TextField txtId,
            TextField txtPaisOrigem) {

        // Verifica se algum campo está em branco
        if (txtAno.getText().trim().isEmpty() ||
                txtNome.getText().trim().isEmpty() ||
                txtId.getText().trim().isEmpty() ||
                txtPaisOrigem.getText().trim().isEmpty()) {

            showError();

            return false;
        }

        return true;
    }

    public static boolean validarAno(
            TextField txtAno,
            TextField txtNome,
            TextField txtId,
            TextField txtPaisOrigem) {

        // Verifica se algum campo está em branco
        if (txtAno.getText().trim().isEmpty() ||
                txtNome.getText().trim().isEmpty() ||
                txtId.getText().trim().isEmpty() ||
                txtPaisOrigem.getText().trim().isEmpty()) {

            showError();

            return false;
        }

        return true;
    }
}


