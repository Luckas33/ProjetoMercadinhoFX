package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class MainController {
    @FXML
    private Button btEntrar;
    @FXML
    private Button btCadastrar;
    @FXML
    private Button btCodigoFonte;

    @FXML
    private Parent root;

    // Método chamado quando o botão de transição é clicado
    public void switchToEntrarScreen(ActionEvent event) throws Exception {
        // Carregar FXML da Tela2
        Parent EntrarScreen = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));

        // Obter a cena atual
        Scene cenaAtual = root.getScene();

        // Criar uma nova cena com a Tela2
        Scene cenaTela2 = new Scene(EntrarScreen, cenaAtual.getWidth(), cenaAtual.getHeight());

        // Obter o palco (Stage) atual
        Stage palco = (Stage) cenaAtual.getWindow();

        // Definir a nova cena no palco
        palco.setScene(cenaTela2);
    }
    public void switchToCadastrarScreen(ActionEvent event) throws Exception {
        // Carregar FXML da Tela2
        Parent CadastrarScreen = FXMLLoader.load(getClass().getResource("CadastrarScreen.fxml"));

        // Obter a cena atual
        Scene cenaAtual = root.getScene();

        // Criar uma nova cena com a Tela2
        Scene cenaTela2 = new Scene(CadastrarScreen, cenaAtual.getWidth(), cenaAtual.getHeight());

        // Obter o palco (Stage) atual
        Stage palco = (Stage) cenaAtual.getWindow();

        // Definir a nova cena no palco
        palco.setScene(cenaTela2);
    }

    @FXML
    public void onbtCodigoFonte(ActionEvent e){
        System.out.println("eae boy");
        Alerts.showAlert("Aviso",null,"Equipe: \nLucas Sobral : Programador Front-end\nJoão Rodrigo : Programador Back-end\nOsvaldo Medeiros : Programador Back-end\nIarley Alves : Programador e Design\nAnderson : Programador Back-end\n\nCódigo Fonte: github/ProjetoMercadoFX", Alert.AlertType.INFORMATION);

    }


}