package com.template.util;

import javafx.scene.control.Alert;

public class DialogUtil {
    public static void showError() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Campos Obrigatórios");
        alerta.setHeaderText("Campos em branco");
        alerta.setContentText("Você não pode salvar uma marca com campos vazios!");
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
