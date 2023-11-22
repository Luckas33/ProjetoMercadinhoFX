package com.programa.projetomercadofx;

import estoques.Estoque;
import estoques.IEstoque;
import globalService.ListaGerente;
import globalService.ListaVendedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 400);
        stage.setTitle("Mercado app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ListaGerente listaGerente = new ListaGerente();
        ListaVendedor listaVendedor = new ListaVendedor();

        launch();


    }
}