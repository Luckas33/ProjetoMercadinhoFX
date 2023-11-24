
package usuarios;

import estoques.IEstoque;
import excecao.*;
import globalService.ListaProduto;
import produtos.Produto;
import produtos.ProdutoHistorico;

import java.util.Vector;


public class Gerente extends Funcionario {
    
    //cria o vetor onde vai ta o historico de vendas e compras do mercado
   public static Vector<ProdutoHistorico> produtoHist;
    
    //construtor
    public Gerente(IEstoque estoque, String nome, String login, String email, String senha) {
        super(estoque, nome, login, email, senha);
        Gerente.produtoHist = new Vector<ProdutoHistorico>();
    }


    // *****teste*****//
    //metodo para cadastrar um produto no estoque, checa se o produto ja existe no estoque, se não existir
    //ele cadastra
     public void cadastrar(String id, int quantidade, double taxalucro) throws PEException, SIException, QNUException, QNException {
        Produto produto = ListaProduto.checarProduto(id);
        if(quantidade>0){
        if(produto != null){ 
            if(!this.estoque.existe(produto.getId())){//calcula o valor total da compra
              double valorTotal = (quantidade * produto.getPreco_compra());

                if(this.estoque.verSaldo() >= valorTotal){ //checa se o saldo do mercado é maior que o preço da compra
                        try{
                            produto.setTaxaLucro(taxalucro);  //seta a taxa de lucro que esse produto vai ter
                        } catch(TLNException e){ // testa se a taxa lucro é negatica
                            System.out.print(e.getMessage());
                            System.out.print(" Taxa: ");
                            System.out.print(taxalucro); // **n seria taxaLucro??**
                        } catch(TLNUException e){ // testa se a taxa lucro é nula
                            System.out.print(e.getMessage());
                        }
                        produto.setPrecoVenda(produto.getPreco_compra() * produto.getTaxaLucro()); //aqui ele seta o preço de venda, sendo a multiplicação do preço de compra pela taxa de lucro
                        this.estoque.inserir(produto.getId(), quantidade); //ele chama o metodo inserir da interface de estoque, onde ele insere o produto e a quantidade no estoque
                        this.estoque.adquirirEstoque(produto.getId(),valorTotal); //atualiza o saldo
                        ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade); //cria um objeto de produto historico, onde os atributos dele vão ser os mesmos do que o produto que foi vendido
                    this.registrarCompra(produtoHistorico); //registra a compra, colocando-a no vetor do historico
                }
                else
                    throw new SIException(produto.getId(),this.estoque.verSaldo(), valorTotal); //restrição de saldo insuficiente para adquirir novos produtos
            }
            else
                throw new PEException(produto.getId()); //restricao se o produto ja existe
        }
        }else if(quantidade==0)
            throw new QNUException();//restriçao se a quantidade for nula
         else
            throw new QNException(quantidade); //restricao se a quantidade for negativa
    }

    //metodo para adicionar mais produtos no estoque, caso ele ja esteja cadastrado
    //se nao tiver cadastrado, nao vai adicionar
    //esqueci de salvar esse método sem o método adquirirEstoque em vez de definirSaldo
      public void adicionar(String id, int quantidade) throws SIException, PIException, QNUException, QNException {
        Produto produto = this.estoque.procurar(id);
        if (quantidade>0){
        if(produto != null){
            double valorTotal = (quantidade * produto.getPreco_compra());  //valor da compra
            if(this.estoque.existe(produto.getId())){
               if(this.estoque.verSaldo() >= valorTotal){    //checa o saldo
                 this.estoque.inserir(produto.getId(), quantidade);  //insere no estoque
                 this.estoque.adquirirEstoque(produto.getId(),valorTotal);
                 ProdutoHistorico produtoHistorico = new ProdutoHistorico(produto.getId(), valorTotal, quantidade);
                 this.registrarCompra(produtoHistorico); //registra a compra
            }
               else{
                   throw new SIException(produto.getId(),this.estoque.verSaldo(), valorTotal); // restrição se o cara nao tem saldo o suficiente para bancar o a compra de novos produtos
               }
            }
            else{
                throw new PIException(produto.getId()); // restricao se o produto nao foi cadastrado ainda
            }
        }
        }else if(quantidade==0)
            throw new QNUException();//restriçao se a quantidade for nula
         else
            throw new QNException(quantidade); //restricao se a quantidade for negativa 
    }

    //metodo pro gerente ver o estoque, ele somente chama o metodo de mostrar o estoque da interface estoque
      public void verEstoque(String tipo){
          this.estoque.mostrarEstoque(tipo);
      }

    //metodo para registrar a compra, ele recebe um objeto de produto historico, adiciona no vetor e seleciona a forma como "compra"
public void registrarCompra(ProdutoHistorico produto){
          if(produto != null){
              Gerente.produtoHist.add(produto);
              produto.setForma("Compra");
          }
}
   //ver o balanço financeiro pela data, escreve uma data e mostra todos os produtos vendidos nesse dia, quanto entrou, quanto saiu 
    public void verBalancoData(String data){
          double ganho = 0.0; 
          double perda = 0.0;
       
         for(ProdutoHistorico produto : Gerente.produtoHist){ // percorre todoo o vetor de historico
            if(data.equals(produto.getData())){  //pega todos os produtos que foram vendidos/comprados na data escrita
             System.out.println(produto);  //printa todos eles
             if(produto.getForma() == "Venda"){
             ganho += produto.getPreco();  //calcula quanto ganhou
             }
             else{
                 perda += produto.getPreco();  //calcula quanto perdeu
             }
            }
         }
         double balanco = ganho - perda;
         System.out.println("Saiu: " + perda + "\n" + "Entrou: " + ganho);  //mpstra os ganhos e perdas
         System.out.printf("Balanço final: $%.2f\n", balanco); //mostra o balanço do dia (sem contar o saldo setado inicialmente, so o quanto foi comprado e vendido)
      }

    //mostra todos os produtos vendidos e comprados, mesmo funcionamento do anterior
    public void verBalancoTotal(){
          double ganho = 0.0;
          double perda = 0.0;
         for(ProdutoHistorico produto : Gerente.produtoHist){ 
             System.out.println(produto);
             if(produto.getForma() == "Venda"){
             ganho += produto.getPreco();
             }
             else{
                 perda += produto.getPreco();
             }
            
         }
         double balanco = ganho - perda;
         System.out.println("Saiu: " + perda + "\n" + "Entrou: " + ganho);
         System.out.printf("Balanço final: $%.2f\n", balanco);
         System.out.println("Saldo do mercado: " + this.estoque.verSaldo());
         
      }

    //metodo para setar o saldo inicial do mercado
    public void iniciarSaldo(double valor){
        try{
            this.estoque.definirSaldo(valor);
        }catch(SNException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo: ");
            System.out.print(valor);
        }
    }

    @Override
    public String toString() {
        return "Gerente: " + "\n" + "Nome: " + nome + "\n" + "Login: " + login + "\n" + "Email: " + email + "\n" +"Senha: " + senha + "\n";
    }
}
      
