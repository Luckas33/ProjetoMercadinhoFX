package com.programa.projetomercadofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GerenteMainController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btCriarProduto;
    @FXML
    private Button btVerEstoque;
    @FXML
    private Button btVerFinanceiro;
    @FXML
    private Parent root;

    public void switchToEntrarScrenn(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

}