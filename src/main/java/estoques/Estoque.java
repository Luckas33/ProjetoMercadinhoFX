
package estoques;

import excecao.*;
import produtos.Produto;

import java.util.Vector;

import bancoDados.FileSave;

public class Estoque implements IEstoque {

    //atributos
    private Vector<Produto> estoque;
    private double saldo;
    private Vector<Produto> produtosObservados;
    

    //construtor
    public Estoque(){
        this.estoque = new Vector<>();
        this.saldo = 0.0;
        this.produtosObservados = new Vector<>();
        desserializarSaldo();
    }
   

    private void attachProduto(Produto produto) {
        produtosObservados.add(produto);
        produto.attach(this);
    }

    private void detachProduto(Produto produto) {
        produtosObservados.remove(produto);
        produto.detach(this);
    }

    private void reattachObservers() {
        for (Produto produto : estoque) {
            produto.attach(this);
        }
    }

    //metodo para inserir produtos no estoque  
    @Override
    public void inserir(Produto produto, int quantidade){
        if (produto != null) {
            desserializar();
            if (!this.existe(produto.getId())){ //checa se o produto não existe no estoque
                produto.setQuantidade(quantidade);  //se não existir, seta a quantidade
                this.estoque.add(produto);           //e coloca no vetor de estoque
                attachProduto(produto);
            } else if (this.existe(produto.getId())) {
                Produto produtoAtualiza = procurar(produto.getId());                    // pega esse produto com a ultima quantidade registrada
                int quantidadeFinal = produtoAtualiza.getQuantidade() + quantidade;     // Estabelece nova quantidade
                produtoAtualiza.setQuantidade(quantidadeFinal); //se já existe no estoque, vai so pegar a quantidade que ja tinha e adicionar a quantidade desejada
            }
            serializar();
        }  
    }

    //metodo para procurar um produto no estoque
    @Override
    public Produto procurar(String id) {
        desserializar();
       for(Produto produto : estoque){ //percorre o vetor estoque
            if(produto.getId().equals(id)){  //se algum produto dentro do estoque tiver o mesmo id do id digitado, retornará ele
                return produto;
            }            
        }
         return null;
    }

    //metodo para ver se o produto existe no estoque, mesmo funcionamento do procurar, mas esse retona um boolean
    @Override
    public boolean existe(String id) { //Anderson: Alterei 
        return id != null && this.procurar(id) != null;
    }

    //metodo para reduzir um produto do estoque
    @Override
    public void reduzir(String id, int quantidade) throws PIException, QINException {
        desserializar();
        if(!this.existe(id)){ 
            throw new PIException(id);
        }
        // Garante que inicialmente esse produto deve existir para sofrer redução
        Produto produto = this.procurar(id);
        if(quantidade>0){
            produto.setQuantidade(produto.getQuantidade() - quantidade); //pega a quantidade que tinha antes, e diminui pela desejada
            serializar();
        }
        else
            throw new QINException(quantidade);
    }

    public void remove(Produto produto){
        desserializar();
        this.estoque.remove(produto);
        detachProduto(produto);
        serializar();
    }



    @Override
    public Vector<Produto> retornaEstoque(){
        desserializar();
        return this.estoque;
    }


    //metodo para checar o saldo
    @Override
    public double verSaldo() {
        return saldo;
    }

    // <Anderson>: Falta salvar saldo... Não é difícil, mas acho feio, então estou pensando;
    //metodo para mudar o saldo
    @Override
    public void definirSaldo(double valor) throws SIException{
        if(valor>=0){
            this.saldo = valor;
            serializarSaldo();
        }else
            throw new SIException(saldo);
    }

    private void serializarSaldo() {
        String caminho = "src/main/java/arquivos/saldo.txt";
        FileSave.gravarObjetos(saldo, caminho);
    }

    private void desserializarSaldo() {
        String caminho = "src/main/java/arquivos/saldo.txt";
        try {
            Double saldoTemp = (Double) FileSave.recuperarObjetos(caminho);
            if (saldoTemp == null){
                this.saldo = 0;
            } else {
                this.saldo = saldoTemp;
            }
        } catch (ClassCastException e) {
            System.out.println("Erro: O objeto recuperado não é um Double: " + e.getMessage());
            this.saldo = 0;
        }
    }
    

    private void serializar() {
        String caminho = "src/main/java/arquivos/estoque.txt";
        FileSave.gravarObjetos(estoque, caminho);
    }

    private void desserializar(){
        String caminho = "src/main/java/arquivos/estoque.txt";
        try {
            Vector<Produto> produtosTemp = (Vector<Produto>) FileSave.recuperarObjetos(caminho);        // Tem a possibilidade do arquivo não existir
            if (produtosTemp != null) {                 // Tem a possibilidade de ser null
                this.estoque.clear();                   // Limpa o que já existe, supondo que há a mesma coisa no arquivo
                for(Produto produto : produtosTemp) {
                    this.estoque.add(produto);          // Adicionando os produtos recuperados do arquivo
                }
                reattachObservers();
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        serializar();
    }


}
