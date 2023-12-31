package com.programa.projetomercadofx;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import excecao.DVIException;
import excecao.PIException;
import excecao.QINException;
import excecao.SIException;
import excecao.DVIException;
import globalService.ListaFuncionario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import usuarios.Funcionario;
import usuarios.Gerente;

public class FinanceiroMainController {
    //////////// ID dos componentes da tela //////////////
    @FXML
    private Label lbSaldoAtual;
    @FXML
    private TextField tfMudarTaxa;
    @FXML
    private Button btVerBalanço;
    @FXML
    private Button btVoltar;
    @FXML
    private Button btCaixaSaque;
    @FXML
    private Button btCaixaDeposito;
    @FXML
    private Parent root;
    @FXML
    private Button btComprarProduto;
    @FXML
    private Button btCaixa;
    @FXML
    private Pane pnCaixaDeposito;
    @FXML
    private Pane pnCaixaSaque;
    @FXML
    private Pane pnTaxa;
    @FXML
    private Pane pnComprarProduto;
    @FXML
    private Pane pnRemoverProduto;
    @FXML
    private ChoiceBox<String> choiceBoxEscolhaCaixa;
    @FXML
    private Button btConfirmarCompraProduto;
    @FXML
    private Button btConfirmarRemoverProduto;
    @FXML
    private Button btConfirmarTaxa;
    @FXML
    private TextField tfIDCompraProduto;
    @FXML
    private TextField tfQuantidadeComprarProduto;
    @FXML
    private TextField tfIDTaxa;
    @FXML
    private TextField tfValorDeposito;
    @FXML
    private TextField tfValorSaque;
    @FXML
    private TextField tfRemoverProduto;
    @FXML
    private Button btRemoverProduto;
    /////////////////Método para trocar de tela///////////////////
    public void switchToGerenteMainScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("GerenteMainScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
    }
    public void switchToFinanceiroBalancoScreen(ActionEvent event) throws Exception {
        Parent tela1 = FXMLLoader.load(getClass().getResource("FinanceiroBalancoScreen.fxml"));
        Scene cenaAtual = root.getScene();
        Scene cenaTela1 = new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight());
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(cenaTela1);
        choiceBoxEscolhaCaixa.setVisible(false);
    }

    ///////////// Botões de escolha de operação /////////////
    public void onBtMudarTaxa(ActionEvent event){
        pnTaxa.setDisable(false);
        pnTaxa.setVisible(true);
        //impedindo dos outros aparecer
        pnCaixaDeposito.setDisable(true);
        pnCaixaDeposito.setVisible(false);
        pnCaixaSaque.setVisible(false);
        pnCaixaSaque.setDisable(true);
        pnComprarProduto.setDisable(true);
        pnComprarProduto.setVisible(false);
        choiceBoxEscolhaCaixa.setVisible(false);
        pnRemoverProduto.setDisable(true);
        pnRemoverProduto.setVisible(false);
    }

    public void onBtComprarProduto(ActionEvent event){
        pnComprarProduto.setDisable(false);
        pnComprarProduto.setVisible(true);
        //impedindo dos outros aparecer
        pnCaixaDeposito.setDisable(true);
        pnCaixaDeposito.setVisible(false);
        pnCaixaSaque.setVisible(false);
        pnCaixaSaque.setDisable(true);
        pnTaxa.setDisable(true);
        pnTaxa.setVisible(false);
        choiceBoxEscolhaCaixa.setVisible(false);
        pnRemoverProduto.setDisable(true);
        pnRemoverProduto.setVisible(false);
    }

    public void onBtCaixa(ActionEvent event){

        choiceBoxEscolhaCaixa.setVisible(true);
        choiceBoxEscolhaCaixa.setDisable(false);
        //impedindo dos outros aparecer
        pnCaixaDeposito.setDisable(true);
        pnCaixaDeposito.setVisible(false);
        pnCaixaSaque.setVisible(false);
        pnCaixaSaque.setDisable(true);
        pnTaxa.setDisable(true);
        pnTaxa.setVisible(false);
        pnComprarProduto.setDisable(true);
        pnComprarProduto.setVisible(false);
        pnRemoverProduto.setDisable(true);
        pnRemoverProduto.setVisible(false);

    }

    public void onCbEscolhaCaixa(Event event){
        String escolha = choiceBoxEscolhaCaixa.getValue();

        if("Depósito".equals(escolha)){
            pnCaixaDeposito.setDisable(false);
            pnCaixaDeposito.setVisible(true);

            pnCaixaSaque.setDisable(true);
            pnCaixaSaque.setVisible(false);
        }else if("Saque".equals(choiceBoxEscolhaCaixa.getValue())){
            pnCaixaSaque.setDisable(false);
            pnCaixaSaque.setVisible(true);

            pnCaixaDeposito.setDisable(true);
            pnCaixaDeposito.setVisible(false);
        }else{
            pnCaixaSaque.setDisable(true);
            pnCaixaSaque.setVisible(false);

            pnCaixaDeposito.setDisable(true);
            pnCaixaDeposito.setVisible(false);
        }
    }

    public void onBtRemoverProduto(){
        pnRemoverProduto.setDisable(false);
        pnRemoverProduto.setVisible(true);
        /////////////////apagar os outros
        choiceBoxEscolhaCaixa.setVisible(false);
        choiceBoxEscolhaCaixa.setDisable(true);
        pnCaixaDeposito.setDisable(true);
        pnCaixaDeposito.setVisible(false);
        pnCaixaSaque.setVisible(false);
        pnCaixaSaque.setDisable(true);
        pnTaxa.setDisable(true);
        pnTaxa.setVisible(false);
        pnComprarProduto.setDisable(true);
        pnComprarProduto.setVisible(false);
    }
    ///////////// Botões de confirmação e preenchimento da escolha feita ///////////////////
    public void onBtConfirmarCaixaDeposito(ActionEvent e){
        try{
            double valor = Double.parseDouble(tfValorDeposito.getText());

            for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                if(funcionario instanceof Gerente){
                    ((Gerente) funcionario).inserirSaldo(valor);
                    Alerts.showAlert("Depósito",null,"Depósito concluído",Alert.AlertType.INFORMATION);
                    ((Gerente) funcionario).conferirSaldo();
                    atualizarSaldoTotal();
                    break;
                }
            }
        }catch (SIException exception){
            exception.printStackTrace();
            Alerts.showAlert("Erro", null, "Insira valor válido", Alert.AlertType.ERROR);
        }

    }

    public void onBtConfirmarCaixaSaque(ActionEvent e){
            double valor = Double.parseDouble(tfValorSaque.getText());

            for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                if(funcionario instanceof Gerente){
                    if(valor > 0) {
                        ((Gerente) funcionario).removerSaldo(valor);
                        ((Gerente) funcionario).conferirSaldo();
                        //Alerts.showAlert("Saque", null, "Saque concluído", Alert.AlertType.INFORMATION);
                        atualizarSaldoTotal();
                    }else{
                        Alerts.showAlert("Erro", null, "Insira valor válido", Alert.AlertType.ERROR);
                    }
                    break;
                }
            }
    }

    public void onBtConfirmarTaxa(ActionEvent e){
        double taxa = Double.parseDouble(tfMudarTaxa.getText());
        String id = tfIDTaxa.getText();

        if(taxa > 0){
            for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                if(funcionario instanceof Gerente){
                    ((Gerente) funcionario).atualizarTaxa(id, taxa);
                    break;
                }
            }
            Alerts.showAlert("Taxa",null,"Taxa Atualizada",Alert.AlertType.INFORMATION);
        }else {
            Alerts.showAlert("Erro Taxa",null,"Taxa não Atualizada",Alert.AlertType.ERROR);
        }
    }

    public void onBtConfirmarCompraProd(ActionEvent event){
        String id = tfIDCompraProduto.getText();
        int quantidade = Integer.parseInt(tfQuantidadeComprarProduto.getText());


        for (Funcionario funcionario : ListaFuncionario.funcionariosVector) {
            if (funcionario instanceof Gerente) {
                try {
                    ((Gerente) funcionario).adicionar(id, quantidade);
                    Alerts.showAlert("Compra",null,"Produto comprado com sucesso",Alert.AlertType.INFORMATION);
                    onBtLimpar(event);
                    atualizarSaldoTotal();
                }catch(SIException e){
                    e.printStackTrace();
                    Alerts.showAlert("Erro Compra",null,"Saldo insuficiente",Alert.AlertType.INFORMATION);
                }catch(PIException e){
                    e.printStackTrace();
                    Alerts.showAlert("Erro Compra",null,"Produto inexistente",Alert.AlertType.INFORMATION);
                }catch(QINException e){
                    e.printStackTrace();
                    Alerts.showAlert("Erro Compra",null,"Quantidade inválida",Alert.AlertType.INFORMATION);
                }catch(DVIException e){
                    e.printStackTrace();
                    Alerts.showAlert("Erro Compra",null,"Data de validade ultrapassada",Alert.AlertType.INFORMATION);
                }
                break;
            }
        }
    }

    public void onBtConfirmarRemoverProduto(ActionEvent event){
        String id = tfRemoverProduto.getText();
        for (Funcionario funcionario : ListaFuncionario.funcionariosVector){
            if(funcionario instanceof Gerente){
                try {
                    ((Gerente) funcionario).removerProduto(id);
                    Alerts.showAlert("Remoção do Estoque",null,"Produto removido com sucesso",Alert.AlertType.INFORMATION);
                    onBtLimpar(event);
                }catch(PIException e){
                    Alerts.showAlert("Erro Remover",null,"Produto não se encontra no estoque.",Alert.AlertType.ERROR);
                }

                break;
            }
        }
    }

    /////////// Métodos Complementares /////////////////////////////
    public void onBtLimpar(ActionEvent e){
        tfValorDeposito.setText(null);
        tfIDCompraProduto.setText(null);
        tfMudarTaxa.setText(null);
        tfValorSaque.setText(null);
        tfIDCompraProduto.setText(null);
        tfQuantidadeComprarProduto.setText(null);
        choiceBoxEscolhaCaixa.setDisable(true);
        tfRemoverProduto.setText(null);
    }

    public void atualizarSaldoTotal(){
        try{
            String saldoTotal = "0.0";

            for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                if(funcionario instanceof Gerente){
                    saldoTotal = String.valueOf(((Gerente) funcionario).retornaSaldo());
                    break;
                }
            }
            lbSaldoAtual.setText(saldoTotal);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize(){
        atualizarSaldoTotal();
        choiceBoxEscolhaCaixa.getItems().addAll("Depósito","Saque");

        choiceBoxEscolhaCaixa.setOnAction(this::onCbEscolhaCaixa);
        choiceBoxEscolhaCaixa.setOnMouseClicked(this::onCbEscolhaCaixa);


        choiceBoxEscolhaCaixa.setDisable(true);
        choiceBoxEscolhaCaixa.setVisible(false);

        pnCaixaDeposito.setDisable(true);
        pnCaixaDeposito.setVisible(false);

        pnCaixaSaque.setVisible(false);
        pnCaixaSaque.setDisable(true);

        pnTaxa.setDisable(true);
        pnTaxa.setVisible(false);

        pnComprarProduto.setDisable(true);
        pnComprarProduto.setVisible(false);

        pnRemoverProduto.setDisable(true);
        pnRemoverProduto.setVisible(false);
    }


}