package com.template.util;

import com.template.model.dto.MarcasDeMaquiagemDTO;
import com.template.validator.IMarcaValidator;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class FormFill {

    private final IMarcaValidator marcaValidator;

    public FormFill(IMarcaValidator marcaValidator) {
        this.marcaValidator = marcaValidator;
    }

    public MarcasDeMaquiagemDTO extrairMarcaFormulario(
            MarcasDeMaquiagemDTO dto,
            TextField txtNome,
            TextField txtPaisOrigem,
            TextField txtAno,
            CheckBox chkTesteAnimais) {

        String nome = txtNome.getText();
        String paisOrigem = txtPaisOrigem.getText();
        String ano = txtAno.getText();

        if (!marcaValidator.validarMarca(nome, paisOrigem, ano)) {
            return null;
        }

        dto.setNome(nome);
        dto.setPaisOrigem(paisOrigem);
        dto.setAnoFundacao(Integer.parseInt(ano));
        dto.setCrueltyFree(chkTesteAnimais.isSelected());

        return dto;
    }
}