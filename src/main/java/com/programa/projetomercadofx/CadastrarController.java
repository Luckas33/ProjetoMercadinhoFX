package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import static com.programa.projetomercadofx.controllerUtil.IsCharacter.isChar;
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
import usuarios.Funcionario;
import usuarios.FuncionarioFactory;

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

    /////////// Métodos para trocar de tela /////////////////
    public void switchToMainScreen(ActionEvent event) throws Exception {
        Parent tela2 = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela2 = new Scene(tela2, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela2);
    }
    public void switchToEntrarScreen(ActionEvent event) throws Exception {
        Parent EntrarScreen = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela2 = new Scene(EntrarScreen, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela2);
    }

    ////////// Métodos Principais //////////////////
    public void onBtConfirmar(ActionEvent event){

            String nome = tfNome.getText();
            String login = tfLogin.getText();
            String email = tfEmail.getText();
            String senha = tfSenha.getText();
            String tipoFuncionario = choiceBoxFuncionarios.getValue();

            Funcionario funcionarioObj;


        if (tipoFuncionario != null) {
            if (tipoFuncionario.equals("Gerente") && isChar(nome) && !ListaFuncionario.getInstance().verificarGerenteExistente(login) && !email.isEmpty() && !senha.isEmpty()) {
                        
                        for(IEstoque estoque : ListaEstoque.getInstance().getEstoqueVector()) {
                            if(estoque != null) {
                                for(IRegistro registro : ListaEstoque.getInstance().getRegistroVector()) {
                                    if(registro != null) {
                                        funcionarioObj = FuncionarioFactory.criarFuncionario(tipoFuncionario, registro, estoque, nome, login, email, senha);
                                        try {
                                           ListaFuncionario.getInstance().cadastraFuncionario(funcionarioObj);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        onBtLimpar(event);
                                        Alerts.showAlert("Cadastro", null, "Conta Gerente cadastrada com sucesso", Alert.AlertType.CONFIRMATION);
                                    }
                                }
                            }
                        }

            } else if (tipoFuncionario.equals("Vendedor") && isChar(nome) && !ListaFuncionario.getInstance().verificarVendedorExistente(login) && !email.isEmpty() && !senha.isEmpty()) {
                
                for(IEstoque estoque : ListaEstoque.getInstance().getEstoqueVector()) {
                    if (estoque != null) {
                        for (IRegistro registro : ListaEstoque.getInstance().getRegistroVector()) {
                            if (registro != null) {
                                funcionarioObj = FuncionarioFactory.criarFuncionario(tipoFuncionario, registro, estoque, nome, login, email, senha);
                                try {
                                    ListaFuncionario.getInstance().cadastraFuncionario(funcionarioObj);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                onBtLimpar(event);
                                Alerts.showAlert("Cadastro", null, "Conta Vendedor cadastrada com sucesso", Alert.AlertType.CONFIRMATION);
                            }
                        }
                    }
                }

            } else if(!isChar(nome)){
                Alerts.showAlert("Cadastro error", null, "Preencha o nome somente com caracteres", Alert.AlertType.ERROR);
            
            } else if(!ListaFuncionario.getInstance().verificarGerenteExistente(login) || !ListaFuncionario.getInstance().verificarVendedorExistente(login)) {
                Alerts.showAlert("Cadastro error", null, "Conta ja existente", Alert.AlertType.ERROR);
            } else {
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

    ////////// Métodos complementares //////////
    @FXML
    public void initialize() {
        choiceBoxFuncionarios.getItems().addAll("Gerente", "Vendedor");
    }

}
