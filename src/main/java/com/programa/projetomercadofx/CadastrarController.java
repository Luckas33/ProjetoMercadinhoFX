package com.programa.projetomercadofx;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
//Falta implementar como ele realmente vai cadastrar
public class CadastrarController {
    @FXML
    private Button btVoltar;
    @FXML
    private Parent root;
    @FXML
    private ChoiceBox<String> choiceBoxFuncionarios;

    public void switchToMainScreen(ActionEvent event) throws Exception {
        // Carregar FXML da Main
        Parent tela2 = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

        // Obter a cena atual
        Scene cenaAtual = root.getScene();

        // Criar uma nova cena com a main
        Scene cenaTela2 = new Scene(tela2, cenaAtual.getWidth(), cenaAtual.getHeight());

        // Obter o palco (Stage) atual
        Stage palco = (Stage) cenaAtual.getWindow();

        // Definir a nova cena no palco
        palco.setScene(cenaTela2);
    }

    @FXML
    public void initialize() {
        choiceBoxFuncionarios.getItems().addAll("Gerente", "Vendedor"); //Adicionando opções ao choicebox

    }
}
