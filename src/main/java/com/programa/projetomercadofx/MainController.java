package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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


    public void switchToEntrarScreen(ActionEvent event) throws IOException {
        // Carregar FXML da Entrar
        Parent tela1 = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        // Obter a cena atual
        Scene cenaAtual = root.getScene();
        // Criar uma nova cena
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        // Obter o palco (Stage) atual
        Stage palco = (Stage) cenaAtual.getWindow();
        // Definir a nova cena no palco
        palco.setScene(cenaTela1);
    }

    public void switchToCadastrarScreen(ActionEvent event) throws IOException {
        Parent tela1 = FXMLLoader.load(getClass().getResource("CadastrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void onbtCreditos(ActionEvent e) {
        Alerts.showAlert("Aviso", null, "Equipe: \n\nLucas Sobral : Desenvolvedor e Programador Front-end \n\nJoão Rodrigo : Programador Full-stack\n\nOsvaldo Medeiros : Programador Back-end\n\nIarley Alves : Programador e Design\n\nAnderson Moura: Programador Back-endn\n\nCódigo Fonte: github.com/Luckas33/ProjetoMercadinhoFX", Alert.AlertType.INFORMATION);
    }

}
