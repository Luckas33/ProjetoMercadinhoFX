/*package testes;

import estoques.*;
import excecao.*;
import usuarios.*;

public class testaExcecao {
    public static void main(String[] args) {
        //testeDVIException();
        //testePCIException();
        //testePEException();
        //testePIException();
        //testeQIException();
        //testeQINException();
        //testeSIException();
        // testeSNException();
        //testeTLIException();
    }

    
        /*IEstoque estoque = new Estoque();
        System.out.println("ESTOQUE INICIAL");
        estoque.mostrarEstoqueTotal();
        Gerente gerente = new Gerente(estoque, "nome","login", "email", "senha");
        Vendedor vendedor = new Vendedor(estoque, "nome","login", "email", "senha");
        System.out.println("---------------------------------");
        
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        Produto produto2 = new Produto("Feijão Bom", "158", "KokoX", 15.66, "Comestivel");
        //Produto produto3 = new Produto("Detegente", "5", "maikon", 5.46, "Limpeza");
        //estoque.inserir(produto1, 2);
        //estoque.inserir(produto2, 4);
        //estoque.inserir(produto3, 5);*/
        
     /*public static void testePCIException(){    //preco de compra invalido
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        try{
            produto1.setPrecoCompra(-1);
        }catch(PCIException e){
            System.out.print(e.getMessage());
            System.out.print(" Preço inserido: ");
            System.out.print(e.getPrecoCompra());
        }
    }
        
    public static void testeTLIException(){    //taxa lucro invalida
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        try{
            produto1.setTaxaLucro(-1);
        }catch(TLIException e){
            System.out.print(e.getMessage());
            System.out.print(" Taxa inserida: ");
            System.out.print(e.getTaxa());
        }
    }

    public static void testeQINException(){    //quantidade invalida
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        try{
            produto1.setQuantidadeVendida(-1);
        }catch(QINException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }
    }

    public static void testePIException(){    //produto inexistente
        IEstoque estoque = new Estoque();    
        try{
            estoque.reduzir("1000", 10);
        }catch(PIException e){
            System.out.print(e.getMessage());
            System.out.print(" Id inserido: ");
            System.out.print(e.getId());
        }catch(QINException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }
    }

    public static void testeSNException(){    //saldo negativo
        IEstoque estoque = new Estoque();
        try{
            estoque.definirSaldo(1000);
        }catch(SNException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getSaldo());
        }
    }

    public static void testeSIException(){    //saldo insuficiente
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        IEstoque estoque = new Estoque();
        Gerente gerente = new Gerente(estoque, "nome","login", "email", "senha");
        try{
            estoque.definirSaldo(2);
        }catch(SNException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getSaldo());
        }
        try{
            gerente.cadastrar(produto1, 10, 10);
        }catch(SIException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getSaldo());
        }catch(PEException e){
            System.out.print(e.getMessage());
            System.out.print(" Id inserido: ");

        }
    }

    public static void testePEException(){    //produto já existente
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        IEstoque estoque = new Estoque();
        Gerente gerente = new Gerente(estoque, "nome","login", "email", "senha");   
        try{
            gerente.cadastrar(produto1, 10, 10);
        }catch(SIException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getSaldo());
        }catch(PEException e){
            System.out.print(e.getMessage());
            System.out.print(" Id inserido: ");
            System.out.print(e.getId());
        }catch(DVIException e){
            System.out.print(e.getMessage());
        }catch(QINException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }
        try{
            gerente.cadastrar(produto1, 10, 10);
        }catch(SIException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getSaldo());
        }catch(PEException e){
            System.out.print(e.getMessage());
            System.out.print(" Id inserido: ");
            System.out.print(e.getId());
        }catch(DVIException e){
            System.out.print(e.getMessage());
        }catch(QINException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }
    }

    public static void testeDVIException(){    //data invalida
        Produto produto1 = new ProdutoComestivel("Feijão", "123", "Kokoko", 15.66, "Comestivel", 20/06/2005);
        IEstoque estoque = new Estoque();
        Gerente gerente = new Gerente(estoque, "nome","login", "email", "senha");    
        try{
            gerente.cadastrar(produto1, 10, 10);
        }catch(SIException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getSaldo());
        }catch(PEException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getId());
        }catch(DVIException e){
            System.out.print(e.getMessage());
        }catch(QINException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }
    }

    

    public static void testeQIException(){    //quantidade insuficiente
        Produto produto1 = new Produto("Feijão", "123", "Kokoko", 15.66, "Comestivel");
        IEstoque estoque = new Estoque();
        Vendedor vendedor = new Vendedor(estoque, "nome","login", "email", "senha");       
        Gerente gerente = new Gerente(estoque, "nome","login", "email", "senha");    
        try{
            gerente.cadastrar(produto1, 20, 10);
        }catch(SIException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getSaldo());
        }catch(PEException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getId());
        }catch(DVIException e){
            System.out.print(e.getMessage());
        }catch(QINException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }
        try{
            vendedor.venderCredito("1234", 4000, 400);
        }catch(PAIException e){
            System.out.print(e.getMessage());
            System.out.print(" Número de parcelas inserido: ");
            System.out.print(e.getParcelas());
        }catch(PIException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo inserido: ");
            System.out.print(e.getId());
        }catch(DVIException e){
            System.out.print(e.getMessage());
        }catch(QINException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }catch(QIException e){
            System.out.print(e.getMessage());
            System.out.print(" Quantidade inserida: ");
            System.out.print(e.getQuantidadeRequerida());
        }
    }
    }

*/
