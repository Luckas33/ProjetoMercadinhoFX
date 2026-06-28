package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import globalService.ListaFuncionario; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EntrarController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btEntrar;
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfSenha;
    @FXML
    private ChoiceBox<String> choiceBoxFuncionarios;
    @FXML
    private Parent root;

    ////////// Mudança de Janela ///////////////////
    public void switchToMainScreen(ActionEvent event) throws Exception {
        Parent tela2 = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela2 = new Scene(tela2, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela2);
    }

    @FXML
    public void initialize() {
        choiceBoxFuncionarios.getItems().addAll("Gerente", "Vendedor");
    }

    ////////////// Métodos importantes ////////////////////
    public void onBtEntrar(ActionEvent event) {
        String login = tfLogin.getText();
        String senha = tfSenha.getText();
        String tipo = choiceBoxFuncionarios.getValue();

        if (tipo == null) {
            Alerts.showAlert("Login", null, "Selecione o tipo de usuário", Alert.AlertType.WARNING);
            return;
        }


        boolean autenticado = false;
        
        if (tipo.equals("Gerente")) {
            autenticado = ListaFuncionario.getInstance().verificarCredenciaisGerente(login, senha);
        } else if (tipo.equals("Vendedor")) {
            autenticado = ListaFuncionario.getInstance().verificarCredenciaisVendedor(login, senha);
        }

        if (autenticado) {
            Alerts.showAlert("Login", null, "Login realizado com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            Alerts.showAlert("Login Error", null, "Credenciais inválidas ou usuário não encontrado.", Alert.AlertType.ERROR);
        }

    }
}
