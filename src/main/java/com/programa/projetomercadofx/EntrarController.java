package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import globalService.ListaFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import usuarios.Funcionario;

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
    private Button btInfo;
    @FXML
    private ChoiceBox<String> choiceBoxTipoFuncionario;
    @FXML
    private Parent root;

//////////Mudança de Janela ///////////////////
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
    public void switchToGerenteMainScrenn(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }
    public void switchToVendedorMainScrenn(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("VendedorMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

////////////// Métodos importantes ////////////////////
    public void onBtConfirmar(ActionEvent event){
        String login = tfLogin.getText();
        String senha = tfSenha.getText();
        String funcionario = choiceBoxTipoFuncionario.getValue();



        if(funcionario != null){
            if(!login.isEmpty() && !senha.isEmpty() ) {
                if(funcionario == "Gerente" && ListaFuncionario.verificarCredenciaisGerente(login, senha) == true){
                    try{
                        switchToGerenteMainScrenn(event);
                        System.out.println("ação Login gerente ocorreu com sucesso");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else if(funcionario == "Vendedor" && ListaFuncionario.verificarCredenciaisVendedor(login, senha) == true){
                    try{
                        switchToVendedorMainScrenn(event);
                        System.out.println("ação Login vendedor ocorreu com sucesso");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    Alerts.showAlert("Entrar error", "Conta inexistente", "Preencha as informações corretamente", Alert.AlertType.ERROR);
                }
            }else {
                Alerts.showAlert("Entrar error", null, "Preencha as informações corretamente", Alert.AlertType.ERROR);
            }
        }else{
            Alerts.showAlert("Entrar Conta ERROR", null, "Tipo de usuário vazio", Alert.AlertType.ERROR);
        }

    }

    public void onClickAjuda(ActionEvent event){
        Alerts.showAlert("Ajuda",null,"Bem-vindo a tela de Entrar, aqui você pode inserir seus dados de login e senha para poder ter acesso a todos as funções do programa", Alert.AlertType.INFORMATION);
    }
    @FXML
    public void initialize(){
        choiceBoxTipoFuncionario.getItems().addAll("Gerente", "Vendedor");

    }
}
