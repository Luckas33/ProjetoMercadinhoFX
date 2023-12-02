package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import static com.programa.projetomercadofx.controllerUtil.IsNumeric.isNumeric;
import excecao.*;
import globalService.ListaFuncionario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import produtos.Produto;
import produtos.ProdutoHistorico;
import usuarios.Funcionario;
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
    private TextField tfValorPago;
    @FXML
    private Button btConfirmarVenda;
    @FXML
    private Label lbSubtotal;
    @FXML
    private ImageView backgroundVendedor;
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

        String id = tfidProduto.getText(); //ID produto
        for (Funcionario funcionario : ListaFuncionario.funcionariosVector) {
            if (funcionario instanceof Vendedor) {
                Produto produto = ((Vendedor) funcionario).retornaProduto(id);
                if (produto != null) {
                    ProdutoHistorico produtoHist = new ProdutoHistorico(produto.getId(), produto.getPrecoVenda(), quantidade);
                    try{
                        produto.setQuantidadeVendida(quantidade);
                    }catch(QINException e){
                        e.printStackTrace();
                        Alerts.showAlert("Erro Venda", null,"Quantidade Inválida",Alert.AlertType.ERROR);
                    }
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
        choiceBoxTipoVenda.setDisable(false);
        btAdicionar.setDisable(true);
        btConfirmarVenda.setDisable(false);
    }

    public void onCbTipoVenda(Event e){   //Método que pega a escolha do choiceBox e ativa e desativa outras opções de escolha
        String escolha = choiceBoxTipoVenda.getValue();
        if("Crédito".equals(escolha)){
            comboboxParcelas.setDisable(false);

        }else if("Dinheiro".equals(escolha)){
            tfValorPago.setDisable(false);
            comboboxParcelas.setDisable(true);
        }else if ("Débito".equals(escolha)){
            tfValorPago.setDisable(true);
            comboboxParcelas.setDisable(true);
        }else{
            tfValorPago.setDisable(true);
            comboboxParcelas.setDisable(true);
        }
    }

    public void onBtConfirmarVenda(ActionEvent event){  //Pega todos os produtos do carrinho e vende um por um com base no tipo de pagamento
        String tipoVenda = choiceBoxTipoVenda.getValue();
        if (!tipoVenda.isEmpty()){
            for (Funcionario funcionario : ListaFuncionario.funcionariosVector) {
                if (funcionario instanceof  Vendedor) {
                    if (tipoVenda == "Débito"){
                        for (int i = 0; i < carrinho.size(); i++) {
                            Produto produto = carrinho.get(i);
                            try {
                                ((Vendedor) funcionario).venderDebito(produto.getId(), produto.getQuantidadeVendida());
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
                                ((Vendedor) funcionario).venderCredito(produto.getId(), produto.getQuantidadeVendida(), parcelas);
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
                                    ((Vendedor) funcionario).venderDinheiro(produto.getId(), produto.getQuantidadeVendida());
                                    Alerts.showAlert("Venda", null, "Venda realizada com sucesso.", Alert.AlertType.INFORMATION);
                                    onBtLimpar(event);
                                } catch ( QINException |
                                         QIException | PIException | DVIException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else if(valorPago == valorFinalD){ //ninguem usa
                            for (int i = 0; i < carrinho.size(); i++) {
                                try {
                                    Produto produto = carrinho.get(i);
                                    ((Vendedor) funcionario).venderDinheiro(produto.getId(), produto.getQuantidadeVendida());
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
    }

    @FXML
    public void initialize() {
        /////// Configurando choiceBox ////////////
        choiceBoxTipoVenda.getItems().addAll("Débito", "Crédito", "Dinheiro");
        comboboxParcelas.getItems().addAll("1", "2", "3", "4", "5");
        choiceBoxTipoVenda.setOnAction(this::onCbTipoVenda);
        choiceBoxTipoVenda.setOnMouseClicked(this::onCbTipoVenda);
        /////////Configurando o estado dos componentes //////////
        btFinalizar.setDisable(true);
        comboboxParcelas.setDisable(true);
        tfValorPago.setDisable(true);
        btConfirmarVenda.setDisable(false);
/////////////////////////////////////////////////////////
        String imagem = "VenderProdutos.png";
        Image banner = new Image(getClass().getResource(imagem).toExternalForm());
        backgroundVendedor.setImage(banner);
        /////////////Configurando vetores e a listView////////////
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
