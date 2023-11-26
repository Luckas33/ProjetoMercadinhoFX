package testes;

import estoques.*; 
import usuarios.*;
import produtos.*;

public class testaEstoque {
    public static void main(String[] args) {
        testeEstoque();        
    }

    public static void testeEstoque(){
        IEstoque estoque = new Estoque();
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        Produto produto2 = new Produto("Feijão Bom", "158", "KokoX", 15.66, "Comestivel");
        Produto produto3 = new Produto("Detegente", "5", "maikon", 5.46, "Limpeza");

        estoque.inserir(produto1, 2);
        estoque.inserir(produto2, 4);
        estoque.inserir(produto3, 3);

        //System.out.println(estoque.existe(null));

        System.out.println("ANTES");
        estoque.mostrarEstoqueTotal();
        
        try {
            System.out.println("DEPOIS");
            estoque.reduzir("123", 2);    
            estoque.mostrarEstoqueTotal();
        } catch (Exception e) {
            System.out.println("PROBLEMA EM REDUZIR");
        }

    }
}
