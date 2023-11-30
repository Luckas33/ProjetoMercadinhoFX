package testes;

import bancoDados.FileSave;
import estoques.*;
import globalService.ListaFuncionario;
import produtos.*;
import registros.IRegistro;
import registros.Registro;
import usuarios.Gerente;
import usuarios.Vendedor;

public class testaAnderson {
    public static void main(String[] args) {
        //testeEstoque();        
        // testeRegistro2();
        testeFuncionario2();
    }

    public static void testeFuncionario2(){
        ListaFuncionario listaFuncionario = new ListaFuncionario();
        
        System.out.println(listaFuncionario.verificarCredenciaisGerente("Bartolomeu", "batman"));
        
    }

    public static void testeFuncionario(){
        IEstoque estoque = new Estoque();
        IRegistro registro = new Registro();
        ListaFuncionario listaFuncionario = new ListaFuncionario();
        
        Gerente gerente1 = new Gerente(registro, estoque, "rodrigo", "jrodri", "rodri@gmail","123");
        Gerente gerente2 = new Gerente(registro, estoque, "Bartolomeu", "Bart", "Batoré@gmail","batman");
        
        try {
            FileSave.apagarObjetos("src/main/java/arquivos/Funcionarios.txt"); 
            listaFuncionario.cadastraFuncionario(gerente1);
            listaFuncionario.cadastraFuncionario(gerente2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(listaFuncionario.verificarCredenciaisGerente("jrodri", "123"));
        }
        
    }

    public static void testeRegistro2(){
        IEstoque estoque = new Estoque();
        IRegistro registro = new Registro();
        Gerente gerente = new Gerente(registro, estoque, "rodrigo", "jrodri", "rodri@gmail","123");
        
        System.out.println(estoque.procurar("123").getTaxaLucro());
        //System.out.println(gerente.retornaSaldo());

        gerente.atualizarTaxa("123", 5.0);

        System.out.println(estoque.procurar("123").getTaxaLucro());
    }
    public static void testeRegistro(){
        IEstoque estoque = new Estoque();
        IRegistro registro = new Registro();
        Gerente gerente = new Gerente(registro, estoque, "rodrigo", "jrodri", "rodri@gmail","123");
        gerente.limparTudo();
        System.out.println("Após limpeza");
        gerente.inserirSaldo(400.0);
        Produto produto1 = new Produto("refrigerante", "9", "coca cola", 20.0, "bebida");
        Produto produto2 = new ProdutoComestivel("Feijão", "123", "Kokoko", 15.66, "Comestivel","12/05/2028");
        
        try {
            gerente.cadastrar(produto1, 5, 1.2);
            gerente.cadastrar(produto2, 10, 2.5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // for(Produto produto: estoque.retornaEstoque()){
        //         System.out.println(produto);
        // }

        // for(ProdutoHistorico produto: registro.retornaRegistro()){
        //         System.out.println(produto);
        // }

        System.out.println("Após cadastro");



        Vendedor vendedor = new Vendedor(registro, estoque, "joao", "joaozin", "susu" , "123");

        try {
            vendedor.venderDebito("123", 8);
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

