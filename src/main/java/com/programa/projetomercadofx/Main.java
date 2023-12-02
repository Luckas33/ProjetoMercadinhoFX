package com.programa.projetomercadofx;

import estoques.Estoque;
import estoques.IEstoque;
import globalService.ListaEstoque;
import globalService.ListaFuncionario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import registros.IRegistro;
import registros.Registro;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 400);
        stage.setTitle("Mercado app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        IEstoque estoque = new Estoque();
        IRegistro registro = new Registro();
        ListaFuncionario listaFuncionario = new ListaFuncionario();
        ListaEstoque listaEstoque = new ListaEstoque();
        ListaEstoque.estoqueVector.add(estoque);
        ListaEstoque.registroVector.add(registro);
        //
        launch();


    }
}