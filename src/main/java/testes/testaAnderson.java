package testes;

import estoques.*;
import produtos.*;
import registros.IRegistro;
import registros.Registro;
import usuarios.Gerente;
import usuarios.Vendedor;

public class testaAnderson {
    public static void main(String[] args) {
        //testeEstoque();        
        testeRegistro();
    }

    public static void testeRegistro(){
        IEstoque estoque = new Estoque();
        IRegistro registro = new Registro();
        Gerente gerente = new Gerente(registro, estoque, "rodrigo", "jrodri", "rodri@gmail","123");
        gerente.limparTudo();
        System.out.println("Após limpeza");
        gerente.inserirSaldo(140.0);
        Produto produto1 = new Produto("refrigerante", "9", "coca cola", 20.0, "bebida");

        try {
            gerente.cadastrar(produto1, 5, 1.2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Produto produto: estoque.retornaEstoque()){
                System.out.println(produto);
        }

        for(ProdutoHistorico produto: registro.retornaRegistro()){
                System.out.println(produto);
        }

        System.out.println("Após cadastro");



        Vendedor vendedor = new Vendedor(registro, estoque, "joao", "joaozin", "susu" , "123");

        try {
            vendedor.venderDebito("9", 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gerente.verBalancoTotal();
    }

    public static void testeEstoque(){
        IEstoque estoque = new Estoque();
        System.out.println("ESTOQUE INICIAL");


        System.out.println("---------------------------------");

        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        Produto produto2 = new Produto("Feijão Bom", "158", "KokoX", 15.66, "Comestivel");
        Produto produto3 = new Produto("Detegente", "5", "maikon", 5.46, "Limpeza");
        estoque.inserir(produto1, 2);
        estoque.inserir(produto2, 4);
        estoque.inserir(produto3, 5);


        try {
            estoque.inserir(estoque.procurar("158"), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ESTOQUE FINAL");

    }
}

