package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import static com.programa.projetomercadofx.controllerUtil.IsCharacter.isChar;
import static globalService.ListaFuncionario.verificarGerenteExistente;
import static globalService.ListaFuncionario.verificarVendedorExistente;
import estoques.IEstoque;
import globalService.ListaEstoque;
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
import registros.IRegistro;
import usuarios.Gerente;
import usuarios.Vendedor;

public class CadastrarController {
//////// ID dos componentes ///////
    @FXML
    private Button btVoltar;
    @FXML
    private Button btEntrar;
    @FXML
    private Button btLimpar;
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
///////////Métodos para trocar de tela /////////////////
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

////////// Métodos Principais //////////////////
    public void onBtConfirmar(ActionEvent event){

            String nome = tfNome.getText();
            String login = tfLogin.getText();
            String email = tfEmail.getText();
            String senha = tfSenha.getText();
            String tipoFuncionario = choiceBoxFuncionarios.getValue();

            Gerente gerenteObj;
            Vendedor vendedorObj;


        if (tipoFuncionario != null) {
            if (tipoFuncionario.equals("Gerente") && isChar(nome) && !verificarGerenteExistente(login) && !email.isEmpty() && !senha.isEmpty()) {
                        for(IEstoque estoque : ListaEstoque.estoqueVector) {
                            if(estoque != null) {
                                for(IRegistro registro : ListaEstoque.registroVector) {
                                    if(registro != null) {
                                        gerenteObj = new Gerente(registro, estoque, nome, login, email, senha);
                                        try {
                                           ListaFuncionario.cadastraFuncionario(gerenteObj); 
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        onBtLimpar(event);
                                        Alerts.showAlert("Cadastro", null, "Conta Gerente cadastrada com sucesso", Alert.AlertType.CONFIRMATION);
                                    }
                                }
                            }
                        }

            } else if (tipoFuncionario.equals("Vendedor") && isChar(nome) && !verificarVendedorExistente(login) && !email.isEmpty() && !senha.isEmpty()) {
                for(IEstoque estoque : ListaEstoque.estoqueVector) {
                    if (estoque != null) {
                        for (IRegistro registro : ListaEstoque.registroVector) {
                            if (registro != null) {
                                vendedorObj = new Vendedor(registro, estoque, nome, login, email, senha);
                                try {
                                    ListaFuncionario.cadastraFuncionario(vendedorObj); 
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                onBtLimpar(event);
                                Alerts.showAlert("Cadastro", null, "Conta Vendedor cadastrada com sucesso", Alert.AlertType.CONFIRMATION);
                            }
                        }
                    }
                }


            }else if(!isChar(nome)){
                Alerts.showAlert("Cadastro error", null, "Preencha o nome somente com caracteres", Alert.AlertType.ERROR);
            }else if(!verificarGerenteExistente(login) || !verificarVendedorExistente(login)) {
                Alerts.showAlert("Cadastro error", null, "Conta ja existente", Alert.AlertType.ERROR);
            }else {
                Alerts.showAlert("Cadastro error", null, "Preencha as informações corretamente", Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Cadastro error", null, "Tipo de usuário vazio", Alert.AlertType.WARNING);
        }

    }
    public void onBtLimpar(ActionEvent e){
        tfNome.setText(null);
        tfEmail.setText(null);
        tfLogin.setText(null);
        tfSenha.setText(null);
        choiceBoxFuncionarios.setValue(null);
    }

//////////Métodos complementares //////////
    @FXML
    public void initialize() {
        choiceBoxFuncionarios.getItems().addAll("Gerente", "Vendedor"); //Adicionando opções ao choicebox
    }

}
