package com.programa.projetomercadofx;

import estoques.Estoque;
import estoques.IEstoque;
import globalService.ListaEstoque;
import globalService.ListaFuncionario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import registros.IRegistro;
import registros.Registro;
import usuarios.Funcionario;
import usuarios.Gerente;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Mercado app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // 1. Inicializa o Estoque e Registros base
        IEstoque estoque = new Estoque();
        IRegistro registro = new Registro();
        
        // 2. Acessa as instâncias únicas através do Singleton
        ListaEstoque listaEstoque = ListaEstoque.getInstance();
        ListaFuncionario.getInstance();

        // 3. Adiciona os objetos às listas via instâncias
        listaEstoque.getEstoqueVector().add(estoque);
        listaEstoque.getRegistroVector().add(registro);

        // 4. Cria o gerente inicial
        Funcionario gerentee = new Gerente(registro, estoque, "rodrigo", "jrodri", "rodri@gmail","123");

        // Limpa dados antigos, se necessário
        ((Gerente) gerentee).limparTudo();

        // Inicia a aplicação JavaFX
        launch(args);
    }
}
