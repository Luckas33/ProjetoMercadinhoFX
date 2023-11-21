package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import estoques.Estoque;
import estoques.IEstoque;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
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
import usuarios.Gerente;
import usuarios.Vendedor;

//Falta implementar como ele realmente vai cadastrar
public class CadastrarController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btConfirmar;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfSenha;
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

    public void onBtConfirmar(ActionEvent event){

            IEstoque estoquee = new Estoque();
            String nome = tfNome.getText();
            String login = tfLogin.getText();
            String email = tfEmail.getText();
            String senha = tfSenha.getText();
            String tipoFuncionario = choiceBoxFuncionarios.getValue();
            Gerente gerenteObj;
            Vendedor vendedorObj;


        if (tipoFuncionario != null) {
            if (tipoFuncionario.equals("Gerente") && !nome.isEmpty() && !login.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                gerenteObj = new Gerente(estoquee, nome, login, senha);
                Alerts.showAlert("Cadastro", null, "Conta de Gerente cadastrada com sucesso", Alert.AlertType.CONFIRMATION);
            } else if (tipoFuncionario.equals("Vendedor") && !nome.isEmpty() && !login.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                vendedorObj = new Vendedor(estoquee, nome, login, senha);
                Alerts.showAlert("Cadastro", null, "Conta de Vendedor cadastrada com sucesso", Alert.AlertType.CONFIRMATION);
            } else {
                Alerts.showAlert("Cadastro error", null, "Preencha as informações corretamente", Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Cadastro error", null, "Tipo de usuário vazio", Alert.AlertType.WARNING);
        }
    }
    @FXML
    public void initialize() {
        choiceBoxFuncionarios.getItems().addAll("Gerente", "Vendedor"); //Adicionando opções ao choicebox

    }
}
