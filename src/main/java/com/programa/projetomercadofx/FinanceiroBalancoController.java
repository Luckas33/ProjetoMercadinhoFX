package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import globalService.ListaEstoque;
import globalService.ListaFuncionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import produtos.Produto;
import produtos.ProdutoHistorico;
import registros.IRegistro;
import usuarios.Funcionario;
import usuarios.Gerente;

import java.util.Vector;

public class FinanceiroBalancoController {
////////////Id dos componenetes  ///////////////
    @FXML
    private ListView<ProdutoHistorico> listViewBalanco;
    @FXML
    private Button btVoltar;
    @FXML
    private Parent root;
    @FXML
    private Button btVerTudo;
    @FXML
    private Button btInserirData;
    @FXML
    private TextField tfData;
    @FXML
    private Button btOKData;
    @FXML
    private Button btInfo;
    private Vector<ProdutoHistorico> registroMostrar;
    private ObservableList<ProdutoHistorico> produtosObservableList = FXCollections.observableArrayList();

/////////////// Mudança de Tela ///////////////////////////
    public void switchToFinanceiroMainScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("FinanceiroMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

////////////////////Métodos lógicos //////////////////
    public void onBtVerTudo(ActionEvent e){
        tfData.setDisable(true);
        btOKData.setDisable(true);
        tfData.setText(null);
        produtosObservableList.clear();

        for(IRegistro registro : ListaEstoque.registroVector){
            if(registro != null){
                registroMostrar = registro.retornaRegistro();
                produtosObservableList.addAll(registroMostrar);
            }
        }
        listViewBalanco.getItems().setAll(produtosObservableList);
    }

    public void onBtInserirData(ActionEvent e){
        tfData.setDisable(false);
        btOKData.setDisable(false);
    }

    public void onBtOKData(ActionEvent e){
        String data = tfData.getText();
        produtosObservableList.clear();
        for(IRegistro registro : ListaEstoque.registroVector){
            if(registro != null){
                registroMostrar = registro.retornaRegistro();
                for(ProdutoHistorico produto : registroMostrar){
                    if(produto.getData().equals(data)){
                        produtosObservableList.add(produto);
                    }

                }
            }
        }
        listViewBalanco.getItems().setAll(produtosObservableList);
    }
    public void onClickAjuda(ActionEvent event){
        Alerts.showAlert("Ajuda",null,"Aqui você pode ver todas as suas entradas e sáidas de produtos", Alert.AlertType.INFORMATION);
    }

///////////////// Métodos Complementares ///////////////

    public void initialize (){
        btOKData.setDisable(true);

        registroMostrar = new Vector<>();
        listViewBalanco.setCellFactory(param -> new ListCell<ProdutoHistorico>() {
            @Override
            protected void updateItem(ProdutoHistorico item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    // Personalize aqui como deseja exibir cada item na lista
                    setText("ID: " + item.getIdVenda() + " | Preço: " + item.getPreco() + " | Tipo de transação: " + item.getForma() + " | Quantidade: " + item.getQuantidadeVendida() + " | Data: " + item.getData());
                }
            }
        });
        listViewBalanco.getItems().setAll(registroMostrar);
    }
}
