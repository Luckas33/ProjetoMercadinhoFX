package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
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
import produtos.Produto;
import produtos.ProdutoComestivel;
import produtos.ProdutoNaoComestivel;

public class GerenteAddProdutoController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btAddProduto;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfTipo;
    @FXML
    private TextField tfPrecoCompra;
    @FXML
    private TextField tfDataValidade;
    @FXML
    private ChoiceBox<String> choiceBoxCategoriaProd;
    @FXML
    private Parent root;

    public void switchToGerenteMainScrenn(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void onBtAddProduto(ActionEvent e){
        String nome = tfNome.getText();
        String id = tfID.getText();
        String marca = tfMarca.getText();
        String tipo = tfTipo.getText();
        double precoCompra = Double.parseDouble(tfPrecoCompra.getText());//casting, para transformar String em double
        String dataValidade = tfDataValidade.getText();
        String categoria = choiceBoxCategoriaProd.getValue();

        Produto produtoComestivel;
        Produto produtoNaoComestivel;

        if(categoria != null){
            if (categoria == "Comestível" && !nome.isEmpty() && !id.isEmpty() && !marca.isEmpty() && precoCompra > 0.0 && !tipo.isEmpty() && !dataValidade.isEmpty()){
                produtoComestivel = new ProdutoComestivel(nome,id,marca,precoCompra,tipo,dataValidade);
                Alerts.showAlert("Adicionar produto", null, "Produto Comestível adicionado com sucesso.", Alert.AlertType.INFORMATION);
            } else if (categoria == "Não Comestível" && !nome.isEmpty() && !id.isEmpty() && !marca.isEmpty() && precoCompra > 0.0 && !tipo.isEmpty() && !dataValidade.isEmpty()){
                produtoNaoComestivel = new ProdutoNaoComestivel(nome, id, marca, precoCompra, tipo);
                Alerts.showAlert("Adicionar produto", null, "Produto Não Comestível adicionado com sucesso.", Alert.AlertType.INFORMATION);
            }else {
                Alerts.showAlert("Erro adicionar produto", null, "Preencha corretamente as informações", Alert.AlertType.ERROR);
            }
        }else {
            Alerts.showAlert("Erro adicionar produto",null,"Selecione a categoria do Produto", Alert.AlertType.ERROR);
        }
    }

    public void onBtLimpar(ActionEvent e){
        tfNome.setText(null);
        tfID.setText(null);
        tfDataValidade.setText(null);
        tfMarca.setText(null);
        tfPrecoCompra.setText(null);
        tfTipo.setText(null);
        choiceBoxCategoriaProd.setValue(null);
    }
    @FXML
    public void initialize(){
        choiceBoxCategoriaProd.getItems().addAll("Comestível","Não Comestível");
    }
}
