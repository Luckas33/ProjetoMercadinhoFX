package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import excecao.*;
import globalService.ListaGerente;
import globalService.ListaVendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import produtos.Produto;
import produtos.ProdutoHistorico;
import usuarios.Vendedor;

import java.util.Vector;

public class VendedorMainController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btConfirmar;
    @FXML
    private ChoiceBox<String> choiceBoxTipoVenda;
    @FXML
    private Parent root;
    @FXML
    private TextField tfidProduto;
    @FXML
    private TextField tfquantidade;
    @FXML
    private ListView<ProdutoHistorico> lvProdutosAdicionados;
    @FXML
    private ComboBox<String> cbParcelas;
    @FXML
    private Label lbParcelas;

    public Vector<ProdutoHistorico> vendas;

    public void switchToEntrarScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public void onBtConfirmar(ActionEvent event) {
        String tipoVenda = choiceBoxTipoVenda.getValue();
        int quantidade = Integer.parseInt(tfquantidade.getText());
        String id = tfidProduto.getText();
        int parcelas = Integer.parseInt(cbParcelas.getValue());


        if (!tipoVenda.isEmpty()) {
            if (tipoVenda == "Débito" && quantidade > 0 && id != null) {
                for (Vendedor vendedor : ListaVendedor.vendedoresVector) {
                    if (vendedor != null) {
                        try {
                            vendedor.venderDebito(id, quantidade);
                        } catch (QNException | QNUException | QIException | PIException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("Produto adicionado ao carrinho com sucesso, opcão Débito");
                        }
                        Produto produto = vendedor.retornaProduto(id);
                        if(produto != null){
                            ProdutoHistorico produtoHist = new ProdutoHistorico(produto.getId(), produto.getPrecoVenda(), produto.getQuantidade());
                            this.vendas.add(produtoHist);
                        }
                    }
                }
            } if (tipoVenda == "Crédito" && quantidade > 0 && id != null && parcelas > 0) {
                for (Vendedor vendedor : ListaVendedor.vendedoresVector) {
                    if (vendedor != null) {
                        try {
                            vendedor.venderCredito(id, quantidade, parcelas);
                        } catch (PANUException | PANException | QNException | QNUException | QIException |
                                 PIException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("Produto adicionado ao carrinho com sucesso, Opção");
                        }

                        Produto produto = vendedor.retornaProduto(id);
                        if (produto != null) {
                            ProdutoHistorico produtoHist = new ProdutoHistorico(produto.getId(), produto.getPrecoVenda(), produto.getQuantidade());
                            this.vendas.add(produtoHist);
                        }
                    }

                }
            }else if(tipoVenda == "Dinheiro" && quantidade > 0 && id != null ){
                for(Vendedor vendedor : ListaVendedor.vendedoresVector){
                    if (vendedor != null){
                        try{
                            vendedor.venderDinheiro(id, quantidade);
                        }catch (QNException | QNUException | QIException | VNException | VNUException | VIException | PIException e){
                            e.printStackTrace();
                        }finally {
                            System.out.println("Produto adicionado ao carrinho, opção Dinheiro" );
                        }
                    }
                }
            }else{
                Alerts.showAlert("Erro venda", null, "Preencha as informações corretamente", Alert.AlertType.ERROR);
            }
            } else {
                Alerts.showAlert("Erro venda", null, "Selecione o tipo de venda!", Alert.AlertType.ERROR);
            }
        }

        @FXML
        public void initialize () {
            choiceBoxTipoVenda.getItems().addAll("Débito", "Crédito", "Dinheiro");
            cbParcelas.setDisable(true);
            lbParcelas.setDisable(true);
             vendas = new Vector<>();
        }
}