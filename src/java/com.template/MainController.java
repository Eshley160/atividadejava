package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController
{
    @FXML private Button btnCadastrar;
    @FXML private Button btnLimparAction;
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
    private void btnCadastrarAction(ActionEvent event) {
        MarcasDeMaquiagemDTO maquiagem = new MarcasDeMaquiagemDTO();
        maquiagem.setNome(txtNome.getText());
        maquiagem.setPaisOrigem(txtPaisOrigem.getText());
        maquiagem.setAnoFundacao(Integer.parseInt(txtAno.getText()));
        maquiagem.setCrueltyFree(chkTesteAnimais.isSelected());
        MarcasDeMaquiagemDAO dao = new MarcasDeMaquiagemDAO();
        dao.cadastrarMarca(maquiagem);
        System.out.println("Marca cadastrada com sucesso!");

        txtNome.clear();
        txtPaisOrigem.clear();
        txtAno.clear();
        chkTesteAnimais.setSelected(false);
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

        carregarMaquiagens();
    }

    @FXML
    private void carregarMaquiagens(){
        MarcasDeMaquiagemDAO objMarcaDAO = new MarcasDeMaquiagemDAO();
        ArrayList<MarcasDeMaquiagemDTO> listaMaquiagens = objMarcaDAO.listarMaquiagens();
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
    }

    @FXML
    private void carregarCampos() {
        MarcasDeMaquiagemDTO marcaDto = tblMarcasDeMaquiagem.getSelectionModel().getSelectedItem();

        if (marcaDto != null) {
            txtId.setText(String.valueOf(marcaDto.getId()));
            txtNome.setText(marcaDto.getNome());
            txtAno.setText(String.valueOf(marcaDto.getAnoFundacao()));
            chkTesteAnimais.setSelected(marcaDto.getCrueltyFree());
            txtPaisOrigem.setText(marcaDto.getPaisOrigem());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        String nome = txtNome.getText();
        String pais = txtPaisOrigem.getText();
        int ano = Integer.parseInt(txtAno.getText());
        boolean testeAnimais = chkTesteAnimais.isSelected();

        MarcasDeMaquiagemDTO marcaDto = new MarcasDeMaquiagemDTO();
        marcaDto.setNome(nome);
        marcaDto.setPaisOrigem(pais);
        marcaDto.setAnoFundacao(ano);
        marcaDto.setCrueltyFree(testeAnimais);

        MarcasDeMaquiagemDAO marcaDao = new MarcasDeMaquiagemDAO();
        marcaDao.cadastrarMarca(marcaDto);

        carregarMaquiagens();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        MarcasDeMaquiagemDTO marcaSelecionada = tblMarcasDeMaquiagem.getSelectionModel().getSelectedItem();

        if (marcaSelecionada != null) {
            MarcasDeMaquiagemDTO marcaDto = new MarcasDeMaquiagemDTO();

            marcaDto.setId(marcaSelecionada.getId());
            marcaDto.setNome(txtNome.getText());
            marcaDto.setAnoFundacao(Integer.parseInt(txtAno.getText()));
            marcaDto.setPaisOrigem(txtPaisOrigem.getText());
            marcaDto.setCrueltyFree(chkTesteAnimais.isSelected());

            MarcasDeMaquiagemDAO marcaDao = new MarcasDeMaquiagemDAO();
            marcaDao.atualizarMarca(marcaDto);

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
            MarcasDeMaquiagemDAO marcaDao = new MarcasDeMaquiagemDAO();
            marcaDao.excluirMarca(marcaSelecionada.getId());

            carregarMaquiagens();
            limparCampos();
        }
    }


}
