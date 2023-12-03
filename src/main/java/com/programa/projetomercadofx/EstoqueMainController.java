package com.programa.projetomercadofx;

import estoques.IEstoque;
import globalService.ListaEstoque;
import globalService.ListaFuncionario;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import produtos.Produto;
import produtos.ProdutoHistorico;
import usuarios.Gerente;

import java.util.Vector;

public class EstoqueMainController {
//////////// ID dos Componentes ////////////////
    @FXML
    private Button btVoltar;
    @FXML
    private Parent root;
    @FXML
    private Button btVerTudo;
    @FXML
    private Button btInserirTipo;
    @FXML
    private TextField tfInserirTipo;
    @FXML
    private Button btOKTipo;
    @FXML
    private ListView<Produto> listViewEstoque;
    private Vector<Produto> estoqueMostrar;

    private ObservableList<Produto> produtosObservableList = FXCollections.observableArrayList();

///////////////// Mudar de Tela ///////////////////////
    public void switchToGerenteMainScren(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

//////////// Métodos para a lógica da tela ////////////////////////

public void onBtVerTudo(ActionEvent e) {
        tfInserirTipo.setDisable(true);
        btOKTipo.setDisable(true);
        tfInserirTipo.setText(null);
       produtosObservableList.clear();

    for (IEstoque estoque : ListaEstoque.estoqueVector) {
        if (estoque != null) {
            estoqueMostrar = estoque.retornaEstoque();
            produtosObservableList.addAll(estoqueMostrar);
        }
    }
    listViewEstoque.getItems().setAll(produtosObservableList);

}

public void onBtInserirTipo(ActionEvent e){
    tfInserirTipo.setDisable(false);
    btOKTipo.setDisable(false);
}

public void onBtOKTipo(ActionEvent e){
        String tipo = tfInserirTipo.getText();
       produtosObservableList.clear();
    for (IEstoque estoque : ListaEstoque.estoqueVector) {
        if (estoque != null) {
            estoqueMostrar = estoque.retornaEstoque();
            for(Produto produto : estoqueMostrar) {
                if(produto.getTipo().equals(tipo)) {
                    produtosObservableList.add(produto);
                }
            }
        }
    }
    listViewEstoque.getItems().setAll(produtosObservableList);

}

////////////// Métodos Complementares ///////////////////////////
public void initialize() {
    tfInserirTipo.setDisable(true);
    btOKTipo.setDisable(true);
    tfInserirTipo.setText(null);

        estoqueMostrar = new Vector<>();

    // Configurar a fábrica de células para personalizar a exibição na ListView
    listViewEstoque.setCellFactory(param -> new ListCell<Produto>() {
        @Override
        protected void updateItem(Produto item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                // Personalize aqui como deseja exibir cada item na lista
                setText("ID: " + item.getId() + " | Preço: " + item.getPrecoVenda() + " | Quantidade: " + item.getQuantidade() + " | Tipo: " + item.getTipo());
            }
        }
    });

    listViewEstoque.getItems().setAll(estoqueMostrar);

}
    }

