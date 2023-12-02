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
import javafx.stage.Stage;

public class GerenteMainController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btCriarProduto;
    @FXML
    private Button btVerEstoque;
    @FXML
    private Button btVerFinanceiro;
    @FXML
    private Parent root;
    @FXML
    private ImageView backgroundGerenteMain;
    @FXML
    private Button btInfo;

    public void switchToEntrarScrenn(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void switchToGerenteAddProdutoScrenn(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteAddProdutoScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void switchToFinanceiroMainScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("FinanceiroMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void switchToEstoqueMainScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("EstoqueMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void onBtInfo(){
        Alerts.showAlert("Informação",null, "Bem-vindo(a) ao menu principal de Gerente, aqui você pode criar,comprar e ver seus produtos.", Alert.AlertType.INFORMATION);
    }
    public void initialize(){
        String imagem = "MenuGerente.png";
        Image banner = new Image(getClass().getResource(imagem).toExternalForm());
        backgroundGerenteMain.setImage(banner);
    }
}