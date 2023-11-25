package com.programa.projetomercadofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FinanceiroMainController {
    @FXML
    private Label lbSaldoAtual;

    @FXML
    private TextField tfMudarTaxa;

    @FXML
    private Button btVerTaxa;

    @FXML
    private Button btVerBalanço;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btSeguir;

    @FXML
    private Parent root;
    @FXML
    private Button btMudarTaxa;
    @FXML
    private Button btComprarProduto;
    @FXML
    private Button btCaixa;
    @FXML
    private Pane pnCaixaDeposito;
    @FXML
    private Pane pnCaixaSaque;
    @FXML
    private Pane pnTaxa;
    @FXML
    private Pane pnComprarProduto;

    public void switchToGerenteMainScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void switchToFinanceiroBalancoScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("FinanceiroBalancoScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }


}
