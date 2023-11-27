
package usuarios;


import estoques.IEstoque;
import excecao.*;
import produtos.Produto;
import produtos.ProdutoComestivel;
import produtos.ProdutoHistorico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Vendedor extends Funcionario {
    //atributos
    private double taxaCredito;
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //construtor
    public Vendedor(IEstoque estoque, String nome, String login, String email, String senha) {
        super(estoque, nome, login, email, senha);
        this.taxaCredito = 1.2;
    }


    // **minha gambiarra abaixo** //

    public void venderDinheiro(String id, int quantidade) throws DVIException, QIException, PIException, QINException{

            if(quantidade > 0){
        
        Produto produto = this.estoque.procurar(id); //checa se tem o produto no estoque
        if(produto != null) {
            double valorTotal = quantidade * produto.getPrecoVenda(); //calcula o valor da venda
            if (produto instanceof ProdutoComestivel) {

                LocalDate data = LocalDate.parse(((ProdutoComestivel) produto).getValidade(), formatador);
                if(date.isBefore(data)){

                if (quantidade <= produto.getQuantidade()) { //checa se a quantidade desejada pelo cliente tem o suficiente no estoque
                    try {
                        this.estoque.definirSaldo(this.estoque.verSaldo() + valorTotal); //atualiza o saldo
                    } catch (SNException e) {
                        e.printStackTrace();
                    }
                    this.estoque.reduzir(produto.getId(), quantidade); //chama o metodo reduzir da interface estoque, onde tira a quantidade vendida do estoque
                    ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade); //cria um objeto produto historico, onde os atributos são os do produto vendido
                    this.registrarVenda(produtoHistorico); //registra a venda

                } else {
                    throw new QIException(id, produto.getQuantidade(), quantidade);
                }
            }
        }
            else{
                if (quantidade <= produto.getQuantidade()) { //checa se a quantidade desejada pelo cliente tem o suficiente no estoque
                    try {
                        this.estoque.definirSaldo(this.estoque.verSaldo() + valorTotal); //atualiza o saldo
                    } catch (SNException e) {
                        e.printStackTrace();
                    }
                    this.estoque.reduzir(produto.getId(), quantidade); //chama o metodo reduzir da interface estoque, onde tira a quantidade vendida do estoque
                    ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade); //cria um objeto produto historico, onde os atributos são os do produto vendido
                    this.registrarVenda(produtoHistorico); //registra a venda

                } else {
                    throw new QIException(id, produto.getQuantidade(), quantidade);
                }
            }
        }
        else
            throw new PIException(id);
        }
        else if(quantidade < 0)
            throw new QINException(quantidade);

    }



    //metodo para vender por cartão de crédito(**teste**)
     public void venderCredito(String id, int quantidade, int parcelas) throws  DVIException, PIException, QIException, QINException{


            if (quantidade > 0) {

                Produto produto = this.estoque.procurar(id); //procura no vetor estoque
                if (produto != null) {
                    if (produto instanceof ProdutoComestivel) {

                        LocalDate data = LocalDate.parse(((ProdutoComestivel) produto).getValidade(), formatador);
                        if (date.isBefore(data)) {

                            if (quantidade <= produto.getQuantidade()) { //checa a quantidade
                                double valorTotal = quantidade * produto.getPrecoVenda() * taxaCredito; //calcula o valor da venda, dessa vez adicionando a taxa de crédito
                                try {
                                    this.estoque.definirSaldo(this.estoque.verSaldo() + valorTotal); //atualiza o saldo
                                } catch (SNException e) {
                                    e.printStackTrace();
                                }
                                this.estoque.reduzir(produto.getId(), quantidade); //reduz a quantidade do estoque
                                ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade);
                                this.registrarVenda(produtoHistorico); //registra a venda

                            } else {
                                throw new QIException(id, produto.getQuantidade(), quantidade);
                            }
                        }
                        else
                            throw new DVIException(id, data);
                    } else {
                        if (quantidade <= produto.getQuantidade()) { //checa a quantidade
                            double valorTotal = quantidade * produto.getPrecoVenda() * taxaCredito; //calcula o valor da venda, dessa vez adicionando a taxa de crédito
                            try {
                                this.estoque.definirSaldo(this.estoque.verSaldo() + valorTotal); //atualiza o saldo
                            } catch (SNException e) {
                                e.printStackTrace();
                            }
                            this.estoque.reduzir(produto.getId(), quantidade); //reduz a quantidade do estoque
                            ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade);
                            this.registrarVenda(produtoHistorico); //registra a venda

                        } else {
                            throw new QIException(id, produto.getQuantidade(), quantidade);
                        }
                    }
                } else
                    throw new PIException(id); //produto ja existente
            } else if (quantidade < 0)
                throw new QINException(quantidade); // quantidade negativa
        }






    //metodo para vender no debito(**teste**)
      public void venderDebito(String id, int quantidade) throws DVIException, QINException, QIException, PIException{
        if(quantidade>0) {
            Produto produto = this.estoque.procurar(id); //procura no vetor estoque
            if (produto != null) {
                if (produto instanceof ProdutoComestivel){

                    LocalDate data = LocalDate.parse(((ProdutoComestivel) produto).getValidade(), formatador);
                    if (date.isBefore(data)){

                if (quantidade <= produto.getQuantidade()) { //checa a quantidade
                    double valorTotal = quantidade * produto.getPrecoVenda(); //calcula o valor da venda
                    try {
                        this.estoque.definirSaldo(this.estoque.verSaldo() + valorTotal); //atualiza o saldo
                    } catch (SNException e) {
                        e.printStackTrace();
                    }
                    this.estoque.reduzir(produto.getId(), quantidade); //reduz a quantidade
                    ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade);
                    this.registrarVenda(produtoHistorico); //registra a venda

                } else {
                    throw new QIException(id, produto.getQuantidade(), quantidade);
                }
            }else{
                        throw new DVIException(id,data);
                    }
        }
                else{
                    if (quantidade <= produto.getQuantidade()) { //checa a quantidade
                        double valorTotal = quantidade * produto.getPrecoVenda(); //calcula o valor da venda
                        try {
                            this.estoque.definirSaldo(this.estoque.verSaldo() + valorTotal); //atualiza o saldo
                        } catch (SNException e) {
                            e.printStackTrace();
                        }
                        this.estoque.reduzir(produto.getId(), quantidade); //reduz a quantidade
                        ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade);
                        this.registrarVenda(produtoHistorico); //registra a venda

                    } else {
                        throw new QIException(id, produto.getQuantidade(), quantidade);
                    }
                }
        }else
            throw new PIException(id); //produto ja existente
        }else if(quantidade < 0)
            throw new QINException(quantidade); //quantidade negativa

    }



      //metodo para mostrar o troco
      //public void troco(double valor){
           // System.out.println("Seu troco é: " + valor);
      //}
      //metodo para registrar a venda, adiciona no vetor de historico e seta a forma como "venda"
      public void registrarVenda(ProdutoHistorico produto){
          if(produto != null){
              Gerente.produtoHist.add(produto);
              produto.setForma("Venda");
          }
      }
      
      public Produto retornaProduto(String id){
        Produto produto = this.estoque.procurar(id);
        if(produto != null){
            return produto;
        }
        return null;
      }


    @Override
    public String toString() {
        return "Vendedor: " + "\n" + "Nome: " + nome + "\n" + "Login: " + login + "\n" + "Email: " + email + "\n" +"Senha: " + senha + "\n";
    }
}
