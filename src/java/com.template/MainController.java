package com.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController
{
    @FXML private Button btnCadastrar;
    @FXML private Button btnLimparAction;
    @FXML private Button btnEditar;
    @FXML private Button btnDeletar;
    @FXML private TextField txtNome;
    @FXML private TextField txtAno;
    @FXML private TextField txtID;
    @FXML private TextField txtTeste;
    @FXML private TextField txtPaisOrigem;
    @FXML private TableView<MarcasDeMaquiagemDTO> tblMarcasDeMaquiagem;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, String> colNome;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, String> colID;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, String> colAno;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, String> colPaisOrigem;
    @FXML private TableColumn<MarcasDeMaquiagemDTO, String> colTeste;

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        MarcasDeMaquiagemDTO maquiagem = new MarcasDeMaquiagemDTO();
        maquiagem.setNome(txtNome.getText());
        maquiagem.setPaisOrigem(txtPaisOrigem.getText());
        maquiagem.setAnoFundacao(Integer.parseInt(txtAno.getText()));
        maquiagem.setCrueltyFree(Boolean.parseBoolean(txtTeste.getText()));
        MarcasDeMaquiagemDAO dao = new MarcasDeMaquiagemDAO();
        dao.cadastrarMarca(maquiagem);
        System.out.println("Marca cadastrada com sucesso!");

        txtNome.clear();
        txtPaisOrigem.clear();
        txtAno.clear();
        txtTeste.clear();
    }



    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}
