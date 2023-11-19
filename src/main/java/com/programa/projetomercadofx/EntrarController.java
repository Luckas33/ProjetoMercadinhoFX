package com.programa.projetomercadofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EntrarController {
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfSenha;
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btcadastrar;
    @FXML
    private Button btVoltar;

    @FXML
    private Parent root;

    public void switchToMainScrenn(ActionEvent event) throws Exception {
        // Carregar FXML da Main
        Parent tela1 = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

        // Obter a cena atual
        Scene cenaAtual = root.getScene();

        // Criar uma nova cena com a tela da Main
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());

        // Obter o palco (Stage) atual
        Stage palco = (Stage) cenaAtual.getWindow();

        // Definir a nova cena no palco
        palco.setScene(cenaTela1);
    }
    public void switchToCadastrarScreen(ActionEvent event) throws Exception {
        // Carregar FXML da Tela Cadastrar
        Parent CadastrarScreen = FXMLLoader.load(getClass().getResource("CadastrarScreen.fxml"));

        // Obter a cena atual
        Scene cenaAtual = root.getScene();

        // Criar uma nova cena com a Tela cadastrar
        Scene cenaTela2 = new Scene(CadastrarScreen, cenaAtual.getWidth(), cenaAtual.getHeight());

        // Obter o palco (Stage) atual
        Stage palco = (Stage) cenaAtual.getWindow();

        // Definir a nova cena no palco
        palco.setScene(cenaTela2);
    }

}
