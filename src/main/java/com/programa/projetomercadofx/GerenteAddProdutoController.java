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
import produtos.ProdutoComestivel;
import produtos.ProdutoNaoComestivel;
import usuarios.Funcionario;
import usuarios.Gerente;
import static com.programa.projetomercadofx.controllerUtil.IsNumeric.isNumeric;

public class GerenteAddProdutoController {
    @FXML
    private Button btVoltar;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btAddProduto;
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
        Stage palco = (Stage) cenaAtual.getWindow();
        palco.setScene(new Scene(tela1, cenaAtual.getWidth(), cenaAtual.getHeight()));
    }

    @FXML
    public void onCBCategoriaProd(Event e) {
        String cat = choiceBoxCategoriaProd.getValue();
        tfDataValidade.setDisable(!"Comestível".equals(cat));
    }

    public void onBtAddProduto(ActionEvent e) {
        String categoria = choiceBoxCategoriaProd.getValue();
        String id = tfID.getText();

        if (categoria == null || !isNumeric(id)) {
            Alerts.showAlert("Erro", null, "Verifique categoria e ID (numérico).", Alert.AlertType.ERROR);
            return;
        }

        Gerente gerente = null;
        for (Funcionario f : ListaFuncionario.getInstance().getFuncionariosVector()) {
            if (f instanceof Gerente) {
                gerente = (Gerente) f;
                break;
            }
        }

        if (gerente != null) {
            executarCadastro(gerente, e);
        } else {
            Alerts.showAlert("Erro", null, "Nenhum gerente encontrado no sistema.", Alert.AlertType.ERROR);
        }
    }

    private void executarCadastro(Gerente gerente, ActionEvent e) {
        try {
            double preco = Double.parseDouble(tfPrecoCompra.getText());
            int qtd = Integer.parseInt(tfQuantidade.getText());
            double taxa = Double.parseDouble(tfTaxaVenda.getText());

            Produto p = choiceBoxCategoriaProd.getValue().equals("Comestível") 
                ? new ProdutoComestivel(tfNome.getText(), tfID.getText(), tfMarca.getText(), preco, tfTipo.getText(), tfDataValidade.getText())
                : new ProdutoNaoComestivel(tfNome.getText(), tfID.getText(), tfMarca.getText(), preco, tfTipo.getText());

            gerente.cadastrar(p, qtd, taxa);
            Alerts.showAlert("Sucesso", null, "Produto adicionado!", Alert.AlertType.INFORMATION);
            onBtLimpar(e);
        } catch (Exception ex) {
            tratarErro(ex, e);
        }
    }

    private void tratarErro(Exception ex, ActionEvent e) {
        String msg = "Erro: " + ex.getMessage();
        if (ex instanceof QINException) msg = "Quantidade requerida negativa.";
        else if (ex instanceof PEException) msg = "Produto já cadastrado.";
        else if (ex instanceof SIException) msg = "Saldo insuficiente.";
        else if (ex instanceof DVIException) msg = "Data de validade ultrapassada.";
        
        Alerts.showAlert("Erro de Cadastro", null, msg, Alert.AlertType.ERROR);
    }

    public void onBtLimpar(ActionEvent e) {
        tfNome.setText(null); tfID.setText(null); tfDataValidade.setText(null);
        tfMarca.setText(null); tfPrecoCompra.setText(null); tfTipo.setText(null);
        tfTaxaVenda.setText(null); tfQuantidade.setText(null);
        choiceBoxCategoriaProd.setValue(null);
    }

    @FXML
    public void initialize() {
        choiceBoxCategoriaProd.getItems().addAll("Comestível", "Não Comestível");
        choiceBoxCategoriaProd.setOnAction(this::onCBCategoriaProd);
        tfDataValidade.setDisable(true);
    }
}