package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import excecao.DVIException;
import excecao.PEException;
import excecao.QINException;
import excecao.SIException;
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
import produtos.ProdutoComestivel;
import produtos.ProdutoNaoComestivel;
import usuarios.Funcionario;
import usuarios.Gerente;
import usuarios.Vendedor;

import static com.programa.projetomercadofx.controllerUtil.IsNumeric.isNumeric;

public class GerenteAddProdutoController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btAddProduto;
    @FXML
    private Label lbDataValidade;
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
    @FXML
    private TextField tfQuantidade;
    @FXML
    private TextField tfTaxaVenda;



    public void switchToGerenteMainScrenn(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }

    public  void onCBCategoriaProd(Event e ){
        String escolhaSelecionada = choiceBoxCategoriaProd.getValue();

        if ("Comestível".equals(escolhaSelecionada)){
            tfDataValidade.setDisable(false);
            lbDataValidade.setDisable(false);
        }else {
            tfDataValidade.setDisable(true);
            lbDataValidade.setDisable(true);
        }
    }

    public void onBtAddProduto(ActionEvent e){
        double precoCompra = 0;
        String nome = tfNome.getText();
        String id = tfID.getText();
        String marca = tfMarca.getText();
        String tipo = tfTipo.getText();
        String precoCompraText = tfPrecoCompra.getText();
        if(!precoCompraText.isEmpty()){
           precoCompra = Double.parseDouble(precoCompraText);//casting, para transformar String em double
        }
        int quantidade = 0;
        Double taxaLucro = 0.0;
        if(tfQuantidade != null && isNumeric(tfQuantidade.getText())){
            quantidade = Integer.parseInt(tfQuantidade.getText());
        }
        if(tfTaxaVenda != null && isNumeric(tfTaxaVenda.getText())) {
            taxaLucro = Double.parseDouble(tfTaxaVenda.getText());
        }

        String dataValidade = tfDataValidade.getText();
        String categoria = choiceBoxCategoriaProd.getValue();

        Produto produtoComestivel;
        Produto produtoNaoComestivel;

        if(categoria != null){
            if(isNumeric(id) ) {
                if (categoria.equals("Comestível") && !nome.isEmpty() && !id.isEmpty() && !marca.isEmpty() && precoCompra > 0.0 && !tipo.isEmpty() && !dataValidade.isEmpty()) {

                    produtoComestivel = new ProdutoComestivel(nome, id, marca, precoCompra, tipo, dataValidade);
                    for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                        if(funcionario instanceof Gerente){
                            try {
                                ((Gerente) funcionario).cadastrar(produtoComestivel, quantidade, taxaLucro);
                                Alerts.showAlert("Adicionar produto", null, "Produto Comestível adicionado com sucesso.", Alert.AlertType.INFORMATION);
                            }
                            catch (QINException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Quantidade requerida negativa",Alert.AlertType.ERROR);
                            }catch (PEException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Produto já cadastrado",Alert.AlertType.ERROR);
                            }catch(SIException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Saldo insuficiente",Alert.AlertType.ERROR);
                            }catch(DVIException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Data de validade já ultrapassada",Alert.AlertType.ERROR);
                            }
                        }
                    }


                } else if (categoria.equals("Não Comestível") && !nome.isEmpty() && !id.isEmpty() && !marca.isEmpty() && precoCompra > 0.0 && !tipo.isEmpty()) {
                    produtoNaoComestivel = new ProdutoNaoComestivel(nome, id, marca, precoCompra, tipo);
                    for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                        if(funcionario instanceof Gerente){
                            try {
                                ((Gerente) funcionario).cadastrar(produtoNaoComestivel, quantidade, taxaLucro);
                                Alerts.showAlert("Adicionar produto", null, "Produto Não Comestível adicionado com sucesso.", Alert.AlertType.INFORMATION);
                            }catch (QINException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Quantidade requerida negativa",Alert.AlertType.ERROR);
                            }catch (PEException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Produto já cadastrado",Alert.AlertType.ERROR);
                            }catch(SIException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Saldo insuficiente",Alert.AlertType.ERROR);
                            }catch(DVIException exception){
                                exception.printStackTrace();
                                onBtLimpar(e);
                                Alerts.showAlert("Erro cadastrar", null,"Data de validade já ultrapassada",Alert.AlertType.ERROR);
                            }
                        }
                    }


                } else {
                    Alerts.showAlert("Erro adicionar produto", null, "Preencha corretamente as informações", Alert.AlertType.ERROR);
                }
            }else{
                Alerts.showAlert("Erro adicionar produto",null,"Preencha a parte ID somente com números", Alert.AlertType.ERROR);
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
        tfTaxaVenda.setText(null);
        tfQuantidade.setText(null);
    }

    @FXML
    public void initialize(){

        choiceBoxCategoriaProd.getItems().addAll("Comestível","Não Comestível");
        choiceBoxCategoriaProd.setOnAction(this::onCBCategoriaProd);
        choiceBoxCategoriaProd.setOnMouseClicked(this::onCBCategoriaProd);
        tfDataValidade.setDisable(true);
        lbDataValidade.setDisable(true);
    }
}
