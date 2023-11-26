
package estoques;

import excecao.SNException;
import produtos.Produto;
import produtos.ProdutoComestivel;

import java.util.Vector;





public class Estoque implements IEstoque {

    //atributos
    private Vector<Produto> estoque;
    private double saldo;
    //**teste**
    

    //construtor
    public Estoque(){
        this.estoque = new Vector<Produto>();
        this.saldo = 0.0;
      
    }
   
    //metodo para inserir produtos no estoque  
    // **nao precisa de excecao(eu acho) **
    @Override
    public void inserir(Produto produto, int quantidade){
            if (produto != null) {
                if (!this.existe(produto.getId())) { //checa se o produto não existe no estoque
                    produto.setQuantidade(quantidade);  //se não existir, seta a quantidade
                    this.estoque.add(produto);           //e coloca no vetor de estoque
                } else if (this.existe(produto.getId())) {
                    produto.setQuantidade(produto.getQuantidade() + quantidade); //se já existe no estoque, vai so pegar a quantidade que ja tinha e adicionar a quantidade desejada
                }

            }

           
    }

    //metodo para procurar um produto no estoque
    @Override
    public Produto procurar(String id) {
       for(Produto produto : estoque){ //percorre o vetor estoque
            if(produto.getId().equals(id)){  //se algum produto dentro do estoque tiver o mesmo id do id digitado, retornará ele
                return produto;
            }            
        }
         return null;
    }

    //metodo para ver se o produto existe no estoque, mesmo funcionamento do procurar, mas esse retona um boolean
    @Override
    public boolean existe(String id) {
        for(Produto produto : estoque){
            if(produto.getId().equals(id)){
                return true;
            }
                
        }
        return false;
    }

    //metodo para reduzir um produto do estoque
    @Override
    public void reduzir(String id, int quantidade) /*throws PIException*/ {
        for(Produto produto : estoque) {
            if (produto != null) {
                produto.setQuantidade(produto.getQuantidade() - quantidade); //pega a quantidade que tinha antes, e diminui pela desejada
            }
        }
            //else
                //throw new PIException(produto.getQuantidade()) // **n achei nenhum metodo que converte id para produto e o contrario
    }

    //metodo para mostrar os produtos do estoque pelo tipo dele
    @Override
    public void mostrarEstoqueTipo(String tipo){
        for(Produto produto : estoque){ //percorre o vetor estoque
            if(produto.getTipo().equals(tipo)){ //se o produto tiver o mesmo tipo do tipo digitado, ele será mostrado
                if(produto instanceof ProdutoComestivel){ //caso seja comestivel, mostrará sua data de validade
                    System.out.println(((ProdutoComestivel) produto).CustomtoString());
                }
                else{
                    System.out.println(produto.toString());
                }
            }
        }
    }

    @Override
    public void mostrarEstoqueTotal(){
        for(Produto produto : estoque){
            if(produto != null){
                if(produto instanceof ProdutoComestivel){
                    System.out.println(((ProdutoComestivel) produto).CustomtoString());
                }
                else{
                    System.out.println(produto.toString());
                }
            }
        }
    }

    //metodo para checar o saldo
    @Override
    public double verSaldo() {
       return saldo;
    }

    //metodo para mudar o saldo
    @Override
    public void definirSaldo(double valor) throws SNException{
        if(valor>=0){
           this.saldo = valor;
        }else
            throw new SNException(valor);
    }


}
