
package produtos;


import excecao.*;

public class Produto {
    //atributos
    protected String nome;
    protected String id;
    protected String marca;
    protected double preco_compra;
    protected double preco_venda;
    protected String tipo;  
    protected int quantidade;
    protected double taxaLucro;
   
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
    }
  
    
    //to string para mostrar todos os produtos no estoque, onde só esses atributos vão importar
    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", id=" + id + ", marca=" + marca +
                ", preco=" + preco_compra + ", tipo=" + tipo +
                ", quantidade=" + quantidade + '}';
    }
   
    // **teste
    public boolean testeQuantidade(String Id,double quantidade_requerida) throws QIException{
        if(this.getQuantidade() >= quantidade_requerida)
            return true;
        else
            throw new QIException(Id, this.getQuantidade(), quantidade_requerida);
    } 

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

    public void setprecoCompra(double preco_compra) throws PCNUException, PCNException {
        if(preco_compra>0)
            this.preco_compra = preco_compra;
       else if(preco_compra ==0)
            throw new PCNUException();
        else
            throw new PCNException(preco_compra);
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
    public void setTaxaLucro(double taxaLucro) throws TLNUException, TLNException {
        if(taxaLucro>0.0)
            this.taxaLucro = taxaLucro;
        else if(taxaLucro==0.0)
            throw new TLNUException(this.taxaLucro);
        else if(taxaLucro<0.0)
            throw new TLNException(this.taxaLucro);
    }

    public double getPrecoVenda() {
        return preco_venda;
    }

    public void setPrecoVenda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

   
    

    
    
}
