package com.programa.projetomercadofx;


import globalService.ListaEstoque;
import globalService.ListaFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private Vector<ProdutoHistorico> registroMostrar;

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
        for(IRegistro registro : ListaEstoque.registroVector){
            if(registro != null){
                registroMostrar = registro.retornaRegistro();
                listViewBalanco.getItems().setAll(registroMostrar);
            }
        }
    }

    public void onBtInserirData(ActionEvent e){
        tfData.setDisable(false);
        String data = tfData.getText();
        for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
            if(funcionario instanceof Gerente){
                ((Gerente) funcionario).verBalancoData(data);
            }
        }

    }

///////////////// Métodos Complementares ///////////////
    public void initialize (){

        registroMostrar = new Vector<>();
        listViewBalanco.setCellFactory(param -> new ListCell<ProdutoHistorico>() {
            @Override
            protected void updateItem(ProdutoHistorico item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    // Personalize aqui como deseja exibir cada item na lista
                    setText("ID: " + item.getIdVenda() + " | Preço: " + item.getPreco() + " | Tipo de transação: " + item.getForma() + " | Quantidade: " + item.getQuantidadeVendida() + " | Data:" + item.getData());
                }
            }
        });
        listViewBalanco.getItems().setAll(registroMostrar);
    }
}
