package com.template.controller;

import com.template.model.dto.MarcasDeMaquiagemDTO;
import com.template.service.MarcaService;
import com.template.validator.IMarcaValidator;
import com.template.validator.MarcaValidator;
import com.template.util.FormFill;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

import static com.template.util.DialogUtil.showError;
import static com.template.util.DialogUtil.showInfo;

public class MainController {

    @FXML private Button btnEditar;
    @FXML private Button btnDeletar;
    @FXML private TextField txtNome;
    @FXML private TextField txtAno;
    @FXML private TextField txtId;
    @FXML private TextField txtPaisOrigem;
    @FXML private CheckBox chkTesteAnimais;
    @FXML private TableView<MarcasDeMaquiagemDTO> tblMarcasDeMaquiagem;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, String> colNome;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, Integer> colID;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, Integer> colAno;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, String> colPaisOrigem;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, Boolean> colTeste;

    private final IMarcaValidator marcaValidator;
    private final FormFill formFill;
    private final MarcaService marcaService;

    public MainController() {
        this.marcaValidator = new MarcaValidator();
        this.formFill = new FormFill(this.marcaValidator);
        this.marcaService = new MarcaService();
    }

    public MainController(IMarcaValidator marcaValidator) {
        this.marcaValidator = marcaValidator;
        this.formFill = new FormFill(this.marcaValidator);
        this.marcaService = new MarcaService();
    }

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoFundacao"));
        colPaisOrigem.setCellValueFactory(new PropertyValueFactory<>("paisOrigem"));
        colTeste.setCellValueFactory(new PropertyValueFactory<>("crueltyFree"));

        txtAno.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtAno.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        btnEditar.setDisable(true);
        btnDeletar.setDisable(true);
        carregarMaquiagens();
    }

    @FXML
    private void carregarMaquiagens() {
        ArrayList<MarcasDeMaquiagemDTO> listaMaquiagens = marcaService.listarMaquiagens();
        tblMarcasDeMaquiagem.getItems().clear();
        tblMarcasDeMaquiagem.getItems().addAll(listaMaquiagens);
    }

    @FXML
    private void limparCampos() {
        txtId.clear();
        txtNome.clear();
        chkTesteAnimais.setSelected(false);
        txtAno.clear();
        txtPaisOrigem.clear();
        tblMarcasDeMaquiagem.getSelectionModel().clearSelection();

        btnEditar.setDisable(true);
        btnDeletar.setDisable(true);
    }

    @FXML
    private void carregarCampos() {
        MarcasDeMaquiagemDTO marcaDTO = tblMarcasDeMaquiagem.getSelectionModel().getSelectedItem();

        if (marcaDTO != null) {
            txtId.setText(String.valueOf(marcaDTO.getId()));
            txtNome.setText(marcaDTO.getNome());
            txtAno.setText(String.valueOf(marcaDTO.getAnoFundacao()));
            chkTesteAnimais.setSelected(marcaDTO.getCrueltyFree());
            txtPaisOrigem.setText(marcaDTO.getPaisOrigem());

            btnEditar.setDisable(false);
            btnDeletar.setDisable(false);
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        MarcasDeMaquiagemDTO marcaDTO = formFill.extrairMarcaFormulario(
                new MarcasDeMaquiagemDTO(),
                txtNome,
                txtPaisOrigem,
                txtAno,
                chkTesteAnimais
        );

        if (marcaDTO == null) {
            showError();
            return;
        }

        boolean sucesso = marcaService.cadastrarMarca(
                marcaDTO.getNome(),
                marcaDTO.getPaisOrigem(),
                String.valueOf(marcaDTO.getAnoFundacao()),
                marcaDTO.isCrueltyFree()
        );

        if (!sucesso) {
            showInfo();
            return;
        }

        carregarMaquiagens();
        limparCampos();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        MarcasDeMaquiagemDTO marcaSelecionada =
                tblMarcasDeMaquiagem.getSelectionModel().getSelectedItem();

        if (marcaSelecionada != null) {

            MarcasDeMaquiagemDTO marcaAtualizada = formFill.extrairMarcaFormulario(
                    marcaSelecionada,
                    txtNome,
                    txtPaisOrigem,
                    txtAno,
                    chkTesteAnimais
            );

            if (marcaAtualizada == null) {
                showError();
                return;
            }

            boolean sucesso = marcaService.atualizarMarca(
                    marcaAtualizada.getId(),
                    marcaAtualizada.getNome(),
                    marcaAtualizada.getPaisOrigem(),
                    String.valueOf(marcaAtualizada.getAnoFundacao()),
                    marcaAtualizada.isCrueltyFree()
            );

            if (!sucesso) {
                showError();
                return;
            }

            carregarMaquiagens();
            limparCampos();
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        MarcasDeMaquiagemDTO marcaSelecionada = tblMarcasDeMaquiagem.getSelectionModel().getSelectedItem();

        if (marcaSelecionada != null) {
            marcaService.excluirMarca(marcaSelecionada.getId());

            carregarMaquiagens();
            limparCampos();
        }
    }
}