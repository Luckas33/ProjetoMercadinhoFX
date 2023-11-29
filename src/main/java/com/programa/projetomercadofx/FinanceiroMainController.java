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
    private Button btSeguir;
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
    private ChoiceBox<String> choiceBoxEscolhaCaixa;
    @FXML
    private Button btConfirmarCompraProduto;
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
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
            Alerts.showAlert("Erro", null, "Insira valor válido", Alert.AlertType.ERROR);
        }

    }

    public void onBtConfirmarCaixaSaque(ActionEvent e){
        try{
            double valor = Double.parseDouble(tfValorSaque.getText());

            for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                if(funcionario instanceof Gerente){
                    ((Gerente) funcionario).removerSaldo(valor);
                    ((Gerente) funcionario).conferirSaldo();
                    Alerts.showAlert("Saque",null,"Saque concluído",Alert.AlertType.INFORMATION);
                    atualizarSaldoTotal();
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
            Alerts.showAlert("Erro", null, "Insira valor válido", Alert.AlertType.ERROR);
        }
    }

    public void onBtConfirmarTaxa(ActionEvent e){
        double taxa = Double.parseDouble(tfMudarTaxa.getText());
        String id = tfIDTaxa.getText();

        if(taxa > 0){
            for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                if(funcionario instanceof Gerente){
                    ((Gerente) funcionario).atualizarTaxa(id, taxa);
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
                    onBtLimpar(event);
                } catch (SIException | PIException | QINException | DVIException e) {
                    e.printStackTrace();
                }
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
    }

    public void atualizarSaldoTotal(){
        try{
            String saldoTotal = "0.0";

            for(Funcionario funcionario : ListaFuncionario.funcionariosVector){
                if(funcionario instanceof Gerente){
                    saldoTotal = String.valueOf(((Gerente) funcionario).retornaSaldo());
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
    }


}