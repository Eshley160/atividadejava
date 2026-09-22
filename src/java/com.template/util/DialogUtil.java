package com.template.util;

import javafx.scene.control.Alert;

public class DialogUtil {
    public static void showError(String msg) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Campos Obrigatórios");
        alerta.setHeaderText("Campos em branco");
        alerta.setContentText(msg);
        alerta.showAndWait();
    }

    public static void showInfo() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Campos Obrigatórios");
        alerta.setHeaderText("Ocorreu um problema.");
        alerta.setContentText("Por favor, preencha o Nome, País de Origem e o Ano de Fundação.");

        alerta.showAndWait();
    }
}
