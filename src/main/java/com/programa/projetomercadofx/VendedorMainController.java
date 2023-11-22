package com.programa.projetomercadofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class VendedorMainController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btConfirmar;
    @FXML
    private ChoiceBox<String> choiceBoxTipoVenda;
    @FXML
    private Parent root;

    public void switchToEntrarScreen(ActionEvent event) throws Exception{
        Parent tela1 = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void onBtConfirmar(ActionEvent event){
        String tipoVenda = choiceBoxTipoVenda.getValue();


    }
    @FXML
    public void initialize(){
        choiceBoxTipoVenda.getItems().addAll("Débito","Crédito", "Dinheiro");
    }
}