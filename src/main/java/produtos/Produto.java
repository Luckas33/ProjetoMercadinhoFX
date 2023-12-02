
package produtos;

import java.io.Serializable;
import java.util.Vector;
import observador.Observer;

import excecao.*;

public abstract class Produto implements Serializable {
    //atributos
    protected String nome;
    protected String id;
    protected String marca;
    protected double preco_compra;
    protected double preco_venda;
    protected String tipo;  
    protected int quantidade;
    protected double taxaLucro;
    protected int quantidadeVendida;
    private static final long serialVersionUID = 1L;
    private transient Vector<Observer> observers = new Vector<>();
   
    //construtor
    public Produto(String nome, String id, String marca, double preco_compra, String tipo) {
        this.nome = nome;
        this.id = id;
        this.marca = marca;
        this.preco_compra = preco_compra;
        this.preco_venda = 0.0;
        this.tipo = tipo;
        this.quantidade = 0; 
        this.taxaLucro = 0.0;
        this.quantidadeVendida = 0;
    }
  
    
    //to string para mostrar todos os produtos no estoque, onde só esses atributos vão importar
    public abstract String toString();

   /////getters e setters
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPrecoCompra(double preco_compra) throws PCIException {
        if(preco_compra>0){
            this.preco_compra = preco_compra;
            notifyAllObservers();
        }
        else
            throw new PCIException(preco_compra);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTaxaLucro(){
        return taxaLucro;
    }
    // **coloquei as excecoes**
    public void setTaxaLucro(double taxaLucro) throws TLIException {
        if(taxaLucro>0.0){
            this.taxaLucro = taxaLucro;
            notifyAllObservers();
        }
        else
            throw new TLIException(this.taxaLucro);
    }

    public double getPrecoVenda() {
        return preco_venda;
    }

    public void setPrecoVenda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) throws QINException{
        if(quantidadeVendida>0){
            this.quantidadeVendida = quantidadeVendida;
        }
        else
            throw new QINException(quantidadeVendida);
    }
    
    public void attach(Observer observer){
        if (observers == null) {
            observers = new Vector<>();
        }
        if (!observers.contains(observer)){
            observers.add(observer);
        }       
    }

    public void detach(Observer observer){
        if (observers != null && !observers.contains(observer)){
            observers.remove(observer); 
        }      
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    } 
}
