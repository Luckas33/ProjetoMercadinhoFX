package testes;

import estoques.*;
import produtos.*;

public class testaEstoque {
    public static void main(String[] args) {
        testeEstoque();        
    }

    public static void testeEstoque(){
        IEstoque estoque = new Estoque();
        System.out.println("ESTOQUE INICIAL");


        System.out.println("---------------------------------");
        /*
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        Produto produto2 = new Produto("Feijão Bom", "158", "KokoX", 15.66, "Comestivel");
        Produto produto3 = new Produto("Detegente", "5", "maikon", 5.46, "Limpeza");
        estoque.inserir(produto1, 2);
        estoque.inserir(produto2, 4);
        estoque.inserir(produto3, 5);

        */
        try {
            estoque.inserir(estoque.procurar("158"), 2);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("ESTOQUE FINAL");

    }
}
