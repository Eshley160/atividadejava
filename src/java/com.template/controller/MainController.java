package com.template.controller;

import com.template.model.dao.MarcasDeMaquiagemDAO;
import com.template.model.dto.MarcasDeMaquiagemDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

import static com.template.util.DialogUtil.showError;
import static com.template.util.DialogUtil.showInfo;

public class MainController
{
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
    private void carregarMaquiagens(){
        MarcasDeMaquiagemDAO marcaDAO = new MarcasDeMaquiagemDAO();
        ArrayList<MarcasDeMaquiagemDTO> listaMaquiagens = marcaDAO.listarMaquiagens();

        tblMarcasDeMaquiagem.getItems().clear();
        tblMarcasDeMaquiagem.setItems(FXCollections.observableArrayList(listaMaquiagens));
    }

    @FXML
    private void limparCampos(){
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
        if (txtNome.getText().trim().isEmpty() || txtPaisOrigem.getText().trim().isEmpty() || txtAno.getText().trim().isEmpty()) {
            showInfo();
        }

        MarcasDeMaquiagemDTO maquiagem = new MarcasDeMaquiagemDTO();
        maquiagem.setNome(txtNome.getText());
        maquiagem.setPaisOrigem(txtPaisOrigem.getText());
        maquiagem.setAnoFundacao(Integer.parseInt(txtAno.getText()));
        maquiagem.setCrueltyFree(chkTesteAnimais.isSelected());

        MarcasDeMaquiagemDAO dao = new MarcasDeMaquiagemDAO();
        dao.cadastrarMarca(maquiagem);
        System.out.println("Marca cadastrada com sucesso!");

        carregarMaquiagens();
        limparCampos();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        MarcasDeMaquiagemDTO marcaSelecionada = tblMarcasDeMaquiagem.getSelectionModel().getSelectedItem();
        if (marcaSelecionada != null) {
            if (txtNome.getText().trim().isEmpty() || txtPaisOrigem.getText().trim().isEmpty() || txtAno.getText().trim().isEmpty()) {
                showError();
            }

            MarcasDeMaquiagemDTO marcaDTO = new MarcasDeMaquiagemDTO();
            marcaDTO.setId(marcaSelecionada.getId());
            marcaDTO.setNome(txtNome.getText());
            marcaDTO.setAnoFundacao(Integer.parseInt(txtAno.getText()));
            marcaDTO.setPaisOrigem(txtPaisOrigem.getText());
            marcaDTO.setCrueltyFree(chkTesteAnimais.isSelected());

            MarcasDeMaquiagemDAO marcaDAO = new MarcasDeMaquiagemDAO();
            marcaDAO.atualizarMarca(marcaDTO);

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
            MarcasDeMaquiagemDAO marcaDAO = new MarcasDeMaquiagemDAO();
            marcaDAO.excluirMarca(marcaSelecionada.getId());

            carregarMaquiagens();
            limparCampos();
        }
    }


}
