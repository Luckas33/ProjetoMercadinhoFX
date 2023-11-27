package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import excecao.*;
import globalService.ListaFuncionario;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
///////// ID dos componentes da tela //////////////
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
    private Label lbValorPago;
    @FXML
    private TextField tfValorPago;
    @FXML
    private Button btConfirmarVenda;
    @FXML
    private Label lbTipoPagamento;
    public Vector<ProdutoHistorico> vendas;
    public Vector<Produto> carrinho;

/////////////////Método para trocar de tela///////////////////
    public void switchToEntrarScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("EntrarScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

///////////// Métodos para a Lógica da tela /////////////
    public void onBtAdicionar(ActionEvent event) { //Método para adicionar os produtos no carrinho
        int quantidade = 0;
        if (tfquantidade != null && isNumeric(tfquantidade.getText())) {
            quantidade = Integer.parseInt(tfquantidade.getText());
        }

        String id = tfidProduto.getText();
        for (Vendedor vendedor : ListaFuncionario.vendedoresVector) {
            if (vendedor != null) {
                Produto produto = vendedor.retornaProduto(id);
                if (produto != null) {
                    ProdutoHistorico produtoHist = new ProdutoHistorico(produto.getId(), produto.getPrecoVenda(), quantidade);
                    produto.setQuantidadeVendida(quantidade);
                    this.carrinho.add(produto);
                    this.vendas.add(produtoHist);
                    atualizarSubtotal();
                    lvProdutosAdicionados.getItems().setAll(vendas);
                }
                else{
                    Alerts.showAlert("Erro Venda", null,"Produto inexistente",Alert.AlertType.ERROR);
                }
            }
        }
        for(Produto produto : carrinho){
            if(produto != null){
                btFinalizar.setDisable(false);
            }
        }
    }

    public void onBtFinalizar(ActionEvent event) { //Método para fechar o carrinho e habilitar a escolha de pagamento
        lbTipoPagamento.setDisable(false);
        choiceBoxTipoVenda.setDisable(false);
        btAdicionar.setDisable(true);

    }

    public void onCbTipoVenda(Event e){   //Método que pega a escolha do choiceBox e ativa e desativa outras opções de escolha
        String escolha = choiceBoxTipoVenda.getValue();
        if("Crédito".equals(escolha)){
            comboboxParcelas.setDisable(false);
            lbParcelas.setDisable(false);

        }else if("Dinheiro".equals(escolha)){
            tfValorPago.setDisable(false);
            lbValorPago.setDisable(false);
            comboboxParcelas.setDisable(true);
            lbParcelas.setDisable(true);
        }else if ("Débito".equals(escolha)){
            tfValorPago.setDisable(true);
            lbValorPago.setDisable(true);
            comboboxParcelas.setDisable(true);
            lbParcelas.setDisable(true);
        }else{
            tfValorPago.setDisable(true);
            lbValorPago.setDisable(true);
            comboboxParcelas.setDisable(true);
            lbParcelas.setDisable(true);
        }
    }

    public void onBtConfirmarVenda(ActionEvent event){  //Pega todos os produtos do carrinho e vende um por um com base no tipo de pagamento
        String tipoVenda = choiceBoxTipoVenda.getValue();
        if (!tipoVenda.isEmpty()){
            for (Vendedor vendedor : ListaFuncionario.vendedoresVector) {
                if (vendedor != null) {
                    if (tipoVenda == "Débito"){
                        for (int i = 0; i < carrinho.size(); i++) {
                            Produto produto = carrinho.get(i);
                            try {
                                vendedor.venderDebito(produto.getId(), produto.getQuantidadeVendida());
                                Alerts.showAlert("Venda", null, "Venda realizada com sucesso.", Alert.AlertType.INFORMATION);
                                onBtLimpar(event);
                                btAdicionar.setDisable(false);
                            } catch (PIException e) {
                                e.printStackTrace();
                                Alerts.showAlert("Erro Venda", null, "Produto inexistente", Alert.AlertType.ERROR);
                            } catch (QINException e) {
                                e.printStackTrace();
                                Alerts.showAlert("Erro Venda", null, "quantidade insuficiente", Alert.AlertType.ERROR);
                            } catch (QIException e) {
                                e.printStackTrace();
                                Alerts.showAlert("Erro Venda", null, "Saldo insuficiente", Alert.AlertType.ERROR);
                            } catch (DVIException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    else if(tipoVenda == "Crédito"){
                        int parcelas = Integer.parseInt(comboboxParcelas.getValue());
                        Double valorFinalC = 0.0;
                        for (int i = 0; i < carrinho.size(); i++) {
                            try {
                                Produto produto = carrinho.get(i);
                                valorFinalC += (produto.getPrecoVenda() * produto.getQuantidadeVendida());
                                vendedor.venderCredito(produto.getId(), produto.getQuantidadeVendida(), parcelas);
                                Alerts.showAlert("Venda", null, "Venda realizada com sucesso.", Alert.AlertType.INFORMATION);
                                onBtLimpar(event);
                            }catch(QINException e){
                                e.printStackTrace();
                                Alerts.showAlert("Erro Venda", null,"Insira uma quantidade válida",Alert.AlertType.ERROR);
                            }catch(QIException e){
                                e.printStackTrace();
                                Alerts.showAlert("Erro Venda", null,"Quantidade insuficiente no estoque",Alert.AlertType.ERROR);
                            }catch(PIException e){
                                e.printStackTrace();
                                Alerts.showAlert("Erro Venda", null,"Produto não existente",Alert.AlertType.ERROR);
                            }catch (DVIException e){
                                e.printStackTrace();
                            }
                        }
                        Alerts.showAlert("Valor Parcelas",null,String.valueOf(valorFinalC/ parcelas),Alert.AlertType.INFORMATION);
                    }
                    else if(tipoVenda == "Dinheiro") {
                        Double valorFinalD = 0.0;
                        double valorPago = 0.0;

                        if(tfValorPago != null){
                            valorPago = Double.parseDouble(tfValorPago.getText());
                        }

                        for (int i = 0; i < carrinho.size(); i++) {
                            Produto produto = carrinho.get(i);
                            valorFinalD += (produto.getPrecoVenda() * produto.getQuantidadeVendida());
                        }
                        if (valorPago > valorFinalD){
                            Double troco = valorPago - valorFinalD;
                            for (int i = 0; i < carrinho.size(); i++) {
                                try {
                                    Produto produto = carrinho.get(i);
                                    vendedor.venderDinheiro(produto.getId(), produto.getQuantidadeVendida());
                                    Alerts.showAlert("Venda", null, "Venda realizada com sucesso.", Alert.AlertType.INFORMATION);
                                    onBtLimpar(event);
                                } catch ( QINException |
                                         QIException | PIException | DVIException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else if(valorPago == valorFinalD){
                            for (int i = 0; i < carrinho.size(); i++) {
                                try {
                                    Produto produto = carrinho.get(i);
                                    vendedor.venderDinheiro(produto.getId(), produto.getQuantidadeVendida());
                                } catch (  QINException |
                                         QIException | PIException | DVIException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                        else{
                            Alerts.showAlert("Erro Venda", null,"Valor insuficiente",Alert.AlertType.ERROR);
                        }
                    }
                }
            }
        }
    }

//////////////////// Métodos complementares //////////////////
    private boolean isNumeric(String str) {//Método para certificar que na String só existem números
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

    private void atualizarSubtotal(){
        Double subTotal = 0.0;
        for(ProdutoHistorico produtoHist : vendas){
            subTotal += (produtoHist.getPreco() * produtoHist.getQuantidadeVendida());

        }
        lbSubtotal.setText(String.valueOf(subTotal));
    }

    public void onBtLimpar(ActionEvent e){ ////// Limpa todas as informações inseridas
        tfquantidade.setText(null);
        tfidProduto.setText(null);
        tfValorPago.setText(null);
        comboboxParcelas.setValue(null);
        lvProdutosAdicionados.getItems().clear();
        lbSubtotal.setText("0.0");
        choiceBoxTipoVenda.setValue(null);
        lbTipoPagamento.setDisable(true);
    }

    @FXML
    public void initialize() {
        choiceBoxTipoVenda.getItems().addAll("Débito", "Crédito", "Dinheiro");
        comboboxParcelas.getItems().addAll("1", "2", "3", "4", "5");
        choiceBoxTipoVenda.setOnAction(this::onCbTipoVenda);
        choiceBoxTipoVenda.setOnMouseClicked(this::onCbTipoVenda);

        btFinalizar.setDisable(true);
        comboboxParcelas.setDisable(true);
        lbParcelas.setDisable(true);
        lbValorPago.setDisable(true);
        tfValorPago.setDisable(true);

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
