package com.programa.projetomercadofx;

import globalService.ListaGerente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import produtos.ProdutoHistorico;
import usuarios.Gerente;

public class EstoqueMainController {
////////// ID dos Componentes ////////////////
    @FXML
    private Button btVoltar;
    @FXML
    private Parent root;
    @FXML
    private Button btTipoProdutos;
    @FXML
    private ListView<ProdutoHistorico> listViewEstoque;

    ////////////// Mudar de Tela ///////////////////////
    public void switchToGerenteMainScren(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

//////////// Métodos para a lógica da tela ////////////////////////

public void onMenuItemTodos(ActionEvent e) {

    for (Gerente gerente : ListaGerente.gerentesVector) {
        if (gerente != null) {
            listViewEstoque.getItems().setAll(gerente.verEstoqueTotal());
        }
    }
}

////////////// Métodos Complementares ///////////////////////////
public void initialize(ActionEvent e) {
    onMenuItemTodos(e);

    // Configurar a fábrica de células para personalizar a exibição na ListView
    listViewEstoque.setCellFactory(param -> new ListCell<ProdutoHistorico>() {
        @Override
        protected void updateItem(ProdutoHistorico item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                // Personalize aqui como deseja exibir cada item na lista
                setText("ID: " + item.getIdVenda() + " | Preço: " + item.getPreco() + " | Quantidade: " + item.getQuantidadeVendida());
            }
        }
    });
    }
}
