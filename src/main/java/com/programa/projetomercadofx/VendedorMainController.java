package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import excecao.*;
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
    private Button btAdicionar;
    @FXML
    private Button btFinalizar;
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
    private ComboBox<String> comboboxParcelas;
    @FXML
    private Label lbParcelas;
    @FXML
    private Label lbSubtotal;
    @FXML
    private Label lbTroco;

    public Vector<ProdutoHistorico> vendas;
    public Vector<Produto> carrinho;

    public void switchToEntrarScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Verifica se todos os caracteres são dígitos
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // Verifica se o ID não começa com zero, a menos que seja apenas o zero
        if (str.length() > 1 && str.startsWith("0")) {
            return false;
        }

        return true;
    }

    public void onBtAdicionar(ActionEvent event) {
        int quantidade = 0;
        if (tfquantidade != null && isNumeric(tfquantidade.getText())) {
            quantidade = Integer.parseInt(tfquantidade.getText());
        }

        String id = tfidProduto.getText();
        for (Vendedor vendedor : ListaVendedor.vendedoresVector) {
            if (vendedor != null) {
                Produto produto = vendedor.retornaProduto(id);
                if (produto != null) {
                    ProdutoHistorico produtoHist = new ProdutoHistorico(produto.getId(), produto.getPrecoVenda(), quantidade);
                    produto.setQuantidadeVendida(quantidade);
                    this.carrinho.add(produto);
                    this.vendas.add(produtoHist);
                    this.atualizarSubtotal();
                }
            }
        }


    }

    //Alerts.showAlert("Erro venda", null, "Preencha as informações corretamente", Alert.AlertType.ERROR);
    //Alerts.showAlert("Erro venda", null, "Selecione o tipo de venda!", Alert.AlertType.ERROR);
    public void onBtFinalizar(ActionEvent event) {
        int quantidade = 0;
        if (tfquantidade != null) {
            quantidade = Integer.parseInt(tfquantidade.getText());
        }
        String tipoVenda = choiceBoxTipoVenda.getValue();
        if (!tipoVenda.isEmpty()){
            for (Vendedor vendedor : ListaVendedor.vendedoresVector) {
                if (vendedor != null) {
                    if (tipoVenda == "Débito"){
                        for (int i = 0; i < carrinho.size(); i++) {
                            try {
                                Produto produto = carrinho.get(i);
                                vendedor.venderDebito(produto.getId(), produto.getQuantidadeVendida());
                            } catch (QNException | QNUException | QIException | PIException e) {
                                e.printStackTrace();
                            }

                        }
                }
                    else if(tipoVenda == "Crédito"){
                        comboboxParcelas.setDisable(false);
                        lbParcelas.setDisable(false);
                        int parcelas = Integer.parseInt(comboboxParcelas.getValue());
                        Double valorFinalC = 0.0;
                        for (int i = 0; i < carrinho.size(); i++) {
                            try {
                                Produto produto = carrinho.get(i);
                                valorFinalC += (produto.getPrecoVenda() * produto.getQuantidadeVendida());
                                vendedor.venderCredito(produto.getId(), produto.getQuantidadeVendida(), parcelas);

                            } catch (PANUException | PANException | QNException | QNUException | QIException | PIException e) {
                                e.printStackTrace();
                            }

                        }
                            System.out.println("Produtos vendidos com sucesso");
                            Alerts.showAlert("Valor Parcelas",null,String.valueOf(valorFinalC/ parcelas),Alert.AlertType.NONE);

                    }
                    else if(tipoVenda == "Dinheiro") {
                        Double valorFinalD = 0.0;
                        double valorPago = 0.0;
                        tfValorPago.setVisible(false);
                        lbValorPago.setVisible(false);
                        tfValorPago.setDisable(false);
                        lbValorPago.setVisible(false);

                        if(tfValorPago != null){
                           valorPago = Double.parseDouble(tfValorPago.getText());
                        }

                        for (int i = 0; i < carrinho.size(); i++) {
                            Produto produto = carrinho.get(i);
                            valorFinalD += (produto.getPrecoVenda() * produto.getQuantidadeVendida());
                        }
                        if (valorPago > valorFinalD){
                            Double troco = valorPago - valorFinalD;
                            lbTroco.setText(String.valueOf(troco));
                            for (int i = 0; i < carrinho.size(); i++) {
                                try {
                                    Produto produto = carrinho.get(i);
                                    vendedor.venderDinheiro(produto.getId(), produto.getQuantidadeVendida());
                                } catch (VNUException | VNException | VIException | QNException | QNUException |
                                         QIException | PIException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        else if(valorPago == valorFinalD){
                            for (int i = 0; i < carrinho.size(); i++) {
                                try {
                                    Produto produto = carrinho.get(i);
                                    vendedor.venderDinheiro(produto.getId(), produto.getQuantidadeVendida());
                                } catch (VNUException | VNException | VIException | QNException | QNUException |
                                         QIException | PIException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void initialize() {
        choiceBoxTipoVenda.getItems().addAll("Débito", "Crédito", "Dinheiro");
        comboboxParcelas.getItems().addAll("1", "2", "3", "4", "5");
        lbSubtotal.setText("0.0");

        comboboxParcelas.setDisable(true);
        lbParcelas.setDisable(true);
        lbValorPago.setDisable(true);
        tfValorPago.setDisable(true);

        lbValorPago.setVisible(true);
        tfValorPago.setVisible(true);

        vendas = new Vector<>();
        carrinho = new Vector<>();

        lvProdutosAdicionados.setCellFactory(param -> new ListCell<ProdutoHistorico>() {
            @Override
            protected void updateItem(ProdutoHistorico item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("ID: " + item.getIdVenda() + " | Preço: " + item.getPreco() + " | Quantidade: " + item.getQuantidadeVendida());
                }
            }
        });

        lvProdutosAdicionados.getItems().setAll(vendas);
    }
}
