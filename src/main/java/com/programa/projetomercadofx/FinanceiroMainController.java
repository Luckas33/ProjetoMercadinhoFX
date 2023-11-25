package com.programa.projetomercadofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FinanceiroMainController {
    @FXML
    private Label lbSaldoAtual;

    @FXML
    private TextField tfMudarTaxa;

    @FXML
    private Button btVerBalanço;

    @FXML
    private Button btVoltar;

    @FXML
    private Parent root;

    public void switchToVendedorMainScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("VendedorMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }
}